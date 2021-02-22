package id.jrealsense.frames;

import id.jrealsense.jni.rs2_frame;
import id.xfunction.logging.XLogger;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.RealSenseErrorHolder;

/**
 * Frame with depth information
 */
public class DepthFrame extends AbstractFrame<DepthFrame> {

    private static final XLogger LOG = XLogger.getLogger(DepthFrame.class);
    
    public DepthFrame(rs2_frame frame) {
        super(frame);
    }

    @Override
    public int embeddedFramesCount() {
        return 0;
    }

    /**
     * Distance from the camera to the object in the center of the frame
     */
    public float getDistance() {
        int w = getWidth();
        int h = getHeight();
        return getDistance(w / 2, h / 2);
    }
    
    /**
     * Distance from the camera to the point with given coordinates
     */
    public float getDistance(int x, int y) {
        var e = RealSenseErrorHolder.create();
        float r = rs2_depth_frame_get_distance(get_rs2_frame(), x, y, e);
        e.verify();
        return r;
    }
    
    @Override
    protected XLogger log() {
        return LOG;
    }
}
