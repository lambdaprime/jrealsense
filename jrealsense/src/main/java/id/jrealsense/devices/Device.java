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
package id.jrealsense.devices;

import id.jrealsense.RealSenseError;
import id.jrealsense.jextract.librealsense;
import java.lang.foreign.MemorySegment;

/**
 * Represents camera device
 *
 * @author lambdaprime intid@protonmail.com
 */
public class Device implements AutoCloseable {

    private MemorySegment rs2_device;

    public Device(MemorySegment rs2_device) {
        this.rs2_device = rs2_device;
    }

    public String getInfo() {
        var e = new RealSenseError();
        var info =
                librealsense.rs2_get_device_info(
                        rs2_device, librealsense.RS2_CAMERA_INFO_NAME(), e.get_rs2_error());
        e.verify();
        return info.getUtf8String(0);
    }

    public String getSerialNumber() {
        var e = new RealSenseError();
        var serial =
                librealsense.rs2_get_device_info(
                        rs2_device,
                        librealsense.RS2_CAMERA_INFO_SERIAL_NUMBER(),
                        e.get_rs2_error());
        e.verify();
        return serial.getUtf8String(0);
    }

    public String getFirmwareVersion() {
        var e = new RealSenseError();
        var version =
                librealsense.rs2_get_device_info(
                        rs2_device,
                        librealsense.RS2_CAMERA_INFO_FIRMWARE_VERSION(),
                        e.get_rs2_error());
        e.verify();
        return version.getUtf8String(0);
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
     * Send request to perform hardware reset. It is asyn operation therefore it is better to sleep
     * after this call to make sure that device is ready.
     */
    public void reset() {
        var e = new RealSenseError();
        librealsense.rs2_hardware_reset(rs2_device, e.get_rs2_error());
        e.verify();
    }

    /** Release the resources. Once closed all further operations are invalid. */
    @Override
    public void close() {
        librealsense.rs2_delete_device(rs2_device);
        rs2_device = null;
    }
}
