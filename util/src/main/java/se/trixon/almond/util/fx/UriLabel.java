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
package se.trixon.almond.util.fx;

import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import se.trixon.almond.util.SystemHelper;

/**
 *
 * @author Patrik Karlström
 */
public class UriLabel extends Hyperlink {

    private URI mUri;

    public UriLabel() {
        init();
    }

    public UriLabel(String text) {
        super(text);
        init();
    }

    public UriLabel(String text, Node graphic) {
        super(text, graphic);
        init();
    }

    public URI getUri() {
        return mUri;
    }

    public void setUri(URI uri) {
        mUri = uri;
    }

    public void setUri(String uriString) {
        try {
            mUri = new URI(uriString);
        } catch (URISyntaxException ex) {
            System.err.println(ex);
        }
    }

    private void init() {
        setOnAction((ActionEvent event) -> {
            SystemHelper.desktopBrowse(mUri.toString());
        });
    }
}
