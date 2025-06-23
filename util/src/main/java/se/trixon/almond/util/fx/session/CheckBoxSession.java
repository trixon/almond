/*
 * Copyright 2025 Patrik Karlström.
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
package se.trixon.almond.util.fx.session;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Patrik Karlström
 */
public class CheckBoxSession {

    private final CheckBox mCheckBox;
    private final SimpleBooleanProperty mValueProperty = new SimpleBooleanProperty();

    public CheckBoxSession(CheckBox checkBox) {
        mCheckBox = checkBox;
        initListeners();
        mValueProperty.set(mCheckBox.isSelected());
    }

    public void load() {
        mCheckBox.setSelected(mValueProperty.get());
    }

    public SimpleBooleanProperty valueProperty() {
        return mValueProperty;
    }

    private void initListeners() {
        mValueProperty.addListener((p, o, n) -> {
            load();
        });

        mCheckBox.selectedProperty().addListener((p, o, n) -> {
            mValueProperty.set(n);
        });
    }

}
