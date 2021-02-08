package id.jrealsense;

import static id.jrealsense.jni.librealsense2.rs2_get_error_message;
import static id.jrealsense.jni.librealsense2.rs2_get_failed_function;

import id.jrealsense.jni.rs2_error;

public class RealSenseError {
    
    private rs2_error error;

    public RealSenseError(rs2_error e) {
        this.error = e;
    }

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
