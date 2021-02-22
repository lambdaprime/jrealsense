package id.jrealsense.examples.apps;

import java.awt.image.BufferedImage;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.StreamType;
import id.jrealsense.devices.DeviceLocator;
import id.jrealsense.examples.Renderer;
import id.xfunction.CommandLineInterface;

/**
 * App example which demonstrates how to stream color frames.
 */
public class ImshowApp {

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
        var renderer = new Renderer(WIDTH, HEIGHT);
        // using try-with-resources to properly release all librealsense resources
        try (
                var ctx = Context.create();
                var pipeline = Pipeline.create(ctx);
                var locator = DeviceLocator.create(ctx);
                var dev = locator.getDevice(0);
                var config = Config.create(ctx);)
        {
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
        while (!renderer.isClosed() && !cli.wasKeyPressed())
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
