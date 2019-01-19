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
public abstract class GlobalStateChangeEvent extends java.util.EventObject {

    private String mKey;
    private Object mObject;

    public GlobalStateChangeEvent(String key, Object object, Object source) {
        super(source);
        mKey = key;
        mObject = object;
    }

    public String getKey() {
        return mKey;
    }

    public Object getObject() {
        return mObject;
    }

    public abstract <T> T getValue();
}
