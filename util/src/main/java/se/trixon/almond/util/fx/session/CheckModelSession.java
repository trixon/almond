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

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

/**
 *
 * @author Patrik Karlström
 */
public class CheckModelSession {

    private final ObservableList mAllItems;
    private final IndexedCheckModel mCheckModel;
    private final SimpleStringProperty mCheckedStringProperty = new SimpleStringProperty();

    public CheckModelSession(IndexedCheckModel checkModel, ObservableList allItems) {
        mCheckModel = checkModel;
        mAllItems = allItems;
        initListeners();
    }

    public CheckModelSession(CheckComboBox checkComboBox) {
        this(checkComboBox.getCheckModel(), checkComboBox.getItems());
    }

    public SimpleStringProperty checkedStringProperty() {
        return mCheckedStringProperty;
    }

    public void load() {
        var storedItems = StringUtils.split(mCheckedStringProperty.get(), ":::");
        if (storedItems != null) {
            for (var storedItem : storedItems) {
                for (var listItem : mAllItems) {
                    if (StringUtils.equals(storedItem, listItem.toString())) {
                        mCheckModel.check(listItem);
                        break;
                    }
                }
            }
        }
    }

    private void initListeners() {
        mCheckedStringProperty.addListener((p, o, n) -> {
            load();
        });

        mCheckModel.getCheckedItems().addListener((ListChangeListener.Change c) -> {
            var items = String.join(":::", c.getList().stream().map(o -> o.toString()).toList());
            mCheckedStringProperty.set(items);
        });
    }

}
