/*
 * Copyright 2023 Patrik Karlström <patrik@trixon.se>.
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
package se.trixon.almond.util.fx.control.editable_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.FxHelper;
import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 * @param <T>
 */
public class EditableList<T extends EditableListItem> extends BorderPane {

    private List<Action> mActions;
    private Action mAddAction;
    private final Builder mBuilder;
    private Action mCloneAction;
    private Action mEditAction;
    private Action mInfoAction;
    private final ListView<T> mListView = new ListView<>();
    private Action mRemAction;
    private Action mRemAllAction;
    private Action mSaveAction;
    private Action mStartAction;
    private final Label mTitleLabel = new Label();
    private ToolBar mToolBar;

    public EditableList(Builder builder) {
        mBuilder = builder;
        createUI();
        applyConfiguration();
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    public String getDialogTitleEdit(T item) {
        return "%s %s".formatted(item == null ? Dict.ADD.toString() : Dict.EDIT.toString(), mBuilder.mItemSingular.toLowerCase(Locale.ROOT));
    }

    public ListView<T> getListView() {
        return mListView;
    }

    public Label getTitleLabel() {
        return mTitleLabel;
    }

    public ToolBar getToolBar() {
        return mToolBar;
    }

    public void postEdit(T t) {
        mListView.getSelectionModel().select(t);
        mListView.requestFocus();
    }

    public void refreshIcons() {
        var size = FxHelper.getUIScaled(mBuilder.getIconSize());
        mAddAction.setGraphic(MaterialIcon._Content.ADD.getImageView(size));
        mRemAction.setGraphic(MaterialIcon._Content.REMOVE.getImageView(size));
        mRemAllAction.setGraphic(MaterialIcon._Content.CLEAR.getImageView(size));
        mSaveAction.setGraphic(MaterialIcon._Content.SAVE.getImageView(size));
        mEditAction.setGraphic(MaterialIcon._Editor.MODE_EDIT.getImageView(size));
        mCloneAction.setGraphic(MaterialIcon._Content.CONTENT_COPY.getImageView(size));
        mInfoAction.setGraphic(MaterialIcon._Action.INFO_OUTLINE.getImageView(size));
        mStartAction.setGraphic(MaterialIcon._Av.PLAY_ARROW.getImageView(size));

    }

    public void select(T t) {
        mListView.requestFocus();
        mListView.getSelectionModel().select(t);
        FxHelper.scrollToItemIfNotVisible(mListView, t);
    }

    protected boolean confirm(String title, String header, String content, String buttonText) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(buttonText);

        var result = FxHelper.showAndWait(alert, (Stage) getScene().getWindow());
        return result.get() == ButtonType.OK;
    }

    private void applyConfiguration() {
        mRemAllAction.disabledProperty().bind(Bindings.isEmpty((ObservableList<T>) mBuilder.itemsProperty().get()));
        mListView.itemsProperty().bind(mBuilder.itemsProperty());
    }

