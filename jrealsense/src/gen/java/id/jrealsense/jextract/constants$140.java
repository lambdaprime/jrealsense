// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$140 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$140() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "rs2_frame_queue_size",
        constants$17.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "rs2_wait_for_frame",
        constants$99.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "rs2_poll_for_frame",
        constants$21.const$5
    );
    static final FunctionDescriptor const$3 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "rs2_try_wait_for_frame",
        constants$140.const$3
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "rs2_enqueue_frame",
        constants$94.const$4
    );
}

