package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.jni.SWIGTYPE_p_p_rs2_error;

/**
 * Many librealsense functions accept rs_error** arguments and in case of
 * error they put pointer to rs_error there (example rs2_create_config).
 * 
 * This class acts like a holder of that pointer to rs2_error and
 * objects of this class can be passed as an argument for those functions.
 */
public class RealSenseErrorHolder extends SWIGTYPE_p_p_rs2_error {
    
    protected RealSenseErrorHolder(long ptr) {
        super(ptr, false);
    }

    /**
     * Factory method, creates new {@link RealSenseErrorHolder}
     */
    public static RealSenseErrorHolder create() {
        var e = new_rs2_error_ptr();
        return new RealSenseErrorHolder(getCPtr(e));
    }

    public RealSenseError getValue() {
        return new RealSenseError(rs2_error_ptr_value(this));
    }

    /**
     * {@link RealSenseError#verify}
     */
    public void verify() {
        getValue().verify();
    }

    @Override
    protected void finalize() throws Throwable {
        delete_rs2_error_ptr(this);
        super.finalize();
    }
}
