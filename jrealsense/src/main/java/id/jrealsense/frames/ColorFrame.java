package id.jrealsense.frames;

import id.jrealsense.rs2_frame;

public class ColorFrame extends AbstractFrame<ColorFrame> {

    public ColorFrame(rs2_frame frame) {
        super(frame);
    }

    @Override
    public int embeddedFramesCount() {
        return 0;
    }
    
}
