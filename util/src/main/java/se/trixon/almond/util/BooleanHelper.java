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

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Patrik Karlström
 */
public class BooleanHelper {

    private static final ResourceBundle sBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "BooleanBundle", Locale.getDefault());

    public static String asCheckBox(boolean value) {
        return asCheckBox(value, "☑", "☐");
    }

    public static String asCheckBox(boolean value, String selected, String unselected) {
        return value ? selected : unselected;
    }

    public static String asCheckBox(boolean value, String text) {
        return asCheckBox(value) + " " + text;
    }

    public static String asCheckBox(boolean value, String text, String selected, String unselected) {
        return asCheckBox(value, selected, unselected) + " " + text;
    }

    public static String asLocalized(boolean value) {
        return sBundle.getString(value ? "true" : "false");
    }

    public static String asYesNo(boolean value) {
        return sBundle.getString(value ? "yes" : "no");
    }
}
