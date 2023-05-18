/*
 * Copyright 2023 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.almond.util;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class SnapHelper {

    public static boolean isConnected(String plugOrSlot) throws IOException, InterruptedException {
        var processBuilder = new ProcessBuilder(StringUtils.split("snapctl is-connected %s".formatted(plugOrSlot)));
        var process = processBuilder.start();

        return 0 == process.waitFor();
    }

    public static boolean isSnap() {
        var env = System.getenv();

        return env.containsKey("SNAP_ARCH") && env.containsKey("SNAP_INSTANCE_NAME");
    }
}
