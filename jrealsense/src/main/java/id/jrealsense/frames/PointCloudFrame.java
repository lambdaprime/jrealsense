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
package id.jrealsense.frames;

import id.jrealsense.RealSenseError;
import id.jrealsense.jextract.librealsense;
import id.jrealsense.jextract.rs2_vertex;
import id.xfunction.logging.XLogger;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

/**
 * Frame with point cloud data available
 *
 * @author lambdaprime intid@protonmail.com
 */
public class PointCloudFrame extends AbstractFrame<PointCloudFrame> {

    private static final XLogger LOG = XLogger.getLogger(PointCloudFrame.class);

    private Supplier<Integer> pointsCount =
            () -> {
                var e = new RealSenseError();
                int r =
                        librealsense.rs2_get_frame_points_count(
                                getRealSenseFrame().get_rs2_frame(), e.get_rs2_error());
                e.verify();
                pointsCount = () -> r;
                return r;
            };

    private Supplier<ByteBuffer> vertexBuffer =
            () -> {
                var e = new RealSenseError();
                var vertices =
                        librealsense.rs2_get_frame_vertices(
                                getRealSenseFrame().get_rs2_frame(), e.get_rs2_error());
                e.verify();
                var buf =
                        vertices.asSlice(0, pointsCount.get() * rs2_vertex.sizeof()).asByteBuffer();
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

    /** Number of points in the cloud */
    public int getPointsCount() {
        return pointsCount.get();
    }

    /** Create accessor to the vertices in this cloud */
    public VertexAccessor createVertexAccessor() {
        return new VertexAccessor(vertexBuffer.get().duplicate());
    }

    @Override
    protected XLogger log() {
        return LOG;
    }
}
