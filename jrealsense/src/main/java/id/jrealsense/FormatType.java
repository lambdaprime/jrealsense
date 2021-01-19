package id.jrealsense;

import static id.jrealsense.librealsense2.rs2_get_stream_profile_data;

import java.util.HashMap;
import java.util.Map;

public class FormatType {

    private rs2_format format;

    private FormatType(rs2_format format) {
        this.format = format;
    }

    public rs2_format get_rs2_format() {
        return format;
    }

    public final static FormatType RS2_FORMAT_ANY = new FormatType(rs2_format.RS2_FORMAT_ANY);
    public final static FormatType RS2_FORMAT_Z16 = new FormatType(rs2_format.RS2_FORMAT_Z16);
    public final static FormatType RS2_FORMAT_DISPARITY16 = new FormatType(rs2_format.RS2_FORMAT_DISPARITY16);
    public final static FormatType RS2_FORMAT_XYZ32F = new FormatType(rs2_format.RS2_FORMAT_XYZ32F);
    public final static FormatType RS2_FORMAT_YUYV = new FormatType(rs2_format.RS2_FORMAT_YUYV);
    public final static FormatType RS2_FORMAT_RGB8 = new FormatType(rs2_format.RS2_FORMAT_RGB8);
    public final static FormatType RS2_FORMAT_BGR8 = new FormatType(rs2_format.RS2_FORMAT_BGR8);
    public final static FormatType RS2_FORMAT_RGBA8 = new FormatType(rs2_format.RS2_FORMAT_RGBA8);
    public final static FormatType RS2_FORMAT_BGRA8 = new FormatType(rs2_format.RS2_FORMAT_BGRA8);
    public final static FormatType RS2_FORMAT_Y8 = new FormatType(rs2_format.RS2_FORMAT_Y8);
    public final static FormatType RS2_FORMAT_Y16 = new FormatType(rs2_format.RS2_FORMAT_Y16);
    public final static FormatType RS2_FORMAT_RAW10 = new FormatType(rs2_format.RS2_FORMAT_RAW10);
    public final static FormatType RS2_FORMAT_RAW16 = new FormatType(rs2_format.RS2_FORMAT_RAW16);
    public final static FormatType RS2_FORMAT_RAW8 = new FormatType(rs2_format.RS2_FORMAT_RAW8);
    public final static FormatType RS2_FORMAT_UYVY = new FormatType(rs2_format.RS2_FORMAT_UYVY);
    public final static FormatType RS2_FORMAT_MOTION_RAW = new FormatType(rs2_format.RS2_FORMAT_MOTION_RAW);
    public final static FormatType RS2_FORMAT_MOTION_XYZ32F = new FormatType(rs2_format.RS2_FORMAT_MOTION_XYZ32F);
    public final static FormatType RS2_FORMAT_GPIO_RAW = new FormatType(rs2_format.RS2_FORMAT_GPIO_RAW);
    public final static FormatType RS2_FORMAT_6DOF = new FormatType(rs2_format.RS2_FORMAT_6DOF);
    public final static FormatType RS2_FORMAT_DISPARITY32 = new FormatType(rs2_format.RS2_FORMAT_DISPARITY32);
    public final static FormatType RS2_FORMAT_Y10BPACK = new FormatType(rs2_format.RS2_FORMAT_Y10BPACK);
    public final static FormatType RS2_FORMAT_DISTANCE = new FormatType(rs2_format.RS2_FORMAT_DISTANCE);
    public final static FormatType RS2_FORMAT_MJPEG = new FormatType(rs2_format.RS2_FORMAT_MJPEG);
    public final static FormatType RS2_FORMAT_Y8I = new FormatType(rs2_format.RS2_FORMAT_Y8I);
    public final static FormatType RS2_FORMAT_Y12I = new FormatType(rs2_format.RS2_FORMAT_Y12I);
    public final static FormatType RS2_FORMAT_INZI = new FormatType(rs2_format.RS2_FORMAT_INZI);
    public final static FormatType RS2_FORMAT_INVI = new FormatType(rs2_format.RS2_FORMAT_INVI);
    public final static FormatType RS2_FORMAT_W10 = new FormatType(rs2_format.RS2_FORMAT_W10);
    public final static FormatType RS2_FORMAT_Z16H = new FormatType(rs2_format.RS2_FORMAT_Z16H);
    public final static FormatType RS2_FORMAT_FG = new FormatType(rs2_format.RS2_FORMAT_FG);
    public final static FormatType RS2_FORMAT_COUNT = new FormatType(rs2_format.RS2_FORMAT_COUNT);

