/*
 * Copyright 2024 Patrik Karlström <patrik@trixon.se>.
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

import java.util.Comparator;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.textfield.TextFields;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.DelayedResetRunner;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class FilterableListSelectionView<T> extends ListSelectionView<T> {

    private Comparator<T> mComparator;
    private Predicate<T> mFilterSourcePredicate;
    private Predicate<T> mFilterTargetPredicate;
    private final FilterSection mSourceFilterSection = new FilterSection(FilterMode.SOURCE);
    private final FilterSection mTargetFilterSection = new FilterSection(FilterMode.TARGET);

    public FilterableListSelectionView() {
        setPadding(Insets.EMPTY);
        ListChangeListener<T> listener = (ListChangeListener.Change<? extends T> c) -> {
            handleListSourceTargetChange(c);
        };

        getSourceItems().addListener(listener);
        getTargetItems().addListener(listener);
    }

    public void filterLoad(ObservableList<T> sourceItems, ObservableList<T> targetItems) {
        sourceItems.removeAll(targetItems);
        getUnfilteredSourceItems().setAll(sourceItems);
        getUnfilteredTargetItems().setAll(targetItems);
        mSourceFilterSection.mFilterTextField.clear();
        mTargetFilterSection.mFilterTextField.clear();

        updateLists();
    }

    public String getFilterTextSource() {
        return mSourceFilterSection.mFilterTextField.getText();
    }

    public String getFilterTextTarget() {
        return mTargetFilterSection.mFilterTextField.getText();
    }

    public ObservableList<T> getUnfilteredSourceItems() {
        return mSourceFilterSection.mUnfilteredItems;
    }

    public ObservableList<T> getUnfilteredTargetItems() {
        return mTargetFilterSection.mUnfilteredItems;
    }

    public void setComparator(Comparator< T> comparator) {
        mComparator = comparator;
    }

    public void setFilterSourceHeader(Node node) {
        mSourceFilterSection.getChildren().set(0, node);
    }

    public void setFilterSourcePredicate(Predicate<T> sourcePredicate) {
        mFilterSourcePredicate = sourcePredicate;
    }

    public void setFilterTargetHeader(Node node) {
        mTargetFilterSection.getChildren().set(0, node);
    }

    public void setFilterTargetPredicate(Predicate<T> targetPredicate) {
        mFilterTargetPredicate = targetPredicate;
    }

    public void updateLists() {
        mSourceFilterSection.updateList();
        mTargetFilterSection.updateList();
    }

    private synchronized void handleListSourceTargetChange(ListChangeListener.Change<? extends T> c) {
        while (c.next()) {
            if (c.wasPermutated() || c.wasUpdated()) {
                continue;
            }

            ObservableList<T> primaryUnfilteredItems;
            ObservableList<T> secondaryUnfilteredItems;

            if (c.getList() == getSourceItems()) {
                primaryUnfilteredItems = mSourceFilterSection.mUnfilteredItems;
                secondaryUnfilteredItems = mTargetFilterSection.mUnfilteredItems;
            } else {
                primaryUnfilteredItems = mTargetFilterSection.mUnfilteredItems;
                secondaryUnfilteredItems = mSourceFilterSection.mUnfilteredItems;
            }

            secondaryUnfilteredItems.removeAll(c.getAddedSubList());
            c.getAddedSubList().forEach(t -> {
                if (!primaryUnfilteredItems.contains(t)) {
                    primaryUnfilteredItems.add(t);
                }
            });
        }
    }

    public enum FilterMode {
        SOURCE, TARGET;
    }

    public class FilterSection extends VBox {

        private final DelayedResetRunner mDelayedResetRunner;
        private final FilterMode mFilterMode;
        private final TextField mFilterTextField = TextFields.createClearableTextField();
        private final ObservableList<T> mUnfilteredItems = FXCollections.observableArrayList();

        public FilterSection(FilterMode filterMode) {
            mFilterMode = filterMode;
            mDelayedResetRunner = new DelayedResetRunner(300, () -> {
                updateList();
            });
            createUI();
            initListeners();
        }

        private void createUI() {
            setSpacing(FxHelper.getUIScaled(4.0));
            mFilterTextField.setPromptText(Dict.FILTER.toString());

            if (mFilterMode == FilterMode.SOURCE) {
                getChildren().addAll(getSourceHeader(), mFilterTextField);
                setSourceHeader(this);
            } else {
                getChildren().addAll(getTargetHeader(), mFilterTextField);
                setTargetHeader(this);
            }
        }

        private void initListeners() {
            mFilterTextField.textProperty().addListener((p, o, n) -> {
                mDelayedResetRunner.reset();
            });
        }

        private void updateList() {
            var theOtherSideList = mFilterMode == FilterMode.SOURCE ? getUnfilteredTargetItems() : getUnfilteredSourceItems();
            var filterPredicate = mFilterMode == FilterMode.SOURCE ? mFilterSourcePredicate : mFilterTargetPredicate;
            var items = mFilterMode == FilterMode.SOURCE ? getSourceItems() : getTargetItems();
            var filteredItems = mUnfilteredItems.stream()
                    .filter(filterPredicate)
                    .filter(t -> !theOtherSideList.contains(t))
                    .sorted(mComparator == null ? (o1, o2) -> 0 : mComparator)
                    .toList();

            items.setAll(filteredItems);
        }
    }
}
