/*
 * Copyright 2018 Patrik Karlström.
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

//import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class LocaleComboBox extends ComboBox<String> {

    public LocaleComboBox() {
        init();
    }

    public Locale getLocale() {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (StringUtils.equals((String) getSelectionModel().getSelectedItem(), locale.getDisplayName())) {
                return locale;
            }
        }

        return null;
    }

    public void setLocale(Locale locale) {
        getSelectionModel().select(locale.getDisplayName());
    }

    private void init() {
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<Locale> locales = new ArrayList<>(Arrays.asList(Locale.getAvailableLocales()));

        locales.sort((Locale o1, Locale o2) -> o1.getDisplayName().compareTo(o2.getDisplayName()));

        locales.forEach((locale) -> {
            data.add(locale.getDisplayName());
        });

        setItems(data);
        setLocale(Locale.getDefault());
//        setOnShown((Event event) -> {
//            /*
//             * This is a workaround for https://bugs.openjdk.java.net/browse/JDK-8129123
//             * It uses com.sun...
//             * Should be removed when resolved.
//             */
//            ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) getSkin();
//            ((ListView<?>) skin.getPopupContent()).scrollTo(getSelectionModel().getSelectedIndex());
//        });
    }
}
