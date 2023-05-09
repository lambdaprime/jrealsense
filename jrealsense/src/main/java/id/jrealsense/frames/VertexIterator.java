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
package id.jrealsense.frames;

import id.jrealsense.primitives.Vertex;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Iterator;

/**
 * Iterator over vertices in ByteBuffer
 *
 * @author lambdaprime intid@protonmail.com
 */
public class VertexIterator implements Iterator<Vertex> {

    private FloatBuffer buf;
    private float[] b = new float[3];

    public VertexIterator(ByteBuffer buf) {
        this.buf = buf.order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    @Override
    public boolean hasNext() {
        return buf.hasRemaining();
    }

    @Override
    public Vertex next() {
        buf.get(b);
        return new Vertex(b[0], b[1], b[2]);
    }
}
