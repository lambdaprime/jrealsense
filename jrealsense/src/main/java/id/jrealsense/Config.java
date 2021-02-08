package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.jni.rs2_config;

public class Config implements AutoCloseable {
    private rs2_config config;

    protected Config(rs2_config config) {
        this.config = config;
    }

    public void enableStream(StreamType stream, int index, int width, int height, FormatType format, int framerate) {
        var e = RealSenseErrorHolder.create();
        rs2_config_enable_stream(config,
                stream.get_rs2_stream(),
                index,
                width,
                height,
                format.get_rs2_format(),
                framerate,
                e);
        e.verify();
    }
    
    /**
     * Factory method, creates new {@link Config}
     */
    public static Config create(Context ctx) {
        var e = RealSenseErrorHolder.create();
        rs2_config config = rs2_create_config(e);
        e.verify();
        return new Config(config);
    }

    public rs2_config get_rs_config() {
        return config;
    }

    @Override
    public void close() {
        rs2_delete_config(config);
    }
}
