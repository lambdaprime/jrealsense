package id.jrealsense.jni;

/**
 * List of librealsense versions which are supported by jrealsense.
 * 
 * For each of this versions jrealsense provides a JNI library.
 * If for particular version of librealsense2 JNI library is missing
 * you would not be able to use jrealsense for it.
 * 
 */
public enum LibrealsenseVersion {

    v2_41("realsense2-jni-2.41"),
    v2_42("realsense2-jni-2.42");

    private String jniLibraryName;
    
    LibrealsenseVersion(String jniLibraryName) {
        this.jniLibraryName = jniLibraryName;
    }
    
    public String getJniLibraryName() {
        return jniLibraryName;
    }
}
