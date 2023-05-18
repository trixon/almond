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
package se.trixon.almond.util.fx.dialogs;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import org.apache.commons.lang3.StringUtils;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.OptionsBase;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class OptionalDialog {

    private static final Options sOptions = new Options();

    static {
        setPreferences(Preferences.userNodeForPackage(OptionalDialog.class));
    }

    public static boolean requestShowDialog(Class cls, String id, AlertType alertType, Window window, String title, String headerText, String message, String checkBoxText) {
        String key = cls.getName() + "_" + id;

        if (sOptions.is(key, true)) {
            var label = new Label(message);
            label.setAlignment(Pos.TOP_LEFT);

            var borderPane = new BorderPane(label);
            label.prefHeightProperty().bind(borderPane.heightProperty());
            label.prefWidthProperty().bind(borderPane.widthProperty());

            var checkBox = new CheckBox(StringUtils.defaultString(checkBoxText, Dict.SHOW_THIS_AGAIN.toString()));
            checkBox.setSelected(true);
            FxHelper.setPadding(new Insets(16, 0, 0, 16), checkBox);
            borderPane.setBottom(checkBox);

            var alert = new Alert(alertType);
            alert.initOwner(window);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.getDialogPane().setContent(borderPane);
            alert.setResizable(true);
            alert.showAndWait();

            sOptions.put(key, checkBox.isSelected());

            return true;
        }

        return false;
    }

    public static void reset(String key) {
        sOptions.put(key, true);
    }

    public static void resetAll() {
        try {
            sOptions.getPreferences().clear();
        } catch (BackingStoreException ex) {
            Logger.getLogger(OptionalDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setPreferences(Preferences preferences) {
        sOptions.setPreferences(preferences.node("optionalDialogState"));
    }

    public static class Options extends OptionsBase {
    }
}
