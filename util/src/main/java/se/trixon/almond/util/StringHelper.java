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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class StringHelper {

    public static String arrayToIntervalString(int[] indices) {
        if (indices == null || indices.length == 0) {
            return "";
        }

        for (int index : indices) {
            if (index < 0) {
                return "";
            }
        }

        Arrays.sort(indices);

        StringBuilder builder = new StringBuilder();
        LinkedList<BlockItem> blockItems = new LinkedList<>();

        int prev = -1;
        int next;
        int lastStart = 0;
        int length = 0;

        log(StringUtils.repeat("FOR ", 10));
        log(StringUtils.repeat(indices.length + " ", 40));

        for (int i = 0; i < indices.length; i++) {
            log(StringUtils.repeat("I ", 30));
            log(StringUtils.repeat(i + " ", 30));

            int current = indices[i];

            if (i == 0) {
                lastStart = current;
                prev = current - 1;
            } else if (length == 0) {
                lastStart = current;
            }

            length++;

            try {
                next = indices[i + 1];
            } catch (IndexOutOfBoundsException e) {
                next = -1;
            }

            boolean endOfBlock = current > prev + 1 || i == indices.length - 1;
            if (next > -1 && !endOfBlock) {
                endOfBlock = current + 1 < next;
            }

            log("count=" + indices.length);
            log("i=" + i);
            log("prev=" + prev);
            log("current=" + current);
            log("next=" + next);
            log("length=" + length);
            log("lastStart=" + lastStart);
            log("endOfBlock=" + endOfBlock);

            if (endOfBlock) {
                BlockItem blockItem = new BlockItem();
                blockItem.length = length;
                blockItem.start = lastStart;
                blockItems.add(blockItem);
                length = 0;
            }

            prev = next - 1;
        }

        for (BlockItem blockItem : blockItems) {
            log(blockItem.toString());

            builder.append(blockItem.start);

            if (blockItem.length == 2) {
                builder.append(",").append(blockItem.start + 1);
            } else if (blockItem.length > 2) {
                builder.append("-").append(blockItem.start + blockItem.length - 1);
            }

            builder.append(",");
        }

        char lastChar = builder.charAt(builder.length() - 1);
        if (lastChar == ',') {
            builder.deleteCharAt(builder.length() - 1);
        }

        log(builder.toString());

        return builder.toString();
    }

    public static String booleanToYesNo(boolean value) {
        return value ? Dict.YES.toString() : Dict.NO.toString();
    }

    /**
     *
     * @param string
     * @return 1,2,3,7,8,9 from "1-3,7-9"
     */
    public static TreeSet<Integer> convertStringToIntSet(String string) {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        String[] args = string.split(",");

        for (String arg : args) {
            try {
                int value = Integer.valueOf(arg.trim());
                treeSet.add(value);
            } catch (java.lang.NumberFormatException e) {
                String[] interval = arg.split("-");
                int start = Math.min(Integer.valueOf(interval[0].trim()), Integer.valueOf(interval[1].trim()));
                int stop = Math.max(Integer.valueOf(interval[0].trim()), Integer.valueOf(interval[1].trim()));
                for (int j = start; j <= stop; j++) {
                    treeSet.add(j);
                }
            }
        }

        return treeSet;
    }

    public static String createRegexFromGlob(String glob) {
        //https://stackoverflow.com/questions/1247772/is-there-an-equivalent-of-java-util-regex-for-glob-type-patterns

        String out = "^";
        for (int i = 0; i < glob.length(); ++i) {
            final char c = glob.charAt(i);

            switch (c) {
                case '*':
                    out += ".*";
                    break;

                case '?':
                    out += '.';
                    break;

                case '.':
                    out += "\\.";
                    break;

                case '\\':
                    out += "\\\\";
                    break;

                default:
                    out += c;
            }
        }

        out += '$';

        return out;
    }

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

    /**
     * @param searchIn the string to search in
     * @param glob the glob pattern
     * @param ignoreCase <code>true</code> if the match is case insensitive,
     * else <code>false</code>.
     * @param autoWrap <code>true</code> if two * should wrap the @param glob,
     * else <code>false</code>.
     * @return The result of the match
     */
    public static boolean matchesSimpleGlob(String searchIn, String glob, boolean ignoreCase, boolean autoWrap) {
        searchIn = StringUtils.defaultString(searchIn);
        glob = StringUtils.defaultString(glob);

        if (autoWrap && !StringUtils.contains(glob, "*")) {
            glob = "*" + glob + "*";
        }
        String regex = createRegexFromGlob(glob);

        try {
            if (ignoreCase) {
                return searchIn.toLowerCase().matches(regex);
            } else {
                return searchIn.matches(regex);
            }
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    private static void log(String string) {
//        System.out.println(string);
//        Xlog.v(StringHelper.class, string);
    }

    private static class BlockItem {

        private int length;
        private int start;

        @Override
        public String toString() {
            return "BlockItem{" + "start=" + start + ", length=" + length + '}';
        }
    }
}
