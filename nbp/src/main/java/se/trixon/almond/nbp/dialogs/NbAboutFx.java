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
package se.trixon.almond.nbp.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.swing.SwingUtilities;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import se.trixon.almond.nbp.fx.FxDialogPanel;
import se.trixon.almond.util.AboutModel;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.FxHelper;
import se.trixon.almond.util.fx.dialogs.about.AboutPane;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class NbAboutFx extends FxDialogPanel {

    private transient final AboutModel mAboutModel;
    private transient final AboutPane mAboutPane;

    public NbAboutFx(AboutModel aboutModel) {
        mAboutModel = aboutModel;
        mAboutPane = new AboutPane(aboutModel);
    }

    public void display() {
        mAboutPane.reset();
        SwingHelper.makeWindowResizable(this);

        DialogDescriptor d = new DialogDescriptor(
                this,
                String.format(Dict.ABOUT_S.toString(), mAboutModel.getAppName()),
                true,
                new Object[]{Dict.CLOSE.toString()},
                Dict.CLOSE.toString(),
                0,
                null,
                null
        );

        initFx(() -> {
        });

        SwingUtilities.invokeLater(() -> {
            setPreferredSize(SwingHelper.getUIScaledDim(520, 400));
            DialogDisplayer.getDefault().notify(d);
        });
    }

    @Override
    protected void fxConstructor() {
        setScene(createScene());
    }

    private Scene createScene() {
        double scaledFontSize = FxHelper.getScaledFontSize();
        Label appLabel = new Label(mAboutModel.getAppName());
        appLabel.setFont(new Font(scaledFontSize * 1.8));
        Label verLabel = new Label(String.format("%s %s", Dict.VERSION.toString(), mAboutModel.getAppVersion()));
        verLabel.setFont(new Font(scaledFontSize * 1.2));
        Label dateLabel = new Label(mAboutModel.getAppDate());
        dateLabel.setFont(new Font(scaledFontSize * 1.2));

        VBox box = new VBox(appLabel, verLabel, dateLabel);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(0, 0, 0, 22));
        BorderPane topBorderPane = new BorderPane(box);
        topBorderPane.setLeft((Node) mAboutModel.getLogo());
        topBorderPane.setPadding(new Insets(22));
        BorderPane mainBorderPane = new BorderPane(mAboutPane);
        mainBorderPane.setTop(topBorderPane);

        return new Scene(mainBorderPane);
    }
}
