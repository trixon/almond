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

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Patrik Karlström
 */
public class CsvHelper {

    public static String getOrDefault(CSVRecord record, String key, String defaultValue) {
        if (record.isSet(key)) {
            return record.get(key);
        } else {
            return defaultValue;
        }
    }

    public static boolean isValidCsv(CSVParser records, String[] columns) {
        for (String column : columns) {
            if (records.getHeaderMap().containsKey(column)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

}
