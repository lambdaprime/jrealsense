package id.jrealsense.filters;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.FrameQueue;
import id.jrealsense.ProcessingBlock;
import id.jrealsense.RealSenseError;
import id.jrealsense.frames.ColorFrame;
import id.jrealsense.frames.DepthFrame;

public class Colorizer extends AbstractFilter<DepthFrame, ColorFrame> {

    public Colorizer(ProcessingBlock block, FrameQueue queue) {
        super(block, queue);
    }

    @Override
    public ColorFrame process(DepthFrame frame) {
        getBlock().process(frame);
        return getQueue().poll(ColorFrame.class);
    }

    public static Colorizer create() {
        var e = RealSenseError.create();
        var block = rs2_create_colorizer(e.get_p_p_rs2_error());
        e.verify();
        var queue = FrameQueue.create(1);
        var ret = new Colorizer(new ProcessingBlock(block), queue);
        ret.startQueue();
        return ret;
    }

}
