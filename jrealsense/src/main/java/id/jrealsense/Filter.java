package id.jrealsense;

public interface Filter<IN extends Frame<IN>, OUT extends Frame<?>> extends AutoCloseable {
    OUT process(IN frame);
}
