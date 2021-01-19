package id.jrealsense.frames;

import id.jrealsense.Frame;
import id.jrealsense.rs2_frame;

public class CompositeFrame extends AbstractFrame implements Frame {

    public CompositeFrame(rs2_frame frame) {
        super(frame);
    }

}
