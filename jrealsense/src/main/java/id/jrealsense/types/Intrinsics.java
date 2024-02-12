/*
 * Copyright 2023 jrealsense project
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
package id.jrealsense.types;

import static id.jrealsense.jextract.rs2_intrinsics.fx$get;
import static id.jrealsense.jextract.rs2_intrinsics.fy$get;
import static id.jrealsense.jextract.rs2_intrinsics.height$get;
import static id.jrealsense.jextract.rs2_intrinsics.ppx$get;
import static id.jrealsense.jextract.rs2_intrinsics.ppy$get;
import static id.jrealsense.jextract.rs2_intrinsics.width$get;

import id.xfunction.XJson;
import id.xfunction.XJsonStringBuilder;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/** Video stream intrinsics. */
public class Intrinsics {

    private MemorySegment rs2_intrinsics = Arena.ofAuto().allocate(ValueLayout.ADDRESS.byteSize());

    /**
     * Horizontal coordinate of the principal point of the image, as a pixel offset from the left
     * edge
     */
    public float ppx() {
        return ppx$get(rs2_intrinsics);
    }

    /**
     * Vertical coordinate of the principal point of the image, as a pixel offset from the top edge
     */
    public float ppy() {
        return ppy$get(rs2_intrinsics);
    }

    /** Focal length of the image plane, as a multiple of pixel width */
    public float fx() {
        return fx$get(rs2_intrinsics);
    }

    /** Focal length of the image plane, as a multiple of pixel height */
    public float fy() {
        return fy$get(rs2_intrinsics);
    }

    /** Width of the image in pixels */
    public int width() {
        return width$get(rs2_intrinsics);
    }

    /** Height of the image in pixels */
    public int height() {
        return height$get(rs2_intrinsics);
    }

    public MemorySegment get_rs2_intrinsics() {
        return rs2_intrinsics;
    }

    @Override
    public String toString() {
        XJsonStringBuilder builder = new XJsonStringBuilder(this);
        builder.append("ppx()", ppx());
        builder.append("ppy()", ppy());
        builder.append("fx()", fx());
        builder.append("fy()", fy());
        builder.append("width()", width());
        builder.append("height()", height());
        return builder.toString();
    }

    /**
     * @see <a
     *     href="http://www.open3d.org/docs/release/cpp_api/classopen3d_1_1camera_1_1_pinhole_camera_intrinsic.html#ac66896c0b913a13760c70261f2765d66">PinholeCameraIntrinsic::ConvertToJsonValue</a>
     */
    public String toOpen3DJson() {
        return XJson.asString(
                "height",
                height(),
                "width",
                width(),
                "intrinsic_matrix",
                List.of(
                        // col1
                        fx(),
                        0.0,
                        0.0,
                        // col2
                        0.0,
                        fy(),
                        0.0,
                        // col3
                        ppx(),
                        ppy(),
                        1.0));
    }

    /**
     * @see <a
     *     href="http://www.open3d.org/docs/release/cpp_api/classopen3d_1_1camera_1_1_pinhole_camera_intrinsic.html#ac66896c0b913a13760c70261f2765d66">PinholeCameraIntrinsic::ConvertToJsonValue</a>
     */
    public void saveAsOpen3DJson(Path file) throws IOException {
        Files.writeString(
                file,
                toOpen3DJson(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
