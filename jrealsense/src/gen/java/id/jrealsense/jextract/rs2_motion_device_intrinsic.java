// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct rs2_motion_device_intrinsic {
 *     float  data[3][4];
 *     float noise_variances[3];
 *     float bias_variances[3];
 * };
 * }
 */
public class rs2_motion_device_intrinsic {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(3, MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT)).withName("data"),
        MemoryLayout.sequenceLayout(3, Constants$root.C_FLOAT$LAYOUT).withName("noise_variances"),
        MemoryLayout.sequenceLayout(3, Constants$root.C_FLOAT$LAYOUT).withName("bias_variances")
    ).withName("rs2_motion_device_intrinsic");
    public static MemoryLayout $LAYOUT() {
        return rs2_motion_device_intrinsic.$struct$LAYOUT;
    }
    public static MemorySegment data$slice(MemorySegment seg) {
        return seg.asSlice(0, 48);
    }
    public static MemorySegment noise_variances$slice(MemorySegment seg) {
        return seg.asSlice(48, 12);
    }
    public static MemorySegment bias_variances$slice(MemorySegment seg) {
        return seg.asSlice(60, 12);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


