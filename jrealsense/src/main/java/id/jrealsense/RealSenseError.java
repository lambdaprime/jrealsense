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

import static id.jrealsense.jni.librealsense2.rs2_get_error_message;
import static id.jrealsense.jni.librealsense2.rs2_get_failed_function;

import id.jrealsense.jni.rs2_error;

public class RealSenseError {
    
    private rs2_error error;

    protected RealSenseError(rs2_error e) {
        this.error = e;
    }

    /**
     * Factory method, creates new {@link RealSenseError}
     */
    public static RealSenseError create() {
        var e = new rs2_error(0, true);
        return new RealSenseError(e);
    }

    public rs2_error get_rs2_error() {
        return error;
    }

    public String getFailedFunction() {
        return rs2_get_failed_function(error);
    }
    
    public String getMessage() {
        return rs2_get_error_message(error);
    }
    
    /**
     * Check if error present or not
     */
    public boolean hasError() {
        return rs2_error.getCPtr(error) != 0;
    }
    
    /**
     * In case of error throw runtime exception with its description.
     * Do nothing otherwise.
     */
    public void verify()
    {
        if (!hasError()) return;
        var buf = String.format("%s: %s",
                getFailedFunction(), getMessage());
        throw new RealSenseException(buf);
    }
    
}
