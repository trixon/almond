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
package se.trixon.almond.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Patrik Karlström
 */
public class GlobalState {

    private final Map<String, Object> mKeyObjectMap = Collections.synchronizedMap(new HashMap<>());
    private final Map<GlobalStateChangeListener, String[]> mListenerKeyMap = Collections.synchronizedMap(new HashMap<>());
    private final Set<GlobalStateChangeListener> mListeners = Collections.synchronizedSet(new HashSet<>());

    synchronized public void addListener(GlobalStateChangeListener listener, String... keys) {
        mListeners.add(listener);
        mListenerKeyMap.put(listener, keys);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(mKeyObjectMap.get(key));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) (mKeyObjectMap.get(key));
    }

    @SuppressWarnings("unchecked")
    public <T> T getOrDefault(String key, Object o) {
        return (T) (mKeyObjectMap.getOrDefault(key, o));
    }

    synchronized public void put(String key, Object object) {
        put(key, object, false);
    }

    public void removeAllListeners() {
        mListeners.clear();
    }

    public void removeListener(GlobalStateChangeListener listener) {
        mListenerKeyMap.remove(listener);
        mListeners.remove(listener);
    }

    public void send(String key, Object object) {
        put(key, object, true);
    }

    @SuppressWarnings("unchecked")
    private void put(String key, Object object, boolean _volatile) {
        if (!_volatile) {
            mKeyObjectMap.put(key, object);
        }

        GlobalStateChangeEvent event = new GlobalStateChangeEvent(key, object, this, _volatile) {
            @Override
            public <T> T getValue() {
                return (T) object;
            }
        };

        synchronized (this) {
            for (GlobalStateChangeListener listener : new HashSet<>(mListeners)) {
                final String[] listenerKeys = mListenerKeyMap.get(listener);
                if (listenerKeys.length == 0 || ArrayUtils.contains(listenerKeys, key)) {
                    listener.globalStateChange(event);
                }
            }
        }
    }
}
