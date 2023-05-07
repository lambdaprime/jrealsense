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
/*
 * Authors:
 * - lambdaprime <intid@protonmail.com>
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
import id.xfunction.cli.CommandLineInterface;
import java.awt.image.BufferedImage;

/**
 * App example which demonstrates how to stream color frames.
 */
public class ImshowApp {

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

    /**
     * Frame width
     */
    private final static int WIDTH = 640;

    /**
     * Frame height
     */
    private final static int HEIGHT = 480;
    
    /**
     * Frames per second
     */
    private final static int FPS = 30;
    
    /*
     * CONFIGURATION END
     */

    private final static CommandLineInterface cli = new CommandLineInterface();
    private final static Utils utils = new Utils();
    
    /**
     * Setup resources and run the looper
     */
    private void run() {
        var renderer = new Renderer(WIDTH, HEIGHT);
        // using try-with-resources to properly release all librealsense resources
        try (
                var ctx = Context.create();
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx);
                var locator = DeviceLocator.create(ctx))
        {
            if (locator.getAllDevices().isEmpty()) {
                System.out.println("No devices found");
                return;
            }
            var dev = locator.getDevice(0);
            cli.print(dev);
            utils.reset(cli, dev);
            config.enableStream(StreamType.RS2_STREAM_COLOR, 0,
                    WIDTH, HEIGHT, FormatType.RS2_FORMAT_BGR8, FPS);
            pipeline.start(config);
            loop(renderer, pipeline);
        } finally {
            renderer.close();
        }

    }

    /**
     * Loop over the frames in the pipeline and render them on the screen
     */
    private void loop(Renderer renderer, Pipeline pipeline) {
        while (!renderer.isClosed() && !cli.wasEnterKeyPressed())
        {
            FrameSet frameSet = pipeline.waitForFrames();
            System.out.println("Number of frames received " + frameSet.size());
            frameSet.getColorFrame(FormatType.RS2_FORMAT_BGR8).ifPresent(frame -> {
                System.out.println("Received color frame");

                int w = frame.getWidth();
                int h = frame.getHeight();

                System.out.println("Width: " + w);
                System.out.println("Height: " + h);

                System.out.println("Frame number: " + frame.getFrameNumber());
                System.out.printf("Timestamp: %f\n", frame.getTimestamp());
                renderer.render(frame.getData(), BufferedImage.TYPE_3BYTE_BGR);
            });
            frameSet.close();
        }
    }

    public static void main(String[] args) {
        new ImshowApp().run();
    }
}
