// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$79 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$79() {}
    static final FunctionDescriptor rs2_get_static_node$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_static_node$MH = RuntimeHelper.downcallHandle(
        "rs2_get_static_node",
        constants$79.rs2_get_static_node$FUNC
    );
    static final FunctionDescriptor rs2_remove_static_node$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_remove_static_node$MH = RuntimeHelper.downcallHandle(
        "rs2_remove_static_node",
        constants$79.rs2_remove_static_node$FUNC
    );
    static final FunctionDescriptor rs2_load_wheel_odometry_config$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_load_wheel_odometry_config$MH = RuntimeHelper.downcallHandle(
        "rs2_load_wheel_odometry_config",
        constants$79.rs2_load_wheel_odometry_config$FUNC
    );
    static final FunctionDescriptor rs2_send_wheel_odometry$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        MemoryLayout.structLayout(
            Constants$root.C_FLOAT$LAYOUT.withName("x"),
            Constants$root.C_FLOAT$LAYOUT.withName("y"),
            Constants$root.C_FLOAT$LAYOUT.withName("z")
        ).withName("rs2_vector"),
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_send_wheel_odometry$MH = RuntimeHelper.downcallHandle(
        "rs2_send_wheel_odometry",
        constants$79.rs2_send_wheel_odometry$FUNC
    );
    static final FunctionDescriptor rs2_set_intrinsics$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_set_intrinsics$MH = RuntimeHelper.downcallHandle(
        "rs2_set_intrinsics",
        constants$79.rs2_set_intrinsics$FUNC
    );
    static final FunctionDescriptor rs2_override_intrinsics$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_override_intrinsics$MH = RuntimeHelper.downcallHandle(
        "rs2_override_intrinsics",
        constants$79.rs2_override_intrinsics$FUNC
    );
}


