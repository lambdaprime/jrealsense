package id.jrealsense;

public interface Filter<IN extends Frame<IN>, OUT extends Frame<?>> extends AutoCloseable {
    
    /**
     * Apply current filter to the given frame.
     */
    OUT process(IN frame);
}
