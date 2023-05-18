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
package se.trixon.almond.util.fx;

import java.util.HashMap;
import java.util.HashSet;
import javafx.beans.property.BooleanProperty;

/**
 *
 * @author Patrik Karlström
 */
public class CategoryBooleanManager {

    private final HashMap<String, HashSet<BooleanProperty>> mCategoryActions = new HashMap<>();

    public static CategoryBooleanManager getInstance() {
        return Holder.INSTANCE;
    }

    private CategoryBooleanManager() {
    }

    public boolean add(String category, BooleanProperty booleanProperty) {
        return getActions(category).add(booleanProperty);
    }

    public void addAll(String category, BooleanProperty... booleanPropertys) {
        for (BooleanProperty booleanProperty : booleanPropertys) {
            getActions(category).add(booleanProperty);
        }
    }

    public void clear(String category) {
        getActions(category).clear();
    }

    public HashSet<BooleanProperty> getActions(String category) {
        return mCategoryActions.computeIfAbsent(category, k -> new HashSet<>());
    }

    public boolean remove(String category, BooleanProperty booleanProperty) {
        return getActions(category).remove(booleanProperty);
    }

    public void setEnabled(String category, boolean enabled) {
        getActions(category).forEach((booleanProperty) -> {
            booleanProperty.set(enabled);
        });
    }

    private static class Holder {

        private static final CategoryBooleanManager INSTANCE = new CategoryBooleanManager();
    }
}
