/*
 * Copyright 2020 Patrik Karlström.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class handles the execution flow of runnables with regard to whether a it's key is marked ready or not.
 *
 * It makes no guarantees as to the order of runnables within each key.
 *
 * Each runnable is executed in its own thread.
 *
 *
 * @author Patrik Karlström
 */
public class ExecutionFlow {

    private final Set<String> mExecutedKeys = Collections.synchronizedSet(new HashSet<>());
    private final Map<String, ArrayList<Runnable>> mKeyToRunnablesMap = Collections.synchronizedMap(new HashMap<>());

    public ExecutionFlow() {
    }

    /**
     * Register a runnable to a key.
     *
     * The runnable will be executed once the key is marked ready, if the key is marked ready, it will be run immediately.
     *
     * @param key
     * @param runnable
     */
    public void executeWhenReady(String key, Runnable runnable) {
        mKeyToRunnablesMap.computeIfAbsent(key, k -> new ArrayList<>()).add(runnable);

        if (mExecutedKeys.contains(key)) {
            execute(key, runnable);
        }
    }

    /**
     * Mark a key as ready and execute all runnables for that key in their own threads.
     *
     * @param key
     */
    public void setReady(String key) {
        if (mExecutedKeys.add(key)) {
            mKeyToRunnablesMap.entrySet().stream()
                    .filter(entry -> (entry.getKey().equals(key)))
                    .forEachOrdered(entry -> {
                        entry.getValue().forEach(runnable -> {
                            execute(key, runnable);
                        });
                    });
        }

    }

    private void execute(String key, Runnable runnable) {
        new Thread(runnable, String.format("%s/%s", getClass().getSimpleName(), key)).start();
    }
}
