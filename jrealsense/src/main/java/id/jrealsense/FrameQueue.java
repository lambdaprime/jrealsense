/*
 * Copyright 2021 jrealsense project
 * 
 * Website: https://github.com/lambdaprime/jrealsense
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Authors:
 * - lambdaprime <intid@protonmail.com>
 */
package id.jrealsense;

import static id.jrealsense.jni.librealsense2.*;

import id.jrealsense.frames.Frame;
import id.jrealsense.frames.RealSenseFrame;
import id.jrealsense.jni.rs2_frame;
import id.jrealsense.jni.rs2_frame_queue;

public class FrameQueue implements AutoCloseable {

    private rs2_frame_queue queue;

    protected FrameQueue(rs2_frame_queue queue) {
        this.queue = queue;
    }

    /**
     * Factory method, creates new {@link FrameQueue}
     */
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
        delete_rs2_frame_ptr(framePtr);
        try {
            var ctor = frameClass.getConstructor(RealSenseFrame.class);
            return ctor.newInstance(new RealSenseFrame(frame));
        } catch (Exception ex) {
            throw new RealSenseException(ex);
        }
    }
    
    public rs2_frame_queue get_rs2_frame_queue() {
        return queue;
    }
}
