package id.jrealsense.examples.apps;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.StreamType;
import id.jrealsense.devices.Device;
import id.jrealsense.devices.DeviceLocator;
import id.xfunction.CommandLineInterface;

/**
 * App example which demonstrates how to calculate distance
 */
public class DistanceApp {

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

    private final static CommandLineInterface cli = new CommandLineInterface();
    private final static Utils utils = new Utils();
    
    /**
     * It is important to load the native library first
     */
    static {
        System.loadLibrary("realsense2-jni"); 
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
                frame.close();
            });
            data.close();
        }
    }

    public static void main(String[] args) {
        new DistanceApp().run();
    }
}
