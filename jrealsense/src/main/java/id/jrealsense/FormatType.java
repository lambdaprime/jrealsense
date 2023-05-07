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

import id.jrealsense.jextract.librealsense;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>librealsense wrapper for rs2_format.
 * 
 * @author lambdaprime intid@protonmail.com
 */
public enum FormatType {

    RS2_FORMAT_ANY(librealsense.RS2_FORMAT_ANY()),
    RS2_FORMAT_Z16(librealsense.RS2_FORMAT_Z16()),
    RS2_FORMAT_DISPARITY16(librealsense.RS2_FORMAT_DISPARITY16()),
    RS2_FORMAT_XYZ32F(librealsense.RS2_FORMAT_XYZ32F()),
    RS2_FORMAT_YUYV(librealsense.RS2_FORMAT_YUYV()),
    RS2_FORMAT_RGB8(librealsense.RS2_FORMAT_RGB8()),
    RS2_FORMAT_BGR8(librealsense.RS2_FORMAT_BGR8()),
    RS2_FORMAT_RGBA8(librealsense.RS2_FORMAT_RGBA8()),
    RS2_FORMAT_BGRA8(librealsense.RS2_FORMAT_BGRA8()),
    RS2_FORMAT_Y8(librealsense.RS2_FORMAT_Y8()),
    RS2_FORMAT_Y16(librealsense.RS2_FORMAT_Y16()),
    RS2_FORMAT_RAW10(librealsense.RS2_FORMAT_RAW10()),
    RS2_FORMAT_RAW16(librealsense.RS2_FORMAT_RAW16()),
    RS2_FORMAT_RAW8(librealsense.RS2_FORMAT_RAW8()),
    RS2_FORMAT_UYVY(librealsense.RS2_FORMAT_UYVY()),
    RS2_FORMAT_MOTION_RAW(librealsense.RS2_FORMAT_MOTION_RAW()),
    RS2_FORMAT_MOTION_XYZ32F(librealsense.RS2_FORMAT_MOTION_XYZ32F()),
    RS2_FORMAT_GPIO_RAW(librealsense.RS2_FORMAT_GPIO_RAW()),
    RS2_FORMAT_6DOF(librealsense.RS2_FORMAT_6DOF()),
    RS2_FORMAT_DISPARITY32(librealsense.RS2_FORMAT_DISPARITY32()),
    RS2_FORMAT_Y10BPACK(librealsense.RS2_FORMAT_Y10BPACK()),
    RS2_FORMAT_DISTANCE(librealsense.RS2_FORMAT_DISTANCE()),
    RS2_FORMAT_MJPEG(librealsense.RS2_FORMAT_MJPEG()),
    RS2_FORMAT_Y8I(librealsense.RS2_FORMAT_Y8I()),
    RS2_FORMAT_Y12I(librealsense.RS2_FORMAT_Y12I()),
    RS2_FORMAT_INZI(librealsense.RS2_FORMAT_INZI()),
    RS2_FORMAT_INVI(librealsense.RS2_FORMAT_INVI()),
    RS2_FORMAT_W10(librealsense.RS2_FORMAT_W10()),
    RS2_FORMAT_Z16H(librealsense.RS2_FORMAT_Z16H()),
    RS2_FORMAT_FG(librealsense.RS2_FORMAT_FG()),
    RS2_FORMAT_COUNT(librealsense.RS2_FORMAT_COUNT());

    private int value;

    FormatType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static Map<Integer, FormatType> SWIG_VALUES = createSwigValuesMap();
    
    public static FormatType valueOf(int formatType) {
        var r = SWIG_VALUES.get(formatType);
        if (r == null)
            throw new RealSenseException("Format type with id %d not found", formatType);
        return r;
    }

    private static Map<Integer, FormatType> createSwigValuesMap() {
        var m = new HashMap<Integer, FormatType>();
        m.put(RS2_FORMAT_ANY.getValue(), RS2_FORMAT_ANY);
        m.put(RS2_FORMAT_Z16.getValue(), RS2_FORMAT_Z16);
        m.put(RS2_FORMAT_DISPARITY16.getValue(), RS2_FORMAT_DISPARITY16);
        m.put(RS2_FORMAT_XYZ32F.getValue(), RS2_FORMAT_XYZ32F);
        m.put(RS2_FORMAT_YUYV.getValue(), RS2_FORMAT_YUYV);
        m.put(RS2_FORMAT_RGB8.getValue(), RS2_FORMAT_RGB8);
        m.put(RS2_FORMAT_BGR8.getValue(), RS2_FORMAT_BGR8);
        m.put(RS2_FORMAT_RGBA8.getValue(), RS2_FORMAT_RGBA8);
        m.put(RS2_FORMAT_BGRA8.getValue(), RS2_FORMAT_BGRA8);
        m.put(RS2_FORMAT_Y8.getValue(), RS2_FORMAT_Y8);
        m.put(RS2_FORMAT_Y16.getValue(), RS2_FORMAT_Y16);
        m.put(RS2_FORMAT_RAW10.getValue(), RS2_FORMAT_RAW10);
        m.put(RS2_FORMAT_RAW16.getValue(), RS2_FORMAT_RAW16);
        m.put(RS2_FORMAT_RAW8.getValue(), RS2_FORMAT_RAW8);
        m.put(RS2_FORMAT_UYVY.getValue(), RS2_FORMAT_UYVY);
        m.put(RS2_FORMAT_MOTION_RAW.getValue(), RS2_FORMAT_MOTION_RAW);
        m.put(RS2_FORMAT_MOTION_XYZ32F.getValue(), RS2_FORMAT_MOTION_XYZ32F);
        m.put(RS2_FORMAT_GPIO_RAW.getValue(), RS2_FORMAT_GPIO_RAW);
        m.put(RS2_FORMAT_6DOF.getValue(), RS2_FORMAT_6DOF);
        m.put(RS2_FORMAT_DISPARITY32.getValue(), RS2_FORMAT_DISPARITY32);
        m.put(RS2_FORMAT_Y10BPACK.getValue(), RS2_FORMAT_Y10BPACK);
        m.put(RS2_FORMAT_DISTANCE.getValue(), RS2_FORMAT_DISTANCE);
        m.put(RS2_FORMAT_MJPEG.getValue(), RS2_FORMAT_MJPEG);
        m.put(RS2_FORMAT_Y8I.getValue(), RS2_FORMAT_Y8I);
        m.put(RS2_FORMAT_Y12I.getValue(), RS2_FORMAT_Y12I);
        m.put(RS2_FORMAT_INZI.getValue(), RS2_FORMAT_INZI);
        m.put(RS2_FORMAT_INVI.getValue(), RS2_FORMAT_INVI);
        m.put(RS2_FORMAT_W10.getValue(), RS2_FORMAT_W10);
        m.put(RS2_FORMAT_Z16H.getValue(), RS2_FORMAT_Z16H);
        m.put(RS2_FORMAT_FG.getValue(), RS2_FORMAT_FG);
        m.put(RS2_FORMAT_COUNT.getValue(), RS2_FORMAT_COUNT);
        return m;
    }
}
