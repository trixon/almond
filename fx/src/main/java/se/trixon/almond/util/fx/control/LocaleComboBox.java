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
package se.trixon.almond.util.fx.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

/**
 * Might suffer from https://bugs.openjdk.java.net/browse/JDK-8129123
 *
 * @author Patrik Karlström
 */
public class LocaleComboBox extends ComboBox<Locale> {

    public LocaleComboBox() {
        init();
    }

    private void init() {
        ObservableList<Locale> data = FXCollections.observableArrayList();
        var locales = new ArrayList<>(Arrays.asList(Locale.getAvailableLocales()));
        locales.sort((o1, o2) -> o1.getDisplayName().toLowerCase(Locale.getDefault()).compareTo(o2.getDisplayName().toLowerCase(Locale.getDefault())));

        var displayNameToLocale = new HashMap<String, Locale>();
        locales.forEach((locale) -> {
            data.add(locale);
            displayNameToLocale.put(locale.getDisplayName(), locale);
        });

        setItems(data);
        setValue(Locale.getDefault());

        var converter = new StringConverter<Locale>() {
            @Override
            public Locale fromString(String string) {
                return displayNameToLocale.get(string);
            }

            @Override
            public String toString(Locale locale) {
                return locale.getDisplayName();
            }
        };

        setConverter(converter);
    }
}
