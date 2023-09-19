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
package se.trixon.almond.util.fx.session;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Spinner;

/**
 *
 * @author Patrik Karlström
 */
public class SpinnerIntegerSession {

    private final Spinner<Integer> mSpinner;
    private final SimpleIntegerProperty mValueProperty = new SimpleIntegerProperty();

    public SpinnerIntegerSession(Spinner spinner) {
        mSpinner = spinner;
        initListeners();
        mValueProperty.set(mSpinner.getValue());
    }

    public void load() {
        mSpinner.getValueFactory().setValue(mValueProperty.get());
    }

    public SimpleIntegerProperty valueProperty() {
        return mValueProperty;
    }

    private void initListeners() {
        mValueProperty.addListener((p, o, n) -> {
            load();
        });

        mSpinner.valueProperty().addListener((p, o, n) -> {
            mValueProperty.set(n);
        });
    }

}
