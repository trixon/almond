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
package se.trixon.almond.util.swing.dialogs.cron;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Patrik Karlström
 */
public class ElementDowPanel extends ElementPanel {

    /**
     * Creates new form ElementDowPanel
     */
    public ElementDowPanel() {
        init();
    }

    private void init() {
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        for (int i = 1; i < 8; i++) {
            c.set(Calendar.DAY_OF_WEEK, i);
            String dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
            mArray.add(dayOfWeek);
        }

        initModel(mArray);
    }
}
