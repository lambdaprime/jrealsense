// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$149 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$149() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rs2_get_log_message_line_number",
        constants$17.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rs2_get_log_message_filename",
        constants$29.const$2
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rs2_get_raw_log_message",
        constants$29.const$2
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "rs2_get_full_log_message",
        constants$29.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "rs2_log",
        constants$94.const$1
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(JAVA_FLOAT,
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "rs2_depth_frame_get_distance",
        constants$149.const$5
    );
}


