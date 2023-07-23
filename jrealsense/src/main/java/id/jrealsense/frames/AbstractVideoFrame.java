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

/**
 * @author lambdaprime intid@protonmail.com
 */
abstract class AbstractVideoFrame<F extends VideoFrame<F>> extends AbstractFrame<F>
        implements VideoFrame<F> {

    protected AbstractVideoFrame(RealSenseFrame frame) {
        super(frame);
    }

    @Override
    public int getWidth() {
        return getRealSenseFrame().getWidth();
    }

    @Override
    public int getHeight() {
        return getRealSenseFrame().getHeight();
    }

    @Override
    public int getStride() {
        return getRealSenseFrame().getStride();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "[Width: "
                + getWidth()
                + ", Height: "
                + getHeight()
                + ", Frame number: "
                + getFrameNumber()
                + ", Timestamp: "
                + getTimestampInstant()
                + "]";
    }
}
