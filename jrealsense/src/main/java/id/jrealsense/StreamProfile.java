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
import java.nio.ByteBuffer;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class StreamProfile {

    private MemorySegment rs2_stream_profile;
    private StreamType stream;
    private FormatType format;
    private int index;
    private int uniqueId;
    private int framerate;

    protected StreamProfile(
            MemorySegment rs2_stream_profile,
            StreamType rs2_stream,
            FormatType rs2_format,
            int index,
            int uniqueId,
            int framerate) {
        this.rs2_stream_profile = rs2_stream_profile;
        this.stream = rs2_stream;
        this.format = rs2_format;
        this.index = index;
        this.uniqueId = uniqueId;
        this.framerate = framerate;
    }

    public MemorySegment get_rs2_stream_profile() {
        return rs2_stream_profile;
    }

    public StreamType getStream() {
        return stream;
    }

    public FormatType getFormat() {
        return format;
    }

    public int getIndex() {
        return index;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public int getFramerate() {
        return framerate;
    }

    /** Factory method, creates new {@link StreamProfile} */
    public static StreamProfile create(MemorySegment profile) {
        var s = new StreamTypeHolder(StreamType.RS2_STREAM_ANY);
        var f = new FormatTypeHolder(FormatType.RS2_FORMAT_ANY);
        var buf = ByteBuffer.allocateDirect(Integer.BYTES * 3).asIntBuffer();
        var index = buf.slice(0, 1);
        var uniqueId = buf.slice(1, 1);
        var framerate = buf.slice(2, 1);
        var e = new RealSenseError();
        librealsense.rs2_get_stream_profile_data(
                profile,
                s.get_rs2_stream(),
                f.get_rs2_format(),
                MemorySegment.ofBuffer(index),
                MemorySegment.ofBuffer(uniqueId),
                MemorySegment.ofBuffer(framerate),
                e.get_rs2_error());
        e.verify();

        return new StreamProfile(
                profile,
                s.getStreamType(),
                f.getFormatType(),
                index.get(),
                uniqueId.get(),
                framerate.get());
    }

    @Override
    public String toString() {
        return "StreamProfile [stream="
                + stream
                + ", format="
                + format
                + ", index="
                + index
                + ", uniqueId="
                + uniqueId
                + ", framerate="
                + framerate
                + "]";
    }
}
