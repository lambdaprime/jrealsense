cmake_minimum_required(VERSION 3.19.3)
project(librealsense2-jni)

find_package(SWIG REQUIRED)

include(UseSWIG)

SET(LIBREALSENSE2_HEADERS "/usr/include/librealsense2" "/usr/include/librealsense2/h")
SET(LIBREALSENSE2_LIBS "/usr/lib/x86_64-linux-gnu")
SET(JAVA_HEADERS "/home/lynx/opt/jdk-11.0.9/include" "/home/lynx/opt/jdk-11.0.9/include/linux")
SET(SWIG_JAVA_OUTPUT "${CMAKE_CURRENT_SOURCE_DIR}/src/gen/java/id/jrealsense/jni")
SET(SWIG_NATIVE_OUTPUT "${CMAKE_CURRENT_SOURCE_DIR}/src/gen/native")

include_directories(
  ${LIBREALSENSE2_HEADERS}
  ${JAVA_HEADERS}
)

link_directories(
  ${LIBREALSENSE2_LIBS}
)

set(CMAKE_SWIG_FLAGS -package "id.jrealsense.jni")

swig_add_library(realsense2-jni
  LANGUAGE java
  OUTPUT_DIR "${SWIG_JAVA_OUTPUT}"
  OUTFILE_DIR "${SWIG_NATIVE_OUTPUT}"
  SOURCES "src/main/swig/librealsense2.i"
)

swig_link_libraries(realsense2-jni
  realsense2)

set_property(DIRECTORY PROPERTY ADDITIONAL_MAKE_CLEAN_FILES
   "${CMAKE_CURRENT_SOURCE_DIR}/src/gen")
