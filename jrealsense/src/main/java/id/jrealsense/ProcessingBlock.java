package id.jrealsense;

import static id.jrealsense.librealsense2.*;

import id.jrealsense.rs2_processing_block;

public class ProcessingBlock implements AutoCloseable {

    private rs2_processing_block block;
    
    public ProcessingBlock(rs2_processing_block block) {
        this.block = block;
    }

    public rs2_processing_block get_rs2_processing_block() {
        return block;
    }
    
    public void process(Frame frame) {
        RealSenseError e = RealSenseError.create();
        rs2_process_frame(block, frame.get_rs2_frame(), e.get_p_p_rs2_error());
        e.verify();
    }

    @Override
    public void close() {
        rs2_delete_processing_block(block);
    }
}
