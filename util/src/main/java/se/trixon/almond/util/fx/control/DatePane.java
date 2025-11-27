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
package se.trixon.almond.util.fx.control;

import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.apache.commons.lang3.Strings;
import org.controlsfx.control.PlusMinusSlider;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class DatePane extends GridPane {

    private DateRangeSlider mDateRangeSlider;
    private DateSelectionMode mDateSelectionMode;
    private DateSlider mDateSlider;
    private DatePicker mFromDatePicker;
    private final PlusMinusSlider mFromSlider = new PlusMinusSlider();
    private DatePicker mToDatePicker;
    private final PlusMinusSlider mToSlider = new PlusMinusSlider();

    public DatePane() {
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

    public DateSelectionMode getDateSelectionMode() {
        return mDateSelectionMode;
    }

    public DatePicker getFromDatePicker() {
        return mFromDatePicker;
    }

    public DatePicker getToDatePicker() {
        return mToDatePicker;
    }

    public void reset() {
        mDateRangeSlider.reset();
    }

    public void setDateSelectionMode(DateSelectionMode dateSelectionMode) {
        mDateSelectionMode = dateSelectionMode;
        getChildren().removeAll(mDateSlider, mDateRangeSlider);
        addRow(0, dateSelectionMode == DateSelectionMode.INTERVAL ? mDateRangeSlider : mDateSlider);
        mToDatePicker.setDisable(dateSelectionMode == DateSelectionMode.POINT_IN_TIME);

        if (dateSelectionMode == DateSelectionMode.INTERVAL) {
            mDateSlider.dateProperty().unbindBidirectional(mFromDatePicker.valueProperty());
            mDateSlider.dateProperty().unbindBidirectional(mToDatePicker.valueProperty());

            mDateRangeSlider.lowDateProperty().bindBidirectional(mFromDatePicker.valueProperty());
            mDateRangeSlider.highDateProperty().bindBidirectional(mToDatePicker.valueProperty());
        } else {
            mDateRangeSlider.lowDateProperty().unbindBidirectional(mFromDatePicker.valueProperty());
            mDateRangeSlider.highDateProperty().unbindBidirectional(mToDatePicker.valueProperty());

            mDateSlider.dateProperty().bindBidirectional(mToDatePicker.valueProperty());
            mDateSlider.dateProperty().bindBidirectional(mFromDatePicker.valueProperty());
        }
    }

    public void setMinMaxDate(LocalDate minDate, LocalDate maxDate) {
        mDateSlider.setMinMaxDate(minDate, maxDate);
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

    private EventHandler<MouseEvent> createPlusMinusMouseHandler(DatePicker datePicker) {
        return mouseEvent -> {
            //System.out.println(mouseEvent);
            if (mouseEvent.getTarget() instanceof Node node) {
                var styleClass = node.getStyleClass().toString();
                LocalDate newDate = null;
                if (Strings.CI.contains(styleClass, "adjust-minus")) {
                    newDate = datePicker.getValue().minusDays(1);
                } else if (Strings.CI.contains(styleClass, "adjust-plus")) {
                    newDate = datePicker.getValue().plusDays(1);
                }
                if (newDate != null && mDateRangeSlider.isValid(newDate)) {
                    datePicker.setValue(newDate);
                }
            }
        };
    }

    private void createUI() {
        mDateRangeSlider = new DateRangeSlider();
        mDateRangeSlider.prefWidthProperty().bind(widthProperty());
        GridPane.setColumnSpan(mDateRangeSlider, GridPane.REMAINING);

        mDateSlider = new DateSlider();
        mDateSlider.prefWidthProperty().bind(widthProperty());

        GridPane.setColumnSpan(mDateSlider, GridPane.REMAINING);

        mFromDatePicker = new DatePicker();
        mFromDatePicker.setValue(LocalDate.of(1900, 1, 1));
        mFromDatePicker.setEditable(true);

        mToDatePicker = new DatePicker();
        mToDatePicker.setValue(LocalDate.of(2099, 12, 31));
        mToDatePicker.setEditable(true);

        FxHelper.setMargin(new Insets(8, 0, 0, 0), mFromDatePicker);
        FxHelper.setMargin(new Insets(8, 0, 0, 8), mToDatePicker);

        setPadding(FxHelper.getUIScaledInsets(0, 8, 8, 8));
        addRow(1, mFromDatePicker, mToDatePicker);
        addRow(2, mFromSlider, mToSlider);

        setDateSelectionMode(DateSelectionMode.INTERVAL);
        FxHelper.autoSizeRegionHorizontal(mFromDatePicker, mFromSlider, mToDatePicker, mToSlider);
        setMinWidth(FxHelper.getUIScaled(250));
    }

    private void initListeners() {
        mFromSlider.setOnValueChanged(pme -> {
            jog(mFromDatePicker, pme.getValue());
        });

        mToSlider.setOnValueChanged(pme -> {
            jog(mToDatePicker, pme.getValue());
        });

        mFromSlider.setOnMouseClicked(createPlusMinusMouseHandler(mFromDatePicker));
        mToSlider.setOnMouseClicked(createPlusMinusMouseHandler(mToDatePicker));
    }

    private void jog(DatePicker datePicker, double value) {
        long days = (long) Math.min(value * 7, 3);
        var date = datePicker.getValue().plusDays(days);
        if (mDateRangeSlider.isValid(date)) {
            datePicker.setValue(date);
        }
    }
}
