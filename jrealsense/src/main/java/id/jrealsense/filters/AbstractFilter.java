package id.jrealsense.filters;

import static id.jrealsense.librealsense2.*;

import id.jrealsense.Filter;
import id.jrealsense.Frame;
import id.jrealsense.FrameQueue;
import id.jrealsense.ProcessingBlock;
import id.jrealsense.RealSenseError;

abstract class AbstractFilter<IN extends Frame<IN>, OUT extends Frame<OUT>>
    implements Filter<IN, OUT>
{

    private ProcessingBlock block;
    private FrameQueue queue;
    
    protected AbstractFilter(ProcessingBlock block, FrameQueue queue) {
        this.block = block;
        this.queue = queue;
    }
    
    public void startQueue() {
        RealSenseError e = RealSenseError.create();
        rs2_start_processing_queue(block.get_rs2_processing_block(),
                queue.get_rs2_frame_queue(), e.get_p_p_rs2_error());
        e.verify();
    }
    
    public void stopQueue() {
        RealSenseError e = RealSenseError.create();
        rs2_start_processing_queue(block.get_rs2_processing_block(),
                queue.get_rs2_frame_queue(), e.get_p_p_rs2_error());
        e.verify();
    }
    
    protected ProcessingBlock getBlock() {
        return block;
    }

    protected FrameQueue getQueue() {
        return queue;
    }
    
    @Override
    public void close() {
        block.close();
        queue.close();
    }
}
