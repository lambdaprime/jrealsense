/*
 * Copyright 2023 jrealsense project
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

import id.jrealsense.devices.Device;
import id.jrealsense.jextract.librealsense;
import java.lang.foreign.MemorySegment;
import java.time.Duration;

public class PlaybackDevice extends Device {

    public PlaybackDevice(MemorySegment device) {
        super(device);
    }

    public void setRealTime(boolean isRealTime) {
        var e = new RealSenseError();
        librealsense.rs2_playback_device_set_real_time(
                rs2_device, isRealTime ? 1 : 0, e.get_rs2_error());
        e.verify();
    }

    public PlaybackStatus getCurrentStatus() {
        var e = new RealSenseError();
        var status =
                librealsense.rs2_playback_device_get_current_status(rs2_device, e.get_rs2_error());
        e.verify();
        return PlaybackStatus.valueOf(status);
    }

    public Duration getPosition() {
        var e = new RealSenseError();
        var nanos = librealsense.rs2_playback_get_position(rs2_device, e.get_rs2_error());
        e.verify();
        return Duration.ofNanos(nanos);
    }

    public void seek(Duration position) {
        var e = new RealSenseError();
        librealsense.rs2_playback_seek(rs2_device, position.toNanos(), e.get_rs2_error());
        e.verify();
    }

    public void stop() {
        var e = new RealSenseError();
        librealsense.rs2_playback_device_stop(rs2_device, e.get_rs2_error());
        e.verify();
    }

    public void pause() {
        var e = new RealSenseError();
        librealsense.rs2_playback_device_pause(rs2_device, e.get_rs2_error());
        e.verify();
    }

    public void resume() {
        var e = new RealSenseError();
        librealsense.rs2_playback_device_resume(rs2_device, e.get_rs2_error());
        e.verify();
    }

    @Override
    public void close() {
        stop();
        super.close();
    }
}
