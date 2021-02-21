package id.jrealsense.frames;

import static id.jrealsense.jni.librealsense2.*;

import java.nio.ByteBuffer;
import java.util.Optional;
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
    private Optional<Integer> width = Optional.empty();
    private Optional<Integer> height = Optional.empty();
    
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
    
    protected AbstractFrame(rs2_frame frame) {
        this.frame = frame;
    }

    @Override
    public StreamProfile getProfile() {
        var e = RealSenseErrorHolder.create();
        var s = rs2_get_frame_stream_profile(frame, e);
        e.verify();
        return StreamProfile.create(s);
    }

    @Override
    public <OUT extends Frame<?>> OUT apply(Filter<F, OUT> filter) {
        @SuppressWarnings("unchecked")
        var ret = filter.process((F) this);
        return ret;
    }
    
    @Override
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

    @Override
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

    @Override
    public int getStride() {
        var e = RealSenseErrorHolder.create();
        var r = rs2_get_frame_stride_in_bytes(frame, e);
        e.verify();
        return r;
    }

    @Override
    public int embeddedFramesCount() {
        var e = RealSenseErrorHolder.create();
        var c = rs2_embedded_frames_count(frame, e);
        e.verify();
        return c;
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
