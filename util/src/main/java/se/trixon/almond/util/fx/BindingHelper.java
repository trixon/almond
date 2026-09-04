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
package se.trixon.almond.util.fx;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class BindingHelper {

    public static void bindCheckBoxEnablement(CheckBox checkBox, Node... nodes) {
        for (var node : nodes) {
            node.disableProperty().bind(checkBox.selectedProperty().not());
        }
    }

    /**
     * Use FxHelper::autoSizeRegionHorizontal instead
     *
     * @param panes
     * @deprecated
     */
    @Deprecated(forRemoval = true)
    public static void bindWidthForChildrens(Pane... panes) {
        for (var pane : panes) {
            pane.getChildren().stream()
                    .filter(Region.class::isInstance)
                    .map(Region.class::cast)
                    .forEach(FxHelper::autoSizeRegionHorizontal);
        }
    }

    /**
     * Use FxHelper::autoSizeRegionHorizontal instead
     *
     * @param pane
     * @param regions
     * @deprecated
     */
    @Deprecated(forRemoval = true)
    public static void bindWidthForRegions(Pane pane, Region... regions) {
        FxHelper.autoSizeRegionHorizontal(regions);
    }

    public static <E extends Enum<E>> StringProperty createStringEnumProxyProperty(ObjectProperty<E> objectProperty, Class<E> enumClass) {
        var stringConverter = new StringConverter<E>() {
            @Override
            public String toString(E enumValue) {
                return enumValue != null ? enumValue.name() : "";
            }

            @Override
            public E fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return Enum.valueOf(enumClass, string);
                }
                return null;
            }
        };

        var stringProperty = new SimpleStringProperty();

        stringProperty.addListener((obs, oldVal, newVal) -> {
            E decodedProperty = stringConverter.fromString(newVal);
            if (!Objects.equals(objectProperty.get(), decodedProperty)) {
                objectProperty.set(decodedProperty);
            }
        });

        objectProperty.addListener((obs, oldVal, newVal) -> {
            var encodedProperty = stringConverter.toString(newVal);
            if (!Objects.equals(stringProperty.get(), encodedProperty)) {
                stringProperty.set(encodedProperty);
            }
        });

        return stringProperty;
    }

    public static void bindBidirectional(ObjectProperty<Double> objectProperty, DoubleProperty doubleProperty) {
        objectProperty.bindBidirectional(doubleProperty.asObject());

        objectProperty.addListener((p, o, n) -> {
            if (!Objects.equals(doubleProperty.get(), n)) {
                doubleProperty.set(n != null ? n : 0);
            }
        });

        doubleProperty.addListener((p, o, n) -> {
            if (!Objects.equals(objectProperty.get(), n.doubleValue())) {
                objectProperty.set(n.doubleValue());
            }
        });
    }

    public static void bindBidirectional(ObjectProperty<Integer> objectProperty, IntegerProperty integerProperty) {
        objectProperty.bindBidirectional(integerProperty.asObject());

        objectProperty.addListener((p, o, n) -> {
            if (!Objects.equals(integerProperty.get(), n)) {
                integerProperty.set(n != null ? n : 0);
            }
        });

        integerProperty.addListener((p, o, n) -> {
            if (!Objects.equals(objectProperty.get(), n.intValue())) {
                objectProperty.set(n.intValue());
            }
        });
    }

}
