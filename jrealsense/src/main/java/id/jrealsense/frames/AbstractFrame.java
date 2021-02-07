package id.jrealsense.frames;

import static id.jrealsense.jni.librealsense2.*;

import java.nio.ByteBuffer;

import id.jrealsense.Filter;
import id.jrealsense.Frame;
import id.jrealsense.RealSenseError;
import id.jrealsense.StreamProfile;
import id.jrealsense.jni.rs2_frame;

abstract class AbstractFrame<F extends Frame<F>> implements Frame<F> {

    private rs2_frame frame;
    
    public AbstractFrame(rs2_frame frame) {
        this.frame = frame;
    }

    public StreamProfile getProfile() {
        var e = RealSenseError.create();
        var s = rs2_get_frame_stream_profile(frame, e.get_p_p_rs2_error());
        e.verify();
        return StreamProfile.create(s);
    }

    public <OUT extends Frame<?>> OUT apply(Filter<F, OUT> filter) {
        var ret = filter.process((F) this);
        return ret;
    }
    
    public int getWidth() {
        var e = RealSenseError.create();
        var r = rs2_get_frame_width(frame, e.get_p_p_rs2_error());
        e.verify();
        return r;
    }

    public int getHeight() {
        var e = RealSenseError.create();
        var r = rs2_get_frame_height(frame, e.get_p_p_rs2_error());
        e.verify();
        return r;
    }

    public int getStride() {
        var e = RealSenseError.create();
        var r = rs2_get_frame_stride_in_bytes(frame, e.get_p_p_rs2_error());
        e.verify();
        return r;
    }

    public int embeddedFramesCount() {
        var e = RealSenseError.create();
        var c = rs2_embedded_frames_count(frame, e.get_p_p_rs2_error());
        e.verify();
        return c;
    }
    
    public rs2_frame get_rs2_frame() {
        return frame;
    }
    
    public ByteBuffer getData() {
        var capacity = getHeight() * getStride();
        var e = RealSenseError.create();
        var dataPtr = rs2_get_frame_data(frame, e.get_p_p_rs2_error());
        e.verify();
        return (ByteBuffer) create_ByteBuffer(dataPtr, capacity);
    }
    
    @Override
    public void close() {
        rs2_release_frame(frame);
    }
}
