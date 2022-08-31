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
package se.trixon.almond.util.fx.dialogs.about;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import se.trixon.almond.util.AboutModel;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.FxHelper;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class AboutPane extends TabPane {

    private final AboutModel mAboutModel;

    public static Action getAction(Stage stage, AboutModel aboutModel) {
        AboutPane aboutPane = new AboutPane(aboutModel);

        Action action = new Action(Dict.ABOUT.toString(), (ActionEvent event) -> {
            aboutPane.reset();
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.initOwner(stage);
            alert.setTitle(Dict.ABOUT_S.toString().formatted(aboutModel.getAppName()));

            ButtonType closeButtonType = new ButtonType(Dict.CLOSE.toString(), ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(closeButtonType);
            alert.setGraphic((ImageView) aboutModel.getLogo());
            alert.setHeaderText(" ");
            alert.setResizable(true);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setContent(aboutPane);
            double scale = SwingHelper.getUIScale();
            dialogPane.setPrefSize(520 * scale, 400 * scale);

            for (var node : dialogPane.getChildren()) {
                if (node instanceof GridPane gridPane) {
                    var appLabel = new Label(aboutModel.getAppName());
                    double scaledFontSize = FxHelper.getScaledFontSize();
                    appLabel.setFont(new Font(scaledFontSize * 1.8));
                    var verLabel = new Label("%s %s".formatted(Dict.VERSION.toString(), aboutModel.getAppVersion()));
                    verLabel.setFont(new Font(scaledFontSize * 1.2));
                    var dateLabel = new Label(aboutModel.getAppDate());
                    dateLabel.setFont(new Font(scaledFontSize * 1.2));

                    var box = new VBox(appLabel, verLabel, dateLabel);
                    box.setAlignment(Pos.CENTER_LEFT);
                    gridPane.add(box, 0, 0);
                    break;
                }
            }

            FxHelper.showAndWait(alert, stage);
        });

        return action;
    }

    public AboutPane(AboutModel aboutModel) {
        mAboutModel = aboutModel;
        init();
    }

    public void reset() {
        getTabs().stream().filter((tab) -> (tab instanceof ResetableTab)).forEachOrdered((tab) -> {
            ((ResetableTab) tab).reset();
        });

        getSelectionModel().selectFirst();
    }

    private void init() {
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        getTabs().add(new AboutTab(mAboutModel));
        getTabs().add(new LibrariesTab(mAboutModel));

        if (mAboutModel.getAuthors() != null) {
            getTabs().add(new AuthorsTab(mAboutModel));
        }

        if (mAboutModel.getTranslation() != null) {
            getTabs().add(new TranslationTab(mAboutModel));
        }

        if (mAboutModel.getTranslation() != null) {
            getTabs().add(new ThanksToTab(mAboutModel));
        }

        getTabs().add(new PropertiesTab(mAboutModel));
    }

    public interface ResetableTab {

        void reset();
    }
}
