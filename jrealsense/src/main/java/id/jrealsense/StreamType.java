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
package id.jrealsense;

import id.jrealsense.jextract.librealsense;
import java.util.Map;

/**
 * <p>librealsense wrapper for rs2_stream.
 * 
 * @author lambdaprime intid@protonmail.com
 */
public enum StreamType {

    RS2_STREAM_ANY(librealsense.RS2_STREAM_ANY()),
    RS2_STREAM_DEPTH(librealsense.RS2_STREAM_DEPTH()),
    RS2_STREAM_COLOR(librealsense.RS2_STREAM_COLOR()),
    RS2_STREAM_INFRARED(librealsense.RS2_STREAM_INFRARED()),
    RS2_STREAM_FISHEYE(librealsense.RS2_STREAM_FISHEYE()),
    RS2_STREAM_GYRO(librealsense.RS2_STREAM_GYRO()),
    RS2_STREAM_ACCEL(librealsense.RS2_STREAM_ACCEL()),
    RS2_STREAM_GPIO(librealsense.RS2_STREAM_GPIO()),
    RS2_STREAM_POSE(librealsense.RS2_STREAM_POSE()),
    RS2_STREAM_CONFIDENCE(librealsense.RS2_STREAM_CONFIDENCE()),
    RS2_STREAM_COUNT(librealsense.RS2_STREAM_COUNT());

    private int value;

    StreamType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static Map<Integer, StreamType> SWIG_VALUES = Map.of(
            librealsense.RS2_STREAM_ANY(), RS2_STREAM_ANY,
            librealsense.RS2_STREAM_DEPTH(), RS2_STREAM_DEPTH,
            librealsense.RS2_STREAM_COLOR(), RS2_STREAM_COLOR,
            librealsense.RS2_STREAM_INFRARED(), RS2_STREAM_INFRARED,
            librealsense.RS2_STREAM_FISHEYE(), RS2_STREAM_FISHEYE,
            librealsense.RS2_STREAM_GYRO(), RS2_STREAM_GYRO,
            librealsense.RS2_STREAM_ACCEL(), RS2_STREAM_ACCEL,
            librealsense.RS2_STREAM_GPIO(), RS2_STREAM_GPIO);

    public static StreamType valueOf(int streamType) {
        var r = SWIG_VALUES.get(streamType);
        if (r == null)
            throw new RealSenseException("Stream type with id %d not found", streamType);
        return r;
    }
}
