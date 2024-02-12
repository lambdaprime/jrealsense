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

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class StreamTypeHolder {

    private MemorySegment value;

    StreamTypeHolder(StreamType stream) {
        value = Arena.ofAuto().allocate(ValueLayout.JAVA_INT.byteSize());
        value.set(ValueLayout.JAVA_INT, 0, stream.get_rs2_stream());
    }

    public MemorySegment get_rs2_stream() {
        return value;
    }

    public StreamType getStreamType() {
        return StreamType.valueOf(value.get(ValueLayout.JAVA_INT, 0));
    }
}
