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
import static id.jrealsense.jni.rs2_camera_info.RS2_CAMERA_INFO_FIRMWARE_VERSION;
import static id.jrealsense.jni.rs2_camera_info.RS2_CAMERA_INFO_NAME;
import static id.jrealsense.jni.rs2_camera_info.RS2_CAMERA_INFO_SERIAL_NUMBER;

import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.jni.rs2_device;

/**
 * Represents camera device
 */
public class Device implements AutoCloseable {

    private rs2_device device;
    
    protected Device(rs2_device dev) {
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
     * Send request to perform hardware reset. It is asyn operation therefore it is
     * better to sleep after this call to make sure that device is ready.
     */
    public void reset() {
        var e = RealSenseErrorHolder.create();
        rs2_hardware_reset(device, e);
        e.verify();
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
