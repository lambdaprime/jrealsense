/*
 * Copyright 2021 jrealsense project
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
package id.jrealsense.examples.apps;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.StreamType;
import id.jrealsense.devices.Device;
import id.jrealsense.devices.DeviceLocator;
import id.xfunction.cli.CommandLineInterface;

/**
 * App example which demonstrates how to calculate distance.
 *
 * <p>To stop the app press Enter.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class DistanceApp {

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
    private static final int WIDTH = 640;

    /** Frame height */
    private static final int HEIGHT = 480;

    /** Frames per second */
    private static final int FPS = 30;

    /*
     * CONFIGURATION END
     */

    private static final CommandLineInterface cli = new CommandLineInterface();
    private static final Utils utils = new Utils();

    /** Setup resources and run the looper */
    private void run() {
        // using try-with-resources to properly release all librealsense resources
        try (var ctx = Context.create();
                var locator = DeviceLocator.create(ctx);
                Device dev = locator.getDevice(0);
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx); ) {
            cli.print(dev);
            utils.reset(cli, dev);
            config.enableStream(
                    StreamType.RS2_STREAM_DEPTH, 0, WIDTH, HEIGHT, FormatType.RS2_FORMAT_Z16, FPS);
            pipeline.start(config);
            loop(pipeline);
        }
    }

    /** Loop over the frames in the pipeline and render them on the screen */
    private void loop(Pipeline pipeline) {
        while (!cli.wasEnterKeyPressed()) {
            FrameSet data = pipeline.waitForFrames();
            cli.print("Number of frames received " + data.size());
            data.getDepthFrame()
                    .ifPresent(
                            frame -> {
                                cli.print("Received depth frame");
                                cli.print(frame.getDistance());
                            });
            data.close();
        }
    }

    public static void main(String[] args) {
        new DistanceApp().run();
    }
}
