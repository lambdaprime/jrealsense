// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$84 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$84() {}
    static final FunctionDescriptor rs2_device_hub_is_device_connected$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_device_hub_is_device_connected$MH = RuntimeHelper.downcallHandle(
        "rs2_device_hub_is_device_connected",
        constants$84.rs2_device_hub_is_device_connected$FUNC
    );
    static final FunctionDescriptor rs2_get_device_count$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_device_count$MH = RuntimeHelper.downcallHandle(
        "rs2_get_device_count",
        constants$84.rs2_get_device_count$FUNC
    );
    static final FunctionDescriptor rs2_delete_device_list$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_delete_device_list$MH = RuntimeHelper.downcallHandle(
        "rs2_delete_device_list",
        constants$84.rs2_delete_device_list$FUNC
    );
    static final FunctionDescriptor rs2_device_list_contains$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_device_list_contains$MH = RuntimeHelper.downcallHandle(
        "rs2_device_list_contains",
        constants$84.rs2_device_list_contains$FUNC
    );
    static final FunctionDescriptor rs2_create_device$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_create_device$MH = RuntimeHelper.downcallHandle(
        "rs2_create_device",
        constants$84.rs2_create_device$FUNC
    );
    static final FunctionDescriptor rs2_delete_device$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_delete_device$MH = RuntimeHelper.downcallHandle(
        "rs2_delete_device",
        constants$84.rs2_delete_device$FUNC
    );
}

