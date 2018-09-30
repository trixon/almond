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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import se.trixon.almond.util.AboutModel;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.UriLabel;

/**
 *
 * @author Patrik Karlström
 */
public class AboutTab extends Tab {

    public AboutTab(AboutModel aboutModel) {
        setText(Dict.ABOUT.toString());

        Label desc = new Label(aboutModel.getAppDescription());
        Label copyright = new Label(aboutModel.getAppCopyright());
        UriLabel appHyperlink = new UriLabel(aboutModel.getAppUrlTitle());
        UriLabel licenseHyperlink = new UriLabel(String.format("%s: %s", Dict.LICENSE.toString(), aboutModel.getAppLicenseUrlTitle()));

        appHyperlink.setUri(aboutModel.getAppUrl());
        licenseHyperlink.setUri(aboutModel.getAppLicenseUrl());

        VBox box = new VBox(8, desc, copyright, appHyperlink, licenseHyperlink);
        box.setAlignment(Pos.CENTER_LEFT);
        final int PADDING = 20;
        box.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        setContent(box);
    }
}
