// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$124 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$124() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rs2_serialize_json",
        constants$29.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rs2_load_json",
        constants$101.const$3
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_FLOAT,
        JAVA_FLOAT,
        JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rs2_run_focal_length_calibration_cpp",
        constants$124.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_FLOAT,
        JAVA_FLOAT,
        JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rs2_run_focal_length_calibration",
        constants$124.const$4
    );
}


