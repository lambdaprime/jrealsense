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
package id.jrealsense;

import id.jrealsense.exceptions.JRealSenseException;
import id.jrealsense.frames.Frame;
import id.jrealsense.frames.RealSenseFrame;
import id.jrealsense.jextract.librealsense;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class FrameQueue implements AutoCloseable {

    private MemorySegment queue;

    protected FrameQueue(MemorySegment queue) {
        this.queue = queue;
    }

    /** Factory method, creates new {@link FrameQueue} */
    public static FrameQueue create(int capacity) {
        var e = new RealSenseError();
        var q = librealsense.rs2_create_frame_queue(capacity, e.get_rs2_error());
        e.verify();
        return new FrameQueue(q);
    }

    @Override
    public void close() {
        librealsense.rs2_delete_frame_queue(queue);
    }

    public <T extends Frame<T>> T poll(Class<T> frameClass) {
        var e = new RealSenseError();
        var framePtr = Arena.ofAuto().allocate(ValueLayout.ADDRESS.byteSize());
        var numOfFrames = librealsense.rs2_poll_for_frame(queue, framePtr, e.get_rs2_error());
        e.verify();
        if (numOfFrames != 1)
            // todo asserts
            throw new JRealSenseException(
                    "Polling from queue failed returning %d frames", numOfFrames);
        try {
            var ctor = frameClass.getConstructor(RealSenseFrame.class);
            return ctor.newInstance(new RealSenseFrame(framePtr.get(ValueLayout.ADDRESS, 0)));
        } catch (Exception ex) {
            throw new JRealSenseException(ex);
        }
    }

    public FrameSet pollFrameSet() {
        var e = new RealSenseError();
        var framePtr = Arena.ofAuto().allocate(ValueLayout.ADDRESS.byteSize());
        var numOfFrames = librealsense.rs2_poll_for_frame(queue, framePtr, e.get_rs2_error());
        e.verify();
        if (numOfFrames != 1)
            throw new JRealSenseException(
                    "Polling from queue failed returning %d frames", numOfFrames);
        return new FrameSet(new RealSenseFrame(framePtr.get(ValueLayout.ADDRESS, 0)));
    }

    public MemorySegment get_rs2_frame_queue() {
        return queue;
    }
}
