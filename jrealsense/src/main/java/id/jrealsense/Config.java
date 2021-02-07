package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.jni.rs2_config;

public class Config implements AutoCloseable {
    private rs2_config config;

    private Config(rs2_config config) {
        this.config = config;
    }

    public void enableStream(StreamType stream, int index, int width, int height, FormatType format, int framerate) {
        RealSenseError e = RealSenseError.create();
        rs2_config_enable_stream(config,
                stream.get_rs2_stream(),
                index,
                width,
                height,
                format.get_rs2_format(),
                framerate,
                e.get_p_p_rs2_error());
        e.verify();
    }
    
    public static Config create(Context ctx) {
        RealSenseError e = RealSenseError.create();
        rs2_config config = rs2_create_config(e.get_p_p_rs2_error());
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
