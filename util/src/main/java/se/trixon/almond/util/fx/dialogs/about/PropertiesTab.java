/*
 * Copyright 2018 Patrik Karlström.
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

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.SystemHelper;
import se.trixon.almond.util.AboutModel;

/**
 *
 * @author Patrik Karlström
 */
public class PropertiesTab extends Tab implements AboutPane.ResetableTab {

    private ContextMenu mContextMenu;
    private final ObservableList<SystemProperty> mData = FXCollections.observableArrayList();
    private int mMaxKeyLength = 0;
    private final TableView mTableView = new TableView();

    public PropertiesTab(AboutModel aboutModel) {
        setText(Dict.PROPERTIES.toString());
        init();
        setContent(mTableView);
    }

    @Override
    public void reset() {
        mTableView.scrollTo(0);
    }

    private void init() {
        initSystemProperties();

        TableColumn keyCol = new TableColumn(Dict.KEY.toString());
        keyCol.setCellValueFactory(new PropertyValueFactory<>("key"));
        keyCol.setSortable(false);
        keyCol.prefWidthProperty().bind(mTableView.widthProperty().multiply(0.3));

        TableColumn valCol = new TableColumn(Dict.VALUE.toString());
        valCol.setCellValueFactory(new PropertyValueFactory<>("val"));
        valCol.setSortable(false);
        valCol.prefWidthProperty().bind(mTableView.widthProperty().multiply(0.65));

        mTableView.setItems(mData);
        mTableView.getColumns().addAll(keyCol, valCol);

        mContextMenu = new ContextMenu();
        MenuItem copyMenuItem = new MenuItem(Dict.COPY.toString());
        copyMenuItem.setOnAction((ActionEvent event) -> {
            StringBuilder builder = new StringBuilder();

            mData.forEach((p) -> {
                builder.append(StringUtils.rightPad(p.getKey(), mMaxKeyLength + 2)).append(p.getVal()).append("\n");
            });

            SystemHelper.copyToClipboard(builder.toString());

        });
        mContextMenu.getItems().add(copyMenuItem);

        mTableView.setContextMenu(mContextMenu);
    }

    private void initSystemProperties() {
        String[] keys = new String[]{
            "user.country",
            "user.home",
            "user.language",
            "user.name",
            "user.timezone",
            "",
            "os.arch",
            "os.name",
            "os.version",
            "",
            "file.encoding.pkg",
            "file.encoding",
            "file.separator",
            "path.separator",
            "",
            "netbeans.home",
            "netbeans.user",
            "netbeans.dirs",
            "netbeans.running.environment",
            "netbeans.productversion",
            "netbeans.buildnumber",
            "netbeans.dynamic.classpath",
            "netbeans.logger.console",
            "",
            "java.awt.graphicsenv",
            "java.awt.printerjob",
            "java.class.path",
            "java.class.version",
            "java.endorsed.dirs",
            "java.ext.dirs",
            "java.home",
            "java.io.tmpdir",
            "java.library.path",
            "java.runtime.name",
            "java.runtime.version",
            "java.specification.name",
            "java.specification.vendor",
            "java.specification.version",
            "java.vendor",
            "java.vendor.url.bug",
            "java.vendor.url",
            "java.version",
            "java.vm.info",
            "java.vm.name",
            "java.vm.specification.name",
            "java.vm.specification.vendor",
            "java.vm.specification.version",
            "java.vm.vendor",
            "java.vm.version",
            "",
            "sun.arch.data.model",
            "sun.boot.class.path",
            "sun.boot.library.path",
            "sun.cpu.endian",
            "sun.cpu.isalist",
            "sun.io.unicode.encoding",
            "sun.java.launcher",
            "sun.jnu.encoding",
            "sun.management.compiler",
            "sun.os.patch.level"
        };

        for (String key : keys) {
            SystemProperty p = new SystemProperty(key);
            if (p.getKey().isEmpty() == p.getVal().isEmpty()) {
                mData.add(p);
            }
        }
    }

    public class SystemProperty {

        private final SimpleStringProperty mKey;
        private final SimpleStringProperty mVal;

        private SystemProperty(String key, String val) {
            mKey = new SimpleStringProperty(key);
            mVal = new SimpleStringProperty(val);
        }

        private SystemProperty(String key) {
            mKey = new SimpleStringProperty(key);
            mVal = new SimpleStringProperty("");

            mMaxKeyLength = Math.max(mMaxKeyLength, key.length());
            if (!key.isEmpty() && System.getProperties().keySet().contains(key)) {
                setVal(System.getProperty(key));
            }
        }

        public String getKey() {
            return mKey.get();
        }

        public String getVal() {
            return mVal.get();
        }

        public void setKey(String key) {
            mKey.set(key);
        }

        public void setVal(String val) {
            mVal.set(val);
        }
    }
}
