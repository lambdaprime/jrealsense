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

import static id.jrealsense.jni.librealsense2.rs2_create_context;
import static id.jrealsense.jni.librealsense2.rs2_delete_context;
import static id.jrealsense.jni.librealsense2Constants.RS2_API_VERSION;

import id.jrealsense.jni.rs2_context;

public class Context implements AutoCloseable {

    private rs2_context ctx;

    protected Context(rs2_context ctx) {
        this.ctx = ctx;
    }

    /**
     * Factory method, creates new {@link Context}
     */
    public static Context create() {
        var e = RealSenseErrorHolder.create();
        var ctx = rs2_create_context(RS2_API_VERSION, e);
        e.verify();
        return new Context(ctx);
    }

    public rs2_context get_rs2_context() {
        return ctx;
    }

    @Override
    public void close() {
        rs2_delete_context(ctx);
    }

}
