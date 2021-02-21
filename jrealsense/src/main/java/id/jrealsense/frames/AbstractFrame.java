package id.jrealsense.frames;

import static id.jrealsense.jni.librealsense2.*;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

import id.jrealsense.Filter;
import id.jrealsense.Frame;
import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.StreamProfile;
import id.jrealsense.jni.rs2_frame;
import id.xfunction.logging.XLogger;

abstract class AbstractFrame<F extends Frame<F>> implements Frame<F> {

    private static final XLogger LOG = XLogger.getLogger(AbstractFrame.class);
    private rs2_frame frame;
    
    private Supplier<Integer> width = () -> {
        var e = RealSenseErrorHolder.create();
        var r = rs2_get_frame_width(frame, e);
        e.verify();
        width = () -> r;
        return r;
    };
    
    private Supplier<Integer> height = () -> {
        var e = RealSenseErrorHolder.create();
        var r = rs2_get_frame_height(frame, e);
        e.verify();
        height = () -> r;
        return r;
    };
    
    private Supplier<Long> frameNumber = () -> {
        var e = RealSenseErrorHolder.create();
        var c = rs2_get_frame_number(frame, e);
        e.verify();
        var r = c.longValue();
        frameNumber = () -> r;
        return r;
    };
    
    private Supplier<Double> timestamp = () -> {
        var e = RealSenseErrorHolder.create();
        var c = rs2_get_frame_timestamp(frame, e);
        e.verify();
        timestamp = () -> c;
        return c;
    };
    
    private Supplier<StreamProfile> profile = () -> {
        var e = RealSenseErrorHolder.create();
        var s = rs2_get_frame_stream_profile(frame, e);
        e.verify();
        var r = StreamProfile.create(s);
        profile  = () -> r;
        return r;
    };
    
    private Supplier<Integer> count = () -> {
        var e = RealSenseErrorHolder.create();
        var c = rs2_embedded_frames_count(frame, e);
        e.verify();
        height = () -> c;
        return c;
    };
    
    private Supplier<Integer> stride = () -> {
        var e = RealSenseErrorHolder.create();
        var r = rs2_get_frame_stride_in_bytes(frame, e);
        e.verify();
        height = () -> r;
        return r;
    };
    
    protected AbstractFrame(rs2_frame frame) {
        this.frame = frame;
    }

    @Override
    public StreamProfile getProfile() {
        return profile.get();
    }

    @Override
    public <OUT extends Frame<?>> OUT apply(Filter<F, OUT> filter) {
        @SuppressWarnings("unchecked")
        var ret = filter.process((F) this);
        return ret;
    }
    
    @Override
    public int getWidth() {
        return width.get();
    }

    @Override
    public int getHeight() {
        return height.get();
    }

    @Override
    public int getStride() {
        return stride.get();
    }

    @Override
    public int embeddedFramesCount() {
        return count.get();
    }
    
    @Override
    public rs2_frame get_rs2_frame() {
        return frame;
    }
    
    @Override
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
        frame.delete();
        LOG.exiting("close");
    }
    
    @Override
    public long getFrameNumber() {
        return frameNumber.get();
    }
    
    @Override
    public double getTimestamp() {
        return timestamp.get();
    }
}
