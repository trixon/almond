/*
 * Copyright 2024 Patrik Karlström <patrik@trixon.se>.
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
package se.trixon.almond.nbp.output;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.openide.util.Exceptions;
import org.openide.windows.IOColorLines;
import org.openide.windows.InputOutput;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class OutputHelper {

    private final boolean mDryRun;
    private final InputOutput mInputOutput;
    private final String mName;
    private String mPadString = "-";
    private int mRowWidth = 80;
    private LocalDateTime mStartTime;

    public static String millisToDateTime(long timestamp) {
        var date = new Date(timestamp);
        return new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(date);
    }

    public static Long[] millisToMinSec(long millis) {
        long min = TimeUnit.MILLISECONDS.toMinutes(millis);
        long sec = TimeUnit.MILLISECONDS.toSeconds(millis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));

        return new Long[]{min, sec};
    }

    public static String nowToDateTime() {
        return millisToDateTime(System.currentTimeMillis());
    }

    public OutputHelper(String name, InputOutput inputOutput, boolean dryRun) {
        mName = name;
        mInputOutput = inputOutput;
        mDryRun = dryRun;
    }

    public String getPadString() {
        return mPadString;
    }

    public int getRowWidth() {
        return mRowWidth;
    }

    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));
    }

    public void printSectionHeader(OutputLineMode outputLineMode, String action, String type, String id) {
        var text = "----   %s %s %s '%s'   ".formatted(OutputHelper.nowToDateTime(), action, type, id);
        var paddedText = StringUtils.rightPad(text, mRowWidth, mPadString);

        println(outputLineMode, "-".repeat(mRowWidth));
        println(outputLineMode, paddedText);
        println(outputLineMode, "-".repeat(mRowWidth));
    }

    public void printSummary(OutputLineMode outputLineMode, String type) {
        var millis = ChronoUnit.MILLIS.between(mStartTime, LocalDateTime.now());
        var minSec = OutputHelper.millisToMinSec(millis);
        var details = String.format("%s (%d %s, %d %s)", mName, minSec[0], Dict.TIME_MIN.toString(), minSec[1], Dict.TIME_SEC.toString());

        printSectionHeader(outputLineMode, Dict.DONE.toString(), type, details);

        if (mDryRun) {
            println(OutputLineMode.WARNING, "DRY-RUN (performed a trial run with no changes made)");
        }
    }

    public void println(OutputLineMode outputLineMode, String line) {
        println(outputLineMode.getColor(), line);
    }

    public void println(Color color, String line) {
        try {
            IOColorLines.println(mInputOutput, line, color);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void resetStartTime() {
        mStartTime = LocalDateTime.now();
    }

    public void setPadString(String padString) {
        mPadString = padString;
    }

    public void setRowWidth(int rowWidth) {
        mRowWidth = rowWidth;
    }

    public void start() {
        resetStartTime();

        if (mDryRun) {
            println(OutputLineMode.WARNING, "DRY-RUN (perform a trial run with no changes made)");
        }
    }

}
