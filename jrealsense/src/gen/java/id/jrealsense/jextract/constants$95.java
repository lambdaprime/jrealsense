// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$95 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$95() {}
    static final FunctionDescriptor rs2_get_frame_bits_per_pixel$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_frame_bits_per_pixel$MH = RuntimeHelper.downcallHandle(
        "rs2_get_frame_bits_per_pixel",
        constants$95.rs2_get_frame_bits_per_pixel$FUNC
    );
    static final FunctionDescriptor rs2_frame_add_ref$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_frame_add_ref$MH = RuntimeHelper.downcallHandle(
        "rs2_frame_add_ref",
        constants$95.rs2_frame_add_ref$FUNC
    );
    static final FunctionDescriptor rs2_release_frame$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_release_frame$MH = RuntimeHelper.downcallHandle(
        "rs2_release_frame",
        constants$95.rs2_release_frame$FUNC
    );
    static final FunctionDescriptor rs2_keep_frame$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_keep_frame$MH = RuntimeHelper.downcallHandle(
        "rs2_keep_frame",
        constants$95.rs2_keep_frame$FUNC
    );
    static final FunctionDescriptor rs2_get_frame_vertices$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_frame_vertices$MH = RuntimeHelper.downcallHandle(
        "rs2_get_frame_vertices",
        constants$95.rs2_get_frame_vertices$FUNC
    );
    static final FunctionDescriptor rs2_export_to_ply$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_export_to_ply$MH = RuntimeHelper.downcallHandle(
        "rs2_export_to_ply",
        constants$95.rs2_export_to_ply$FUNC
    );
}

