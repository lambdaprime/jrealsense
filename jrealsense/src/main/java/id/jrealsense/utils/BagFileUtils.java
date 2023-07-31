/*
 * Copyright 2023 jrealsense project
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
package id.jrealsense.utils;

import id.jrealsense.Config;
import id.jrealsense.Context;
import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.Pipeline;
import id.jrealsense.PlaybackStatus;
import id.jrealsense.StreamType;
import java.nio.file.Path;
import java.time.Duration;

/**
 * Utilities to process bag files.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class BagFileUtils {

    public record StreamSelector(
            StreamType streamType, FormatType formatType, int width, int height) {}

    /**
     * Consume frames from bag file.
     *
     * @author lambdaprime intid@protonmail.com
     */
    @FunctionalInterface
    public interface FrameConsumer {

        /**
         * @return false to stop processing bag file. After that no more frames will be sent to the
         *     consumer.
         */
        boolean accept(FrameSet frameSet);
    }

    /**
     * Plays given bag file and count total number of frames.
     *
     * <p>Unfortunately there is no API to get it directly, possibly because it is not part of bag
     * metadata.
     *
     * @param streamSelector allows to specify frames from which stream to count (color, depth,
     *     etc). If empty counts all.
     * @see <a href="https://github.com/IntelRealSense/librealsense/issues/1887">How to get the
     *     depth frames from recorded .bag file using pyrealsense2?</a>
     */
    public int countNumberOfFrames(Path bagFile, StreamSelector streamSelector) {
        var c = new int[1];
        replay(
                bagFile,
                fs -> {
                    c[0]++;
                    return true;
                },
                streamSelector);
        return c[0];
    }

    /**
     * Replay frames from a bag file to the given consumer.
     *
     * @param streamSelector allows to specify frames from which stream to replay (color, depth,
     *     etc). If empty replays all.
     */
    public void replay(Path bagFile, FrameConsumer frameSetConsumer, StreamSelector... selectors) {
        try (var ctx = Context.create();
                var pipeline = Pipeline.create(ctx);
                var config = Config.create(ctx); ) {
            config.enableDeviceFromFile(bagFile, false);
            for (var selector : selectors)
                config.enableStream(
                        selector.streamType,
                        0,
                        selector.width,
                        selector.height,
                        selector.formatType,
                        30);
            try (var profile = pipeline.start(config);
                    var device = profile.getPlaybackDevice()) {
                device.pause();
                device.setRealTime(false);
                device.seek(Duration.ZERO);
                device.resume();
                while (device.getCurrentStatus() == PlaybackStatus.RS2_PLAYBACK_STATUS_PLAYING) {
                    try (var data = pipeline.pollForFrames().orElse(null)) {
                        if (data == null) continue;
                        if (!frameSetConsumer.accept(data)) break;
                    }
                }
            }
        }
    }
}
