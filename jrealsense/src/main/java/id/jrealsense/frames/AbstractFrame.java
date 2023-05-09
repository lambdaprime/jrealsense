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

import id.jrealsense.Filter;
import id.jrealsense.StreamProfile;
import id.xfunction.logging.XLogger;
import java.nio.ByteBuffer;

/**
 * @author lambdaprime intid@protonmail.com
 */
abstract class AbstractFrame<F extends Frame<F>> implements Frame<F> {

    private RealSenseFrame frame;

    protected AbstractFrame(RealSenseFrame frame) {
        this.frame = frame;
    }

    protected abstract XLogger log();

    @Override
    public StreamProfile getProfile() {
        return frame.getProfile();
    }

    @Override
    public <OUT extends Frame<?>> OUT apply(Filter<F, OUT> filter) {
        @SuppressWarnings("unchecked")
        var ret = filter.process((F) this);
        return ret;
    }

    @Override
    public int embeddedFramesCount() {
        return frame.embeddedFramesCount();
    }

    @Override
    public RealSenseFrame getRealSenseFrame() {
        return frame;
    }

    @Override
    public ByteBuffer getData() {
        return frame.getData();
    }

    @Override
    public void close() {
        log().entering("close");
        frame.close();
        log().exiting("close");
    }

    @Override
    public long getFrameNumber() {
        return frame.getFrameNumber();
    }

    @Override
    public double getTimestamp() {
        return frame.getTimestamp();
    }
}
