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
package id.jrealsense.frames;

import id.jrealsense.RealSenseError;
import id.jrealsense.StreamProfile;
import id.jrealsense.jextract.librealsense;
import id.xfunction.logging.XLogger;
import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.function.Supplier;

/**
 * This class acts as a single holder of low level {@link id.jrealsense.jni.rs2_frame} object. It
 * manages its lifetime and all {@link id.jrealsense.frames.Frame} classes depend on it.
 *
 * <p>It is possible to have multiple frames referring to same object of this class. Because it
 * caches most of the values from {@link id.jrealsense.jni.rs2_frame} calling any function for
 * {@link id.jrealsense.jni.rs2_frame} on two separate frames will result only on one JNI call. For
 * example given two frames {@link ColorFrame} and {@link DepthFrame} which use same object of this
 * class. Calling method {@link DepthFrame#getWidth} and {@link ColorFrame#getWidth} will result in
 * only one JNI call.
 *
 * <p>Keep in mind that once this frame is closed all further operations on frames which depend on
 * it are undefined.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class RealSenseFrame implements AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(RealSenseFrame.class);

    private MemorySegment frame;
    private boolean ignoreOnClose;

    private Supplier<Integer> width =
            () -> {
                var e = new RealSenseError();
                var r = librealsense.rs2_get_frame_width(get_rs2_frame(), e.get_rs2_error());
                e.verify();
                width = () -> r;
                return r;
            };

    private Supplier<Integer> height =
            () -> {
                var e = new RealSenseError();
                var r = librealsense.rs2_get_frame_height(get_rs2_frame(), e.get_rs2_error());
                e.verify();
                height = () -> r;
                return r;
            };

    private Supplier<Long> frameNumber =
            () -> {
                var e = new RealSenseError();
                var r = librealsense.rs2_get_frame_number(get_rs2_frame(), e.get_rs2_error());
                e.verify();
                frameNumber = () -> r;
                return r;
            };

    private Supplier<Double> timestamp =
            () -> {
                var e = new RealSenseError();
                var c = librealsense.rs2_get_frame_timestamp(get_rs2_frame(), e.get_rs2_error());
                e.verify();
                timestamp = () -> c;
                return c;
            };

    private Supplier<Instant> instant =
            () -> {
                var v = Instant.ofEpochMilli(timestamp.get().longValue());
                instant = () -> v;
                return v;
            };

    private Supplier<StreamProfile> profile =
            () -> {
                var e = new RealSenseError();
                var s =
                        librealsense.rs2_get_frame_stream_profile(
                                get_rs2_frame(), e.get_rs2_error());
                e.verify();
                var r = StreamProfile.create(s);
                profile = () -> r;
                return r;
            };

    private Supplier<Integer> count =
            () -> {
                var e = new RealSenseError();
                var c = librealsense.rs2_embedded_frames_count(get_rs2_frame(), e.get_rs2_error());
                e.verify();
                count = () -> c;
                return c;
            };

    private Supplier<Integer> stride =
            () -> {
                var e = new RealSenseError();
                var r =
                        librealsense.rs2_get_frame_stride_in_bytes(
                                get_rs2_frame(), e.get_rs2_error());
                e.verify();
                stride = () -> r;
                return r;
            };

    public RealSenseFrame(MemorySegment frame) {
        this.frame = frame;
    }

    public StreamProfile getProfile() {
        return profile.get();
    }

    public int getWidth() {
        return width.get();
    }

    public int getHeight() {
        return height.get();
    }

    public int getStride() {
        return stride.get();
    }

    public int embeddedFramesCount() {
        return count.get();
    }

    public MemorySegment get_rs2_frame() {
        return frame;
    }

    public ByteBuffer getData() {
        var capacity = getHeight() * getStride();
        var e = new RealSenseError();
        var data = librealsense.rs2_get_frame_data(get_rs2_frame(), e.get_rs2_error());
        e.verify();
        return data.asSlice(0, capacity).asByteBuffer();
    }

    @Override
    public void close() {
        LOG.entering("close");
        if (ignoreOnClose) {
            LOG.finer("Frame marked for NOT to be released");
        } else {
            librealsense.rs2_release_frame(frame);
        }
        // frame.delete();
        frame = null;
        LOG.exiting("close");
    }

    public long getFrameNumber() {
        return frameNumber.get();
    }

    public double getTimestamp() {
        return timestamp.get();
    }

    public Instant getTimestampInstant() {
        return instant.get();
    }

    /**
     * Prevent internal frame from being released when this frame is closed. It may be needed when
     * lifetime of the internal frame is managed by some other process.
     */
    public void setIgnoreOnClose(boolean ignore) {
        ignoreOnClose = ignore;
    }

    public boolean isIgnoreOnClose() {
        return ignoreOnClose;
    }
}
