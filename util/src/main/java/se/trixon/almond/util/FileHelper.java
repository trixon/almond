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

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class FileHelper {

    public static String convertStreamToString(java.io.InputStream is) {
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }

    public static String replaceInvalidChars(String s) {
        String[] invalidChars = new String[]{"<", ">", ":", "\"", "/", "\\", "|", "?", "*"};
        String[] replaceChars = new String[]{"_", "_", "_", "_", "_", "_", "_", "_", "_"};

        return StringUtils.replaceEach(s, invalidChars, replaceChars);
    }
}
