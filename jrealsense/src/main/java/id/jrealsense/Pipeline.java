package id.jrealsense;

import static id.jrealsense.librealsense2.*;
import static id.jrealsense.librealsense2Constants.RS2_DEFAULT_TIMEOUT;

import id.jrealsense.frames.CompositeFrame;
import id.jrealsense.rs2_pipeline;

public class Pipeline implements AutoCloseable {

    private rs2_pipeline pipeline;

    private Pipeline(rs2_pipeline pline) {
        this.pipeline = pline;
    }

    public void start() {
        RealSenseError e = RealSenseError.create();
        rs2_pipeline_start(pipeline, e.get_p_p_rs2_error());
        e.verify();
    }
    
    public void start(Config config) {
        RealSenseError e = RealSenseError.create();
        rs2_pipeline_start_with_config(pipeline, config.get_rs_config(), e.get_p_p_rs2_error());
        e.verify();
    }
    
    public void stop() {
        RealSenseError e = RealSenseError.create();
        rs2_pipeline_stop(pipeline, e.get_p_p_rs2_error());
        e.verify();
    }
    
    public static Pipeline create(Context ctx) {
        RealSenseError e = RealSenseError.create();
        rs2_pipeline pline = rs2_create_pipeline(ctx.get_rs2_context(), e.get_p_p_rs2_error());
        e.verify();
        return new Pipeline(pline);
    }

    /**
     * Wait for next set of frames from the camera
     */
    public FrameSet waitForFrames() {
        RealSenseError e = RealSenseError.create();
        var frame = rs2_pipeline_wait_for_frames(pipeline, RS2_DEFAULT_TIMEOUT, e.get_p_p_rs2_error());
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
