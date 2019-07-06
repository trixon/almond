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

/**
 *
 * @author Patrik Karlström
 */
public class ArrayHelper {

    public static int[] adjustOffset(int[] integers, int offset) {
        for (int i = 0; i < integers.length; i++) {
            integers[i] = integers[i] + offset;
        }

        return integers;
    }

    public static int continuousValueToArrayIndex(int size, double value, double beg, double end) {
        int index = size - 1;

        if (value <= beg) {
            index = 0;
        } else if (value >= end) {
            //nop
        } else {
            double step = (end - beg) / size;
            double lowLimit = Double.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                double limit = beg + i * step;
                if (value >= lowLimit && value <= limit) {
                    index = i;
                    break;
                }

                lowLimit = limit;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        System.out.println(continuousValueToArrayIndex(32, 0.0099, -0.01, 0.01));
    }

    public static int[] stringToInt(String[] strings) throws NumberFormatException {
        int[] integers = new int[strings.length];

        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.valueOf(strings[i]);
        }

        return integers;
    }
}