    private void createUI() {
        mAddAction = new Action(toolTipTextSingular(Dict.ADD.toString()), actionEvent -> {
            edit(null);
        });

        mRemAction = new Action(toolTipTextSingular(Dict.REMOVE.toString()), actionEvent -> {
            var baseTitle = Dict.Dialog.TITLE_REMOVE_S.toString().formatted(mBuilder.getItemSingular().toLowerCase(Locale.ENGLISH));
            var action = Dict.Dialog.TITLE_REMOVE_S.toString().formatted("'%s'".formatted(getSelected().getName()));
            var baseHeader = Dict.Dialog.YOU_ARE_ABOUT_TO_S.toString().formatted(action.toLowerCase(Locale.ENGLISH));

            if (confirm(baseTitle + "?",
                    baseHeader,
                    Dict.Dialog.ACTION_CANT_BE_UNDONE.toString(),
                    baseTitle)) {
                mBuilder.getOnRemove().accept(getSelected());
            }
        });

        mRemAllAction = new Action(toolTipTextPlural(Dict.REMOVE_ALL.toString()), actionEvent -> {
            var baseTitle = Dict.Dialog.TITLE_REMOVE_ALL_S.toString().formatted(mBuilder.getItemPlural().toLowerCase(Locale.ENGLISH));
            var action = Dict.Dialog.TITLE_REMOVE_ALL_S.toString().formatted(mBuilder.getItemPlural().toLowerCase(Locale.ENGLISH));
            var baseHeader = Dict.Dialog.YOU_ARE_ABOUT_TO_S.toString().formatted(action.toLowerCase(Locale.ENGLISH));

            if (confirm(
                    baseTitle + "?",
                    baseHeader,
                    Dict.Dialog.ACTION_CANT_BE_UNDONE.toString(),
                    baseTitle)) {
                mBuilder.getOnRemoveAll().run();
            }
        });

        mSaveAction = new Action(Dict.SAVE.toString(), actionEvent -> {
//            save(getSelected());
        });

        mEditAction = new Action(toolTipTextSingular(Dict.EDIT.toString()), actionEvent -> {
            edit(getSelected());
        });

        mCloneAction = new Action(toolTipTextSingular(Dict.CLONE.toString()), actionEvent -> {
            onClone();
        });

        mInfoAction = new Action(toolTipTextSingular(Dict.INFORMATION.toString()), actionEvent -> {
            mBuilder.getOnInfo().accept(getSelected());
        });

        mStartAction = new Action(toolTipTextSingular(Dict.RUN.toString()), actionEvent -> {
            mBuilder.getOnStart().accept(getSelected());
        });

        mActions = new ArrayList<>();

        if (mBuilder.getOnEdit() != null) {
            mActions.add(mAddAction);
        }
        if (mBuilder.getOnRemove() != null) {
            mActions.add(mRemAction);
        }
        if (mBuilder.getOnSave() != null) {
            mActions.add(mSaveAction);
        }
        if (mBuilder.getOnEdit() != null) {
            mActions.add(mEditAction);
        }
        if (mBuilder.getOnClone() != null) {
            mActions.add(mCloneAction);
        }
        if (mBuilder.getOnRemoveAll() != null) {
            mActions.add(mRemAllAction);
        }

        mActions.add(ActionUtils.ACTION_SPAN);
        if (mBuilder.getOnInfo() != null) {
            mActions.add(mInfoAction);
        }
        if (mBuilder.getOnStart() != null) {
            mActions.add(mStartAction);
        }

        var nullSelectionBooleanBinding = mListView.getSelectionModel().selectedItemProperty().isNull();
        mEditAction.disabledProperty().bind(nullSelectionBooleanBinding);
        mCloneAction.disabledProperty().bind(nullSelectionBooleanBinding);
        mRemAction.disabledProperty().bind(nullSelectionBooleanBinding);
        mInfoAction.disabledProperty().bind(nullSelectionBooleanBinding);
        mStartAction.disabledProperty().bind(nullSelectionBooleanBinding);

        mToolBar = ActionUtils.createToolBar(mActions, ActionUtils.ActionTextBehavior.HIDE);
        FxHelper.undecorateButtons(mToolBar.getItems().stream());
        FxHelper.slimToolBar(mToolBar);

        var vbox = new VBox(mToolBar);
        if (StringUtils.isNotBlank(mBuilder.getTitle())) {
            mTitleLabel.setText(mBuilder.getTitle());
            vbox.getChildren().add(0, mTitleLabel);
            mTitleLabel.setFont(Font.font(FxHelper.getScaledFontSize() * 1.2));
            FxHelper.setPadding(FxHelper.getUIScaledInsets(5, 0, 0, 10), mTitleLabel);
        }
        setTop(vbox);
        setCenter(mListView);

        if (mBuilder.mOnSelect != null) {
            mListView.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> {
                mBuilder.mOnSelect.accept(o, n);
            });
        }

