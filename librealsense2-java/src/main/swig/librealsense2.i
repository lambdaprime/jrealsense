%module librealsense2

SWIG_JAVABODY_PROXY(public, public, SWIGTYPE)
SWIG_JAVABODY_TYPEWRAPPER(public, public, public, SWIGTYPE)
  
 //%include "cpointer.i"
 //%pointer_functions(rs2_error, rs2_error_p)

%{
struct rs2_error {};
struct rs2_device_info {};
struct rs2_device {};
struct rs2_log_message {};
struct rs2_raw_data_buffer {};
struct rs2_frame {};
struct rs2_frame_queue {};
struct rs2_pipeline {};
struct rs2_pipeline_profile {};
struct rs2_config {};
struct rs2_device_list {};
struct rs2_stream_profile_list {};
struct rs2_processing_block_list {};
struct rs2_stream_profile {};
struct rs2_frame_callback {};
struct rs2_log_callback {};
struct rs2_syncer {};
struct rs2_device_serializer {};
struct rs2_source {};
struct rs2_processing_block {};
struct rs2_frame_processor_callback {};
struct rs2_playback_status_changed_callback {};
struct rs2_update_progress_callback {};
struct rs2_context {};
struct rs2_device_hub {};
struct rs2_sensor_list {};
struct rs2_sensor {};
struct rs2_options {};
struct rs2_options_list {};
struct rs2_devices_changed_callback {};
struct rs2_notification {};
struct rs2_notifications_callback {};
struct rs2_firmware_log_message {};
struct rs2_firmware_log_parsed_message {};
struct rs2_firmware_log_parser {};
struct rs2_terminal_parser {};
%}
struct rs2_error {};
struct rs2_device_info {};
struct rs2_device {};
struct rs2_log_message {};
struct rs2_raw_data_buffer {};
struct rs2_frame {};
struct rs2_frame_queue {};
struct rs2_pipeline {};
struct rs2_pipeline_profile {};
struct rs2_config {};
struct rs2_device_list {};
struct rs2_stream_profile_list {};
struct rs2_processing_block_list {};
struct rs2_stream_profile {};
struct rs2_frame_callback {};
struct rs2_log_callback {};
struct rs2_syncer {};
struct rs2_device_serializer {};
struct rs2_source {};
struct rs2_processing_block {};
struct rs2_frame_processor_callback {};
struct rs2_playback_status_changed_callback {};
struct rs2_update_progress_callback {};
struct rs2_context {};
struct rs2_device_hub {};
struct rs2_sensor_list {};
struct rs2_sensor {};
struct rs2_options {};
struct rs2_options_list {};
struct rs2_devices_changed_callback {};
struct rs2_notification {};
struct rs2_notifications_callback {};
struct rs2_firmware_log_message {};
struct rs2_firmware_log_parsed_message {};
struct rs2_firmware_log_parser {};
struct rs2_terminal_parser {};

%{
#include "rs_types.h"
%}
%include "rs_types.h"

%{
rs2_error** as_ptr(rs2_error* e) { return &e; }

%}
rs2_error** as_ptr(rs2_error* e) { return &e; }

%{
#include "rs.h"
%}
%include "rs.h"

%{
#include "rs_context.h"
%}
%include "rs_context.h"

%{
#include "rs_pipeline.h"
%}
%include "rs_pipeline.h"

%{
#include "rs_device.h"
%}
%include "rs_device.h"

%{
#include "rs_sensor.h"
%}
%include "rs_sensor.h"

%{
#include "rs_config.h"
%}
%include "rs_config.h"

%{
#include "rs_frame.h"
%}
%include "rs_frame.h"
