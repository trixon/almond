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
package se.trixon.almond.util.swing.dialogs.about;

import se.trixon.almond.util.AboutModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class TranslationTab extends BaseListTab {

    private final AboutModel mAboutModel;

    public TranslationTab(AboutModel aboutModel) {
        super();
        mAboutModel = aboutModel;
        init();
    }

    private void init() {
        String[] translators = StringUtils.split(mAboutModel.getTranslation(), ";");

        StringBuilder builder = new StringBuilder();
        for (String translator : translators) {
            builder.append(LIST_SIGN).append(translator).append("\n");
        }

        editorPane.setText(builder.toString());
    }
}
