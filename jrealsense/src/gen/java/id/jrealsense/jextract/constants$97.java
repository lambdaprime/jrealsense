// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$97 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$97() {}
    static final FunctionDescriptor rs2_allocate_points$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_allocate_points$MH = RuntimeHelper.downcallHandle(
        "rs2_allocate_points",
        constants$97.rs2_allocate_points$FUNC
    );
    static final FunctionDescriptor rs2_allocate_composite_frame$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_allocate_composite_frame$MH = RuntimeHelper.downcallHandle(
        "rs2_allocate_composite_frame",
        constants$97.rs2_allocate_composite_frame$FUNC
    );
    static final FunctionDescriptor rs2_extract_frame$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_extract_frame$MH = RuntimeHelper.downcallHandle(
        "rs2_extract_frame",
        constants$97.rs2_extract_frame$FUNC
    );
    static final FunctionDescriptor rs2_embedded_frames_count$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_embedded_frames_count$MH = RuntimeHelper.downcallHandle(
        "rs2_embedded_frames_count",
        constants$97.rs2_embedded_frames_count$FUNC
    );
    static final FunctionDescriptor rs2_synthetic_frame_ready$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_synthetic_frame_ready$MH = RuntimeHelper.downcallHandle(
        "rs2_synthetic_frame_ready",
        constants$97.rs2_synthetic_frame_ready$FUNC
    );
    static final FunctionDescriptor rs2_pose_frame_get_pose_data$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_pose_frame_get_pose_data$MH = RuntimeHelper.downcallHandle(
        "rs2_pose_frame_get_pose_data",
        constants$97.rs2_pose_frame_get_pose_data$FUNC
    );
}

