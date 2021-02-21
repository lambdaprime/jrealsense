package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;
import static id.jrealsense.jni.librealsense2Constants.RS2_DEFAULT_TIMEOUT;

import id.jrealsense.frames.CompositeFrame;
import id.jrealsense.jni.rs2_pipeline;
import id.xfunction.logging.XLogger;

public class Pipeline implements AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(FrameSet.class);
    private rs2_pipeline pipeline;

    protected Pipeline(rs2_pipeline pline) {
        this.pipeline = pline;
    }

    /**
     * Starts pipeline with default configuration
     */
    public void start() {
        LOG.entering("start");
        var e = RealSenseErrorHolder.create();
        rs2_pipeline_start(pipeline, e);
        e.verify();
        LOG.exiting("start");
    }

    public void start(Config config) {
        LOG.entering("start");
        var e = RealSenseErrorHolder.create();
        rs2_pipeline_start_with_config(pipeline, config.get_rs_config(), e);
        e.verify();
        LOG.exiting("start");
    }
    
    public void stop() {
        LOG.entering("stop");
        var e = RealSenseErrorHolder.create();
        rs2_pipeline_stop(pipeline, e);
        e.verify();
        LOG.exiting("stop");
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
        LOG.entering("waitForFrames");
        var e = RealSenseErrorHolder.create();
        var frame = rs2_pipeline_wait_for_frames(pipeline, RS2_DEFAULT_TIMEOUT, e);
        e.verify();
        if (frame == null)
            throw new RuntimeException("Received null frame. Make sure pipeline is started.");
        var res = new FrameSet(new CompositeFrame(frame));
        LOG.exiting("waitForFrames");
        return res;
    }

    @Override
    public void close() {
        LOG.entering("close");
        stop();
        rs2_delete_pipeline(pipeline);
        LOG.exiting("close");
    }

}
