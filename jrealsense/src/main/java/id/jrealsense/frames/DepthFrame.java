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
import id.jrealsense.jextract.librealsense;
import id.xfunction.logging.XLogger;

/**
 * Frame with depth information
 *
 * @author lambdaprime intid@protonmail.com
 */
public class DepthFrame extends AbstractVideoFrame<DepthFrame> {

    private static final XLogger LOG = XLogger.getLogger(DepthFrame.class);

    public DepthFrame(RealSenseFrame frame) {
        super(frame);
    }

    @Override
    public int embeddedFramesCount() {
        return 0;
    }

    /** Distance from the camera to the object in the center of the frame */
    public float getDistance() {
        int w = getWidth();
        int h = getHeight();
        return getDistance(w / 2, h / 2);
    }

    /** Distance from the camera to the point with given coordinates */
    public float getDistance(int x, int y) {
        var e = new RealSenseError();
        float r =
                librealsense.rs2_depth_frame_get_distance(
                        getRealSenseFrame().get_rs2_frame(), x, y, e.get_rs2_error());
        e.verify();
        return r;
    }

    @Override
    protected XLogger log() {
        return LOG;
    }
}
