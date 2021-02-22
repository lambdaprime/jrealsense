package id.jrealsense.frames;

import id.jrealsense.Frame;
import id.jrealsense.jni.rs2_frame;
import id.xfunction.logging.XLogger;

public class CompositeFrame extends AbstractFrame<CompositeFrame> implements Frame<CompositeFrame> {

    private static final XLogger LOG = XLogger.getLogger(CompositeFrame.class);
    
    public CompositeFrame(rs2_frame frame) {
        super(frame);
    }

    @Override
    protected XLogger log() {
        return LOG;
    }

}
