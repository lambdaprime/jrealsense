// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$92 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$92() {}
    static final VarHandle const$0 = constants$91.const$3.varHandle(MemoryLayout.PathElement.groupElement("z"));
    static final StructLayout const$1 = MemoryLayout.structLayout(
        JAVA_FLOAT.withName("x"),
        JAVA_FLOAT.withName("y"),
        JAVA_FLOAT.withName("z"),
        JAVA_FLOAT.withName("w")
    ).withName("rs2_quaternion");
    static final VarHandle const$2 = constants$92.const$1.varHandle(MemoryLayout.PathElement.groupElement("x"));
    static final VarHandle const$3 = constants$92.const$1.varHandle(MemoryLayout.PathElement.groupElement("y"));
    static final VarHandle const$4 = constants$92.const$1.varHandle(MemoryLayout.PathElement.groupElement("z"));
    static final VarHandle const$5 = constants$92.const$1.varHandle(MemoryLayout.PathElement.groupElement("w"));
}


