// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$86 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$86() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "__fmaf",
        constants$85.const$4
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "scalbf",
        constants$63.const$2
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "__scalbf",
        constants$63.const$2
    );
    static final VarHandle const$3 = JAVA_INT.varHandle();
    static final MemorySegment const$4 = RuntimeHelper.lookupGlobalVariable("signgam", JAVA_INT);
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "rs2_notification_category_to_string",
        constants$86.const$5
    );
}


