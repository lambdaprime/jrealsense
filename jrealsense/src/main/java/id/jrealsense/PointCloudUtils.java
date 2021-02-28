package id.jrealsense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import id.jrealsense.frames.PointCloudFrame;
import id.jrealsense.frames.VideoFrame;
import id.xfunction.logging.XLogger;

import static id.jrealsense.jni.librealsense2.*;

/**
 * Set of utils to work with point clouds
 */
public class PointCloudUtils {
    
    private static final XLogger LOG = XLogger.getLogger(PointCloudUtils.class);

    /**
     * Export point cloud to Wavefront (.obj) format
     */
    public void exportToObj(Path file, PointCloudFrame frame) {
        LOG.entering("exportToObj");
        try (var writer = Files.newBufferedWriter(file,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);) {
            for (var v: frame.createVertexAccessor()) {
                writer.append(String.format("v %f %f %f\n", v.x, v.y, v.z));
            }
        } catch (IOException e) {
            throw new RealSenseException(e);
        }
        LOG.exiting("exportToObj");
    }
    
    /**
     * Export point cloud to Stanford (.ply) format
     */
    public void exportToPly(Path file, PointCloudFrame frame, VideoFrame<?> texture) {
        LOG.entering("exportToPly");
        var e = RealSenseErrorHolder.create();
        rs2_export_to_ply(frame.getRealSenseFrame().get_rs2_frame(),
                file.toAbsolutePath().toString(),
                texture.getRealSenseFrame().get_rs2_frame(),
                e);
        e.verify();
        LOG.exiting("exportToPly");
    }
}
