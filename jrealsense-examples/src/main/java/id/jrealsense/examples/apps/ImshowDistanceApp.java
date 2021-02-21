package id.jrealsense.examples.apps;

import java.awt.image.BufferedImage;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.StreamType;
import id.jrealsense.devices.Device;
import id.jrealsense.devices.DeviceLocator;
import id.jrealsense.examples.Renderer;
import id.jrealsense.filters.Colorizer;
import id.xfunction.CommandLineInterface;

/**
 * App example which demonstrates how to work with multiple streams.
 * It shows color image from the camera + distance to the object in
 * the middle of it.
 */
public class ImshowDistanceApp {

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
                var locator = DeviceLocator.create(ctx);
                var dev = locator.getDevice(0);
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx);
                var colorMap = Colorizer.create())
        {
            cli.print(dev);
            utils.reset(cli, dev);
            config.enableStream(StreamType.RS2_STREAM_DEPTH, 0,
                    WIDTH, HEIGHT, FormatType.RS2_FORMAT_Z16, FPS);
            config.enableStream(StreamType.RS2_STREAM_COLOR, 0,
                    WIDTH, HEIGHT, FormatType.RS2_FORMAT_BGR8, FPS);
            pipeline.start(config);
            loop(renderer, pipeline, colorMap);
        } finally {
            renderer.close();
        }
    }

    /**
     * Loop over the frames in the pipeline and render them on the screen
     */
    private void loop(Renderer renderer, Pipeline pipeline, Colorizer colorMap) {
        while (!renderer.isClosed() && !cli.wasKeyPressed())
        {
            FrameSet data = pipeline.waitForFrames();
            cli.print("Number of frames received " + data.size());
            data.getColorFrame(FormatType.RS2_FORMAT_BGR8).ifPresent(colorFrame -> {
                System.out.println("Received color frame");

                int w = colorFrame.getWidth();
                int h = colorFrame.getHeight();

                System.out.println("Width: " + w);
                System.out.println("Height: " + h);

                renderer.render(colorFrame.getData(), BufferedImage.TYPE_3BYTE_BGR);
                colorFrame.close();
            });
            data.getDepthFrame().ifPresent(frame -> {
                cli.print("Received depth frame");
                cli.print(frame.getDistance());
                frame.close();
            });
            data.close();
        }
    }

    public static void main(String[] args) {
        new ImshowDistanceApp().run();
    }
}
