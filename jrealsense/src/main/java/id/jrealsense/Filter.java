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
package id.jrealsense;

import id.jrealsense.filters.FilterData;

/**
 * It is implementation details of the filter how to do the processing.
 *
 * <p>Standard way is to ask processing block to process the frame and poll the processed frame from
 * internal queue once it is ready.
 *
 * @author lambdaprime intid@protonmail.com
 */
public interface Filter<IN extends FilterData, OUT extends FilterData> extends AutoCloseable {

    /** Apply current filter to the given frame. */
    OUT process(IN frame);
}
