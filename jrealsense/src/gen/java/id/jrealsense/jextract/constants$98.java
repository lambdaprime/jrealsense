// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$98 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$98() {}
    static final FunctionDescriptor rs2_extract_target_dimensions$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rs2_extract_target_dimensions$MH = RuntimeHelper.downcallHandle(
        "rs2_extract_target_dimensions",
        constants$98.rs2_extract_target_dimensions$FUNC
    );
    static final FunctionDescriptor rs2_option_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_option_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_option_to_string",
        constants$98.rs2_option_to_string$FUNC
    );
    static final FunctionDescriptor rs2_sr300_visual_preset_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_sr300_visual_preset_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_sr300_visual_preset_to_string",
        constants$98.rs2_sr300_visual_preset_to_string$FUNC
    );
    static final FunctionDescriptor rs2_rs400_visual_preset_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_rs400_visual_preset_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_rs400_visual_preset_to_string",
        constants$98.rs2_rs400_visual_preset_to_string$FUNC
    );
    static final FunctionDescriptor rs2_l500_visual_preset_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_l500_visual_preset_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_l500_visual_preset_to_string",
        constants$98.rs2_l500_visual_preset_to_string$FUNC
    );
    static final FunctionDescriptor rs2_sensor_mode_to_string$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle rs2_sensor_mode_to_string$MH = RuntimeHelper.downcallHandle(
        "rs2_sensor_mode_to_string",
        constants$98.rs2_sensor_mode_to_string$FUNC
    );
}


