// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$26 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$26() {}
    static final MethodHandle const$0 = RuntimeHelper.upcallHandle(at_quick_exit$__func.class, "apply", constants$25.const$1);
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "at_quick_exit",
        constants$2.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.upcallHandle(on_exit$__func.class, "apply", constants$26.const$2);
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        constants$26.const$2
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "on_exit",
        constants$17.const$2
    );
}


