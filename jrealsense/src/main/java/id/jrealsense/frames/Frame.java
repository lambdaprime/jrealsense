package id.jrealsense.frames;

import java.nio.ByteBuffer;

import id.jrealsense.Filter;
import id.jrealsense.StreamProfile;

public interface Frame<T extends Frame<T>> extends AutoCloseable {

    /**
     * Stream profile to which this frame belongs
     */
    StreamProfile getProfile();
    
    /**
     * Some frames may contain multiple embedded frames.
     * They are called composite frames (see {@link id.jrealsense.frames.CompositeFrame})
     * This method returns number of embedded frames in a composite or 0
     * For non composite frames it is 0
     */
    int embeddedFramesCount();
    
    /**
     * Apply filter to the current frame.
     */
    <OUT extends Frame<?>> OUT apply(Filter<T, OUT> filter);
    
    RealSenseFrame getRealSenseFrame();

    /**
     * Once frame is not needed anymore it must be released
     */
    void close();
    
    /**
     * This methods wraps frame raw data as it returned by librealsense
     * library into ByteBuffer and returns it.
     */
    ByteBuffer getData();
    
    /**
     * Frame number in milliseconds since the device was started 
     */
    long getFrameNumber();
    
    /**
     * Frame number in milliseconds since the device was started 
     */
    double getTimestamp();
}