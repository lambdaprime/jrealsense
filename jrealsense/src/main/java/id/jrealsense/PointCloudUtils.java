package id.jrealsense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import id.jrealsense.frames.PointCloudFrame;
import id.xfunction.logging.XLogger;

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
}
