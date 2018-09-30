/*
 * Copyright 2018 Patrik Karlström.
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class GeoHeader {

    private String mFileHeader;
    private String mLineEnding = "\r\n";
    private final LinkedHashMap<String, String> mMap = new LinkedHashMap<>();

    public GeoHeader() {
    }

    public GeoHeader(String fileHeader) {
        mFileHeader = "FileHeader " + fileHeader;
    }

    public GeoHeader(String fileHeader, LinkedHashMap<String, String> map) {
        mFileHeader = "FileHeader " + fileHeader;
        mMap.putAll(map);
    }

    public GeoHeader(ArrayList<String> lines) {
        boolean begin = false;

        for (String line : lines) {
            if (StringUtils.startsWithIgnoreCase(line, "FileHeader")) {
                mFileHeader = line;
            }

            if (StringUtils.startsWithIgnoreCase(line, "end")) {
                break;
            }

            if (begin) {
                int a = StringUtils.indexOf(line, '"', 0);
                int b = StringUtils.indexOf(line, '"', a + 1);
                int c = StringUtils.indexOf(line, '"', b + 1);
                int d = StringUtils.indexOf(line, '"', c + 1);

                String key = StringUtils.substring(line, a + 1, b);
                String val = "";
                if (c > b) {
                    val = StringUtils.substring(line, c + 1, d);
                }

                mMap.put(key, val);
            }

            if (mFileHeader != null && StringUtils.startsWithIgnoreCase(line, "begin")) {
                begin = true;
            }
        }
    }

    public String get(String key) {
        return mMap.get(key);
    }

    public void put(String key, String value) {
        mMap.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(mFileHeader).append(mLineEnding);
        builder.append("begin").append(mLineEnding);
        for (String key : mMap.keySet()) {
            builder.append(String.format("\tFileInfo \"%s\",\"%s\"", key, mMap.get(key))).append(mLineEnding);
        }
        builder.append("end").append(mLineEnding);

        return builder.toString();
    }
}
