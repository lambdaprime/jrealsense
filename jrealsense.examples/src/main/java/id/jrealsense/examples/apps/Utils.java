package id.jrealsense.examples.apps;

import id.jrealsense.devices.Device;
import id.xfunction.CommandLineInterface;
import id.xfunction.XUtils;

public class Utils {

    public void reset(CommandLineInterface cli, Device dev) {
        cli.print("Hardware reset...");
        dev.reset();
        XUtils.sleep(5000);
        cli.print("Ready");
    }
}
