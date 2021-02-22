package id.jrealsense.frames;

import id.jrealsense.jni.rs2_frame;
import id.xfunction.logging.XLogger;

public class ColorFrame extends AbstractFrame<ColorFrame> {

    private static final XLogger LOG = XLogger.getLogger(ColorFrame.class);
    
    public ColorFrame(rs2_frame frame) {
        super(frame);
    }

    @Override
    public int embeddedFramesCount() {
        return 0;
    }

    @Override
    protected XLogger log() {
        return LOG;
    }
    
}
