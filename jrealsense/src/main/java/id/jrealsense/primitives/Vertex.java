package id.jrealsense.primitives;

public class Vertex {

    public float x, y, z;

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public String toString() {
        return String.format("<%f; %f; %f>", x, y, z);
    }
}
