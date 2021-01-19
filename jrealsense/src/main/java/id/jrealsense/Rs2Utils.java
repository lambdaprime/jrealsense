package id.jrealsense;

import static id.jrealsense.rs2_camera_info.*;
import static id.jrealsense.librealsense2.*;

import id.jrealsense.rs2_device;

/**
 * Set of helper functions to work with librealsense2
 */
public class Rs2Utils {

    /**
     * Print RealSense device information to stdout
     */
    public static void printDeviceInfo(rs2_device dev)
    {
        RealSenseError e = RealSenseError.create();
        System.out.format("\nUsing device 0, an %s\n", rs2_get_device_info(dev, RS2_CAMERA_INFO_NAME, e.get_p_p_rs2_error()));
        e.verify();
        System.out.format("    Serial number: %s\n", rs2_get_device_info(dev, RS2_CAMERA_INFO_SERIAL_NUMBER, e.get_p_p_rs2_error()));
        e.verify();
        System.out.format("    Firmware version: %s\n\n", rs2_get_device_info(dev, RS2_CAMERA_INFO_FIRMWARE_VERSION, e.get_p_p_rs2_error()));
        e.verify();
    }

}
