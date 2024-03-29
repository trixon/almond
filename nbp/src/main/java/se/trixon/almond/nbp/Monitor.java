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
package se.trixon.almond.nbp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Patrik Karlström
 */
public class Monitor {

    private DateTimeFormatter mDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss: ");
    private OutputWriter mErr;
    private final InputOutput mIo;
    private OutputWriter mOut;
    private boolean mUseTimestamps = true;

    public Monitor(String tag, boolean newIO, boolean useTimestamps) {
        mUseTimestamps = useTimestamps;
        mIo = IOProvider.getDefault().getIO(tag, newIO);
    }

    public void errln(String message) {
        mErr = mIo.getErr();
        printDate(mErr);
        mErr.println(message);
        mErr.close();
    }

    public boolean isUseTimestamps() {
        return mUseTimestamps;
    }

    public void outln(String message) {
        mOut = mIo.getOut();
        printDate(mOut);
        mOut.println(message);
        mOut.close();
    }

    public void outlnEvent(String message) {
        mOut = mIo.getOut();
        printDate(mOut);
        mOut.println(message);
        mOut.close();
    }

    public void setUseTimestamps(boolean useTimestamps) {
        mUseTimestamps = useTimestamps;
    }

    private void printDate(OutputWriter anOutputWriter) {
        if (mUseTimestamps) {
            anOutputWriter.print(LocalDateTime.now().format(mDateTimeFormatter));
        }
    }
}
