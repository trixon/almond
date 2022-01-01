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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessLogThread extends Thread {

    private final int mChannel;
    private final InputStream mInputStream;
    private final Log mLog;

    public ProcessLogThread(InputStream inputStream, int channel, final Log log) {
        mInputStream = inputStream;
        mChannel = channel;
        mLog = log;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream), 1);
            String line;
            while ((line = reader.readLine()) != null) {
                if (mChannel == 0) {
                    mLog.timedOut(line);
                } else {
                    mLog.timedErr(line);
                }
            }
        } catch (IOException e) {
            Xlog.timedErr(e.getLocalizedMessage());
        }
    }
}
