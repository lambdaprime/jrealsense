// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$112 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$112() {}
    static final FunctionDescriptor rs2_log_to_file$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_log_to_file$MH = RuntimeHelper.downcallHandle(
        "rs2_log_to_file",
        constants$112.rs2_log_to_file$FUNC
    );
    static final FunctionDescriptor rs2_log_to_callback_cpp$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_log_to_callback_cpp$MH = RuntimeHelper.downcallHandle(
        "rs2_log_to_callback_cpp",
        constants$112.rs2_log_to_callback_cpp$FUNC
    );
    static final FunctionDescriptor rs2_log_to_callback$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_log_to_callback$MH = RuntimeHelper.downcallHandle(
        "rs2_log_to_callback",
        constants$112.rs2_log_to_callback$FUNC
    );
    static final FunctionDescriptor rs2_reset_logger$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_reset_logger$MH = RuntimeHelper.downcallHandle(
        "rs2_reset_logger",
        constants$112.rs2_reset_logger$FUNC
    );
    static final FunctionDescriptor rs2_enable_rolling_log_file$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_enable_rolling_log_file$MH = RuntimeHelper.downcallHandle(
        "rs2_enable_rolling_log_file",
        constants$112.rs2_enable_rolling_log_file$FUNC
    );
    static final FunctionDescriptor rs2_get_log_message_line_number$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_log_message_line_number$MH = RuntimeHelper.downcallHandle(
        "rs2_get_log_message_line_number",
        constants$112.rs2_get_log_message_line_number$FUNC
    );
}