        refreshIcons();
    }

    private void edit(T item) {
        mBuilder.getOnEdit().accept(getDialogTitleEdit(item), item);
    }

    private T getSelected() {
        return mListView.getSelectionModel().getSelectedItem();
    }

    private void onClone() {
        var newItem = (T) mBuilder.getOnClone().apply(getSelected());
        mListView.getSelectionModel().select(newItem);
        mListView.requestFocus();
        edit(getSelected());
    }

    private String toolTipTextPlural(String s) {
        return "%s %s".formatted(s, mBuilder.mItemPlural.toLowerCase(Locale.ROOT));
    }

    private String toolTipTextSingular(String s) {
        return "%s %s".formatted(s, mBuilder.mItemSingular.toLowerCase(Locale.ROOT));
    }

    public static class Builder<T extends EditableListItem> {

        private int mIconSize = 32;
        private String mItemPlural = Dict.ITEMS.toString();
        private String mItemSingular = Dict.ITEM.toString();
        private ObjectProperty<ObservableList<T>> mItemsProperty = new SimpleObjectProperty<>();
        private Function<T, T> mOnClone;
        private BiConsumer<String, T> mOnEdit;
        private Consumer<T> mOnInfo;
        private Consumer<T> mOnRemove;
        private Runnable mOnRemoveAll;
        private Function<T, T> mOnSave;
        private BiConsumer<T, T> mOnSelect;
        private Consumer<T> mOnStart;
        private String mTitle;

        public Builder() {
            mItemsProperty.setValue(FXCollections.observableArrayList());
        }

        public EditableList<T> build() {
            return new EditableList<>(this);
        }

        public int getIconSize() {
            return mIconSize;
        }

        public String getItemPlural() {
            return mItemPlural;
        }

        public String getItemSingular() {
            return mItemSingular;
        }

        public Function<T, T> getOnClone() {
            return mOnClone;
        }

        public BiConsumer<String, T> getOnEdit() {
            return mOnEdit;
        }

        public Consumer<T> getOnInfo() {
            return mOnInfo;
        }

        public Consumer<T> getOnRemove() {
            return mOnRemove;
        }

        public Runnable getOnRemoveAll() {
            return mOnRemoveAll;
        }

        public Function<T, T> getOnSave() {
            return mOnSave;
        }

        public BiConsumer<T, T> getOnSelect() {
            return mOnSelect;
        }

        public Consumer<T> getOnStart() {
            return mOnStart;
        }

        public String getTitle() {
            return mTitle;
        }

        public ObjectProperty<ObservableList<T>> itemsProperty() {
            return mItemsProperty;
        }

        public Builder<T> setIconSize(int iconSize) {
            mIconSize = iconSize;
            return this;
        }

        public Builder<T> setItemPlural(String itemPlural) {
            mItemPlural = itemPlural;
            return this;
        }

        public Builder<T> setItemSingular(String itemSingular) {
            mItemSingular = itemSingular;
            return this;
        }

        public Builder<T> setItemsProperty(ObjectProperty<ObservableList<T>> itemsProperty) {
            mItemsProperty = itemsProperty;
            return this;
        }

        public Builder<T> setOnClone(Function<T, T> onClone) {
            mOnClone = onClone;
            return this;
        }

        public Builder<T> setOnEdit(BiConsumer<String, T> onEdit) {
            mOnEdit = onEdit;
            return this;
        }

        public Builder<T> setOnInfo(Consumer<T> onInfo) {
            mOnInfo = onInfo;
            return this;
        }

        public Builder<T> setOnRemove(Consumer<T> onRemove) {
            mOnRemove = onRemove;
            return this;
        }

        public Builder<T> setOnRemoveAll(Runnable onRemoveAll) {
            mOnRemoveAll = onRemoveAll;
            return this;
        }

        public Builder<T> setOnSave(Function<T, T> onSave) {
            mOnSave = onSave;
            return this;
        }

        public Builder<T> setOnSelect(BiConsumer<T, T> onSelect) {
            mOnSelect = onSelect;
            return this;
        }

        public Builder<T> setOnStart(Consumer<T> onStart) {
            mOnStart = onStart;
            return this;
        }

        public Builder<T> setTitle(String title) {
            mTitle = title;
            return this;
        }
    }
}
