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
package id.jrealsense.filters;

import id.jrealsense.FrameQueue;
import id.jrealsense.FrameSet;
import id.jrealsense.ProcessingBlock;
import id.jrealsense.RealSenseError;
import id.jrealsense.StreamType;
import id.jrealsense.jextract.librealsense;

/**
 * Filter which aligns frames from different streams in {@link FrameSet}.
 *
 * <p>Most common use case is align depth frames to their corresponding color frames.
 *
 * @see <a href="https://dev.intelrealsense.com/docs/rs-align-advanced">rs-align-advanced</a>
 * @author lambdaprime intid@protonmail.com
 */
public class AlignFilter extends AbstractFilter<FrameSet, FrameSet> {

    public AlignFilter(ProcessingBlock block, FrameQueue queue) {
        super(block, queue);
    }

    @Override
    public FrameSet process(FrameSet frameSet) {
        getBlock().process(frameSet.getFrame());
        return getQueue().pollFrameSet();
    }

    /** Factory method, creates new {@link AlignFilter} */
    public static AlignFilter create(StreamType streamType) {
        var e = new RealSenseError();
        var block = librealsense.rs2_create_align(streamType.get_rs2_stream(), e.get_rs2_error());
        e.verify();
        var queue = FrameQueue.create(1);
        var ret = new AlignFilter(new ProcessingBlock(block), queue);
        ret.startQueue();
        return ret;
    }
}