    public static Map<Integer, FormatType> SWIG_VALUES = createSwigValuesMap();

    public static FormatType valueOf(int formatType) {
        var r = SWIG_VALUES.get(formatType);
        if (r == null)
            throw new RealSenseException("Format type with id %d not found", formatType);
        return r;
    }

    private static Map<Integer, FormatType> createSwigValuesMap() {
        var m = new HashMap<Integer, FormatType>();
        m.put(rs2_format.RS2_FORMAT_ANY.swigValue(), RS2_FORMAT_ANY);
        m.put(rs2_format.RS2_FORMAT_Z16.swigValue(), RS2_FORMAT_Z16);
        m.put(rs2_format.RS2_FORMAT_DISPARITY16.swigValue(), RS2_FORMAT_DISPARITY16);
        m.put(rs2_format.RS2_FORMAT_XYZ32F.swigValue(), RS2_FORMAT_XYZ32F);
        m.put(rs2_format.RS2_FORMAT_YUYV.swigValue(), RS2_FORMAT_YUYV);
        m.put(rs2_format.RS2_FORMAT_RGB8.swigValue(), RS2_FORMAT_RGB8);
        m.put(rs2_format.RS2_FORMAT_BGR8.swigValue(), RS2_FORMAT_BGR8);
        m.put(rs2_format.RS2_FORMAT_RGBA8.swigValue(), RS2_FORMAT_RGBA8);
        m.put(rs2_format.RS2_FORMAT_BGRA8.swigValue(), RS2_FORMAT_BGRA8);
        m.put(rs2_format.RS2_FORMAT_Y8.swigValue(), RS2_FORMAT_Y8);
        m.put(rs2_format.RS2_FORMAT_Y16.swigValue(), RS2_FORMAT_Y16);
        m.put(rs2_format.RS2_FORMAT_RAW10.swigValue(), RS2_FORMAT_RAW10);
        m.put(rs2_format.RS2_FORMAT_RAW16.swigValue(), RS2_FORMAT_RAW16);
        m.put(rs2_format.RS2_FORMAT_RAW8.swigValue(), RS2_FORMAT_RAW8);
        m.put(rs2_format.RS2_FORMAT_UYVY.swigValue(), RS2_FORMAT_UYVY);
        m.put(rs2_format.RS2_FORMAT_MOTION_RAW.swigValue(), RS2_FORMAT_MOTION_RAW);
        m.put(rs2_format.RS2_FORMAT_MOTION_XYZ32F.swigValue(), RS2_FORMAT_MOTION_XYZ32F);
        m.put(rs2_format.RS2_FORMAT_GPIO_RAW.swigValue(), RS2_FORMAT_GPIO_RAW);
        m.put(rs2_format.RS2_FORMAT_6DOF.swigValue(), RS2_FORMAT_6DOF);
        m.put(rs2_format.RS2_FORMAT_DISPARITY32.swigValue(), RS2_FORMAT_DISPARITY32);
        m.put(rs2_format.RS2_FORMAT_Y10BPACK.swigValue(), RS2_FORMAT_Y10BPACK);
        m.put(rs2_format.RS2_FORMAT_DISTANCE.swigValue(), RS2_FORMAT_DISTANCE);
        m.put(rs2_format.RS2_FORMAT_MJPEG.swigValue(), RS2_FORMAT_MJPEG);
        m.put(rs2_format.RS2_FORMAT_Y8I.swigValue(), RS2_FORMAT_Y8I);
        m.put(rs2_format.RS2_FORMAT_Y12I.swigValue(), RS2_FORMAT_Y12I);
        m.put(rs2_format.RS2_FORMAT_INZI.swigValue(), RS2_FORMAT_INZI);
        m.put(rs2_format.RS2_FORMAT_INVI.swigValue(), RS2_FORMAT_INVI);
        m.put(rs2_format.RS2_FORMAT_W10.swigValue(), RS2_FORMAT_W10);
        m.put(rs2_format.RS2_FORMAT_Z16H.swigValue(), RS2_FORMAT_Z16H);
        m.put(rs2_format.RS2_FORMAT_FG.swigValue(), RS2_FORMAT_FG);
        m.put(rs2_format.RS2_FORMAT_COUNT.swigValue(), RS2_FORMAT_COUNT);
        return m;
    }
}
