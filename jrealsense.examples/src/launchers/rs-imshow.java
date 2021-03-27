/**
 * Launcher script which starts ImshowApp
 */

import java.nio.file.*;
import java.util.*;
import static java.util.stream.Collectors.*;

public class Launcher {
    public static void main(String[] args) throws Exception {
        var procInfo = ProcessHandle.current().info();
        var javaPath = procInfo.command().orElse("java");
        var cmdArgs = procInfo.arguments().orElse(new String[0]);
        if (cmdArgs.length == 0) {
            System.out.println("Cannot find arguments of current process");
            return;
        }
        var location = Paths.get(cmdArgs[0]).toAbsolutePath().getParent();
        if (!location.toFile().exists()) {
            System.out.format("Failed to find launcher location. Path %s does not exist.\n", location);
            return;
        }
        var launchCmd = new String[]{
            javaPath,
            "-cp",
            location + "/libs/*",
            "-Djava.library.path=" + location + "/libs",
            "id.jrealsense.examples.apps.ImshowApp"};
        System.out.println(Arrays.stream(launchCmd).collect(joining(" ")));
        new ProcessBuilder(launchCmd)
            .inheritIO()
            .start()
            .waitFor();
    }
}
