package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;
import static id.jrealsense.jni.librealsense2Constants.RS2_API_VERSION;

import id.jrealsense.jni.rs2_context;

public class Context implements AutoCloseable {

    private rs2_context ctx;

    private Context(rs2_context ctx) {
        this.ctx = ctx;
    }

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
