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
package id.jrealsense.app;

import id.jrealsense.FormatType;
import id.jrealsense.Sensor;
import id.jrealsense.StreamType;
import id.jrealsense.utils.BagFileUtils;
import id.jrealsense.utils.BagFileUtils.StreamSelector;
import id.jrealsense.utils.FrameUtils;
import id.xfunction.cli.CommandLineInterface;
import id.xfunction.function.Unchecked;
import id.xfunction.nio.file.XFiles;
import id.xfunction.nio.file.XPaths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Save frames from bag files as RGBD images.
 *
 * @see SaveFramesFromBagFileApp#USAGE
 * @author lambdaprime intid@protonmail.com
 */
public class SaveFramesFromBagFileApp {
    public static final String USAGE =
            """
            Save frames from bag files as aligned RGBD images. It includes camera intrinsics too (which was used to record the video)

            Website: https://github.com/lambdaprime/jrealsense

            One option to record video and save it into bag files is realsense-viewer app.
            realsense-viewer allows to play recorded bag files back too.

            Each RGBD image is a pair of files: FRAME_NUM-rgb.png, FRAME_NUM-depth.png (grayscale 16bit)

            It supports ONLY bag files which contain video in the following format:
            - resolution 640x480
            - fps 30
            - RS2_STREAM_COLOR stream with RS2_FORMAT_BGR8 format
            - RS2_STREAM_DEPTH stream with RS2_FORMAT_Z16 format
            These parameters can be configured with realsense-viewer app before video is recorded.

            Usage: <BAG_FILE_PATH> <STEPS_COUNT>

            Where:

            - BAG_FILE_PATH -- path to input bag file
            - STEPS_COUNT -- specifies which frames to save. Saves each STEPS_COUNT frame.

            Useful links:
            - Capture your own dataset (http://www.open3d.org/docs/release/tutorial/reconstruction_system/capture_your_own_dataset.html#capture-your-own-dataset)
            """;
    private static final CommandLineInterface cli = new CommandLineInterface();
    private static final FrameUtils utils = new FrameUtils();

    /** Setup resources and run the looper */
    private void run(Path bagFile, int stepCount) {
        BagFileUtils bagUtils = new BagFileUtils();
        var rgbFormat = FormatType.RS2_FORMAT_BGR8;
        var rgbSelector = new StreamSelector(StreamType.RS2_STREAM_COLOR, rgbFormat, 640, 480);
        var depthFormat = FormatType.RS2_FORMAT_Z16;
        var depthSelector = new StreamSelector(StreamType.RS2_STREAM_DEPTH, depthFormat, 640, 480);

        var c = bagUtils.countNumberOfFrames(bagFile, rgbSelector);
        cli.print("Total number of frames in bag file %s", c);
        var outputDir =
                bagFile.resolveSibling(
                        XPaths.splitFileName(bagFile.getFileName().toString())[0] + ".rgbd");
        Unchecked.run(
                () -> {
                    XFiles.deleteRecursively(outputDir);
                    Files.createDirectories(outputDir);
                });
        var hasSavedIntrinsics = new boolean[2];
        bagUtils.replay(
                bagFile,
                fs -> {
                    try {
                        if (fs.size() == 0) return true;
                        fs = utils.alignToColorStream(fs);
                        fs.getColorFrame(rgbFormat)
                                .ifPresent(
                                        frame -> {
                                            if (!hasSavedIntrinsics[0]) {
                                                Unchecked.run(
                                                        () ->
                                                                new Sensor()
                                                                        .getVideoStreamIntrinsics(
                                                                                frame.getProfile())
                                                                        .saveAsOpen3DJson(
                                                                                outputDir.resolve(
                                                                                        "intrinsics-rgb.json")));
                                                hasSavedIntrinsics[0] = true;
                                            }
                                            var frameNum = frame.getFrameNumber();
                                            if (frameNum % stepCount != 0) return;
                                            utils.saveAsPng(
                                                    frame,
                                                    rgbFormat,
                                                    outputDir.resolve(frameNum + "-rgb.png"));
                                        });
                        fs.getDepthFrame()
                                .ifPresent(
                                        depthFrame -> {
                                            if (!hasSavedIntrinsics[1]) {
                                                Unchecked.run(
                                                        () ->
                                                                new Sensor()
                                                                        .getVideoStreamIntrinsics(
                                                                                depthFrame
                                                                                        .getProfile())
                                                                        .saveAsOpen3DJson(
                                                                                outputDir.resolve(
                                                                                        "intrinsics-depth.json")));
                                                hasSavedIntrinsics[1] = true;
                                            }
                                            var frameNum = depthFrame.getFrameNumber();
                                            if (frameNum % stepCount != 0) return;
                                            utils.saveAsPng(
                                                    depthFrame,
                                                    depthFormat,
                                                    outputDir.resolve(frameNum + "-depth.png"));
                                        });
                        return true;
                    } finally {
                        fs.close();
                    }
                },
                rgbSelector,
                depthSelector);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            cli.print(USAGE);
            return;
        }
        new SaveFramesFromBagFileApp().run(Paths.get(args[0]), Integer.parseInt(args[1]));
    }
}
