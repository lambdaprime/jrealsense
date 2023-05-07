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
import java.lang.foreign.SegmentScope;
import java.lang.foreign.ValueLayout;

public class RealSenseError {
    
    private MemorySegment errorPtr = MemorySegment.allocateNative(ValueLayout.ADDRESS.byteSize(), SegmentScope.auto());

    public MemorySegment get_rs2_error() {
        return errorPtr;
    }
    
    public String getFailedFunction() {
        return getFailedFunction(errorPtr.get(ValueLayout.ADDRESS, 0));
    }
    
    public String getMessage() {
        return getMessage(errorPtr.get(ValueLayout.ADDRESS, 0));
    }
    
    /**
     * Check if error present or not
     */
    public boolean hasError() {
        return hasError(errorPtr.get(ValueLayout.ADDRESS, 0));
    }
    
    /**
     * In case of error throw runtime exception with its description.
     * Do nothing otherwise.
     */
    public void verify()
    {
        var error = errorPtr.get(ValueLayout.ADDRESS, 0);
        if (!hasError(error)) return;
        var buf = String.format("%s: %s",
                getFailedFunction(error), getMessage(error));
        throw new RealSenseException(buf);
    }

    private String getFailedFunction(MemorySegment error) {
        return librealsense.rs2_get_failed_function(error).getUtf8String(0);
    }
    
    private String getMessage(MemorySegment error) {
        return librealsense.rs2_get_error_message(error).getUtf8String(0);
    }
    
    private boolean hasError(MemorySegment error) {
        return error.address() != MemorySegment.NULL.address();
    }

}
