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
package se.trixon.almond.util.fx.dialogs.about;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import se.trixon.almond.util.fx.dialogs.about.AboutPane.ResetableTab;
import se.trixon.almond.util.AboutModel;

/**
 *
 * @author Patrik Karlström
 */
public abstract class BaseListTab extends Tab implements ResetableTab {

    public static final String LIST_SIGN = "  ● ";

    protected final AboutModel mAboutModel;
    protected final TextArea mTextArea = new TextArea();

    public BaseListTab(AboutModel aboutModel, String tabText) {
        mAboutModel = aboutModel;
        setText(tabText);
        setContent(mTextArea);
        mTextArea.setEditable(false);
    }

    @Override
    public void reset() {
        mTextArea.home();
    }
}
