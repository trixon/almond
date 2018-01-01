/* 
 * Copyright 2018 Patrik Karlsson.
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

/**
 *
 * @author Patrik Karlsson
 */
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.Icon;
import javax.swing.JLabel;
import se.trixon.almond.nbp.NbLog;

public class UriLabel extends JLabel {

    private URI mUri;

    public UriLabel() {
        init();
    }

    public UriLabel(Icon icon) {
        super(icon);
        init();
    }

    public UriLabel(Icon icon, int horizontalAlignment) {
        super(icon, horizontalAlignment);
        init();
    }

    public UriLabel(String text) {
        super(text);
        init();
    }

    public UriLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        init();
    }

    public UriLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    @Override
    public void setText(String text) {
        super.setText("<html><a href=\"\">" + text + "</a>");
    }

    public void setURI(URI uri) {
        mUri = uri;
    }

    public void setURI(String uriString) {
        try {
            mUri = new URI(uriString);
        } catch (URISyntaxException ex) {
            System.err.println(ex);
        }
    }

    private void init() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                launchURI(evt);
            }
        });
    }

    private void launchURI(MouseEvent evt) {
        if (mUri != null && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(mUri);
            } catch (IOException | UnsupportedOperationException ex) {
                NbLog.e(this.getClass(), ex.getLocalizedMessage());
            }
        }
    }
}
