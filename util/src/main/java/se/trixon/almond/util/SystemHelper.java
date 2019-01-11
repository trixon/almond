/*
 * Copyright 2019 Patrik Karlström.
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.security.CodeSource;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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

    public static void copyToClipboard(String string) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(string), null);
    }

    public static boolean copyToClipboard(BufferedImage bufferedImage) {
        return copyToClipboard(SwingFXUtils.toFXImage(bufferedImage, null));
    }

    public static boolean copyToClipboard(Image image) {
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putImage(image);

        return Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    public static void desktopBrowse(String url) {
        new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException | IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }).start();
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

    public static String getJavaRuntimeVersion() {
        return System.getProperty("java.runtime.version");
    }

    public static String getLanguageSuffix() {
        return "-" + Locale.getDefault().getLanguage();
    }

    public static String getLocalizedResourceAsString(Class cls, String patternName, String defaultName) {
        String s = null;
        try {
            s = new Scanner(cls.getResourceAsStream(String.format(patternName, SystemHelper.getUserLanguage())), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            try {
                s = new Scanner(cls.getResourceAsStream(defaultName), "UTF-8").useDelimiter("\\A").next();
            } catch (NullPointerException e2) {
            }
        }

        return s;
    }

    public static String getPackageAsPath(Class clazz) {
        return clazz.getPackage().getName().replace(".", "/") + "/";
    }

    public static Image getResourceAsImage(Class cls, String name) {
        Image image = null;
        try {
            String path = String.format("/%s%s", getPackageAsPath(cls), name);
            image = new Image(path);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return image;
    }

    public static ImageIcon getResourceAsImageIcon(Class cls, String name) {
        ImageIcon imageIcon = null;
        try {
            String path = String.format("/%s%s", getPackageAsPath(cls), name);
            imageIcon = new ImageIcon(cls.getResource(path));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return imageIcon;
    }

    public static ImageView getResourceAsImageView(Class cls, String name) {
        ImageView imageView = null;
        try {
            String path = String.format("/%s%s", getPackageAsPath(cls), name);
            imageView = new ImageView(new Image(path));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return imageView;
    }

    public static String getResourceAsString(Class cls, String name) {
        String s = null;
        try {
            s = new Scanner(cls.getResourceAsStream(name), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            s = "crap";
        }

        return s;
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
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static void setMacApplicationName(String name) {
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", name);
        System.setProperty("apple.awt.application.name", name);
    }
}
