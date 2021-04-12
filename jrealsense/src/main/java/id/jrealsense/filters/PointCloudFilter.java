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
package id.jrealsense.filters;

import static id.jrealsense.jni.librealsense2.rs2_create_pointcloud;

import id.jrealsense.FrameQueue;
import id.jrealsense.ProcessingBlock;
import id.jrealsense.RealSenseErrorHolder;
import id.jrealsense.frames.DepthFrame;
import id.jrealsense.frames.PointCloudFrame;

/**
 * Filter which generates point cloud from depth frame.
 */
public class PointCloudFilter extends AbstractFilter<DepthFrame, PointCloudFrame> {

    public PointCloudFilter(ProcessingBlock block, FrameQueue queue) {
        super(block, queue);
    }

    @Override
    public PointCloudFrame process(DepthFrame frame) {
        getBlock().process(frame);
        return getQueue().poll(PointCloudFrame.class);
    }
    
    /**
     * Factory method, creates new {@link PointCloudFilter}
     */
    public static PointCloudFilter create() {
        var e = RealSenseErrorHolder.create();
        var block = rs2_create_pointcloud(e);
        e.verify();
        var queue = FrameQueue.create(1);
        var ret = new PointCloudFilter(new ProcessingBlock(block), queue);
        ret.startQueue();
        return ret;
    }

}
