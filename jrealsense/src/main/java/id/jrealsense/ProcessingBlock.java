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

/**
 * @author lambdaprime intid@protonmail.com
 */
public class ProcessingBlock implements AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(ProcessingBlock.class);
    private MemorySegment block;

    public ProcessingBlock(MemorySegment block) {
        this.block = block;
    }

    public MemorySegment get_rs2_processing_block() {
        return block;
    }

    public void process(RealSenseFrame frame) {
        LOG.entering("process");
        var e = new RealSenseError();
        // ownership is moved to the block object
        frame.setIgnoreOnClose(true);
        librealsense.rs2_process_frame(block, frame.get_rs2_frame(), e.get_rs2_error());
        e.verify();
        LOG.exiting("process");
    }

    @Override
    public void close() {
        LOG.entering("close");
        librealsense.rs2_delete_processing_block(block);
        LOG.exiting("close");
    }
}
