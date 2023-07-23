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
import java.nio.ByteBuffer;
import java.time.Instant;

/**
 * @author lambdaprime intid@protonmail.com
 */
public interface Frame<T extends Frame<T>> extends AutoCloseable {

    /** Stream profile to which this frame belongs */
    StreamProfile getProfile();

    /**
     * Some frames may contain multiple embedded frames. They are called composite frames (see
     * {@link id.jrealsense.frames.CompositeFrame}) This method returns number of embedded frames in
     * a composite or 0 For non composite frames it is 0
     */
    int embeddedFramesCount();

    /** Apply filter to the current frame. */
    <OUT extends Frame<?>> OUT apply(Filter<T, OUT> filter);

    RealSenseFrame getRealSenseFrame();

    /** Once frame is not needed anymore it must be released */
    @Override
    void close();

    /**
     * This methods wraps frame raw data as it returned by librealsense library into ByteBuffer and
     * returns it.
     */
    ByteBuffer getData();

    /** Frame number in milliseconds since the device was started */
    long getFrameNumber();

    /** Frame timestamp in milliseconds since epoc time */
    double getTimestamp();

    /** Frame timestamp */
    Instant getTimestampInstant();
}
