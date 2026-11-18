/*
 * Copyright 2023 Patrik Karlström <patrik@trixon.se>.
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

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javafx.stage.Stage;
import org.controlsfx.dialog.ExceptionDialog;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class ExceptionDialogDisplayerHandler extends Handler {

    private final Stage mStage;

    public ExceptionDialogDisplayerHandler(Stage stage) {
        mStage = stage;
    }

    @Override
    public void close() throws SecurityException {
    }

    @Override
    public void flush() {
    }

    @Override
    public void publish(LogRecord logRecord) {
        if (logRecord.getThrown() != null) {
            FxHelper.runLater(() -> {
                var exceptionDialog = new ExceptionDialog(logRecord.getThrown());
                exceptionDialog.initOwner(mStage);

                var dialogPane = exceptionDialog.getDialogPane();
                FxHelper.applyFontScale(dialogPane.getScene());
                FxHelper.showAndWait(exceptionDialog, mStage);
            });
        }
    }
}
