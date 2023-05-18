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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class LocaleComboBox extends JComboBox<String> {

    public LocaleComboBox() {
        initialize();
    }

    public Locale getSelectedLocale() {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (StringUtils.equals((String) getSelectedItem(), locale.getDisplayName())) {
                return locale;
            }
        }

        return null;
    }

    public void setSelectedLocale(Locale locale) {
        setSelectedItem(locale.getDisplayName());
    }

    private void initialize() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        ArrayList<Locale> locales = new ArrayList<>(Arrays.asList(Locale.getAvailableLocales()));

        locales.sort((Locale o1, Locale o2) -> o1.getDisplayName().compareTo(o2.getDisplayName()));

        locales.forEach((locale) -> {
            model.addElement(locale.getDisplayName());
        });

        setModel(model);
        setSelectedLocale(Locale.getDefault());
    }
}
