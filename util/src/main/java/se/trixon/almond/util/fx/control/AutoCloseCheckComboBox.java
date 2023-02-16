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

import impl.org.controlsfx.skin.CheckComboBoxSkin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import org.controlsfx.control.CheckComboBox;

/**
 *
 * @author Patrik Karlström
 * @param <T>
 */
public class AutoCloseCheckComboBox<T> extends CheckComboBox<T> {

    private ComboBoxListViewSkin mComboBoxListViewSkin;
    private boolean mFirstRun = true;
    private ListView mListView;
    private ComboBox mOriginalComboBox;

    public AutoCloseCheckComboBox() {
        super();
        init();
    }

    public AutoCloseCheckComboBox(ObservableList<T> items) {
        super(items);
        init();
    }

    private void init() {
        Platform.runLater(() -> {
            final var autoCloseCheckComboBoxSkin = new AutoCloseCheckComboBoxSkin(this);
            setSkin(autoCloseCheckComboBoxSkin);

            setOnMouseEntered(me -> {
                if (mFirstRun) {
                    mFirstRun = false;
                    mComboBoxListViewSkin = (ComboBoxListViewSkin) mOriginalComboBox.getSkin();
                    mListView = (ListView) mComboBoxListViewSkin.getPopupContent();

                    mListView.setOnMouseEntered(me1 -> {
                        //TODO Cancel hide
                    });

                    mListView.setOnMouseExited(me1 -> {
                        autoCloseCheckComboBoxSkin.hide();
                    });
                }
            });

            setOnMouseExited(me -> {
                //TODO hide after a short delay, unless cancelled
            });
        });
    }

    public class AutoCloseCheckComboBoxSkin extends CheckComboBoxSkin {

        public AutoCloseCheckComboBoxSkin(CheckComboBox control) {
            super(control);

            try {
                var comboboxField = CheckComboBoxSkin.class.getDeclaredField("comboBox");
                comboboxField.setAccessible(true);
                mOriginalComboBox = (ComboBox) comboboxField.get(this);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(AutoCloseCheckComboBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
