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

import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.SimpleStringProperty;
import org.controlsfx.control.CheckComboBox;

/**
 *
 * @author Patrik Karlström
 */
public class SessionCheckComboBox<T> extends CheckComboBox<T> {

    private final CheckModelSession mSession;

    public SessionCheckComboBox() {
        mSession = new CheckModelSession(this);
    }

    public SimpleStringProperty checkedStringProperty() {
        return mSession.checkedStringProperty();
    }

    public void clearChecks() {
        getCheckModel().clearChecks();
    }

    public CheckModelSession getSession() {
        return mSession;
    }

    public void loadAndRestoreCheckItems() {
        loadAndRestoreCheckItems(getItems().stream());
    }

    public void loadAndRestoreCheckItems(Stream<T> stream) {
        var checkModel = getCheckModel();
        var checkedItems = checkModel.getCheckedItems();

        getItems().setAll(new TreeSet<>(stream.collect(Collectors.toSet())));
        checkedItems.stream().forEach(s -> checkModel.check(s));

        mSession.load();
    }

}
