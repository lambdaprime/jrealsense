/*
 * Copyright 2021 jrealsense project
 * 
 * Website: https://github.com/lambdaprime/jrealsense
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.jrealsense;

import id.jrealsense.frames.PointCloudFrame;
import id.jrealsense.frames.VideoFrame;
import id.jrealsense.jextract.librealsense;
import id.xfunction.logging.XLogger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Set of utils to work with point clouds
 *
 * @author lambdaprime intid@protonmail.com
 */
public class PointCloudUtils {

    private static final XLogger LOG = XLogger.getLogger(PointCloudUtils.class);

    /** Export point cloud to Wavefront (.obj) format */
    public void exportToObj(Path file, PointCloudFrame frame) {
        LOG.entering("exportToObj");
        try (var writer =
                Files.newBufferedWriter(
                        file, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE); ) {
            for (var v : frame.createVertexAccessor()) {
                writer.append(String.format("v %f %f %f\n", v.x, v.y, v.z));
            }
        } catch (IOException e) {
            throw new RealSenseException(e);
        }
        LOG.exiting("exportToObj");
    }

    /** Export point cloud to Stanford (.ply) format */
    public void exportToPly(Path file, PointCloudFrame frame, VideoFrame<?> texture) {
        LOG.entering("exportToPly");
        var e = new RealSenseError();
        librealsense.rs2_export_to_ply(
                frame.getRealSenseFrame().get_rs2_frame(),
                XMemorySegment.toNativeString(file.toAbsolutePath().toString()),
                texture.getRealSenseFrame().get_rs2_frame(),
                e.get_rs2_error());
        e.verify();
        LOG.exiting("exportToPly");
    }
}
