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
import id.jrealsense.types.Intrinsics;

/**
 * RealSense sensor functionality
 *
 * @author lambdaprime intid@protonmail.com
 */
public class Sensor {

    /** When called on a video profile, returns the intrinsics of specific stream configuration. */
    public Intrinsics getVideoStreamIntrinsics(StreamProfile streamProfile) {
        var e = new RealSenseError();
        var intrinsics = new Intrinsics();
        librealsense.rs2_get_video_stream_intrinsics(
                streamProfile.get_rs2_stream_profile(),
                intrinsics.get_rs2_intrinsics(),
                e.get_rs2_error());
        e.verify();
        return intrinsics;
    }
}
