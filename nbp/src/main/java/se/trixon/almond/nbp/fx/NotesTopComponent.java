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
package se.trixon.almond.nbp.fx;

import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import se.trixon.almond.util.Dict;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//se.trixon.almond.nbp.fx//Notes//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NotesTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "se.trixon.almond.nbp.fx.NotesTopComponent")
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NotesAction",
        preferredID = "NotesTopComponent"
)
@Messages({
    "CTL_NotesAction=Notes"
})
public final class NotesTopComponent extends FxTopComponent {

    private static final String KEY_NOTES = "notes";
    private transient final Preferences mPreferences = NbPreferences.forModule(NotesTopComponent.class);
    private transient TextArea mTextArea;

    public NotesTopComponent() {
        setName(Dict.NOTES.toString());
    }

    @Override
    protected void fxComponentOpened() {
        super.fxComponentOpened();
        mTextArea.setText(mPreferences.get(KEY_NOTES, ""));
    }

    @Override
    protected void initFX() {
        mTextArea = new TextArea();
        setScene(new Scene(mTextArea));
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
        Platform.runLater(() -> {
            mPreferences.put(KEY_NOTES, mTextArea.getText());
        });
    }
}
