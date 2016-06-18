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
package se.trixon.util.swing.dialogs.cron;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Patrik Karlsson
 */
public class ElementMonthPanel extends ElementPanel {

    /**
     * Creates new form ElementMonthPanel
     */
    public ElementMonthPanel() {
        setOffset(1);
        init();
    }

    private void init() {
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            c.set(Calendar.MONTH, i);
            String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            mArray.add(month);
        }

        initModel(mArray);
    }
}
