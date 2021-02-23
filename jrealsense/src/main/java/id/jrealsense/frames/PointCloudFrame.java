package id.jrealsense.frames;

import id.xfunction.logging.XLogger;

import static id.jrealsense.jni.librealsense2.*;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.RealSenseException;

/**
 * Frame with point cloud data available
 */
public class PointCloudFrame extends AbstractFrame<PointCloudFrame> {

    private static final XLogger LOG = XLogger.getLogger(PointCloudFrame.class);
    
    private Supplier<Integer> pointsCount = () -> {
        var e = RealSenseErrorHolder.create();
        int r = rs2_get_frame_points_count(getRealSenseFrame().get_rs2_frame(), e);
        e.verify();
        pointsCount = () -> r;
        return r;
    };
    
    private Supplier<ByteBuffer> vertexBuffer = () -> {
        var e = RealSenseErrorHolder.create();
        ByteBuffer buf = (ByteBuffer) create_vertexByteBuffer(getRealSenseFrame().get_rs2_frame(), e);
        e.verify();
        if (buf == null)
            throw new RealSenseException("Failed to get vertices from point cloud");
        vertexBuffer = () -> buf;
        return buf;
    };
    
    public PointCloudFrame(RealSenseFrame frame) {
        super(frame);
    }

    @Override
    public int embeddedFramesCount() {
        return 0;
    }

    /**
     * Number of points in the cloud
     */
    public int getPointsCount() {
        return pointsCount.get();
    }
    
    /**
     * Create accessor to the vertices in this cloud
     */
    public VertexAccessor createVertexAccessor() {
        return new VertexAccessor(vertexBuffer.get().duplicate());
    }

    @Override
    protected XLogger log() {
        return LOG;
    }
}
