// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * void (*rs2_log_callback_ptr)(enum rs2_log_severity,struct rs2_log_message*,void* arg);
 * }
 */
public interface rs2_log_callback_ptr {

    void apply(int _x0, java.lang.foreign.MemorySegment _x1, java.lang.foreign.MemorySegment arg);
    static MemorySegment allocate(rs2_log_callback_ptr fi, SegmentScope scope) {
        return RuntimeHelper.upcallStub(constants$66.rs2_log_callback_ptr_UP$MH, fi, constants$66.rs2_log_callback_ptr$FUNC, scope);
    }
    static rs2_log_callback_ptr ofAddress(MemorySegment addr, SegmentScope scope) {
        MemorySegment symbol = MemorySegment.ofAddress(addr.address(), 0, scope);
        return (int __x0, java.lang.foreign.MemorySegment __x1, java.lang.foreign.MemorySegment _arg) -> {
            try {
                constants$66.rs2_log_callback_ptr_DOWN$MH.invokeExact(symbol, __x0, __x1, _arg);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


