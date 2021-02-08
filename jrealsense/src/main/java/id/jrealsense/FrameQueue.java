package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.jni.rs2_frame;
import id.jrealsense.jni.rs2_frame_queue;

public class FrameQueue implements AutoCloseable {

    private rs2_frame_queue queue;

    private FrameQueue(rs2_frame_queue queue) {
        this.queue = queue;
    }

    public static FrameQueue create(int capacity) {
        var e = RealSenseErrorHolder.create();
        var q = rs2_create_frame_queue(capacity, e);
        e.verify();
        return new FrameQueue(q);
    }
    
    @Override
    public void close() {
        rs2_delete_frame_queue(queue);
    }
    
    public <T extends Frame<T>> T poll(Class<T> frameClass) {
        var e = RealSenseErrorHolder.create();
        var framePtr = new_rs2_frame_ptr();
        var numOfFrames = rs2_poll_for_frame(queue, framePtr, e);
        e.verify();;
        if (numOfFrames != 1)
            //todo asserts
            throw new RealSenseException("Polling from queue failed returning %d frames",
                numOfFrames);
        rs2_frame frame = rs2_frame_ptr_value(framePtr);
        try {
            var ctor = frameClass.getConstructor(rs2_frame.class);
            return ctor.newInstance(frame);
        } catch (Exception ex) {
            throw new RealSenseException(ex);
        }
    }
    
    public rs2_frame_queue get_rs2_frame_queue() {
        return queue;
    }
}
