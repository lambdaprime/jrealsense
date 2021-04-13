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
import id.jrealsense.devices.Device;
import id.jrealsense.devices.DeviceLocator;
import id.jrealsense.jni.LibrealsenseVersion;
import id.xfunction.CommandLineInterface;

/**
 * App example which demonstrates how to calculate distance.
 * 
 * To stop the app press Enter.
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
    
    /**
     * librealsense version
     */
    private final static String LIBREALSENSE_VERSION = LibrealsenseVersion.v2_42.getJniLibraryName();

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
     * It is important to load the native library first
     */
    static {
        System.loadLibrary(LIBREALSENSE_VERSION); 
    }

    /**
     * Setup resources and run the looper
     */
    private void run() {
        // using try-with-resources to properly release all librealsense resources
        try (
                var ctx = Context.create();
                var locator = DeviceLocator.create(ctx);
                Device dev = locator.getDevice(0);
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx);)
        {
            cli.print(dev);
            utils.reset(cli, dev);
            config.enableStream(StreamType.RS2_STREAM_DEPTH, 0,
                    WIDTH, HEIGHT, FormatType.RS2_FORMAT_Z16, FPS);
            pipeline.start(config);
            loop(pipeline);
        }
    }

    /**
     * Loop over the frames in the pipeline and render them on the screen
     */
    private void loop(Pipeline pipeline) {
        while (!cli.wasKeyPressed())
        {
            FrameSet data = pipeline.waitForFrames();
            cli.print("Number of frames received " + data.size());
            data.getDepthFrame().ifPresent(frame -> {
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
