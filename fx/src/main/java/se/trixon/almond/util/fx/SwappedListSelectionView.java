/*
 * Copyright 2024 Patrik Karlström.
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

import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.action.Action;

/**
 *
 * @author Patrik Karlström
 */
public class SwappedListSelectionView<T> extends ListSelectionView<T> {

    public ObservableList<Action> getSwappedSourceActions() {
        return getTargetActions();
    }

    public ObservableList<T> getSwappedSourceItems() {
        return getTargetItems();
    }

    public ObservableList<Action> getSwappedTargetActions() {
        return getSourceActions();
    }

    public ObservableList<T> getSwappedTargetItems() {
        return getSourceItems();
    }

    public void setSwappedSourceHeader(Node node) {
        setTargetHeader(node);
    }

    public void setSwappedTargetHeader(Node node) {
        setSourceHeader(node);
    }

}
