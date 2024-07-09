/*
 * Copyright 2024 Patrik Karlström.
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
package se.trixon.almond.util.fx.control.editable_list;

import java.util.Objects;

/**
 *
 * @author Patrik Karlström
 */
public class DefaultEditableListItem implements EditableListItem {

    private String mName;

    public DefaultEditableListItem() {
    }

    public DefaultEditableListItem(String name) {
        mName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultEditableListItem other = (DefaultEditableListItem) obj;
        return Objects.equals(this.mName, other.mName);
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.mName);
        return hash;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }

}
