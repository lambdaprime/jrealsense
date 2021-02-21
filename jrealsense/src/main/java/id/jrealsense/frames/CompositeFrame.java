package id.jrealsense.frames;

import id.jrealsense.Frame;
import id.jrealsense.jni.rs2_frame;

public class CompositeFrame extends AbstractFrame<CompositeFrame> implements Frame<CompositeFrame> {

    public CompositeFrame(rs2_frame frame) {
        super(frame);
    }

}
