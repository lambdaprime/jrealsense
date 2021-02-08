package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;
import static id.jrealsense.jni.librealsense2Constants.RS2_DEFAULT_TIMEOUT;

import id.jrealsense.frames.CompositeFrame;
import id.jrealsense.jni.rs2_pipeline;

public class Pipeline implements AutoCloseable {

    private rs2_pipeline pipeline;

    protected Pipeline(rs2_pipeline pline) {
        this.pipeline = pline;
    }

    public void start() {
        var e = RealSenseErrorHolder.create();
        rs2_pipeline_start(pipeline, e);
        e.verify();
    }
    
    public void start(Config config) {
        var e = RealSenseErrorHolder.create();
        rs2_pipeline_start_with_config(pipeline, config.get_rs_config(), e);
        e.verify();
    }
    
    public void stop() {
        var e = RealSenseErrorHolder.create();
        rs2_pipeline_stop(pipeline, e);
        e.verify();
    }
    
    /**
     * Factory method, creates new {@link Pipeline}
     */
    public static Pipeline create(Context ctx) {
        var e = RealSenseErrorHolder.create();
        rs2_pipeline pline = rs2_create_pipeline(ctx.get_rs2_context(), e);
        e.verify();
        return new Pipeline(pline);
    }

    /**
     * Wait for next set of frames from the camera
     */
    public FrameSet waitForFrames() {
        var e = RealSenseErrorHolder.create();
        var frame = rs2_pipeline_wait_for_frames(pipeline, RS2_DEFAULT_TIMEOUT, e);
        e.verify();
        if (frame == null)
            throw new RuntimeException("Received null frame. Make sure pipeline is started.");
        return new FrameSet(new CompositeFrame(frame));
    }

    @Override
    public void close() {
        stop();
        rs2_delete_pipeline(pipeline);
    }

}
