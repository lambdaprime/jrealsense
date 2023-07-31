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

import id.jrealsense.FormatType;
import id.jrealsense.FrameSet;
import id.jrealsense.StreamType;
import id.jrealsense.exceptions.JRealSenseException;
import id.jrealsense.filters.AlignFilter;
import id.jrealsense.filters.Colorizer;
import id.jrealsense.frames.ColorFrame;
import id.jrealsense.frames.DepthFrame;
import id.jrealsense.frames.VideoFrame;
import id.xfunction.Preconditions;
import id.xfunction.XByte;
import id.xfunction.logging.XLogger;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class FrameUtils implements AutoCloseable {
    private static final XLogger LOGGER = XLogger.getLogger(FrameUtils.class);
    private static final Map<FormatType, Integer> bufferedImageTypeMap =
            Map.of(
                    FormatType.RS2_FORMAT_BGR8, BufferedImage.TYPE_3BYTE_BGR,

                    // support for images produced by colorizer
                    FormatType.RS2_FORMAT_RGB8, BufferedImage.TYPE_INT_RGB,
                    FormatType.RS2_FORMAT_Z16, BufferedImage.TYPE_USHORT_GRAY);

    private Optional<Colorizer> colorMap = Optional.empty();
    private Optional<AlignFilter> aligner = Optional.empty();

    /** Create Java {@link BufferedImage} from {@link VideoFrame} */
    public BufferedImage toBufferedImage(VideoFrame<?> frame, FormatType frameFormatType) {
        var type = bufferedImageTypeMap.get(frameFormatType);
        if (type == null)
            throw new JRealSenseException(
                    "Format "
                            + frameFormatType
                            + " is not supported. Supported formats are "
                            + bufferedImageTypeMap.keySet());
        var image = new BufferedImage(frame.getWidth(), frame.getHeight(), type);
        var imageBuffer = image.getRaster().getDataBuffer();
        var frameData = frame.getCopyOfData();
        LOGGER.info("Frame size in bytes {0}", frameData.capacity());
        if (imageBuffer instanceof DataBufferByte byteBuffer) {
            var targetPixels = byteBuffer.getData();
            frameData.get(targetPixels);
        } else if (imageBuffer instanceof DataBufferInt intBuffer) {
            Preconditions.isTrue(frameFormatType == FormatType.RS2_FORMAT_RGB8);
            var targetPixels = intBuffer.getData();
            for (int i = 0; i < targetPixels.length; i++) {
                var j = i * 3;
                targetPixels[i] =
                        XByte.toInt(
                                0, frameData.get(j), frameData.get(j + 1), frameData.get(j + 2));
            }
        } else if (imageBuffer instanceof DataBufferUShort shortBuffer) {
            Preconditions.isTrue(frameFormatType == FormatType.RS2_FORMAT_Z16);
            var data = frameData.asShortBuffer();
            var targetPixels = shortBuffer.getData();
            for (int i = 0; i < targetPixels.length; i++) {
                // PNG is big-endian, Z16 is little-endian
                targetPixels[i] = Short.reverseBytes(data.get(i));
            }
        } else {
            throw new JRealSenseException(
                    "Non supported type " + imageBuffer.getClass().getSimpleName());
        }
        return image;
    }

    /** Save {@link VideoFrame} as PNG file */
    public void saveAsPng(VideoFrame<?> frame, FormatType frameFormatType, Path pngFile) {
        LOGGER.info("Saving frame {0} to {1}", frame, pngFile.toAbsolutePath());
        var img = toBufferedImage(frame, frameFormatType);
        try {
            ImageIO.write(img, "png", pngFile.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Converts depth frame to colorized RS2_FORMAT_RGB8 frame. */
    public ColorFrame colorize(DepthFrame depthFrame) {
        if (colorMap.isEmpty()) colorMap = Optional.of(Colorizer.create());
        LOGGER.info("Input frame is a depth frame, applying colorizer");
        return colorMap.get().process(depthFrame);
    }

    /**
     * Align frames to their corresponding color frames in frame set. This is needed mainly for
     * depth frames as they may be misaligned.
     */
    public FrameSet alignToColorStream(FrameSet frameSet) {
        if (aligner.isEmpty())
            aligner = Optional.of(AlignFilter.create(StreamType.RS2_STREAM_COLOR));
        return aligner.get().process(frameSet);
    }

    @Override
    public void close() {
        colorMap.ifPresent(Colorizer::close);
    }
}
