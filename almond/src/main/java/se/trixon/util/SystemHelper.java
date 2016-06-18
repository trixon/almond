/* 
 * Copyright 2016 Patrik Karlsson.
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
package se.trixon.util;

import java.awt.event.InputEvent;
import java.io.File;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.CodeSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

/**
 *
 * @author Patrik Karlsson
 */
public class SystemHelper {

    public static void enableRmiServer() {
        System.setProperty("java.rmi.server.hostname", getHostname());
    }

    public static int getCommandMask() {
        int code;

        if (SystemUtils.IS_OS_MAC) {
            code = InputEvent.META_MASK;
        } else {
            code = InputEvent.CTRL_MASK;
        }

        return code;
    }

    public static String getHostname() {
        String hostname = "Unknown";

        try {
            InetAddress inetAddress;
            inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
        } catch (UnknownHostException ex) {
            Xlog.e("SystemHelper", "Hostname can not be resolved.");
        }

        return hostname;
    }

    public static String getJarVersion(Class c) {
        String version = "";
        CodeSource codeSource = c.getProtectionDomain().getCodeSource();
        try {
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            version = StringUtils.split(jarFile.getName(), "-", 2)[1].replace(".jar", "");
        } catch (ArrayIndexOutOfBoundsException | URISyntaxException ex) {
            // nvm
        }

        return version;
    }

    public static String getPackageAsPath(Class clazz) {
        return clazz.getPackage().getName().replace(".", "/") + "/";
    }
}
