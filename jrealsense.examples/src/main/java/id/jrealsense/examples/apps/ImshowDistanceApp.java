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
import id.jrealsense.devices.DeviceLocator;
import id.jrealsense.examples.Renderer;
import id.jrealsense.filters.Colorizer;
import id.xfunction.cli.CommandLineInterface;
import java.awt.image.BufferedImage;

/**
 * App example which demonstrates how to work with multiple streams. It shows color image from the
 * camera + distance to the object in the middle of it.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class ImshowDistanceApp {

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
        var renderer = new Renderer(WIDTH, HEIGHT);
        // using try-with-resources to properly release all librealsense resources
        try (var ctx = Context.create();
                var locator = DeviceLocator.create(ctx);
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx);
                var colorMap = Colorizer.create()) {
            if (locator.getAllDevices().isEmpty()) {
                System.err.println("No devices found");
                return;
            }
            var dev = locator.getDevice(0);
            cli.print(dev);
            utils.reset(cli, dev);
            config.enableStream(
                    StreamType.RS2_STREAM_DEPTH, 0, WIDTH, HEIGHT, FormatType.RS2_FORMAT_Z16, FPS);
            config.enableStream(
                    StreamType.RS2_STREAM_COLOR, 0, WIDTH, HEIGHT, FormatType.RS2_FORMAT_BGR8, FPS);
            pipeline.start(config);
            loop(renderer, pipeline, colorMap);
        } finally {
            renderer.close();
        }
    }

    /** Loop over the frames in the pipeline and render them on the screen */
    private void loop(Renderer renderer, Pipeline pipeline, Colorizer colorMap) {
        while (!renderer.isClosed() && !cli.wasEnterKeyPressed()) {
            FrameSet data = pipeline.waitForFrames();
            cli.print("Number of frames received " + data.size());
            data.getColorFrame(FormatType.RS2_FORMAT_BGR8)
                    .ifPresent(
                            colorFrame -> {
                                System.out.println("Received color frame");

                                System.out.println(colorFrame);

                                renderer.render(colorFrame.getData(), BufferedImage.TYPE_3BYTE_BGR);
                            });
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
        new ImshowDistanceApp().run();
    }
}
