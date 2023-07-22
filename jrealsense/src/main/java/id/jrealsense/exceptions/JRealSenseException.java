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
package id.jrealsense.exceptions;

/**
 * Generic runtime exception for all <b>jrealsense</b> operations.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class JRealSenseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JRealSenseException() {
        super();
    }

    public JRealSenseException(String message) {
        super(message);
    }

    public JRealSenseException(String fmt, Object... objs) {
        super(String.format(fmt, objs));
    }

    public JRealSenseException(Exception e) {
        super(e);
    }
}
