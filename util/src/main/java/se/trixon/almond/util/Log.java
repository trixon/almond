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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Patrik Karlström
 */
public class Log {

    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public String mGlobalTag = "";
    private boolean mActive = true;
    private DateTimeFormatter mDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss: ");
    private LogListener mErr = (String s) -> {
        System.err.println(s);
    };
    private int mLevel = VERBOSE;
    private LogListener mOut = (String s) -> {
        System.out.println(s);
    };
    private boolean mUseGlobalTag = false;
    private boolean mUseTimestamps = true;

    public Log(LogListener out, LogListener err) {
        mOut = out;
        mErr = err;
    }

    public Log() {
    }

    public synchronized void a(Class c, String msg) {
        a(getTag(c.getCanonicalName()), msg);
    }

    public synchronized void a(String tag, String msg) {
        if (mActive && mLevel <= ASSERT) {
            printErr("ASSERT", getMessage(getTag(tag), Objects.toString(msg, "NULL")));
        }
    }

    public synchronized void d(Class c, String msg) {
        d(getTag(c.getCanonicalName()), msg);
    }

    public synchronized void d(String tag, String msg) {
        if (mActive && mLevel <= DEBUG) {
            print("DEBUG", getMessage(getTag(tag), Objects.toString(msg, "NULL")));
        }
    }

    public synchronized void e(Class c, String msg) {
        e(getTag(c.getCanonicalName()), msg);
    }

    public synchronized void e(String tag, String msg) {
        if (mActive && mLevel <= ERROR) {
            printErr("ERROR", getMessage(getTag(tag), Objects.toString(msg, "NULL")));
        }
    }

    public synchronized void err(String message) {
        mErr.println(message);
    }

    public synchronized String getGlobalTag() {
        return mGlobalTag;
    }

    public synchronized int getLevel() {
        return mLevel;
    }

    public synchronized void i(Class c, String msg) {
        i(getTag(c.getCanonicalName()), msg);
    }

    public synchronized void i(String tag, String msg) {
        if (mActive && mLevel <= INFO) {
            print("INFO", getMessage(getTag(tag), Objects.toString(msg, "NULL")));
        }
    }

    public synchronized boolean isActive() {
        return mActive;
    }

    public synchronized boolean isUseGlobalTag() {
        return mUseGlobalTag;
    }

    public synchronized boolean isUseTimestamps() {
        return mUseTimestamps;
    }

    public synchronized void out(String message) {
        mOut.println(message);
    }

    public synchronized void setActive(boolean active) {
        mActive = active;
    }

    public void setErr(LogListener logListener) {
        mErr = logListener;
    }

    public synchronized void setGlobalTag(String globalTag) {
        mGlobalTag = globalTag;
    }

    public synchronized void setLevel(int level) {
        mLevel = level;
    }

    public void setOut(LogListener logListener) {
        mOut = logListener;
    }

    public synchronized void setUseGlobalTag(boolean useGlobalTag) {
        mUseGlobalTag = useGlobalTag;
    }

    public synchronized void setUseTimestamps(boolean useTimestamps) {
        mUseTimestamps = useTimestamps;
    }

    public synchronized void timedErr(String message) {
        mErr.println(getDate() + message);
    }

    public synchronized void timedOut(String message) {
        mOut.println(getDate() + message);
    }

    public synchronized void v(Class<?> c, String msg) {
        v(getTag(c.getCanonicalName()), msg);
    }

    public synchronized void v(String tag, String msg) {
        if (mActive && mLevel <= VERBOSE) {
            print("VERBOSE", getMessage(getTag(tag), Objects.toString(msg, "NULL")));
        }
    }

    public synchronized void w(Class c, String msg) {
        w(getTag(c.getCanonicalName()), msg);
    }

    public synchronized void w(String tag, String msg) {
        if (mActive && mLevel <= WARN) {
            printErr("WARNING", getMessage(getTag(tag), Objects.toString(msg, "NULL")));
        }
    }

    private String getDate() {
        return mUseTimestamps ? LocalDateTime.now().format(mDateTimeFormatter) : "";
    }

    private String getMessage(String s1, String s2) {
        return "[%s] %s".formatted(s1, s2);
    }

    private String getMessage(String s1, String s2, String s3) {
        return "[%s] [%s] %s".formatted(s1, s2, s3);
    }

    private String getTag(String localTag) {
        String tag;

        if (mUseGlobalTag) {
            tag = mGlobalTag;
        } else {
            tag = localTag;
        }

        return tag;
    }

    private void print(String levelClass, String message) {
        mOut.println("%s%s %s".formatted(getDate(), levelClass, message));
    }

    private void printErr(String levelClass, String message) {
        mErr.println("%s%s %s".formatted(getDate(), levelClass, message));
    }

}
