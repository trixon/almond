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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

public class CheckModelSession {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final ObservableList mAllItems;
    private final IndexedCheckModel mCheckModel;
    private final SimpleStringProperty mCheckedStringProperty = new SimpleStringProperty();
    private final AtomicInteger mInternalUpdateDepth = new AtomicInteger();

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
        runInternal(() -> {
            mCheckModel.clearChecks();
            for (var storedItem : deserialize(mCheckedStringProperty.get())) {
                for (var listItem : mAllItems) {
                    if (Strings.CS.equals(storedItem, listItem.toString())) {
                        mCheckModel.check(listItem);
                        break;
                    }
                }
            }
        });
    }

    private List<String> deserialize(String value) {
        if (value == null) {
            return Collections.emptyList();
        }

        value = value.trim();
        if (value.startsWith("json:")) {
            try {
                return MAPPER.readValue(
                        value.substring(5),
                        new TypeReference<List<String>>() {
                });
            } catch (JsonProcessingException e) {
                throw new IllegalStateException(
                        "Failed to parse JSON selection state",
                        e);
            }
        }

        if (value.isEmpty()) {
            return Collections.emptyList();
        }

        var tokens = StringUtils.splitPreserveAllTokens(value, ":::");

        return tokens == null
                ? Collections.emptyList()
                : Arrays.asList(tokens);
    }

    private void initListeners() {
        mCheckedStringProperty.addListener((p, o, n) -> {
            if (isInternalUpdate()) {
                return;
            }

            load();
        });

        mCheckModel.getCheckedItems().addListener((ListChangeListener.Change c) -> {
            if (isInternalUpdate()) {
                return;
            }

            var json = serialize(
                    mCheckModel.getCheckedItems()
                            .stream()
                            .map(String::valueOf)
                            .toList());

            runInternal(() -> mCheckedStringProperty.set(json));
        });
    }

    private boolean isInternalUpdate() {
        return mInternalUpdateDepth.get() > 0;
    }

    private void runInternal(Runnable runnable) {
        mInternalUpdateDepth.incrementAndGet();

        try {
            runnable.run();
        } finally {
            mInternalUpdateDepth.decrementAndGet();
        }
    }

    private String serialize(List<String> values) {
        try {
            return "json:" + MAPPER.writeValueAsString(values);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                    "Failed to serialize selection state",
                    e);
        }
    }
}
