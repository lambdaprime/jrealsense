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
package id.jrealsense.primitives;

/**
 * @author lambdaprime intid@protonmail.com
 */
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
