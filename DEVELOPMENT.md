Intended for **jrealsense** developers and not users.

# Prereq

[RealSense](https://github.com/IntelRealSense/librealsense/blob/master/doc/distribution_linux.md)

Install following packages:

```bash
apt -y install cmake swig
```

# Build

First you need to build JNI library and generate Java wrapper classes for it:

```bash
export JAVA_HOME=<PATH_TO_JAVA>
mkdir jrealsense/bld
cd jrealsense/bld/
cmake -DJAVA_HEADERS=$JAVA_HOME/include\;$JAVA_HOME/include/linux ..
make
```

Now build **jrealsense**:


```bash
cd ../..
gradle clean build
```
