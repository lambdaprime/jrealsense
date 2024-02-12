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

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class XMemorySegment {

    public static MemorySegment createPointer(MemorySegment value) {
        var ptr = Arena.ofAuto().allocate(ValueLayout.ADDRESS.byteSize());
        ptr.set(ValueLayout.ADDRESS, 0, value);
        System.out.println("isReadOnly " + ptr.isReadOnly());
        return ptr;
    }

    public static MemorySegment toNativeString(String s) {
        var m = Arena.ofAuto().allocate(s.length() + 1);
        m.setUtf8String(0, s);
        return m;
    }
}
