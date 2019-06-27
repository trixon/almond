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
package se.trixon.almond.util.fx;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.lang3.ArrayUtils;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.NotificationPane;
import se.trixon.almond.util.PrefsHelper;
import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlström
 */
public class FxHelper {

    public static final String STYLE_HOVERED_BUTTON = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    public static final String STYLE_IDLE_BUTTON = "-fx-background-color: transparent;";

    private static final String STAGE_ALWAYS_ON_TOP = "AlmondStage_AlwaysOnTop";
    private static final String STAGE_FULL_SCREEN = "AlmondStage_FullScreen";
    private static final String STAGE_H = "AlmondStage_Height";
    private static final String STAGE_MAXIMIZED = "AlmondStage_Maximized";
    private static final String STAGE_W = "AlmondStage_Width";
    private static final String STAGE_X = "AlmondStage_X";
    private static final String STAGE_Y = "AlmondStage_Y";

    public static void adjustButtonWidth(Stream<Node> stream, double prefWidth) {
        stream.filter((item) -> (item instanceof ButtonBase))
                .map((item) -> (ButtonBase) item)
                .forEachOrdered((buttonBase) -> {
                    buttonBase.setPrefWidth(prefWidth);
                });
    }

    public static void autoCommitSpinner(Spinner spinner) {
        spinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                spinner.increment(0);
            }
        });
    }

    public static void autoCommitSpinners(Spinner... spinners) {
        for (Spinner spinner : spinners) {
            autoCommitSpinner(spinner);
        }
    }

    public static void autoSizeColumn(GridPane gridPane, int columnCount) {
        gridPane.getColumnConstraints().clear();

        for (int i = 0; i < columnCount; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / columnCount);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }

    public static void autoSizeRegion(Region... regions) {
        for (Region region : regions) {
            GridPane.setHgrow(region, Priority.ALWAYS);
            GridPane.setFillWidth(region, true);
            region.setMaxWidth(Double.MAX_VALUE);
        }
    }

    public static Color colorFromHexRGBA(String rgba) {
        return Color.web(rgba);
    }

    public static java.awt.Color colorToColor(Color color) {
        if (color == null) {
            return java.awt.Color.BLACK;
        } else {
            return new java.awt.Color(
                    (float) color.getRed(),
                    (float) color.getGreen(),
                    (float) color.getBlue(),
                    (float) color.getOpacity());
        }
    }

    @Deprecated
    public static String colorToHex(Color color) {
        return String.format("%02X%02X%02X",
                (int) (color.getRed() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getBlue() * 0xff));
    }

    public static String colorToHexABGR(Color color) {
        return String.format("%02X%02X%02X%02X",
                (int) (color.getOpacity() * 0xff),
                (int) (color.getBlue() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getRed() * 0xff));
    }

    public static String colorToHexABGR(java.awt.Color color) {
        return String.format("%02X%02X%02X%02X",
                color.getAlpha(),
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static String colorToHexBGR(Color color) {
        return String.format("%02X%02X%02X",
                (int) (color.getBlue() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getRed() * 0xff));
    }

    public static String colorToHexBGR(java.awt.Color color) {
        return String.format("%02X%02X%02X",
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static int colorToHexInt(Color color) {
        return Integer.decode("0x" + colorToHex(color));
    }

    public static String colorToHexRGB(Color color) {
        return String.format("%02X%02X%02X",
                (int) (color.getRed() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getBlue() * 0xff));
    }

    public static String colorToHexRGB(java.awt.Color color) {
        return String.format("%02X%02X%02X",
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static String colorToHexRGBA(java.awt.Color color) {
        return String.format("%02X%02X%02X%02X",
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                color.getAlpha()
        );
    }

    public static String colorToHexRGBA(Color color) {
        return String.format("%02X%02X%02X%02X",
                (int) (color.getRed() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getBlue() * 0xff),
                (int) (color.getOpacity() * 0xff)
        );
    }

    public static int colorToInt(Color color) {
        int r = (int) (color.getRed() * 0xff);
        int g = (int) (color.getGreen() * 0xff);
        int b = (int) (color.getBlue() * 0xff);

        return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
    }

    public static String colorToString(Color color) {
        return "#" + colorToHex(color);
    }

    public static Background createBackground(Color color) {
        return new Background(
                new BackgroundFill(color,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                ));
    }

    public static void disableControls(ObservableList<Node> nodes, boolean disabled, Control... excludedControls) {
        for (Node node : nodes) {
            if (!ArrayUtils.contains(excludedControls, node)) {
                if (node instanceof Pane) {
                    Pane pane = (Pane) node;
                    disableControls(pane.getChildrenUnmodifiable(), disabled, excludedControls);
                } else if (node instanceof ToolBar) {
                    ToolBar toolBar = (ToolBar) node;
                    disableControls(toolBar.getItems(), disabled, excludedControls);
                } else {
                    try {
                        node.setDisable(disabled);
                    } catch (Exception e) {
                        //Fails on Bound value cannot be set. E.g Button with an Action
                    }
                }
            }
        }
    }

    public static boolean isAlwaysOnTop(Class c) {
        return Preferences.userNodeForPackage(c).getBoolean(STAGE_ALWAYS_ON_TOP, false);
    }

    public static boolean isDarkThemeEnabled() {
        return Boolean.getBoolean("trixon.almond.fx.dark");
    }

    public static boolean isFullScreen(Class c) {
        return Preferences.userNodeForPackage(c).getBoolean(STAGE_FULL_SCREEN, false);
    }

    public static void loadDarkTheme(Scene scene) {
        if (isDarkThemeEnabled()) {
            scene.getStylesheets().add("css/modena_dark.css");
        }
    }

    public static void loadDarkTheme(Parent parent) {
        if (isDarkThemeEnabled()) {
            parent.getStylesheets().add("css/modena_dark.css");
        }
    }

    public static void loadDarkTheme(Parent... parents) {
        if (isDarkThemeEnabled()) {
            for (Parent parent : parents) {
                loadDarkTheme(parent);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(colorToHexBGR(java.awt.Color.red));
    }

    public static void matrixRotateNode(Node node, double alf, double bet, double gam) {
        double A11 = Math.cos(alf) * Math.cos(gam);
        double A12 = Math.cos(bet) * Math.sin(alf) + Math.cos(alf) * Math.sin(bet) * Math.sin(gam);
        double A13 = Math.sin(alf) * Math.sin(bet) - Math.cos(alf) * Math.cos(bet) * Math.sin(gam);
        double A21 = -Math.cos(gam) * Math.sin(alf);
        double A22 = Math.cos(alf) * Math.cos(bet) - Math.sin(alf) * Math.sin(bet) * Math.sin(gam);
        double A23 = Math.cos(alf) * Math.sin(bet) + Math.cos(bet) * Math.sin(alf) * Math.sin(gam);
        double A31 = Math.sin(gam);
        double A32 = -Math.cos(gam) * Math.sin(bet);
        double A33 = Math.cos(bet) * Math.cos(gam);

        double d = Math.acos((A11 + A22 + A33 - 1d) / 2d);

        if (d != 0d) {
            double den = 2d * Math.sin(d);
            Point3D p = new Point3D((A32 - A23) / den, (A13 - A31) / den, (A21 - A12) / den);
            node.setRotationAxis(p);
            node.setRotate(Math.toDegrees(d));
        }
    }

    public static void notify(String message, NotificationPane notificationPane, MaskerPane maskerPane, int iconSize) {
        Platform.runLater(() -> {
            maskerPane.setVisible(false);
            notificationPane.show(message, MaterialIcon._Action.INFO_OUTLINE.getImageView(iconSize));
        });

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
                Platform.runLater(() -> {
                    notificationPane.hide();
                });
            } catch (InterruptedException ex) {
                //Exceptions.printStackTrace(ex);
            }
        }).start();
    }

    public static void setEditable(boolean editable, Spinner... spinners) {
        for (Spinner spinner : spinners) {
            spinner.setEditable(editable);
        }
    }

    public static void setDarkThemeEnabled(boolean enabled) {
        System.setProperty("trixon.almond.fx.dark", String.valueOf(enabled));
    }

    public static void setMargin(Insets insets, Region... regions) {
        for (Region region : regions) {
            GridPane.setMargin(region, insets);
        }
    }

    public static void setPadding(Insets insets, Region... regions) {
        for (Region region : regions) {
            region.setPadding(insets);
        }
    }

    public static Optional showAndWait(Dialog dialog, Stage stage) {
        Stage alertStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        if (stage != null) {
            alertStage.setAlwaysOnTop(stage.isAlwaysOnTop());
        }
        alertStage.toFront();

        return dialog.showAndWait();
    }

    public static void undecorateButton(ButtonBase buttonBase) {
        buttonBase.setStyle(STYLE_IDLE_BUTTON);
        buttonBase.setOnMouseEntered(e -> buttonBase.setStyle(STYLE_HOVERED_BUTTON));
        buttonBase.setOnMouseExited(e -> buttonBase.setStyle(STYLE_IDLE_BUTTON));
    }

    public static void undecorateButtons(Stream<Node> stream) {
        stream.filter((item) -> (item instanceof ButtonBase))
                .map((item) -> (ButtonBase) item)
                .forEachOrdered((buttonBase) -> {
                    undecorateButton(buttonBase);
                });
    }

    public static void windowStateRestore(Stage stage, double defaultWidth, double defaultHeight, Class c) throws BackingStoreException {
        Preferences p = Preferences.userNodeForPackage(c);

        if (PrefsHelper.keyExists(p, STAGE_X) && PrefsHelper.keyExists(p, STAGE_Y) && PrefsHelper.keyExists(p, STAGE_H) && PrefsHelper.keyExists(p, STAGE_W)) {
            stage.setX(p.getDouble(STAGE_X, -1));
            stage.setY(p.getDouble(STAGE_Y, -1));

            stage.setWidth(p.getDouble(STAGE_W, -1));
            stage.setHeight(p.getDouble(STAGE_H, -1));

            stage.setAlwaysOnTop(p.getBoolean(STAGE_ALWAYS_ON_TOP, false));
            stage.setFullScreen(p.getBoolean(STAGE_FULL_SCREEN, false));
            stage.setMaximized(p.getBoolean(STAGE_MAXIMIZED, false));
        } else {
            stage.setWidth(defaultWidth);
            stage.setHeight(defaultHeight);
            stage.centerOnScreen();
        }
    }

    public static void windowStateSave(Stage stage, Class c) {
        Preferences p = Preferences.userNodeForPackage(c);

        p.putDouble(STAGE_H, stage.getHeight());
        p.putDouble(STAGE_W, stage.getWidth());
        p.putDouble(STAGE_X, stage.getX());
        p.putDouble(STAGE_Y, stage.getY());

        p.putBoolean(STAGE_ALWAYS_ON_TOP, stage.isAlwaysOnTop());
        p.putBoolean(STAGE_FULL_SCREEN, stage.isFullScreen());
        p.putBoolean(STAGE_MAXIMIZED, stage.isMaximized());
    }

}
