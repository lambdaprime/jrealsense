package id.jrealsense.devices;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.Context;
import id.jrealsense.RealSenseError;
import id.jrealsense.jni.rs2_device_list;

public class DeviceLocator implements AutoCloseable {

    private rs2_device_list deviceList;
    
    /**
     * Factory method, creates new DeviceLocator
     */
    public static DeviceLocator create(Context ctx) {
        RealSenseError e = RealSenseError.create();
        var config = rs2_query_devices(ctx.get_rs2_context(), e.get_p_p_rs2_error());
        e.verify();
        return new DeviceLocator(config);
    }
    
    public DeviceLocator(rs2_device_list deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * Get librealsense low level object
     */
    public rs2_device_list getDeviceList() {
        return deviceList;
    }
    
    /**
     * Return number of available devices
     */
    public int getNumOfDevices() {
        RealSenseError e = RealSenseError.create();
        int count = rs2_get_device_count(deviceList, e.get_p_p_rs2_error());
        e.verify();
        return count;
    }

    /**
     * Return device by its zero based consecutive number
     */
    public Device getDevice(int id) {
        RealSenseError e = RealSenseError.create();
        var dev = rs2_create_device(deviceList, id, e.get_p_p_rs2_error());
        e.verify();
        return new Device(dev);
    }
    
    /**
     * Get all available devices
     */
    public DeviceList getAllDevices() {
        var list = new DeviceList();
        for (int i = 0; i < getNumOfDevices(); i++) {
            list.add(getDevice(i));
        }
        return list;
    }
    
    /**
     * Release the resources.
     * Once closed all further operations are invalid.
     */
    @Override
    public void close() {
        rs2_delete_device_list(deviceList);
        deviceList = null;
    }
}
