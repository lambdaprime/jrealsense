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
import java.lang.foreign.MemorySegment;

public class Context implements AutoCloseable {

    private MemorySegment ctx;

    protected Context(MemorySegment ctx) {
        this.ctx = ctx;
    }

    /**
     * Factory method, creates new {@link Context}
     */
    public static Context create() {
        var e = new RealSenseError();
        var ctx = librealsense.rs2_create_context(librealsense.RS2_API_VERSION(), e.get_rs2_error());
        e.verify();
        return new Context(ctx);
    }

    public MemorySegment get_rs2_context() {
        return ctx;
    }

    @Override
    public void close() {
        librealsense.rs2_delete_context(ctx);
    }

}
