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
import java.util.Objects;
import javax.swing.SwingUtilities;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

public class NbPrint {

    private DateTimeFormatter mDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss: ");
    private InputOutput mInputOutput;
    private boolean mUseTimestamps = true;

    public NbPrint(String title) {
        SwingUtilities.invokeLater(() -> {
            mInputOutput = IOProvider.getDefault().getIO(title, false);
        });
    }

    public void err(String x) {
        SwingUtilities.invokeLater(() -> {
            try (OutputWriter outputWriter = mInputOutput.getErr()) {
                printDate(outputWriter);
                outputWriter.println(Objects.toString(x, "NULL"));
            }
        });
    }

    public void err(Object x) {
        if (x == null) {
            err("NULL");
        } else {
            err(String.valueOf(x));
        }
    }

    public synchronized boolean isUseTimestamps() {
        return mUseTimestamps;
    }

    public void out(String x) {
        SwingUtilities.invokeLater(() -> {
            try (OutputWriter outputWriter = mInputOutput.getOut()) {
                printDate(outputWriter);
                outputWriter.println(Objects.toString(x, "NULL"));
            }
        });
    }

    public void out(Object x) {
        if (x == null) {
            NbPrint.this.out("NULL");
        } else {
            NbPrint.this.out(String.valueOf(x));
        }
    }

    public synchronized void select() {
        SwingUtilities.invokeLater(() -> {
            mInputOutput.select();
        });
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        mDateTimeFormatter = dateTimeFormatter;
    }

    public synchronized void setUseTimestamps(boolean useTimestamps) {
        mUseTimestamps = useTimestamps;
    }

    private void printDate(OutputWriter outputWriter) {
        if (mUseTimestamps) {
            outputWriter.print(LocalDateTime.now().format(mDateTimeFormatter));
        }
    }
}
