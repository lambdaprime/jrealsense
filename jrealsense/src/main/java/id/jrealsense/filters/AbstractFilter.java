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

import id.jrealsense.Filter;
import id.jrealsense.FrameQueue;
import id.jrealsense.ProcessingBlock;
import id.jrealsense.RealSenseError;
import id.jrealsense.jextract.librealsense;

/**
 * @author lambdaprime intid@protonmail.com
 */
abstract class AbstractFilter<IN extends FilterData, OUT extends FilterData>
        implements Filter<IN, OUT> {

    private ProcessingBlock block;
    private FrameQueue queue;

    protected AbstractFilter(ProcessingBlock block, FrameQueue queue) {
        this.block = block;
        this.queue = queue;
    }

    public void startQueue() {
        var e = new RealSenseError();
        librealsense.rs2_start_processing_queue(
                block.get_rs2_processing_block(), queue.get_rs2_frame_queue(), e.get_rs2_error());
        e.verify();
    }

    protected ProcessingBlock getBlock() {
        return block;
    }

    protected FrameQueue getQueue() {
        return queue;
    }

    @Override
    public void close() {
        block.close();
        queue.close();
    }
}
