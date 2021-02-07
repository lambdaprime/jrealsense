package id.jrealsense;

/**
 * Generic runtime exception for all <b>jrealsense</b> operations.
 */
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
