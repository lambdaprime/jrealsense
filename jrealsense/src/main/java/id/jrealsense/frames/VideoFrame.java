package id.jrealsense.frames;

/**
 * Frame which has notion of pixels and stores some information about
 * them (color, depth, etc).
 */
public interface VideoFrame<T extends VideoFrame<T>> extends Frame<T> {

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
    
}
