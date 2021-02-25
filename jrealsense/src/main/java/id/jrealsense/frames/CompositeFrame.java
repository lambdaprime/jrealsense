package id.jrealsense.frames;

import id.xfunction.logging.XLogger;

public class CompositeFrame extends AbstractFrame<CompositeFrame> implements Frame<CompositeFrame> {

    private static final XLogger LOG = XLogger.getLogger(CompositeFrame.class);
    
    public CompositeFrame(RealSenseFrame frame) {
        super(frame);
    }

    @Override
    protected XLogger log() {
        return LOG;
    }

}
