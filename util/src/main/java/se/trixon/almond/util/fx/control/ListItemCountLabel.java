/*
 * Copyright 2025 Patrik Karlström <patrik@trixon.se>.
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

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class ListItemCountLabel extends Label {

    private ObservableList mFilteredList;

    private ListView mListView;
    private ObservableList mRawList;

    public ListItemCountLabel() {
        createUI();
    }

    public ListItemCountLabel(String string) {
        super(string);
        createUI();
    }

    public ListItemCountLabel(String string, Node node) {
        super(string, node);
        createUI();
    }

    public void init(ListView listView, ObservableList filteredList, ObservableList rawList) {
        mListView = listView;
        mFilteredList = filteredList;
        mRawList = rawList;

        ListChangeListener listChangeListener = (ListChangeListener.Change c) -> {
            updateLabel();
        };

        mListView.itemsProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
//            updateLabel();
        });
//        mListView.getItems().addListener(listChangeListener);
        filteredList.addListener(listChangeListener);
        mRawList.addListener(listChangeListener);

        mListView.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> {
            updateLabel();
        });
    }

    private void createUI() {
        setAlignment(Pos.BASELINE_RIGHT);
        setPadding(FxHelper.getUIScaledInsets(0, 4, 0, 0));
    }

    private void updateLabel() {
        var index = mListView.getSelectionModel().getSelectedIndex();
        var pos = "";
        if (index != -1) {
            pos = "@%d/".formatted(index + 1);
        }

        setText("%s%d/%d".formatted(
                pos,
                mFilteredList.size(),
                mRawList.size()
        ));
    }

}
