/*
 * Copyright 2021 Patrik Karlström.
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
package se.trixon.almond.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import se.trixon.almond.util.fx.dialogs.OptionalDialog;

/**
 *
 * @author Patrik Karlström
 */
public class SnapHelperFx {

    public static void checkSnapStatus(Class cls, String id, Window window, String snapName, String... plugsAndSlots) {
        try {
            if (SnapHelper.isSnap()) {
                StringBuilder sb = new StringBuilder();
                for (String plugsAndSlot : plugsAndSlots) {
                    if (!SnapHelper.isConnected(plugsAndSlot)) {
                        sb.append(String.format("sudo snap connect %s:%s\n", snapName, plugsAndSlot));
                    }
                }

                if (sb.length() > 0) {
                    String message = "For a better experience, consider executing:\n\n"
                            + sb.toString()
                            + "\nor through the Permissions UI in the snap-store application.";

                    OptionalDialog.requestShowDialog(
                            cls,
                            id,
                            AlertType.INFORMATION,
                            window,
                            "Information",
                            String.format("It looks like you are running '%s' as a snap", snapName),
                            message,
                            null
                    );
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
