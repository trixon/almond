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
package se.trixon.almond.util.swing.dialogs.cron;

/**
 *
 * @author Patrik Karlström
 */
public class ElementMinutePanel extends ElementPanel {

    /**
     * Creates new form ElementMinutePanel
     */
    public ElementMinutePanel() {
        init();
    }

    private void init() {
        for (int i = 0; i < 60; i++) {
            mArray.add(String.valueOf(i));
        }

        initModel(mArray);
    }
}
