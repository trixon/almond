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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrik Karlström
 */
public class CategoryActionManager {

    private final HashMap<String, HashSet<Object>> mCategoryActions = new HashMap<>();

    public static CategoryActionManager getInstance() {
        return Holder.INSTANCE;
    }

    private CategoryActionManager() {
    }

    public boolean add(String category, Object o) {
        try {
            Method method = o.getClass().getMethod("setDisabled", boolean.class);
            return getActions(category).add(o);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(CategoryActionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void addAll(String category, Object... actions) {
        for (Object action : actions) {
            getActions(category).add(action);
        }
    }

    public void clear(String category) {
        getActions(category).clear();
    }

    public HashSet<Object> getActions(String category) {
        return mCategoryActions.computeIfAbsent(category, k -> new HashSet<>());
    }

    public boolean remove(String category, Object action) {
        return getActions(category).remove(action);
    }

    public void setEnabled(String category, boolean enabled) {
        getActions(category).forEach((o) -> {
            try {
                Method method = o.getClass().getMethod("setDisabled", boolean.class);
                method.invoke(o, !enabled);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                try {
                    Method method = o.getClass().getMethod("setDisable", boolean.class);
                    method.invoke(o, !enabled);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex2) {
                    Logger.getLogger(CategoryActionManager.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        });
    }

    private static class Holder {

        private static final CategoryActionManager INSTANCE = new CategoryActionManager();
    }
}
