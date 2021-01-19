package id.jrealsense;

import java.util.Map;

public class StreamType {

    private rs2_stream stream;

    private StreamType(rs2_stream stream) {
        this.stream = stream;
    }

    public rs2_stream get_rs2_stream() {
        return stream;
    }

    public static StreamType RS2_STREAM_ANY = new StreamType(rs2_stream.RS2_STREAM_ANY);
    public static StreamType RS2_STREAM_DEPTH = new StreamType(rs2_stream.RS2_STREAM_DEPTH);
    public static StreamType RS2_STREAM_COLOR = new StreamType(rs2_stream.RS2_STREAM_COLOR);
    public static StreamType RS2_STREAM_INFRARED = new StreamType(rs2_stream.RS2_STREAM_INFRARED);
    public static StreamType RS2_STREAM_FISHEYE = new StreamType(rs2_stream.RS2_STREAM_FISHEYE);
    public static StreamType RS2_STREAM_GYRO = new StreamType(rs2_stream.RS2_STREAM_GYRO);
    public static StreamType RS2_STREAM_ACCEL = new StreamType(rs2_stream.RS2_STREAM_ACCEL);
    public static StreamType RS2_STREAM_GPIO = new StreamType(rs2_stream.RS2_STREAM_GPIO);
    public static StreamType RS2_STREAM_POSE = new StreamType(rs2_stream.RS2_STREAM_POSE);
    public static StreamType RS2_STREAM_CONFIDENCE = new StreamType(rs2_stream.RS2_STREAM_CONFIDENCE);
    public static StreamType RS2_STREAM_COUNT = new StreamType(rs2_stream.RS2_STREAM_COUNT);
    
    public static Map<Integer, StreamType> SWIG_VALUES = Map.of(
            rs2_stream.RS2_STREAM_ANY.swigValue(), RS2_STREAM_ANY,
            rs2_stream.RS2_STREAM_DEPTH.swigValue(), RS2_STREAM_DEPTH,
            rs2_stream.RS2_STREAM_COLOR.swigValue(), RS2_STREAM_COLOR,
            rs2_stream.RS2_STREAM_INFRARED.swigValue(), RS2_STREAM_INFRARED,
            rs2_stream.RS2_STREAM_FISHEYE.swigValue(), RS2_STREAM_FISHEYE,
            rs2_stream.RS2_STREAM_GYRO.swigValue(), RS2_STREAM_GYRO,
            rs2_stream.RS2_STREAM_ACCEL.swigValue(), RS2_STREAM_ACCEL,
            rs2_stream.RS2_STREAM_GPIO.swigValue(), RS2_STREAM_GPIO);

    public static StreamType valueOf(int streamType) {
        var r = SWIG_VALUES.get(streamType);
        if (r == null)
            throw new RealSenseException("Stream type with id %d not found", streamType);
        return r;
    }
}
