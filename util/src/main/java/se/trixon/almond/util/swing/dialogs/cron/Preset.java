/* 
 * Copyright 2022 Patrik Karlström.
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
package se.trixon.almond.util.swing.dialogs.cron;

/**
 *
 * @author Patrik Karlström
 */
public class Preset {

    private String mName;
    private String mPattern;

    public Preset() {
    }

    public Preset(String name, String pattern) {
        mName = name;
        mPattern = pattern;
    }

    public String getName() {
        return mName;
    }

    public String getPattern() {
        return mPattern;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPattern(String pattern) {
        mPattern = pattern;
    }

    @Override
    public String toString() {
        if (mName == null) {
            return "";
        } else {
            return mName;
        }
    }
}
