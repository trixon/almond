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

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.CodeSource;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

/**
 *
 * @author Patrik Karlström
 */
public class SystemHelper {

    private static final Logger LOGGER = Logger.getLogger(SystemHelper.class.getName());
    private static final double UI_SCALE;
    private static Consumer<String> sDesktopBrowser;

    static {
        sDesktopBrowser = url -> new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException | IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }).start();

        //TODO Fix this for NBP & FX
        var sizeString = "12";
        if (System.getProperties().containsKey("fontsize")) {
            sizeString = System.getProperties().getProperty("fontsize");
        } else {
            var sunJavaCommand = System.getProperties().getOrDefault("sun.java.command", "").toString();
            if (sunJavaCommand.contains("--fontsize ")) {
                var remainder = StringUtils.substringAfter(sunJavaCommand, "--fontsize ");
                var string = StringUtils.substringBefore(remainder, " ");
                if (StringUtils.isNumeric(string)) {
                    sizeString = string;
                }
            }
        }

        double size = Double.parseDouble(sizeString);
        UI_SCALE = size / 12.0;
    }

    /**
     *
     * @param millisec
     * @return difference from System.currentTimeMillis()
     */
    public static long age(long millisec) {
        return System.currentTimeMillis() - millisec;
    }

    public static void copyToClipboard(String string) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(string), null);
    }

    public static void desktopBrowse(String url) {
        sDesktopBrowser.accept(url);
    }

    public static void desktopOpen(File file) {
        new Thread(() -> {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public static void desktopOpenOrElseParent(File file) {
        new Thread(() -> {
            try {
                Desktop.getDesktop().open(file);
            } catch (IllegalArgumentException | IOException e1) {
                try {
                    Desktop.getDesktop().open(file.getParentFile());
                } catch (IllegalArgumentException | IOException e2) {
                    LOGGER.log(Level.SEVERE, null, e2);
                }
            }
        }).start();
    }

    public static void enableRmiServer() {
        System.setProperty("java.rmi.server.hostname", getHostname());
    }

    public static ResourceBundle getBundle(Class c, String bundle) {
        String bundlePath = c.getPackage().getName() + "." + bundle;
        return ResourceBundle.getBundle(bundlePath, Locale.getDefault(), c.getClassLoader());
    }

    public static ResourceBundle getBundle(File file) {
        return getBundle(file.getAbsolutePath());
    }

    public static ResourceBundle getBundle(String path) {
        String fullPath = FilenameUtils.getFullPath(path);
        String filePath = FilenameUtils.getBaseName(path);
        ResourceBundle resourceBundle = null;
        File file = new File(fullPath);

        try {
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            resourceBundle = ResourceBundle.getBundle(filePath, Locale.getDefault(), loader);
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return resourceBundle;
    }

    public static int getCommandMask() {
        int code;

        if (SystemUtils.IS_OS_MAC) {
            code = InputEvent.META_DOWN_MASK;
        } else {
            code = InputEvent.CTRL_DOWN_MASK;
        }

        return code;
    }

    public static String getHostname() {
        String hostname = "Unknown";

        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Xlog.e("SystemHelper", "Hostname can not be resolved.");
        }

        return hostname;
    }

    public static String getHostnameCanonical() {
        String hostname = "Unknown";

        try {
            hostname = InetAddress.getLocalHost().getCanonicalHostName();
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

    public static String getJavaRuntimeVersion() {
        return System.getProperty("java.runtime.version");
    }

    public static String getLanguageSuffix() {
        return "-" + Locale.getDefault().getLanguage();
    }

    public static String getLocalizedResourceAsString(Class cls, String patternName, String defaultName) {
        String s = null;
        try {
            s = new Scanner(cls.getResourceAsStream(patternName.formatted(SystemHelper.getUserLanguage())), StandardCharsets.UTF_8).useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            try {
                s = new Scanner(cls.getResourceAsStream(defaultName), StandardCharsets.UTF_8).useDelimiter("\\A").next();
            } catch (NullPointerException e2) {
            }
        }

        return s;
    }

    public static String getPackageAsPath(Class clazz) {
        return clazz.getPackage().getName().replace(".", "/") + "/";
    }

    public static ImageIcon getResourceAsImageIcon(Class cls, String name) {
        ImageIcon imageIcon = null;
        try {
            String path = "/%s%s".formatted(getPackageAsPath(cls), name);
            imageIcon = new ImageIcon(cls.getResource(path));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return imageIcon;
    }

    public static String getResourceAsString(Class cls, String name) {
        String s = null;
        try {
            s = new Scanner(cls.getResourceAsStream(name), StandardCharsets.UTF_8).useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            s = "crap";
        }

        return s;
    }

    public static String getSystemInfo() {
        return getSystemInfo("");
    }

    public static String getSystemInfo(String indent) {
        var map = new LinkedHashMap<String, String>();
        map.put("Operating System", "%s version %s running on %s".formatted(
                System.getProperty("os.name"),
                System.getProperty("os.version"),
                System.getProperty("os.arch")
        ));
        map.put("Java; VM; Vendor", "%s; %s %s; %s".formatted(
                System.getProperty("java.version"),
                System.getProperty("java.vm.name"),
                System.getProperty("java.vm.version"),
                System.getProperty("java.vendor")
        ));
        map.put("Runtime", "%s %s".formatted(
                System.getProperty("java.runtime.name"),
                System.getProperty("java.runtime.version")
        ));
        map.put("Java Home", System.getProperty("java.home"));
        map.put("System Locale; Encoding", "%s; %s".formatted(
                Locale.getDefault().toString(),
                System.getProperty("file.encoding")
        ));
        map.put("Home Directory", System.getProperty("user.home"));
        if (System.getProperties().containsKey("netbeans.user")) {
            map.put("User Directory", System.getProperty("netbeans.user"));
        }

        int max = Integer.MIN_VALUE;
        for (var entry : map.entrySet()) {
            max = Math.max(max, entry.getKey().length());
        }

        var sb = new StringBuilder("\n");
        String separator = " = ";

        for (var entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(indent).append(StringUtils.rightPad(key, max)).append(separator).append(value).append("\n");
        }

        return sb.toString();
    }

    public static double getUIScale() {
        return UI_SCALE;
    }

    public static String getUserCountry() {
        return System.getProperty("user.country");
    }

    public static String getUserLanguage() {
        return System.getProperty("user.language");
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    /**
     *
     * @param host
     * @return true if host is resolves an ip.
     */
    public static boolean isReachable(String host) {
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            return inetAddress.isReachable(5000);
        } catch (IOException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(getSystemInfo());
    }

    public static void printEvery(int counter, int total, int every, Logger logger, String formatMessage) {
        if (counter % every == 0) {
            if (logger != null) {
                logger.info(formatMessage.formatted(counter));
            } else {
                System.out.println(formatMessage.formatted(counter, total));
            }
        }
    }

    public static void runLaterDelayed(long delay, Runnable r) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                r.run();
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemHelper.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
        }, r.getClass().getName()).start();
    }

    public static void setDesktopBrowser(Consumer<String> desktopBrowser) {
        sDesktopBrowser = desktopBrowser;
    }

    public static void setMacApplicationName(String name) {
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", name);
        System.setProperty("apple.awt.application.name", name);
    }
}
