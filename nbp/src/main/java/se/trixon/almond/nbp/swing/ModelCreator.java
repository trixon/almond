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
package se.trixon.almond.nbp.swing;

import javax.swing.DefaultComboBoxModel;
import se.trixon.almond.util.Dict;

/**
 *
 *
 *
 * @author Patrik Karlström
 *
 */
public class ModelCreator {

    public static DefaultComboBoxModel<String> createDefaultComboBoxModel(Model model) {

        switch (model) {

            case BEGINS_CONTAINS_ENDS:

                return new DefaultComboBoxModel<>(new String[]{
                    Dict.STARTS_WITH.toString(),
                    Dict.CONTAINS.toString(),
                    Dict.ENDS_WITH.toString()});

            default:

                return null;

        }

    }

    public enum Model {

        /**
         *
         * Begins with, contains, ends with.
         *
         */
        BEGINS_CONTAINS_ENDS;

    }

}
