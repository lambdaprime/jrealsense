// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * void (*rs2_playback_status_changed_callback_ptr)(enum rs2_playback_status);
 * }
 */
public interface rs2_playback_status_changed_callback_ptr {

    void apply(int _x0);
    static MemorySegment allocate(rs2_playback_status_changed_callback_ptr fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$143.const$4, fi, constants$15.const$0, scope);
    }
    static rs2_playback_status_changed_callback_ptr ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (int __x0) -> {
            try {
                constants$143.const$5.invokeExact(symbol, __x0);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


