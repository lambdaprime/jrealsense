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

import id.jrealsense.Context;
import id.jrealsense.RealSenseError;
import id.jrealsense.jextract.librealsense;
import java.lang.foreign.MemorySegment;

public class DeviceLocator implements AutoCloseable {

    private MemorySegment deviceList;
    
    /**
     * Factory method, creates new {@link DeviceLocator}
     */
    public static DeviceLocator create(Context ctx) {
        var e = new RealSenseError();
        var deviceList = librealsense.rs2_query_devices(ctx.get_rs2_context(), e.get_rs2_error());
        e.verify();
        return new DeviceLocator(deviceList);
    }
    
    protected DeviceLocator(MemorySegment deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * Get librealsense low level object
     */
    public MemorySegment getDeviceList() {
        return deviceList;
    }
    
    /**
     * Return number of available devices
     */
    public int getNumOfDevices() {
        var e = new RealSenseError();
        int count = librealsense.rs2_get_device_count(deviceList, e.get_rs2_error());
        e.verify();
        return count;
    }

    /**
     * Return device by its zero based consecutive number
     */
    public Device getDevice(int id) {
        var e = new RealSenseError();
        var dev = librealsense.rs2_create_device(deviceList, id, e.get_rs2_error());
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
        librealsense.rs2_delete_device_list(deviceList);
        deviceList = null;
    }
}
