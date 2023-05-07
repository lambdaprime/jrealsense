// Generated by jextract

package id.jrealsense.jextract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct rs2_extrinsics {
 *     float rotation[9];
 *     float translation[3];
 * };
 * }
 */
public class rs2_extrinsics {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(9, Constants$root.C_FLOAT$LAYOUT).withName("rotation"),
        MemoryLayout.sequenceLayout(3, Constants$root.C_FLOAT$LAYOUT).withName("translation")
    ).withName("rs2_extrinsics");
    public static MemoryLayout $LAYOUT() {
        return rs2_extrinsics.$struct$LAYOUT;
    }
    public static MemorySegment rotation$slice(MemorySegment seg) {
        return seg.asSlice(0, 36);
    }
    public static MemorySegment translation$slice(MemorySegment seg) {
        return seg.asSlice(36, 12);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


