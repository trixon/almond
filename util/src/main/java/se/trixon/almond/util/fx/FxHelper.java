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

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.skin.ListViewSkin;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.lang3.ArrayUtils;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.action.Action;
import se.trixon.almond.util.PrefsHelper;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class FxHelper {

    public static final String STYLE_HOVERED_BUTTON = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    public static final String STYLE_IDLE_BUTTON = "-fx-background-color: transparent;";
    private static final String DARCULA = "darcula.css";
    private static final String FORMAT_HEX_3 = "%02X%02X%02X";
    private static final String FORMAT_HEX_4 = "%02X%02X%02X%02X";
    private static final String FORMAT_TITLE_DESC = "%s (%s)";

    private static final String STAGE_ALWAYS_ON_TOP = "AlmondStage_AlwaysOnTop";
    private static final String STAGE_FULL_SCREEN = "AlmondStage_FullScreen";
    private static final String STAGE_H = "AlmondStage_Height";
    private static final String STAGE_MAXIMIZED = "AlmondStage_Maximized";
    private static final String STAGE_W = "AlmondStage_Width";
    private static final String STAGE_X = "AlmondStage_X";
    private static final String STAGE_Y = "AlmondStage_Y";
    private static Color sDarkColor = Color.web("#3c3f41");

    public static void adjustButtonHeight(Stream<Node> stream, double prefHeight) {
        stream.filter(item -> (item instanceof ButtonBase))
                .map(item -> (ButtonBase) item)
                .forEachOrdered(buttonBase -> {
                    buttonBase.setPrefHeight(prefHeight);
                });
    }

    public static void adjustButtonWidth(Stream<Node> stream, double prefWidth) {
        stream.filter(item -> (item instanceof ButtonBase))
                .map(item -> (ButtonBase) item)
                .forEachOrdered(buttonBase -> {
                    buttonBase.setPrefWidth(prefWidth);
                });
    }

    public static void applyFontScale(Scene scene) {
        runLater(() -> {
            scene.getRoot().setStyle("-fx-font-size: %dpx;".formatted((int) getScaledFontSize()));
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

    @Deprecated
    public static void autoSizeRegion(Region... regions) {
        autoSizeRegionHorizontal(regions);
    }

    public static void autoSizeRegionHorizontal(Region... regions) {
        for (Region region : regions) {
            GridPane.setHgrow(region, Priority.ALWAYS);
            GridPane.setFillWidth(region, true);
            region.setMaxWidth(Double.MAX_VALUE);
        }
    }

    public static void autoSizeRegionVertical(Region... regions) {
        for (Region region : regions) {
            GridPane.setVgrow(region, Priority.ALWAYS);
            GridPane.setFillHeight(region, true);
            region.setMaxHeight(Double.MAX_VALUE);
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
        return FORMAT_HEX_3.formatted(
                (int) (color.getRed() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getBlue() * 0xff));
    }

    public static String colorToHexABGR(Color color) {
        return FORMAT_HEX_4.formatted(
                (int) (color.getOpacity() * 0xff),
                (int) (color.getBlue() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getRed() * 0xff));
    }

    public static String colorToHexABGR(java.awt.Color color) {
        return FORMAT_HEX_4.formatted(
                color.getAlpha(),
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static String colorToHexBGR(Color color) {
        return FORMAT_HEX_3.formatted(
                (int) (color.getBlue() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getRed() * 0xff));
    }

    public static String colorToHexBGR(java.awt.Color color) {
        return FORMAT_HEX_3.formatted(
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static int colorToHexInt(Color color) {
        return Integer.decode("0x" + colorToHex(color));
    }

    public static String colorToHexRGB(Color color) {
        return FORMAT_HEX_3.formatted(
                (int) (color.getRed() * 0xff),
                (int) (color.getGreen() * 0xff),
                (int) (color.getBlue() * 0xff));
    }

    public static String colorToHexRGB(java.awt.Color color) {
        return FORMAT_HEX_3.formatted(
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static String colorToHexRGBA(java.awt.Color color) {
        return FORMAT_HEX_4.formatted(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                color.getAlpha()
        );
    }

    public static String colorToHexRGBA(Color color) {
        return FORMAT_HEX_4.formatted(
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

    public static String createKeyCodeDisplayText(KeyCode code, KeyCombination.Modifier... modifiers) {
        return new KeyCodeCombination(code, modifiers).getDisplayText();
    }

    public static String createTitleAndKeyCode(String title, KeyCode code, KeyCombination.Modifier... modifiers) {
        return FORMAT_TITLE_DESC.formatted(title, createKeyCodeDisplayText(code, modifiers));
    }

    public static Tooltip createTitleAndKeyCodeTooltip(String title, KeyCode code, KeyCombination.Modifier... modifiers) {
        return new Tooltip(createTitleAndKeyCode(title, code, modifiers));
    }

    public static void disableControls(ObservableList<Node> nodes, boolean disabled, Control... excludedControls) {
        for (Node node : nodes) {
            if (!ArrayUtils.contains(excludedControls, node)) {
                if (node instanceof Pane pane) {
                    disableControls(pane.getChildrenUnmodifiable(), disabled, excludedControls);
                } else if (node instanceof ToolBar toolBar) {
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

    public static ButtonBase getButtonForAction(Action action, ObservableList<Node> items) {
        for (Node item : items) {
            if (item instanceof ButtonBase buttonBase) {
                if (buttonBase.getOnAction().equals(action)) {
                    return buttonBase;
                }
            }
        }

        return null;
    }

    public static Color getDarkColor() {
        return sDarkColor;
    }

    public static double getScaledFontSize() {
        return Font.getDefault().getSize() * SwingHelper.getUIScale();
    }

    public static Tooltip getTooltip(String text, KeyCodeCombination keyCodeCombination) {
        return new Tooltip(FORMAT_TITLE_DESC.formatted(text, keyCodeCombination.getDisplayText()));
    }

    public static double getUIScaled(double value) {
        return value * SwingHelper.getUIScale();
    }

    public static int getUIScaled(int value) {
        return (int) (value * SwingHelper.getUIScale());
    }

    public static Insets getUIScaledInsets(double topRightBottomLeft) {
        return new Insets(topRightBottomLeft * SwingHelper.getUIScale());
    }

    public static Insets getUIScaledInsets(double top, double right, double bottom, double left) {
        return new Insets(
                top * SwingHelper.getUIScale(),
                right * SwingHelper.getUIScale(),
                bottom * SwingHelper.getUIScale(),
                left * SwingHelper.getUIScale()
        );
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
        runLater(() -> {
            if (isDarkThemeEnabled()) {
                scene.getStylesheets().add(FxHelper.class.getResource(DARCULA).toExternalForm());
            }
            applyFontScale(scene);
        });
    }

    public static void loadDarkTheme(Parent parent) {
        if (isDarkThemeEnabled()) {
            runLater(() -> {
                parent.getStylesheets().add(FxHelper.class.getResource(DARCULA).toExternalForm());
            });
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
        runLater(() -> {
            maskerPane.setVisible(false);
            notificationPane.show(message, MaterialIcon._Action.INFO_OUTLINE.getImageView(iconSize));
        });

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
                runLater(() -> {
                    notificationPane.hide();
                });
            } catch (InterruptedException ex) {
                //Exceptions.printStackTrace(ex);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void removeSceneInitFlicker(Window window) {
        if (isDarkThemeEnabled()) {
            window.getScene().setFill(FxHelper.getDarkColor());
        }
    }

    public static void removeSceneInitFlicker(Node node) {
        if (isDarkThemeEnabled()) {
            node.getScene().setFill(FxHelper.getDarkColor());
        }
    }

    /**
     * Run r now if isFxApplicationThread, else runLater
     *
     * @param r
     */
    public static void runLater(Runnable r) {
        if (Platform.isFxApplicationThread()) {
            r.run();
        } else {
            Platform.runLater(r);
        }
    }

    public static void runLaterDelayed(long delay, Runnable r) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runLater(r);
            } catch (InterruptedException ex) {
                Logger.getLogger(FxHelper.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void scrollToItemIfNotVisible(ListView<?> listview, Object item) {
        try {
            var listViewSkin = (ListViewSkin<?>) listview.getSkin();
            var virtualFlow = (VirtualFlow<?>) listViewSkin.getChildren().get(0);
            int firstIndex = virtualFlow.getFirstVisibleCell().getIndex();
            int lastIndex = virtualFlow.getLastVisibleCell().getIndex();
            int index = listview.getItems().indexOf(item);
            if (index < firstIndex || index > lastIndex) {
                listview.scrollTo(index);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void setDarkColor(Color darkColor) {
        sDarkColor = darkColor;
    }

    public static void setDarkThemeEnabled(boolean enabled) {
        System.setProperty("trixon.almond.fx.dark", String.valueOf(enabled));
    }

    public static void setEditable(boolean editable, Spinner... spinners) {
        for (Spinner spinner : spinners) {
            spinner.setEditable(editable);
        }
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

    public static void setPrefWidth(double width, Region... regions) {
        for (Region region : regions) {
            region.setPrefWidth(width);
        }
    }

    public static void setTooltip(Action action, KeyCodeCombination keyCodeCombination) {
        action.setLongText(FORMAT_TITLE_DESC.formatted(action.getText(), keyCodeCombination.getDisplayText()));
    }

    public static void setValignment(VPos value, Node... nodes) {
        for (var node : nodes) {
            GridPane.setValignment(node, value);
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

    public static void slimToolBar(ToolBar toolBar) {
        toolBar.setStyle("-fx-spacing: 0px; -fx-background-insets: 0, 0 0 0 0;");
        toolBar.setPadding(Insets.EMPTY);
        toolBar.setBorder(Border.EMPTY);
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

    public static void unloadDarkTheme(Scene scene) {
        runLater(() -> {
            setDarkThemeEnabled(false);
            scene.getStylesheets().remove(FxHelper.class.getResource(DARCULA).toExternalForm());
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
