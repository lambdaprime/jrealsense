// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * void (*on_exit$__func)(int,void*);
 * }
 */
public interface on_exit$__func {

    void apply(int _x0, java.lang.foreign.MemorySegment _x1);
    static MemorySegment allocate(on_exit$__func fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$26.const$3, fi, constants$26.const$2, scope);
    }
    static on_exit$__func ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (int __x0, java.lang.foreign.MemorySegment __x1) -> {
            try {
                constants$26.const$4.invokeExact(symbol, __x0, __x1);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


