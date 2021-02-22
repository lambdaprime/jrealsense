package id.jrealsense;

/**
 * It is implementation details of the filter how to do the processing.
 * 
 * Standard way is to ask processing block to process the frame and poll
 * the processed frame from internal queue once it is ready.
 */
public interface Filter<IN extends Frame<IN>, OUT extends Frame<?>> extends AutoCloseable {
    
    /**
     * Apply current filter to the given frame.
     */
    OUT process(IN frame);
}
