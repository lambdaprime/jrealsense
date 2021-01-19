package id.jrealsense.frames;

import id.jrealsense.rs2_frame;

public class DepthFrame extends AbstractFrame<DepthFrame> {

    public DepthFrame(rs2_frame frame) {
        super(frame);
    }

    @Override
    public int embeddedFramesCount() {
        return 0;
    }
    
}
