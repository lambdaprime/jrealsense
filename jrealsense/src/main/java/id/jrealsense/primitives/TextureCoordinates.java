package id.jrealsense.primitives;

public class TextureCoordinates {

    public float u, v;

    public TextureCoordinates(float u, float v) {
        this.u = u;
        this.v = v;
    }
    
    @Override
    public String toString() {
        return String.format("<%f; %f; %f>", u, v);
    }
}
