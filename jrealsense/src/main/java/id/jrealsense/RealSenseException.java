package id.jrealsense;

public class RealSenseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RealSenseException() {
        super();
    }
    
    public RealSenseException(String message) {
        super(message);
    }

    public RealSenseException(String fmt, Object...objs) {
        super(String.format(fmt, objs));
    }
    
    public RealSenseException(Exception e) {
        super(e);
    }
}
