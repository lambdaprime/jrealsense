package id.jrealsense.devices;

import static id.jrealsense.jni.librealsense2.*;
import static id.jrealsense.jni.rs2_camera_info.RS2_CAMERA_INFO_FIRMWARE_VERSION;
import static id.jrealsense.jni.rs2_camera_info.RS2_CAMERA_INFO_NAME;
import static id.jrealsense.jni.rs2_camera_info.RS2_CAMERA_INFO_SERIAL_NUMBER;

import id.jrealsense.RealSenseError;
import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.jni.rs2_device;

/**
 * Represents camera device
 */
public class Device implements AutoCloseable {

    private rs2_device device;
    
    public Device(rs2_device dev) {
        this.device = dev;
    }

    public String getInfo() {
        var e = RealSenseErrorHolder.create();
        var info = rs2_get_device_info(device, RS2_CAMERA_INFO_NAME, e);
        e.verify();
        return info;
    }
    
    public String getSerialNumber() {
        var e = RealSenseErrorHolder.create();
        var serial = rs2_get_device_info(device, RS2_CAMERA_INFO_SERIAL_NUMBER, e);
        e.verify();
        return serial;
    }
    
    public String getFirmwareVersion() {
        var e = RealSenseErrorHolder.create();
        var version = rs2_get_device_info(device, RS2_CAMERA_INFO_FIRMWARE_VERSION, e);
        e.verify();
        return version;
    }
    
    @Override
    public String toString() {
        var b = new StringBuilder();
        b.append(getInfo() + "\n");
        b.append(String.format("Serial number: %s\n", getSerialNumber()));
        b.append(String.format("Firmware version: %s\n", getFirmwareVersion()));
        return b.toString();
    }
    
    /**
     * Release the resources.
     * Once closed all further operations are invalid.
     */
    @Override
    public void close() {
        rs2_delete_device(device);
        device = null;
    }

}
