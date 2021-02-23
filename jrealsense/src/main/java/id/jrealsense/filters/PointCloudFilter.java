package id.jrealsense.filters;

import static id.jrealsense.jni.librealsense2.rs2_create_pointcloud;

import id.jrealsense.FrameQueue;
import id.jrealsense.ProcessingBlock;
import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.frames.DepthFrame;
import id.jrealsense.frames.PointCloudFrame;

/**
 * Filter which generates point cloud from depth frame.
 */
public class PointCloudFilter extends AbstractFilter<DepthFrame, PointCloudFrame> {

    public PointCloudFilter(ProcessingBlock block, FrameQueue queue) {
        super(block, queue);
    }

    @Override
    public PointCloudFrame process(DepthFrame frame) {
        getBlock().process(frame);
        return getQueue().poll(PointCloudFrame.class);
    }
    
    /**
     * Factory method, creates new {@link PointCloudFilter}
     */
    public static PointCloudFilter create() {
        var e = RealSenseErrorHolder.create();
        var block = rs2_create_pointcloud(e);
        e.verify();
        var queue = FrameQueue.create(1);
        var ret = new PointCloudFilter(new ProcessingBlock(block), queue);
        ret.startQueue();
        return ret;
    }

}
