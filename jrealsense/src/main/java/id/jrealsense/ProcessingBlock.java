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
import id.jrealsense.jni.rs2_processing_block;
import id.xfunction.logging.XLogger;

public class ProcessingBlock implements AutoCloseable {

    private static final XLogger LOG = XLogger.getLogger(ProcessingBlock.class);
    private rs2_processing_block block;
    
    public ProcessingBlock(rs2_processing_block block) {
        this.block = block;
    }

    public rs2_processing_block get_rs2_processing_block() {
        return block;
    }
    
    public void process(Frame<?> frame) {
        LOG.entering("process");
        var e = RealSenseErrorHolder.create();
        var realFrame = frame.getRealSenseFrame();
        realFrame.setIgnoreOnRelease(true);
        rs2_process_frame(block, realFrame.get_rs2_frame(), e);
        e.verify();
        LOG.exiting("process");
    }

    @Override
    public void close() {
        LOG.entering("close");
        rs2_delete_processing_block(block);
        LOG.exiting("close");
    }
}
