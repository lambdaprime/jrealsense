// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$80 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$80() {}
    static final FunctionDescriptor rs2_set_extrinsics$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_set_extrinsics$MH = RuntimeHelper.downcallHandle(
        "rs2_set_extrinsics",
        constants$80.rs2_set_extrinsics$FUNC
    );
    static final FunctionDescriptor rs2_get_dsm_params$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_dsm_params$MH = RuntimeHelper.downcallHandle(
        "rs2_get_dsm_params",
        constants$80.rs2_get_dsm_params$FUNC
    );
    static final FunctionDescriptor rs2_override_dsm_params$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_override_dsm_params$MH = RuntimeHelper.downcallHandle(
        "rs2_override_dsm_params",
        constants$80.rs2_override_dsm_params$FUNC
    );
    static final FunctionDescriptor rs2_reset_sensor_calibration$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_reset_sensor_calibration$MH = RuntimeHelper.downcallHandle(
        "rs2_reset_sensor_calibration",
        constants$80.rs2_reset_sensor_calibration$FUNC
    );
    static final FunctionDescriptor rs2_set_motion_device_intrinsics$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_set_motion_device_intrinsics$MH = RuntimeHelper.downcallHandle(
        "rs2_set_motion_device_intrinsics",
        constants$80.rs2_set_motion_device_intrinsics$FUNC
    );
    static final FunctionDescriptor rs2_get_max_usable_depth_range$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_max_usable_depth_range$MH = RuntimeHelper.downcallHandle(
        "rs2_get_max_usable_depth_range",
        constants$80.rs2_get_max_usable_depth_range$FUNC
    );
}


