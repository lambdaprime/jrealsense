/*
 * Copyright 2023 jrealsense project
 * 
 * Website: https://github.com/lambdaprime/jrealsense
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.jrealsense.examples.ros.apps;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.StreamType;
import id.jrealsense.devices.DeviceLocator;
import id.jrealsense.examples.apps.Utils;
import id.jrealsense.filters.PointCloudFilter;
import id.jrealsense.frames.PointCloudFrame;
import id.jros1client.JRos1ClientConfiguration;
import id.jros1client.JRos1ClientFactory;
import id.jros1messages.sensor_msgs.PointCloud2Message;
import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosclient.TopicSubmissionPublisher;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.sensor_msgs.PointFieldMessage;
import id.jrosmessages.sensor_msgs.PointFieldMessage.DataType;
import id.xfunction.cli.CommandLineInterface;
import id.xfunction.function.Unchecked;
import id.xfunction.lang.XThread;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * App example which demonstrates how to work with point clouds. It constructs point cloud using
 * depth frame and exports it to obj file which later can be viewed in different 3D modeling
 * systems.
 *
 * <p>To stop the app press Enter.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class PointcloudApp {

    /*
     * Here is configuration of the stream we are going to work with.
     * Some cameras may not support this configuration so then it
     * should be changed accordingly. So see all available configurations
     * which are supported by the camera use librealsense command:
     *
     * $ rs-enumerate-devices
     *
     */

    /*
     * CONFIGURATION START
     */

    /** Frame width */
    private static final int WIDTH = 424;

    /** Frame height */
    private static final int HEIGHT = 240;

    /** Frames per second (Hz) */
    private static final int FPS = 6;

    /** ROS master URL */
    private static final String MASTER_URL = "http://localhost:11311/";

    /*
     * CONFIGURATION END
     */

    private static final CommandLineInterface cli = new CommandLineInterface();
    private static final Utils utils = new Utils();

    /** Setup resources and run the looper */
    private void run() throws Exception {
        var clientConfig = new JRos1ClientConfiguration();
        clientConfig.setMaxMessageLoggingLength(1200);
        // using try-with-resources to properly release all librealsense resources
        try (var client = new JRos1ClientFactory().createClient(MASTER_URL, clientConfig);
                var ctx = Context.create();
                var locator = DeviceLocator.create(ctx);
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx);
                var pointCloud = PointCloudFilter.create()) {
            if (locator.getAllDevices().isEmpty()) {
                System.err.println("No devices found");
                return;
            }
            var dev = locator.getDevice(0);
            String topic = "/PointCloud";
            var publisher = new TopicSubmissionPublisher<>(PointCloud2Message.class, topic);
            client.publish(publisher);

            cli.print(dev);
            utils.reset(cli, dev);
            config.enableStream(
                    StreamType.RS2_STREAM_DEPTH, 0, WIDTH, HEIGHT, FormatType.RS2_FORMAT_Z16, FPS);
            config.enableStream(
                    StreamType.RS2_STREAM_COLOR, 0, WIDTH, HEIGHT, FormatType.RS2_FORMAT_BGR8, FPS);
            pipeline.start(config);
            loop(pipeline, pointCloud, publisher);
        }
    }

    /** Loop over the frames in the pipeline and render them on the screen */
    private void loop(
            Pipeline pipeline,
            PointCloudFilter pointCloud,
            TopicSubmissionPublisher<PointCloud2Message> publisher) {
        var pointStep = 12;
        PointCloud2Message message =
                new PointCloud2Message()
                        .withHeader(new HeaderMessage().withFrameId("map").withStamp(Time.now()))
                        .withHeight(1)
                        .withIsDense(true)
                        .withPointStep(pointStep)
                        .withFields(
                                new PointFieldMessage()
                                        .withName("x")
                                        .withOffset(0)
                                        .withCount(1)
                                        .withDataType(DataType.FLOAT64),
                                new PointFieldMessage()
                                        .withName("y")
                                        .withOffset(4)
                                        .withCount(1)
                                        .withDataType(DataType.FLOAT64),
                                new PointFieldMessage()
                                        .withName("z")
                                        .withOffset(8)
                                        .withCount(1)
                                        .withDataType(DataType.FLOAT64));
        while (!cli.wasEnterKeyPressed()) {
            FrameSet frameSet = pipeline.waitForFrames();
            cli.print("Number of frames received " + frameSet.size());
            frameSet.getDepthFrame()
                    .ifPresent(
                            frame -> {
                                System.out.println("Received depth frame");

                                int w = frame.getWidth();
                                int h = frame.getHeight();

                                cli.print("Width: %d\n", w);
                                cli.print("Height: %d\n", h);
                                cli.print("Frame number: %d\n", frame.getFrameNumber());
                                cli.print("Timestamp: %f\n", frame.getTimestamp());

                                message.header.withStamp(new Time((int) frame.getTimestamp(), 0));

                                var cloud = pointCloud.process(frame);
                                cli.print("Number of points: %d\n", cloud.getPointsCount());
                                populateMessage(message, cloud);

                                publisher.submit(message);
                                cli.print("Published");

                                cloud.close();
                            });
            frameSet.close();
            XThread.sleep(2000);
        }
    }

    private void populateMessage(PointCloud2Message message, PointCloudFrame cloud) {
        var buf = new ByteArrayOutputStream();
        for (var v : cloud.createVertexAccessor()) {
            var b = ByteBuffer.allocate(message.point_step).order(ByteOrder.nativeOrder());
            b.putFloat(v.x);
            b.putFloat(v.y);
            b.putFloat(v.z);
            Unchecked.run(() -> buf.write(b.array()));
        }
        message.data = buf.toByteArray();
        message.withRowStep(buf.size());
        message.withWidth(buf.size() / message.point_step);
    }

    public static void main(String[] args) throws Exception {
        new PointcloudApp().run();
    }
}
