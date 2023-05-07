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

import id.jrealsense.frames.RealSenseFrame;
import id.jrealsense.jextract.librealsense;
import id.xfunction.logging.XLogger;
import java.lang.foreign.MemorySegment;

public class Pipeline implements AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(FrameSet.class);
    private MemorySegment pipeline;

    protected Pipeline(MemorySegment pline) {
        this.pipeline = pline;
    }

    /**
     * Starts pipeline with default configuration
     */
    public void start() {
        LOG.entering("start");
        var e = new RealSenseError();
        librealsense.rs2_pipeline_start(pipeline, e.get_rs2_error());
        e.verify();
        LOG.exiting("start");
    }

    public void start(Config config) {
        LOG.entering("start");
        var e = new RealSenseError();
        librealsense.rs2_pipeline_start_with_config(pipeline, config.get_rs_config(), e.get_rs2_error());
        e.verify();
        LOG.exiting("start");
    }
    
    public void stop() {
        LOG.entering("stop");
        var e = new RealSenseError();
        librealsense.rs2_pipeline_stop(pipeline, e.get_rs2_error());
        e.verify();
        LOG.exiting("stop");
    }
    
    /**
     * Factory method, creates new {@link Pipeline}
     */
    public static Pipeline create(Context ctx) {
        var e = new RealSenseError();
        var pline = librealsense.rs2_create_pipeline(ctx.get_rs2_context(), e.get_rs2_error());
        e.verify();
        return new Pipeline(pline);
    }

    /**
     * Wait for next set of frames from the camera
     */
    public FrameSet waitForFrames() {
        LOG.entering("waitForFrames");
        var e = new RealSenseError();
        var frame = librealsense.rs2_pipeline_wait_for_frames(pipeline, librealsense.RS2_DEFAULT_TIMEOUT(), e.get_rs2_error());
        e.verify();
        if (frame == null)
            throw new RuntimeException("Received null frame. Make sure pipeline is started.");
        var res = new FrameSet(new RealSenseFrame(frame));
        LOG.exiting("waitForFrames");
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
