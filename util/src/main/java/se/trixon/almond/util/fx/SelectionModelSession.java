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
package se.trixon.almond.util.fx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.SingleSelectionModel;

/**
 *
 * @author Patrik Karlström
 */
public class SelectionModelSession {

    private final SingleSelectionModel<String> mSelectionModel;
    private final SimpleIntegerProperty mSelectedIndexProperty = new SimpleIntegerProperty();

    public SelectionModelSession(SingleSelectionModel<String> selectionModel) {
        mSelectionModel = selectionModel;
        initListeners();
    }

    public SimpleIntegerProperty selectedIndexProperty() {
        return mSelectedIndexProperty;
    }

    public void load() {
        mSelectionModel.select(mSelectedIndexProperty.get());
    }

    private void initListeners() {
        mSelectedIndexProperty.addListener((p, o, n) -> {
            load();
        });

        mSelectionModel.selectedIndexProperty().addListener((p, o, n) -> {
            mSelectedIndexProperty.set(n.intValue());
        });
    }
}
