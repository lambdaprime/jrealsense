package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.jni.rs2_processing_block;
import id.xfunction.logging.XLogger;

public class ProcessingBlock implements AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(ProcessingBlock.class);
    private rs2_processing_block block;
    
    public ProcessingBlock(rs2_processing_block block) {
        this.block = block;
    }

    public rs2_processing_block get_rs2_processing_block() {
        return block;
    }
    
    public void process(Frame<?> frame) {
        LOG.entering("process");
        var e = RealSenseErrorHolder.create();
        var realFrame = frame.getRealSenseFrame();
        realFrame.setIgnoreOnRelease(true);
        rs2_process_frame(block, realFrame.get_rs2_frame(), e);
        e.verify();
        LOG.exiting("process");
    }

    @Override
    public void close() {
        LOG.entering("close");
        rs2_delete_processing_block(block);
        LOG.exiting("close");
    }
}
