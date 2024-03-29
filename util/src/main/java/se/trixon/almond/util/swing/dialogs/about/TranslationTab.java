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
package se.trixon.almond.util.swing.dialogs.about;

import org.apache.commons.lang3.StringUtils;
import se.trixon.almond.util.AboutModel;

/**
 *
 * @author Patrik Karlström
 */
public class TranslationTab extends BaseListTab {

    private transient final AboutModel mAboutModel;

    public TranslationTab(AboutModel aboutModel) {
        super();
        mAboutModel = aboutModel;
        init();
    }

    private void init() {
        var translators = StringUtils.split(mAboutModel.getTranslation(), ";");
        var builder = new StringBuilder();

        for (var translator : translators) {
            builder.append(LIST_SIGN).append(translator).append("\n");
        }

        editorPane.setText(builder.toString());
        reset();
    }
}
