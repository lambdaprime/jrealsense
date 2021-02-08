package id.jrealsense;

import static id.jrealsense.FormatType.*;
import static id.jrealsense.jni.librealsense2.rs2_extract_frame;
import static id.jrealsense.jni.librealsense2.rs2_release_frame;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import id.jrealsense.frames.CompositeFrame;
import id.jrealsense.frames.DepthFrame;

/**
 * Set of helper functions to work with librealsense2
 */
public class FrameSet implements AutoCloseable {

    private CompositeFrame frame;
    private Integer count;
    
    public FrameSet(CompositeFrame frame) {
        this.frame = frame;
    }
    
    public Optional<CompositeFrame> firsOrDefault(StreamType s) {
        return firsOrDefault(s, RS2_FORMAT_ANY);
    }
    
    public Optional<CompositeFrame> firsOrDefault(StreamType stream, FormatType format) {
        return asStream(stream).filter(frame -> {
            if (frame.getProfile().getStream() == stream &&
                    (format == RS2_FORMAT_ANY || format == frame.getProfile().getFormat()))
                return true;
            frame.close();
            return false;
        }).findFirst();
    }
    
    public Optional<DepthFrame> getDepthFrame() {
        return firsOrDefault(StreamType.RS2_STREAM_DEPTH, FormatType.RS2_FORMAT_Z16)
                .map(CompositeFrame::get_rs2_frame)
                .map(DepthFrame::new);
    }
    
    public Optional<DepthFrame> getColorFrame(FormatType type) {
        return firsOrDefault(StreamType.RS2_STREAM_COLOR, type)
                .map(CompositeFrame::get_rs2_frame)
                .map(DepthFrame::new);
    }
    
    /**
     * Number of embedded frames
     */
    public int size() {
        if (count == null)
            count = frame.embeddedFramesCount();
        return count;
    }
    
    public Stream<CompositeFrame> asStream(StreamType s) {
        var e = RealSenseErrorHolder.create();
        return IntStream.range(0, size())
            .mapToObj(i -> {
                var fref = rs2_extract_frame(frame.get_rs2_frame(), i, e);
                e.verify();
                return new CompositeFrame(fref);
            });
    }

    @Override
    public void close() {
        rs2_release_frame(frame.get_rs2_frame());
        frame = null;
    }
}
