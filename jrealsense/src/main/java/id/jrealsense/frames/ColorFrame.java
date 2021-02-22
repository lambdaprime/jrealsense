package id.jrealsense.frames;

import id.xfunction.logging.XLogger;

public class ColorFrame extends AbstractFrame<ColorFrame> {

    private static final XLogger LOG = XLogger.getLogger(ColorFrame.class);
    
    public ColorFrame(RealSenseFrame frame) {
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
