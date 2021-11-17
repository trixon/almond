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
package se.trixon.almond.util.io;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class GeoHeader {

    private String mFileHeader;
    private final LinkedHashMap<String, String> mFileInfos = new LinkedHashMap<>();

    public GeoHeader() {
    }

    public GeoHeader(String fileHeader) {
        mFileHeader = "FileHeader " + fileHeader;
    }

    public GeoHeader(String fileHeader, LinkedHashMap<String, String> map) {
        mFileHeader = "FileHeader " + fileHeader;
        mFileInfos.putAll(map);
    }

    public GeoHeader(LinkedList<String> lines) {
        mFileHeader = lines.get(0);
        for (var line : lines) {
            if (StringUtils.startsWithIgnoreCase(line, "FileHeader") || StringUtils.equalsAny(line.trim(), "begin", "end")) {
                continue;
            }

            String[] keyVal = GeoHelper.getKeyVal(line);
            mFileInfos.put(keyVal[0], keyVal[1]);
        }
    }

    public String get(String key) {
        return mFileInfos.get(key);
    }

    public void put(String key, String value) {
        mFileInfos.put(key, value);
    }

    @Override
    public String toString() {
        return toStringBuilder().toString();
    }

    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append(mFileHeader).append(Geo.LINE_ENDING);
        sb.append("begin").append(Geo.LINE_ENDING);
        for (Map.Entry<String, String> entry : mFileInfos.entrySet()) {
            sb.append(String.format("\tFileInfo \"%s\",\"%s\"", entry.getKey(), entry.getValue())).append(Geo.LINE_ENDING);
        }
        sb.append("end").append(Geo.LINE_ENDING);

        return sb;
    }
}
