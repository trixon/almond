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
package se.trixon.almond.nbp.dialogs;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.NotifyDescriptor;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.SnapHelper;

/**
 *
 * @author Patrik Karlström
 */
public class NbSnapHelper {

    public static void checkSnapStatus(Class cls, String id, String snapName, String... plugsAndSlots) {
        try {
            if (SnapHelper.isSnap()) {
                StringBuilder sb = new StringBuilder();
                for (String plugsAndSlot : plugsAndSlots) {
                    if (!SnapHelper.isConnected(plugsAndSlot)) {
                        sb.append("sudo snap connect %s:%s\n".formatted(snapName, plugsAndSlot));
                    }
                }

                if (sb.length() > 0) {
                    String message = "It looks like you are running '%s' as a snap".formatted(snapName) + "\n\nFor a better experience, consider executing:\n\n"
                            + sb.toString()
                            + "\nor through the Permissions UI in the snap-store application.";

                    NbOptionalDialog.requestShowDialog(
                            cls,
                            id,
                            NotifyDescriptor.INFORMATION_MESSAGE,
                            Dict.INFORMATION.toString(),
                            message,
                            null
                    );
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }
}
