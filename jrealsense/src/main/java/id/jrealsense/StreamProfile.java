package id.jrealsense;

import static id.jrealsense.jni.librealsense2.rs2_get_stream_profile_data;

import id.jrealsense.jni.rs2_stream_profile;

public class StreamProfile extends rs2_stream_profile {

    private rs2_stream_profile streamProfile;
    private StreamType stream;
    private FormatType format;
    private int index;
    private int uniqueId;
    private int framerate;

    protected StreamProfile(
            rs2_stream_profile streamProfile,
            StreamType rs2_stream,
            FormatType rs2_format,
            int index,
            int uniqueId,
            int framerate) {
        this.streamProfile = streamProfile;
        this.stream = rs2_stream;
        this.format = rs2_format;
        this.index = index;
        this.uniqueId = uniqueId;
        this.framerate = framerate;
    }

    
    public rs2_stream_profile getStreamProfile() {
        return streamProfile;
    }

    public StreamType getStream() {
        return stream;
    }

    public FormatType getFormat() {
        return format;
    }

    public int getIndex() {
        return index;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public int getFramerate() {
        return framerate;
    }

    /**
     * Factory method, creates new {@link StreamProfile}
     */
    public static StreamProfile create(rs2_stream_profile profile) {
        var s = new int[1];
        var f = new int[1];
        var index = new int[1];
        var uniqueId = new int[1];
        var framerate = new int[1];
        var e = RealSenseErrorHolder.create();
        rs2_get_stream_profile_data(profile, s, f, index, uniqueId, framerate, e);
        e.verify();
        
        return new StreamProfile(profile,
                StreamType.valueOf(s[0]),
                FormatType.valueOf(f[0]),
                index[0],
                uniqueId[0],
                framerate[0]);
    }

}
