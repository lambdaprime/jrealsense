// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$81 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$81() {}
    static final FunctionDescriptor rs2_project_point_to_pixel$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_project_point_to_pixel$MH = RuntimeHelper.downcallHandle(
        "rs2_project_point_to_pixel",
        constants$81.rs2_project_point_to_pixel$FUNC
    );
    static final FunctionDescriptor rs2_deproject_pixel_to_point$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle rs2_deproject_pixel_to_point$MH = RuntimeHelper.downcallHandle(
        "rs2_deproject_pixel_to_point",
        constants$81.rs2_deproject_pixel_to_point$FUNC
    );
    static final FunctionDescriptor rs2_transform_point_to_point$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_transform_point_to_point$MH = RuntimeHelper.downcallHandle(
        "rs2_transform_point_to_point",
        constants$81.rs2_transform_point_to_point$FUNC
    );
    static final FunctionDescriptor rs2_fov$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_fov$MH = RuntimeHelper.downcallHandle(
        "rs2_fov",
        constants$81.rs2_fov$FUNC
    );
    static final FunctionDescriptor rs2_project_color_pixel_to_depth_pixel$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_project_color_pixel_to_depth_pixel$MH = RuntimeHelper.downcallHandle(
        "rs2_project_color_pixel_to_depth_pixel",
        constants$81.rs2_project_color_pixel_to_depth_pixel$FUNC
    );
    static final FunctionDescriptor rs2_create_context$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_create_context$MH = RuntimeHelper.downcallHandle(
        "rs2_create_context",
        constants$81.rs2_create_context$FUNC
    );
}


