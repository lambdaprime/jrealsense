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
/*
 * Authors:
 * - lambdaprime <intid@protonmail.com>
 */
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
