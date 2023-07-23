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

import static id.jrealsense.FormatType.RS2_FORMAT_ANY;

import id.jrealsense.filters.FilterData;
import id.jrealsense.frames.ColorFrame;
import id.jrealsense.frames.CompositeFrame;
import id.jrealsense.frames.DepthFrame;
import id.jrealsense.frames.Frame;
import id.jrealsense.frames.RealSenseFrame;
import id.jrealsense.jextract.librealsense;
import id.xfunction.logging.XLogger;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The lifetime of all frames in the frame set is bound to the lifetime of the frame set itself.
 * Closing them explicitly has no effect.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class FrameSet implements FilterData, AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(FrameSet.class);
    private RealSenseFrame frame;
    private boolean isClosed;

    private Supplier<Integer> count =
            () -> {
                var c = frame.embeddedFramesCount();
                count = () -> c;
                return c;
            };

    private Supplier<List<? extends Frame<?>>> frames =
            () -> {
                var e = new RealSenseError();
                var l =
                        IntStream.range(0, size())
                                .mapToObj(
                                        i -> {
                                            var fref =
                                                    librealsense.rs2_extract_frame(
                                                            frame.get_rs2_frame(),
                                                            i,
                                                            e.get_rs2_error());
                                            e.verify();
                                            return new CompositeFrame(new RealSenseFrame(fref));
                                        })
                                .collect(Collectors.toList());
                frames = () -> l;
                return l;
            };

    public FrameSet(RealSenseFrame frame) {
        this.frame = frame;
    }

    public Optional<Frame<?>> firsOrDefault(StreamType s) {
        return firsOrDefault(s, RS2_FORMAT_ANY);
    }

    public Optional<Frame<?>> firsOrDefault(StreamType stream, FormatType format) {
        return asStream()
                .filter(
                        frame -> {
                            if (frame.getProfile().getStream() == stream
                                    && (format == RS2_FORMAT_ANY
                                            || format == frame.getProfile().getFormat()))
                                return true;
                            return false;
                        })
                .findFirst();
    }

    public Optional<DepthFrame> getDepthFrame() {
        return firsOrDefault(StreamType.RS2_STREAM_DEPTH, FormatType.RS2_FORMAT_Z16)
                .map(Frame::getRealSenseFrame)
                .map(frame -> new DepthFrame(frame));
    }

    public Optional<ColorFrame> getColorFrame(FormatType type) {
        return firsOrDefault(StreamType.RS2_STREAM_COLOR, type)
                .map(Frame::getRealSenseFrame)
                .map(frame -> new ColorFrame(frame));
    }

    /** Number of embedded frames */
    public int size() {
        return count.get();
    }

    @SuppressWarnings("unchecked")
    public Stream<Frame<?>> asStream() {
        return (Stream<Frame<?>>) frames.get().stream();
    }

    public RealSenseFrame getFrame() {
        return frame;
    }

    @Override
    public void close() {
        LOG.entering("close");
        if (isClosed) {
            LOG.fine("already closed");
            return;
        }
        if (frame.isIgnoreOnClose()) {
            isClosed = true;
            LOG.fine("Ignoring close operation as ownership has changed");
            return;
        }
        asStream().forEach(Frame::close);
        frame.close();
        isClosed = true;
        LOG.exiting("close");
    }
}
