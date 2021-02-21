package id.jrealsense.frames;

import static id.jrealsense.jni.librealsense2.*;

import java.nio.ByteBuffer;
import java.util.Optional;

import id.jrealsense.Filter;
import id.jrealsense.Frame;
import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.StreamProfile;
import id.jrealsense.jni.rs2_frame;
import id.xfunction.logging.XLogger;

abstract class AbstractFrame<F extends Frame<F>> implements Frame<F> {

    private static final XLogger LOG = XLogger.getLogger(AbstractFrame.class);
    private rs2_frame frame;
    private Optional<Integer> width = Optional.empty();
    private Optional<Integer> height = Optional.empty();
    
    protected AbstractFrame(rs2_frame frame) {
        this.frame = frame;
    }

    public StreamProfile getProfile() {
        var e = RealSenseErrorHolder.create();
        var s = rs2_get_frame_stream_profile(frame, e);
        e.verify();
        return StreamProfile.create(s);
    }

    public <OUT extends Frame<?>> OUT apply(Filter<F, OUT> filter) {
        var ret = filter.process((F) this);
        return ret;
    }
    
    public int getWidth() {
        if (width.isPresent()) {
            return width.get();
        }
        var e = RealSenseErrorHolder.create();
        var r = rs2_get_frame_width(frame, e);
        e.verify();
        width = Optional.of(r);
        return r;
    }

    public int getHeight() {
        if (height.isPresent()) {
            return height.get();
        }
        var e = RealSenseErrorHolder.create();
        var r = rs2_get_frame_height(frame, e);
        e.verify();
        height = Optional.of(r);
        return r;
    }

    public int getStride() {
        var e = RealSenseErrorHolder.create();
        var r = rs2_get_frame_stride_in_bytes(frame, e);
        e.verify();
        return r;
    }

    public int embeddedFramesCount() {
        var e = RealSenseErrorHolder.create();
        var c = rs2_embedded_frames_count(frame, e);
        e.verify();
        return c;
    }
    
    public rs2_frame get_rs2_frame() {
        return frame;
    }
    
    public ByteBuffer getData() {
        var capacity = getHeight() * getStride();
        var e = RealSenseErrorHolder.create();
        var dataPtr = rs2_get_frame_data(frame, e);
        e.verify();
        return (ByteBuffer) create_ByteBuffer(dataPtr, capacity);
    }
    
    @Override
    public void close() {
        LOG.entering("close");
        rs2_release_frame(frame);
        LOG.exiting("close");
    }
}
