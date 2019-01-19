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
package se.trixon.almond.util;

import java.util.HashMap;
import java.util.HashSet;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Patrik Karlström
 */
public class GlobalState {

    private final HashMap<String, Object> mKeyObjectMap = new HashMap<>();
    private final HashMap<GlobalStateChangeListener, String[]> mListenerKeyMap = new HashMap<>();
    private final HashSet<GlobalStateChangeListener> mListeners = new HashSet<>();

    public void addListener(GlobalStateChangeListener listener, String... keys) {
        mListeners.add(listener);
        mListenerKeyMap.put(listener, keys);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(mKeyObjectMap.get(key));
    }

    public <T> T get(String key) {
        return (T) (mKeyObjectMap.get(key));
    }

    public void put(String key, Object object) {
        mKeyObjectMap.put(key, object);
        GlobalStateChangeEvent event = new GlobalStateChangeEvent(key, object, this) {
            @Override
            public <T> T getValue() {
                return (T) object;
            }
        };

        for (GlobalStateChangeListener listener : mListeners) {
            final String[] listenerKeys = mListenerKeyMap.get(listener);
            if (listenerKeys.length == 0 || ArrayUtils.contains(listenerKeys, key)) {
                listener.globalStateChange(event);
            }
        }

    }

    public void removeAllListeners() {
        mListeners.clear();
    }

    public void removeListener(GlobalStateChangeListener listener) {
        mListenerKeyMap.remove(listener);
        mListeners.remove(listener);
    }
}
