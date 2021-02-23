package id.jrealsense.examples.apps;

import java.nio.file.Path;
import java.nio.file.Paths;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.PointCloudUtils;
import id.jrealsense.StreamType;
import id.jrealsense.devices.DeviceLocator;
import id.jrealsense.filters.PointCloudFilter;
import id.xfunction.CommandLineInterface;

/**
 * App example which demonstrates how to work with point clouds.
 * It constructs point cloud using depth frame and exports it to
 * obj file which later can be viewed in different 3D modeling
 * systems.
 * 
 * To stop the app press Enter.
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
    
    /**
     * Frame width
     */
    private final static int WIDTH = 424;

    /**
     * Frame height
     */
    private final static int HEIGHT = 240;
    
    /**
     * Frames per second (Hz)
     */
    private final static int FPS = 6;
    
    /**
     * Output file
     */
    private static final Path OUT = Paths.get("/tmp/jrealsense.obj");
    
    /*
     * CONFIGURATION END
     */

    private final static CommandLineInterface cli = new CommandLineInterface();
    private final static PointCloudUtils pointCloudUtils = new PointCloudUtils();
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
                var dev = locator.getDevice(0);
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx);
                var pointCloud = PointCloudFilter.create())
        {
            cli.print(dev);
            utils.reset(cli, dev);
            config.enableStream(StreamType.RS2_STREAM_DEPTH, 0,
                    WIDTH, HEIGHT, FormatType.RS2_FORMAT_Z16, FPS);
            pipeline.start(config);
            loop(pipeline, pointCloud);
        }
    }

    /**
     * Loop over the frames in the pipeline and render them on the screen
     */
    private void loop(Pipeline pipeline, PointCloudFilter pointCloud) {
        while (!cli.wasKeyPressed())
        {
            FrameSet frameSet = pipeline.waitForFrames();
            cli.print("Number of frames received " + frameSet.size());
            frameSet.getDepthFrame().ifPresent(frame -> {
                System.out.println("Received depth frame");

                int w = frame.getWidth();
                int h = frame.getHeight();

                System.out.println("Width: " + w);
                System.out.println("Height: " + h);
                System.out.println("Frame number: " + frame.getFrameNumber());
                System.out.printf("Timestamp: %f\n", frame.getTimestamp());
                
                System.out.println(frame);
                var cloud = pointCloud.process(frame);
                System.out.println("Number of points: " + cloud.getPointsCount());
                pointCloudUtils.exportToObj(OUT, cloud);
                cloud.close();
            });
            frameSet.close();
        }
    }

    public static void main(String[] args) {
        new PointcloudApp().run();
    }
}
