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
package id.jrealsense.devices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This is an implementation for list of devices List&lt;Device&gt;.
 *
 * <p>It owns all {@link Device} objects which added into it and closes them once list itself is
 * closed in order to release the resources acquired by each {@link Device} object.
 *
 * <p>To get control over {@link Device} object lifetime it needs to be removed from the list.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class DeviceList implements List<Device>, AutoCloseable {

    private List<Device> devices = new ArrayList<Device>();

    @Override
    public int size() {
        return devices.size();
    }

    @Override
    public boolean isEmpty() {
        return devices.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return devices.contains(o);
    }

    @Override
    public Iterator<Device> iterator() {
        return devices.iterator();
    }

    @Override
    public Object[] toArray() {
        return devices.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return devices.toArray(a);
    }

    @Override
    public boolean add(Device e) {
        return devices.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return devices.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return devices.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Device> c) {
        return devices.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Device> c) {
        return devices.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return devices.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return devices.retainAll(c);
    }

    @Override
    public void clear() {
        close();
    }

    @Override
    public Device get(int index) {
        return devices.get(index);
    }

    @Override
    public Device set(int index, Device element) {
        return devices.set(index, element);
    }

    @Override
    public void add(int index, Device element) {
        devices.add(index, element);
    }

    @Override
    public Device remove(int index) {
        return devices.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return devices.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return devices.lastIndexOf(o);
    }

    @Override
    public ListIterator<Device> listIterator() {
        return devices.listIterator();
    }

    @Override
    public ListIterator<Device> listIterator(int index) {
        return devices.listIterator(index);
    }

    @Override
    public List<Device> subList(int fromIndex, int toIndex) {
        return devices.subList(fromIndex, toIndex);
    }

    /** Release the resources. Once closed all further operations are invalid. */
    @Override
    public void close() {
        devices.forEach(Device::close);
        devices.clear();
        devices = null;
    }
}
