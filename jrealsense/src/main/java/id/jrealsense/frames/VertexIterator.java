package id.jrealsense.frames;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Iterator;

import id.jrealsense.primitives.Vertex;

/**
 * Iterator over vertices in ByteBuffer
 */
public class VertexIterator implements Iterator<Vertex> {

    private FloatBuffer buf;

    public VertexIterator(ByteBuffer buf) {
        this.buf = buf.order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    @Override
    public boolean hasNext() {
        return buf.hasRemaining();
    }

    @Override
    public Vertex next() {
        var b = new float[3];
        buf.get(b);
        return new Vertex(b[0], b[1], b[2]);
    }
}
