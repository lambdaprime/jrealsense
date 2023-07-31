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
/**
 * <b>jrealsense</b> - Java module which allows to interact with Intel RealSense cameras.
 *
 * <p>Provides additional extensions to original functionality of librealsense2 (see {@link
 * BagFileUtils}}, {@link PointCloudUtils} and others).
 *
 * <p><b>jrealsense</b> is based on <a
 * href="https://docs.oracle.com/en/java/javase/19/core/foreign-function-and-memory-api.html">Java
 * FFM</a> and so, it does not require any additional JNI libraries in the system.
 *
 * <h2>Requirements</h2>
 *
 * <ul>
 *   <li>Java 20
 *   <li>librealsense2 2.53.1
 * </ul>
 *
 * <h2>Usage</h2>
 *
 * <ul>
 *   <li>Make sure that <a href="https://www.intelrealsense.com/sdk-2/">librealsense2</a> is
 *       available in the system library path or set it manually with -Djava.library.path= system
 *       property. In Ubuntu by default it is found in:
 *       -Djava.library.path=/usr/lib/x86_64-linux-gnu
 *   <li>Use --enable-preview Java argument.
 * </ul>
 *
 * @see <a href="https://github.com/lambdaprime/jrealsense/releases">Download</a>
 * @see <a href="https://github.com/lambdaprime/jrealsense">GitHub repository</a>
 * @see <a href="https://intelrealsense.github.io/librealsense/doxygen/annotated.html">Intel
 *     Realsense Cross-platform API</a>
 * @author lambdaprime intid@protonmail.com
 */
module jrealsense {
    requires id.xfunction;
    requires java.logging;
    requires transitive java.desktop;

    exports id.jrealsense;
    exports id.jrealsense.app;
    exports id.jrealsense.filters;
    exports id.jrealsense.frames;
    exports id.jrealsense.devices;
    exports id.jrealsense.types;
    exports id.jrealsense.primitives;
    exports id.jrealsense.exceptions;
    exports id.jrealsense.utils;
}
