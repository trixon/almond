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
package se.trixon.almond.util.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Patrik Karlström
 */
public class DelayedResetRunner {

    private Timer mTimer;
    private long mStart = System.currentTimeMillis();

    public DelayedResetRunner(int delay, Runnable r) {
        ActionListener actionListener = (ActionEvent e) -> {
            if (System.currentTimeMillis() - mStart >= delay) {
                r.run();
                mTimer.stop();
            }
        };

        mTimer = new Timer(delay, actionListener);
        mTimer.start();
    }

    public void reset() {
        mStart = System.currentTimeMillis();
        mTimer.restart();
    }
}
