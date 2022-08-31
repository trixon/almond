/*
 * Copyright 2022 Patrik Karlström.
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

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Patrik Karlström
 */
public class Xlog {

    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public static String sGlobalTag = "";
    private static boolean sActive = true;
    private static DateTimeFormatter sDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss: ");
    private static int sLevel = VERBOSE;
    private static boolean sUseGlobalTag = false;
    private static boolean sUseTimestamps = true;

    public synchronized static void a(Class c, String msg) {
        a(getTag(c.getCanonicalName()), msg);
    }

    public synchronized static void a(String tag, String msg) {
        if (sActive && sLevel <= ASSERT) {
            printErr("ASSERT", getMessage(getTag(tag), getNullSafeMsg(msg)));
        }
    }

    public synchronized static void d(Class c, String msg) {
        d(getTag(c.getCanonicalName()), msg);
    }

    public synchronized static void d(String tag, String msg) {
        if (sActive && sLevel <= DEBUG) {
            print("DEBUG", getMessage(getTag(tag), getNullSafeMsg(msg)));
        }
    }

    public synchronized static void e(Class c, String msg) {
        e(getTag(c.getCanonicalName()), msg);
    }

    public synchronized static void e(String tag, String msg) {
        if (sActive && sLevel <= ERROR) {
            printErr("ERROR", getMessage(getTag(tag), getNullSafeMsg(msg)));
        }
    }

    public synchronized static String getGlobalTag() {
        return sGlobalTag;
    }

    public synchronized static int getLevel() {
        return sLevel;
    }

    public synchronized static void i(Class c, String msg) {
        i(getTag(c.getCanonicalName()), msg);
    }

    public synchronized static void i(String tag, String msg) {
        if (sActive && sLevel <= INFO) {
            print("INFO", getMessage(getTag(tag), getNullSafeMsg(msg)));
        }
    }

    public synchronized static boolean isActive() {
        return sActive;
    }

    public synchronized static boolean isUseGlobalTag() {
        return sUseGlobalTag;
    }

    public synchronized static boolean isUseTimestamps() {
        return sUseTimestamps;
    }

    public synchronized static void setActive(boolean active) {
        sActive = active;
    }

    public synchronized static void setGlobalTag(String globalTag) {
        sGlobalTag = globalTag;
    }

    public synchronized static void setLevel(int level) {
        sLevel = level;
    }

    public synchronized static void setUseGlobalTag(boolean useGlobalTag) {
        sUseGlobalTag = useGlobalTag;
    }

    public synchronized static void timedErr(String message) {
        printDate(System.err);
        System.err.println(message);
    }

    public synchronized static void timedOut(String message) {
        printDate(System.out);
        System.out.println(message);
    }

    public synchronized static void v(Class<?> c, String msg) {
        v(getTag(c.getCanonicalName()), msg);
    }

    public synchronized static void v(String tag, String msg) {
        if (sActive && sLevel <= VERBOSE) {
            print("VERBOSE", getMessage(getTag(tag), getNullSafeMsg(msg)));
        }
    }

    public static synchronized void w(Class c, String msg) {
        w(getTag(c.getCanonicalName()), msg);
    }

    public static synchronized void w(String tag, String msg) {
        if (sActive && sLevel <= WARN) {
            printErr("WARNING", getMessage(getTag(tag), getNullSafeMsg(msg)));
        }
    }

    private static String getMessage(String s1, String s2) {
        return "[%s] %s".formatted(s1, s2);
    }

    private static String getMessage(String s1, String s2, String s3) {
        return "[%s] [%s] %s".formatted(s1, s2, s3);
    }

    private static String getNullSafeMsg(String msg) {
        if (msg == null) {
            return "NULL";
        } else {
            return msg;
        }
    }

    private static String getTag(String localTag) {
        String tag;

        if (sUseGlobalTag) {
            tag = sGlobalTag;
        } else {
            tag = localTag;
        }

        return tag;
    }

    private static void print(String levelClass, String message) {
        printDate(System.out);
        System.out.println("%s %s".formatted(levelClass, message));
    }

    private static void printDate(PrintStream printStream) {
        if (sUseTimestamps) {
            printStream.print(LocalDateTime.now().format(sDateTimeFormatter));
        }
    }

    private static void printErr(String levelClass, String message) {
        printDate(System.err);
        System.out.println("%s %s".formatted(levelClass, message));
    }
}
