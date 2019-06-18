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
package se.trixon.almond.nbp.fx;

import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import se.trixon.almond.util.Dict;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//se.trixon.almond.nbp.fx//NotesHtml//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NotesHtmlTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "se.trixon.almond.nbp.fx.NotesHtmlTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NotesHtmlAction",
        preferredID = "NotesHtmlTopComponent"
)
@Messages({
    "CTL_NotesHtmlAction=Notes"
})
public final class NotesHtmlTopComponent extends FxTopComponent {

    private static final String KEY_NOTES_HTML = "noteshtml";
    private final Preferences mPreferences = NbPreferences.forModule(NotesHtmlTopComponent.class);
    private HTMLEditor mEditor;

    public NotesHtmlTopComponent() {
        setName(Dict.NOTES.toString());
    }

    @Override
    protected void fxComponentOpened() {
        super.fxComponentOpened();
        mEditor.setHtmlText(mPreferences.get(KEY_NOTES_HTML, ""));
    }

    @Override
    protected void initFX() {
        mEditor = new HTMLEditor();
        setScene(new Scene(mEditor));
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
            mPreferences.put(KEY_NOTES_HTML, mEditor.getHtmlText());
        });
    }
}
