package id.jrealsense.frames;

abstract class AbstractVideoFrame<F extends VideoFrame<F>> extends AbstractFrame<F>
    implements VideoFrame<F>
{
    
    protected AbstractVideoFrame(RealSenseFrame frame) {
        super(frame);
    }

    @Override
    public int getWidth() {
        return getRealSenseFrame().getWidth();
    }

    @Override
    public int getHeight() {
        return getRealSenseFrame().getHeight();
    }

    @Override
    public int getStride() {
        return getRealSenseFrame().getStride();
    }

}
