package id.jrealsense.frames;

import java.nio.ByteBuffer;

import id.jrealsense.Filter;
import id.jrealsense.Frame;
import id.jrealsense.StreamProfile;
import id.xfunction.logging.XLogger;

abstract class AbstractFrame<F extends Frame<F>> implements Frame<F> {

    private RealSenseFrame frame;
    
    protected AbstractFrame(RealSenseFrame frame) {
        this.frame = frame;
    }

    protected abstract XLogger log();
    
    @Override
    public StreamProfile getProfile() {
        return frame.getProfile();
    }

    @Override
    public <OUT extends Frame<?>> OUT apply(Filter<F, OUT> filter) {
        @SuppressWarnings("unchecked")
        var ret = filter.process((F) this);
        return ret;
    }
    
    @Override
    public int getWidth() {
        return frame.getWidth();
    }

    @Override
    public int getHeight() {
        return frame.getHeight();
    }

    @Override
    public int getStride() {
        return frame.getStride();
    }

    @Override
    public int embeddedFramesCount() {
        return frame.embeddedFramesCount();
    }
    
    @Override
    public RealSenseFrame getRealSenseFrame() {
        return frame;
    }
    
    @Override
    public ByteBuffer getData() {
        return frame.getData();
    }
        
    @Override
    public void close() {
        log().entering("close");
        frame.close();
        log().exiting("close");
    }
    
    @Override
    public long getFrameNumber() {
        return frame.getFrameNumber();
    }
    
    @Override
    public double getTimestamp() {
        return frame.getTimestamp();
    }
    
}
