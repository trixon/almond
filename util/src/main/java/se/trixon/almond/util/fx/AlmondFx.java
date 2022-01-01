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
package se.trixon.almond.util.fx;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import javafx.stage.Stage;
import se.trixon.almond.util.AlmondGui;

/**
 *
 * @author Patrik Karlström
 */
public class AlmondFx extends AlmondGui {

    private static final Logger LOGGER = Logger.getLogger(AlmondFx.class.getName());
    private Stage mStage;

    public static AlmondFx getInstance() {
        return Holder.INSTANCE;
    }

    private AlmondFx() {
    }

    public void addStageWatcher(Stage stage, Class c) {
        mStage = stage;
        stage.setOnCloseRequest(windowEvent -> {
            FxHelper.windowStateSave(stage, c);
        });

        try {
            FxHelper.windowStateRestore(stage, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT, c);
        } catch (BackingStoreException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private static class Holder {

        private static final AlmondFx INSTANCE = new AlmondFx();
    }
}
