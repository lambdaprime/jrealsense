package id.librealsense2.examples.rs_distance;

import static id.librealsense2.librealsense2.*;
import static id.librealsense2.rs2_format.*;
import static id.librealsense2.rs2_camera_info.*;
import static id.librealsense2.rs2_stream.*;
import static id.librealsense2.rs2_frame.*;
import static id.librealsense2.rs2_extension.*;

import id.librealsense2.rs2_config;
import id.librealsense2.rs2_context;
import id.librealsense2.rs2_device;
import id.librealsense2.rs2_device_list;
import id.librealsense2.rs2_error;
import id.librealsense2.rs2_format;
import id.librealsense2.rs2_frame;
import id.librealsense2.rs2_pipeline;
import id.librealsense2.rs2_pipeline_profile;
import id.librealsense2.rs2_stream;

public class RsDistanceApp {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //These parameters are reconfigurable                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    final static rs2_stream STREAM =          RS2_STREAM_DEPTH;  // rs2_stream is a types of data provided by RealSense device           //
    final static rs2_format FORMAT =         RS2_FORMAT_ANY;//Z16;    // rs2_format identifies how binary data is encoded within a frame      //
    final static int WIDTH =          0;               // Defines the number of columns for each frame or zero for auto resolve//
    final static int HEIGHT =          0;                 // Defines the number of lines for each frame or zero for auto resolve  //
    final static int FPS =            30;                // Defines the rate of frames per second                                //
    final static int STREAM_INDEX =    0;                 // Defines the stream index, used for multiple streams of the same type //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static { 
        System.loadLibrary("realsense2-jni"); 
    }

    boolean hasError(rs2_error e) {
        return rs2_error.getCPtr(e) != 0;
    }
    void check_error(rs2_error e)
    {
        if (hasError(e))
        {
            System.out.format("rs_error was raised when calling %s:\n",
                    rs2_get_failed_function(e));
            System.out.format("    %s\n", rs2_get_error_message(e));
            System.exit(-1);
        }
    }

    void print_device_info(rs2_device dev)
    {
        rs2_error e = new rs2_error(0, true);
        System.out.format("\nUsing device 0, an %s\n", rs2_get_device_info(dev, RS2_CAMERA_INFO_NAME, as_ptr(e)));
        check_error(e);
        System.out.format("    Serial number: %s\n", rs2_get_device_info(dev, RS2_CAMERA_INFO_SERIAL_NUMBER, as_ptr(e)));
        check_error(e);
        System.out.format("    Firmware version: %s\n\n", rs2_get_device_info(dev, RS2_CAMERA_INFO_FIRMWARE_VERSION, as_ptr(e)));
        check_error(e);
    }

    void run() {
        rs2_error e = new rs2_error(0, true);
        System.out.println(rs2_error.getCPtr(e));

        // Create a context object. This object owns the handles to all connected realsense devices.
        // The returned object should be released with rs2_delete_context(...)
        rs2_context ctx = rs2_create_context(RS2_API_VERSION, as_ptr(e));

        System.out.println(rs2_error.getCPtr(e));
        check_error(e);

        /* Get a list of all the connected devices. */
        // The returned object should be released with rs2_delete_device_list(...)
        rs2_device_list device_list = rs2_query_devices(ctx, as_ptr(e));
        check_error(e);

        int dev_count = rs2_get_device_count(device_list, as_ptr(e));
        check_error(e);
        System.out.format("There are %d connected RealSense devices.\n", dev_count);
        if (0 == dev_count)
            return;

        // Get the first connected device
        // The returned object should be released with rs2_delete_device(...)
        rs2_device dev = rs2_create_device(device_list, 0, as_ptr(e));
        check_error(e);

        print_device_info(dev);

        // Create a pipeline to configure, start and stop camera streaming
        // The returned object should be released with rs2_delete_pipeline(...)
        rs2_pipeline pipeline = rs2_create_pipeline(ctx, as_ptr(e));
        check_error(e);

        // Create a config instance, used to specify hardware configuration
        // The retunred object should be released with rs2_delete_config(...)
        rs2_config config = rs2_create_config(as_ptr(e));
        check_error(e);

        // Request a specific configuration
        rs2_config_enable_stream(config, STREAM, STREAM_INDEX, WIDTH, HEIGHT, FORMAT, FPS, as_ptr(e));
        check_error(e);

        // Start the pipeline streaming
        // The retunred object should be released with rs2_delete_pipeline_profile(...)
        rs2_pipeline_profile pipeline_profile = rs2_pipeline_start_with_config(pipeline, config, as_ptr(e));
        if (hasError(e))
        {
            System.out.format("The connected device doesn't support depth streaming!\n");
            return;
        }

        while (true)
        {
            System.out.println("wait for frames");
            // This call waits until a new composite_frame is available
            // composite_frame holds a set of frames. It is used to prevent frame drops
            // The returned object should be released with rs2_release_frame(...)
            rs2_frame frames = rs2_pipeline_wait_for_frames(pipeline, RS2_DEFAULT_TIMEOUT, as_ptr(e));
            check_error(e);

            // Returns the number of frames embedded within the composite frame
            int num_of_frames = rs2_embedded_frames_count(frames, as_ptr(e));
            check_error(e);

            System.out.println("Frames count: " + num_of_frames);

            int i;
            for (i = 0; i < num_of_frames; ++i)
            {
                // The retunred object should be released with rs2_release_frame(...)
                rs2_frame frame = rs2_extract_frame(frames, i, as_ptr(e));
                check_error(e);

                // Check if the given frame can be extended to depth frame interface
                // Accept only depth frames and skip other frames
                if (0 == rs2_is_frame_extendable_to(frame, RS2_EXTENSION_DEPTH_FRAME, as_ptr(e)))
                    continue;

                // Get the depth frame's dimensions
                int width = rs2_get_frame_width(frame, as_ptr(e));
                check_error(e);
                int height = rs2_get_frame_height(frame, as_ptr(e));
                check_error(e);

                // Query the distance from the camera to the object in the center of the image
                float dist_to_center = rs2_depth_frame_get_distance(frame, width / 2, height / 2, as_ptr(e));
                check_error(e);

                // Print the distance
                System.out.format("The camera is facing an object %.3f meters away.\n", dist_to_center);

                rs2_release_frame(frame);
            }

            rs2_release_frame(frames);
        }
    }

    public static void main(String[] args) {
        new RsDistanceApp().run();
    }
}
