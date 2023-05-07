# Generating Java Foreign Function & Memory API bindings

```
cd src/gen/java
jextract --source -t id.jrealsense.jextract --header-class-name librealsense -I/usr/include/librealsense2 -lrealsense2  jrealsense.h
```
