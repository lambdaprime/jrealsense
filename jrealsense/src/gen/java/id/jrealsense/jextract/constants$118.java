// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$118 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$118() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rs2_loopback_is_enabled",
        constants$17.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rs2_connect_tm2_controller",
        constants$95.const$3
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rs2_disconnect_tm2_controller",
        constants$118.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "rs2_reset_to_factory_calibration",
        constants$94.const$4
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rs2_write_calibration",
        constants$94.const$4
    );
}


