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

import id.jrealsense.frames.RealSenseFrame;
import id.jrealsense.jextract.librealsense;
import id.xfunction.logging.XLogger;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentScope;
import java.lang.foreign.ValueLayout;
import java.util.Optional;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class Pipeline implements AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(Pipeline.class);
    private MemorySegment pipeline;
    private boolean isStarted;

    protected Pipeline(MemorySegment pline) {
        this.pipeline = pline;
    }

    /** Starts pipeline with default configuration */
    public void start() {
        LOG.entering("start");
        isStarted = true;
        var e = new RealSenseError();
        librealsense.rs2_pipeline_start(pipeline, e.get_rs2_error());
        e.verify();
        LOG.exiting("start");
    }

    public PipelineProfile start(Config config) {
        LOG.entering("start");
        isStarted = true;
        var e = new RealSenseError();
        var profile =
                librealsense.rs2_pipeline_start_with_config(
                        pipeline, config.get_rs_config(), e.get_rs2_error());
        e.verify();
        LOG.exiting("start");
        return new PipelineProfile(profile);
    }

    public void stop() {
        LOG.entering("stop");
        if (!isStarted) return;
        var e = new RealSenseError();
        librealsense.rs2_pipeline_stop(pipeline, e.get_rs2_error());
        e.verify();
        LOG.exiting("stop");
    }

    /** Factory method, creates new {@link Pipeline} */
    public static Pipeline create(Context ctx) {
        var e = new RealSenseError();
        var pline = librealsense.rs2_create_pipeline(ctx.get_rs2_context(), e.get_rs2_error());
        e.verify();
        return new Pipeline(pline);
    }

    /** Wait for next set of frames from the camera */
    public FrameSet waitForFrames() {
        LOG.entering("waitForFrames");
        var e = new RealSenseError();
        var frame =
                librealsense.rs2_pipeline_wait_for_frames(
                        pipeline, librealsense.RS2_DEFAULT_TIMEOUT(), e.get_rs2_error());
        e.verify();
        if (frame == null)
            throw new RuntimeException("Received null frame. Make sure pipeline is started.");
        var res = new FrameSet(new RealSenseFrame(frame));
        LOG.exiting("waitForFrames");
        return res;
    }

    public Optional<FrameSet> pollForFrames() {
        LOG.entering("pollForFrames");
        var e = new RealSenseError();
        var outputPtr =
                MemorySegment.allocateNative(ValueLayout.ADDRESS.byteSize(), SegmentScope.auto());
        var ret = librealsense.rs2_pipeline_poll_for_frames(pipeline, outputPtr, e.get_rs2_error());
        e.verify();
        if (ret == 0) return Optional.empty();
        var output = outputPtr.get(ValueLayout.ADDRESS, 0);
        var res = Optional.of(new FrameSet(new RealSenseFrame(output)));
        LOG.exiting("pollForFrames");
        return res;
    }

    @Override
    public void close() {
        LOG.entering("close");
        stop();
        librealsense.rs2_delete_pipeline(pipeline);
        LOG.exiting("close");
    }
}
