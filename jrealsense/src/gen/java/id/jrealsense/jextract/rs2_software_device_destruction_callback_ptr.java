// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * void (*rs2_software_device_destruction_callback_ptr)(void*);
 * }
 */
public interface rs2_software_device_destruction_callback_ptr {

    void apply(java.lang.foreign.MemorySegment _x0);
    static MemorySegment allocate(rs2_software_device_destruction_callback_ptr fi, SegmentScope scope) {
        return RuntimeHelper.upcallStub(constants$67.rs2_software_device_destruction_callback_ptr_UP$MH, fi, constants$67.rs2_software_device_destruction_callback_ptr$FUNC, scope);
    }
    static rs2_software_device_destruction_callback_ptr ofAddress(MemorySegment addr, SegmentScope scope) {
        MemorySegment symbol = MemorySegment.ofAddress(addr.address(), 0, scope);
        return (java.lang.foreign.MemorySegment __x0) -> {
            try {
                constants$67.rs2_software_device_destruction_callback_ptr_DOWN$MH.invokeExact(symbol, __x0);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


