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
package se.trixon.almond.util.swing.dialogs.about;

import org.apache.commons.lang3.StringUtils;
import se.trixon.almond.util.AboutModel;

/**
 *
 * @author Patrik Karlström
 */
public class ThanksToTab extends BaseListTab {

    private transient final AboutModel mAboutModel;

    public ThanksToTab(AboutModel aboutModel) {
        super();
        mAboutModel = aboutModel;
        init();
    }

    private void init() {
        String[] thanksTo = StringUtils.split(mAboutModel.getThanksTo(), ";");

        StringBuilder builder = new StringBuilder();
        for (String thanks : thanksTo) {
            builder.append(LIST_SIGN).append(thanks).append("\n");
        }

        editorPane.setText(builder.toString());
    }
}
