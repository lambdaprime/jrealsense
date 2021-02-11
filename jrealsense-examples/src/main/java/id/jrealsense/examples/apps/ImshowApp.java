package id.jrealsense.examples.apps;

import java.awt.image.BufferedImage;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.StreamType;
import id.jrealsense.examples.Renderer;
import id.jrealsense.filters.Colorizer;
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
                Context ctx = Context.create();
                Pipeline pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx);
                var colorMap = Colorizer.create())
        {
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
        while (renderer.isClosed() || !cli.wasKeyPressed())
        {
            FrameSet data = pipeline.waitForFrames();
            System.out.println("Number of frames received " + data.size());
            data.getColorFrame(FormatType.RS2_FORMAT_BGR8).ifPresent(colorFrame -> {
                System.out.println("Received color frame");

                int w = colorFrame.getWidth();
                int h = colorFrame.getHeight();

                System.out.println("Width: " + w);
                System.out.println("Height: " + h);

                renderer.render(colorFrame.getData(), BufferedImage.TYPE_3BYTE_BGR);
                colorFrame.close();
            });
            data.close();
        }
    }

    public static void main(String[] args) {
        new ImshowApp().run();
    }
}
