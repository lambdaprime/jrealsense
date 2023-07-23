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
package id.jrealsense;

import id.jrealsense.jextract.librealsense;
import java.lang.foreign.MemorySegment;
import java.nio.file.Path;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class Config implements AutoCloseable {
    private MemorySegment config;

    protected Config(MemorySegment config) {
        this.config = config;
    }

    public void enableStream(
            StreamType stream, int index, int width, int height, FormatType format, int framerate) {
        var e = new RealSenseError();
        librealsense.rs2_config_enable_stream(
                config,
                stream.get_rs2_stream(),
                index,
                width,
                height,
                format.getValue(),
                framerate,
                e.get_rs2_error());
        e.verify();
    }

    public void enableDeviceFromFile(Path filePath) {
        var e = new RealSenseError();
        librealsense.rs2_config_enable_device_from_file(
                config, XMemorySegment.toNativeString(filePath.toString()), e.get_rs2_error());
        e.verify();
    }

    public void enableDeviceFromFile(Path filePath, boolean isRepeat) {
        var e = new RealSenseError();
        librealsense.rs2_config_enable_device_from_file_repeat_option(
                config,
                XMemorySegment.toNativeString(filePath.toString()),
                isRepeat ? 1 : 0,
                e.get_rs2_error());
        e.verify();
    }

    /** Factory method, creates new {@link Config} */
    public static Config create(Context ctx) {
        var e = new RealSenseError();
        var config = librealsense.rs2_create_config(e.get_rs2_error());
        e.verify();
        return new Config(config);
    }

    public MemorySegment get_rs_config() {
        return config;
    }

    @Override
    public void close() {
        librealsense.rs2_delete_config(config);
    }
}
