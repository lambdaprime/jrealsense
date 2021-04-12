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
/**
 * <p>Java module which allows to interact with
 * Intel RealSense cameras.</p>
 * 
 * @see <a href="https://github.com/lambdaprime/jrealsense/releases">Download</a>
 * @see <a href="https://github.com/lambdaprime/jrealsense">GitHub repository</a>
 * 
 */
module jrealsense {
    requires id.xfunction;
    requires java.logging;
    exports id.jrealsense;
    exports id.jrealsense.jni;
    exports id.jrealsense.filters;
    exports id.jrealsense.frames;
    exports id.jrealsense.devices;
}
