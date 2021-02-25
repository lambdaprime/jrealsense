package id.jrealsense.frames;

import java.nio.ByteBuffer;
import java.util.Iterator;

import id.jrealsense.primitives.Vertex;

/**
 * Provides access to vertices in point cloud.
 * They are usually mapped into ByteBuffer which in turn
 * mapped over data what librealsense returns to the user.
 */
public class VertexAccessor implements Iterable<Vertex> {

    private ByteBuffer buf;

    public VertexAccessor(ByteBuffer buf) {
        this.buf = buf;
    }

    @Override
    public Iterator<Vertex> iterator() {
        return new VertexIterator(buf);
    }

}
