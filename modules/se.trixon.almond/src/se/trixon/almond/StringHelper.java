/*
 * Copyright 2015 Patrik Karlsson.
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
package se.trixon.almond;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class StringHelper {

    public static String[] intervalStringToArray(String intervalString) {
        ArrayList<String> arrayList = new ArrayList<>();

        String[] intervalItems = StringUtils.split(intervalString, ",");
        for (String intervalItem : intervalItems) {
            intervalItem = intervalItem.trim();

            if (intervalItem.contains("-")) {
                String startStop[] = StringUtils.split(intervalItem, "-");
                int start = Integer.valueOf(startStop[0]);
                int stop = Integer.valueOf(startStop[1]);
                
                for (int i = start; i < stop + 1; i++) {
                    arrayList.add(String.valueOf(i));
                }
            } else {
                arrayList.add(intervalItem);
            }
        }

        return Arrays.copyOf(arrayList.toArray(), arrayList.toArray().length, String[].class);
    }
}
