package id.jrealsense;

import java.nio.ByteBuffer;

import id.jrealsense.jni.rs2_frame;

public interface Frame<T extends Frame<T>> extends AutoCloseable {

    /**
     * @return frame width in pixels
     */
    int getWidth();
    
    /**
     * @return frame height in pixels
     */
    int getHeight();
    
    /**
     * @return number of bytes per each line with current frame width
     */
    int getStride();
    
    /**
     * Some frames may contain multiple embedded frames.
     * The are called composite @see
     * This method returns number of embedded frames in a composite or 0
     * For non composite frames it is 0
     */
    int embeddedFramesCount();
    
    /**
     * Apply filter to the current frame.
     */
    <OUT extends Frame<?>> OUT apply(Filter<T, OUT> filter);
    
    rs2_frame get_rs2_frame();

    /**
     * Once frame is not needed anymore it must be released
     */
    void close();
    
    ByteBuffer getData();
}
