// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$99 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$99() {}
    static final FunctionDescriptor rs2_ambient_light_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_ambient_light_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_ambient_light_to_string",
        constants$99.rs2_ambient_light_to_string$FUNC
    );
    static final FunctionDescriptor rs2_digital_gain_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_digital_gain_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_digital_gain_to_string",
        constants$99.rs2_digital_gain_to_string$FUNC
    );
    static final FunctionDescriptor rs2_host_perf_mode_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_host_perf_mode_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_host_perf_mode_to_string",
        constants$99.rs2_host_perf_mode_to_string$FUNC
    );
    static final FunctionDescriptor rs2_emitter_frequency_mode_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_emitter_frequency_mode_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_emitter_frequency_mode_to_string",
        constants$99.rs2_emitter_frequency_mode_to_string$FUNC
    );
    static final FunctionDescriptor rs2_is_option_read_only$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_is_option_read_only$MH = RuntimeHelper.downcallHandle(
        "rs2_is_option_read_only",
        constants$99.rs2_is_option_read_only$FUNC
    );
    static final FunctionDescriptor rs2_get_option$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_get_option$MH = RuntimeHelper.downcallHandle(
        "rs2_get_option",
        constants$99.rs2_get_option$FUNC
    );
}


