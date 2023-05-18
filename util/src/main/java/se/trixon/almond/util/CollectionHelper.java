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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.SortOrder;

/**
 *
 * @author Patrik Karlström
 */
public class CollectionHelper {

    public static Double decDouble(Map<String, Double> map, String key) {
        incDouble(map, key, -1);

        return map.get(key);
    }

    public static Double decDouble(Map<String, Double> map, String key, double d) {
        incDouble(map, key, -d);

        return map.get(key);
    }

    public static Integer decInteger(Map<String, Integer> map, String key) {
        incInteger(map, key, -1);

        return map.get(key);
    }

    public static Integer decInteger(Map<String, Integer> map, String key, int i) {
        incInteger(map, key, -i);

        return map.get(key);
    }

    public static Double incDouble(Map<String, Double> map, String key) {
        incDouble(map, key, 1.0);

        return map.get(key);
    }

    public static Double incDouble(Map<String, Double> map, String key, double d) {
        map.put(key, map.getOrDefault(key, 0.0) + d);

        return map.get(key);
    }

    public static Integer incInteger(Map<String, Integer> map, String key, int i) {
        map.put(key, map.getOrDefault(key, 0) + i);

        return map.get(key);
    }

    public static Integer incInteger(Map<String, Integer> map, String key) {
        incInteger(map, key, 1);

        return map.get(key);
    }

    public static void moveItem(List list, int sourceIndex, int targetIndex) {
        if (sourceIndex <= targetIndex) {
            Collections.rotate(list.subList(sourceIndex, targetIndex + 1), -1);
        } else {
            Collections.rotate(list.subList(targetIndex, sourceIndex + 1), 1);
        }
    }

    public static void replaceIfAfter(Map<String, Timestamp> map, String key, Timestamp newTimestamp) {
        Timestamp oldTimestamp = map.getOrDefault(key, new Timestamp(Long.MIN_VALUE));
        map.put(key, DateHelper.getMax(oldTimestamp, newTimestamp));
    }

    public static void replaceIfBefore(Map<String, Timestamp> map, String key, Timestamp newTimestamp) {
        Timestamp oldTimestamp = map.getOrDefault(key, new Timestamp(Long.MAX_VALUE));
        map.put(key, DateHelper.getMin(oldTimestamp, newTimestamp));
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return sortByValue(map, SortOrder.ASCENDING);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, SortOrder sortOrder) {
        //https://stackoverflow.com/a/2581754
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        if (sortOrder == SortOrder.DESCENDING) {
            Collections.reverse(list);
        }

        Map<K, V> result = new LinkedHashMap<>();

        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
