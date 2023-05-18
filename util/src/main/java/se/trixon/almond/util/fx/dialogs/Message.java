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

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class Message {

    public static void error(Window owner, String title, String headerText, String contentText) {
        display(AlertType.ERROR, owner, title, headerText, contentText);
    }

    public static void error(Node node, String title, String headerText, String contentText) {
        display(AlertType.ERROR, node, title, headerText, contentText);
    }

    public static void information(Node node, String title, String headerText, String contentText) {
        display(AlertType.INFORMATION, node, title, headerText, contentText);
    }

    public static void information(Window owner, String title, String headerText, String contentText) {
        display(AlertType.INFORMATION, owner, title, headerText, contentText);
    }

    public static void none(Node node, String title, String headerText, String contentText) {
        display(AlertType.NONE, node, title, headerText, contentText);
    }

    public static void none(Window owner, String title, String headerText, String contentText) {
        display(AlertType.NONE, owner, title, headerText, contentText);
    }

    public static void warning(Node node, String title, String headerText, String contentText) {
        display(AlertType.WARNING, node, title, headerText, contentText);
    }

    public static void warning(Window owner, String title, String headerText, String contentText) {
        display(AlertType.WARNING, owner, title, headerText, contentText);
    }

    private static void display(AlertType alertType, Node node, String title, String headerText, String contentText) {
        Window owner = null;

        try {
            owner = node.getScene().getWindow();
        } catch (Exception e) {
            //nvm
        }

        display(alertType, owner, title, headerText, contentText);
    }

    private static void display(AlertType alertType, Window owner, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.initOwner(owner);
        if (title != null) {
            alert.setTitle(title);
        }
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        FxHelper.showAndWait(alert, (Stage) owner);
    }
}
