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

import java.nio.file.*;
import java.util.*;
import static java.util.stream.Collectors.*;

/**
 * Launcher script which starts ImshowApp
 *
 * @author lambdaprime intid@protonmail.com
 */
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
