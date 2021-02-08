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
