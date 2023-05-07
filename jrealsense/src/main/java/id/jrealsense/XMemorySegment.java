package id.jrealsense;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentScope;
import java.lang.foreign.ValueLayout;

public class XMemorySegment {

    public static MemorySegment createPointer(MemorySegment value) {
        var ptr = MemorySegment.allocateNative(ValueLayout.ADDRESS.byteSize(), SegmentScope.auto());
        ptr.set(ValueLayout.ADDRESS, 0, value);
        System.out.println("isReadOnly " + ptr.isReadOnly());
        return ptr;
    }
    
    public static MemorySegment toNativeString(String s) {
        var m = MemorySegment.allocateNative(s.length() + 1, SegmentScope.auto());
        m.setUtf8String(0, s);
        return m;
    }
}
