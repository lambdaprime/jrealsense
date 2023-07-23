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

import id.jrealsense.exceptions.JRealSenseException;
import id.jrealsense.jextract.librealsense;
import java.util.HashMap;
import java.util.Map;

/**
 * librealsense wrapper for rs2_playback_status.
 *
 * @author lambdaprime intid@protonmail.com
 */
public enum PlaybackStatus {
    RS2_PLAYBACK_STATUS_UNKNOWN(librealsense.RS2_PLAYBACK_STATUS_UNKNOWN()),
    RS2_PLAYBACK_STATUS_PLAYING(librealsense.RS2_PLAYBACK_STATUS_PLAYING()),
    RS2_PLAYBACK_STATUS_PAUSED(librealsense.RS2_PLAYBACK_STATUS_PAUSED()),
    RS2_PLAYBACK_STATUS_STOPPED(librealsense.RS2_PLAYBACK_STATUS_STOPPED()),
    RS2_PLAYBACK_STATUS_COUNT(librealsense.RS2_PLAYBACK_STATUS_COUNT());

    private int rs2_playback_status;

    PlaybackStatus(int value) {
        this.rs2_playback_status = value;
    }

    public int get_rs2_playback_status() {
        return rs2_playback_status;
    }

    public static Map<Integer, PlaybackStatus> SWIG_VALUES = createSwigValuesMap();

    public static PlaybackStatus valueOf(int formatType) {
        var r = SWIG_VALUES.get(formatType);
        if (r == null)
            throw new JRealSenseException("Format type with id %d not found", formatType);
        return r;
    }

    private static Map<Integer, PlaybackStatus> createSwigValuesMap() {
        var m = new HashMap<Integer, PlaybackStatus>();
        m.put(RS2_PLAYBACK_STATUS_UNKNOWN.get_rs2_playback_status(), RS2_PLAYBACK_STATUS_UNKNOWN);
        m.put(RS2_PLAYBACK_STATUS_PLAYING.get_rs2_playback_status(), RS2_PLAYBACK_STATUS_PLAYING);
        m.put(RS2_PLAYBACK_STATUS_PAUSED.get_rs2_playback_status(), RS2_PLAYBACK_STATUS_PAUSED);
        m.put(RS2_PLAYBACK_STATUS_STOPPED.get_rs2_playback_status(), RS2_PLAYBACK_STATUS_STOPPED);
        m.put(RS2_PLAYBACK_STATUS_COUNT.get_rs2_playback_status(), RS2_PLAYBACK_STATUS_COUNT);
        return m;
    }
}
