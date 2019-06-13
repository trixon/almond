/*
 * Copyright 2019 Patrik Karlström.
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

import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class DateRangePane extends GridPane {

    private DateRangeSlider mDateRangeSlider;
    private DatePicker mFromDatePicker;
    private DatePicker mToDatePicker;

    public DateRangePane() {
        createUI();
        initListeners();
    }

    public void addFromDatePickerListener(ChangeListener<Object> changeListener) {
        mFromDatePicker.valueProperty().addListener(changeListener);
    }

    public void addToDatePickerListener(ChangeListener<Object> changeListener) {
        mToDatePicker.valueProperty().addListener(changeListener);
    }

    public DateRangeSlider getDateRangeSlider() {
        return mDateRangeSlider;
    }

    public DatePicker getFromDatePicker() {
        return mFromDatePicker;
    }

    public DatePicker getToDatePicker() {
        return mToDatePicker;
    }

    public void setMinMaxDate(LocalDate minDate, LocalDate maxDate) {
        mDateRangeSlider.setMinMaxDate(minDate, maxDate);

        Callback<DatePicker, DateCell> selectionLimiter = d -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean empty) {
                super.updateItem(localDate, empty);
                setDisable(localDate.isAfter(maxDate) || localDate.isBefore(minDate));
            }
        };

        mFromDatePicker.setDayCellFactory(selectionLimiter);
        mToDatePicker.setDayCellFactory(selectionLimiter);
    }

    private void createUI() {
        mDateRangeSlider = new DateRangeSlider();
        mDateRangeSlider.prefWidthProperty().bind(widthProperty());
        GridPane.setColumnSpan(mDateRangeSlider, GridPane.REMAINING);

        mFromDatePicker = new DatePicker();
        mFromDatePicker.setValue(LocalDate.now().minusDays(7));
        mFromDatePicker.setEditable(false);
        GridPane.setFillWidth(mFromDatePicker, true);
        GridPane.setHgrow(mFromDatePicker, Priority.ALWAYS);

        mToDatePicker = new DatePicker();
        mToDatePicker.setValue(LocalDate.now());
        mToDatePicker.setEditable(false);
        GridPane.setFillWidth(mToDatePicker, true);
        GridPane.setHgrow(mToDatePicker, Priority.ALWAYS);

        Label fromLabel = new Label(Dict.FROM.toString());
        Label toLabel = new Label(Dict.TO.toString());
        FxHelper.setPadding(new Insets(2, 0, 0, 0), fromLabel, toLabel);
        FxHelper.setMargin(new Insets(0, 0, 0, 8), toLabel, mToDatePicker);

        setPadding(new Insets(8));
        addRow(0, mDateRangeSlider);
        addRow(1, fromLabel, toLabel);
        addRow(2, mFromDatePicker, mToDatePicker);

        mDateRangeSlider.lowDateProperty().bindBidirectional(mFromDatePicker.valueProperty());
        mDateRangeSlider.highDateProperty().bindBidirectional(mToDatePicker.valueProperty());
    }

    private void initListeners() {
    }
}
