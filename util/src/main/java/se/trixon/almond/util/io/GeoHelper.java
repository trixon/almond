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
package se.trixon.almond.util.io;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class GeoHelper {

    public static String[] getKeyVal(String line) {
        int a = StringUtils.indexOf(line, '"', 0);
        int b = StringUtils.indexOf(line, '"', a + 1);
        int c = StringUtils.indexOf(line, '"', b + 1);
        int d = StringUtils.indexOf(line, '"', c + 1);

        String key = StringUtils.substring(line, a + 1, b);
        String val = "";
        if (c > b) {
            val = StringUtils.substring(line, c + 1, d);
        }

        return new String[]{key, val};
    }

    public static LinkedList<String> getSection(String sectionHeader, String nextSectionHeader, LinkedList<String> lines) {
        int rowCounter = 0;
        int open = 0;
        boolean hitSection = false;

        for (String line : lines) {
            if (hitSection && StringUtils.startsWithIgnoreCase(line.trim(), sectionHeader)) {
                break;
            }
            rowCounter++;

            if (hitSection) {
                if (line.trim().equalsIgnoreCase("begin")) {
                    open++;
                } else if (line.trim().equalsIgnoreCase("end")) {
                    open--;
                }

                if (open == 0 || StringUtils.startsWithIgnoreCase(line.trim(), nextSectionHeader) && hitSection) {
                    break;
                }
            }

            if (StringUtils.startsWithIgnoreCase(line.trim(), sectionHeader)) {
                hitSection = true;
            }
        }

        LinkedList<String> section = new LinkedList(lines.subList(0, rowCounter));

        GeoHelper.removeHead(lines, rowCounter);
//        System.out.println("SECTION " + sectionHeader);
//        System.out.println(String.join("\n", section));
//        System.out.println("REMAINING");
//        System.out.println(String.join("\n", lines));
        return section;
    }

    public static StringBuilder lineListToStringBuilder(LinkedList<GeoLine> geoLines) {
        StringBuilder sb = new StringBuilder();
        sb.append("LineList").append(Geo.LINE_ENDING);

        if (!geoLines.isEmpty()) {
            sb.append("begin").append(Geo.LINE_ENDING);
            for (GeoLine geoLine : geoLines) {
                sb.append("\t".repeat(1)).append(geoLine.toString());
            }
            sb.append("end").append(Geo.LINE_ENDING);
        }

        return sb;
    }

    public static LinkedList<GeoPoint> parsePointList(LinkedList<String> section) {
        stripWrapper(section);
        LinkedList<GeoPoint> points = new LinkedList<>();

        while (!section.isEmpty()) {
            points.add(new GeoPoint(GeoHelper.getSection("Point ", "Point", section)));
        }

        return points;
    }

    public static StringBuilder pointListToStringBuilder(LinkedList<GeoPoint> geoPoints, int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(indentLevel)).append("PointList").append(Geo.LINE_ENDING);

        if (!geoPoints.isEmpty()) {
            sb.append("\t".repeat(indentLevel)).append("begin").append(Geo.LINE_ENDING);
            for (GeoPoint geoPoint : geoPoints) {
                sb.append("\t".repeat(indentLevel + 1)).append(geoPoint.toString());//.append(Geo.LINE_ENDING);
            }
            sb.append("\t".repeat(indentLevel)).append("end").append(Geo.LINE_ENDING);
        }

        return sb;
    }

    public static void removeHead(List lines, int rows) {
        for (int i = 0; i < rows; i++) {
            lines.remove(0);
        }
    }

    public static void stripWrapper(LinkedList<String> section) {
        if (section.size() > 2) {
            GeoHelper.removeHead(section, 2);
            section.removeLast();
        }
    }
}
