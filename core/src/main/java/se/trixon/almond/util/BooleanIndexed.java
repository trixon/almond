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

import java.util.ArrayList;

/**
 *
 * @author Patrik Karlström
 */
public class BooleanIndexed<T> {

    private final ArrayList<T> mItems = new ArrayList<>();

    public BooleanIndexed() {
        this(null, null);
    }

    public BooleanIndexed(T asFalse, T asTrue) {
        mItems.add(asFalse);
        mItems.add(asTrue);
    }

    public T asFalse() {
        return get(false);
    }

    public T asFalse(T t) {
        var prev = asFalse();
        set(false, t);

        return prev;
    }

    public T asTrue() {
        return get(true);
    }

    public T asTrue(T t) {
        var prev = asTrue();
        set(true, t);

        return prev;
    }

    public T get(boolean index) {
        return mItems.get(index ? 1 : 0);
    }

    public void set(boolean index, T t) {
        mItems.set(index ? 1 : 0, t);
    }
}
