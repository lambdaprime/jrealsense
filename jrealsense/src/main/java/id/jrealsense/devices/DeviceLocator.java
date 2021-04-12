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
package id.jrealsense.devices;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.Context;
import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.jni.rs2_device_list;

public class DeviceLocator implements AutoCloseable {

    private rs2_device_list deviceList;
    
    /**
     * Factory method, creates new {@link DeviceLocator}
     */
    public static DeviceLocator create(Context ctx) {
        var e = RealSenseErrorHolder.create();
        var config = rs2_query_devices(ctx.get_rs2_context(), e);
        e.verify();
        return new DeviceLocator(config);
    }
    
    protected DeviceLocator(rs2_device_list deviceList) {
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
        var e = RealSenseErrorHolder.create();
        int count = rs2_get_device_count(deviceList, e);
        e.verify();
        return count;
    }

    /**
     * Return device by its zero based consecutive number
     */
    public Device getDevice(int id) {
        var e = RealSenseErrorHolder.create();
        var dev = rs2_create_device(deviceList, id, e);
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
