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
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
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

//        int current;
        int prev = -1;
//        int next;
//        int nextNext;
        int lastStart = 0;
        int length = 0;

        for (int i = 0; i < indices.length; i++) {
            log("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            int current = indices[i];

            if (i == 0) {
                lastStart = current;
//                prev = -1;
            } else if (length == 0) {
                lastStart = current;
            }

            length++;

//            next = -1;
//            nextNext = -1;
//
//            try {
//                next = indices[i + 1];
//                try {
//                    nextNext = indices[i + 2];
//                } catch (IndexOutOfBoundsException e) {
//                }
//            } catch (IndexOutOfBoundsException e) {
//            }
//            boolean addBlock = (next - current > 1 && nextNext - current > 2) || nextNext == -1;
            boolean endOfBlock = current > prev + 1 || i == indices.length - 1;

            log("c="+current);
            if (endOfBlock) {
                BlockItem blockItem = new BlockItem();
                blockItem.length = length;
                blockItem.start = lastStart;
                blockItems.add(blockItem);
                length = 0;
            }

            prev = current;
        }

//        blockItems.clear();
//        BlockItem bi;
//        
//        bi= new BlockItem();        
//        bi.start = 0;
//        bi.length = 2;
//        blockItems.add(bi);
//        
//        bi = new BlockItem();
//        bi.start = 5;
//        bi.length = 1;
//        blockItems.add(bi);
//        
//        bi = new BlockItem();
//        bi.start = 8;
//        bi.length = 3;
//        blockItems.add(bi);
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

//        char lastChar = builder.charAt(builder.length() - 1);
//        if (lastChar == ',') {
//            builder.deleteCharAt(builder.length() - 1);
//        }
        StringBuilder builder1 = new StringBuilder();
        for (int index : indices) {
            builder1.append(index).append(",");
        }

        log(builder1.toString());
        log(builder.toString());

        return builder.toString();
    }

    public static String XXXarrayToIntervalStringXXX(int[] indices) {
        System.err.println("arrayToIntervalString");
        Xlog.v(StringHelper.class, "arrayToIntervalString");

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

//        for (int index : indices) {
//            builder.append(index).append(",");
//        }
        int currentStart = indices[0];
        builder.append(indices[0]);
        int prev;
        int current;
        int next;

        for (int i = 1; i < indices.length; i++) {
            System.err.println("for...");
            Xlog.v(StringHelper.class, "for..");
            prev = indices[i - 1];
            current = indices[i];

            if (i < indices.length - 1) {
                next = indices[i + 1];
            } else {
                next = -1;
            }

            Xlog.v(StringHelper.class, "p=" + prev);
            Xlog.v(StringHelper.class, "c=" + current);
            Xlog.v(StringHelper.class, "n=" + next);

            System.err.println("p=" + prev);
            System.err.println("c=" + current);
            System.err.println("n=" + next);
            //1,2,4,5,6
            //1,2,4-6
            char lastChar = builder.charAt(builder.length() - 1);

            if (current - prev > 1 || next - current < 1) {
                builder.append(",");
            }

            if (next - current < 1) {
                builder.append("-");
            }

            if (next - current > 1 || next == -1) {
                builder.append(current);
            }
        }

//        for (int index : indices) {
//        }
//
//        if (builder.length() > 0) {
//            builder.deleteCharAt(builder.length() - 1);
//        }
        StringBuilder builder1 = new StringBuilder();
        for (int index : indices) {
            builder1.append(index).append(",");
        }

        Xlog.v(StringHelper.class, builder1.toString());
        Xlog.v(StringHelper.class, builder.toString());

        return builder.toString();
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

    private static void log(String string) {
        System.err.println(string);
        Xlog.v(StringHelper.class, string);
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
