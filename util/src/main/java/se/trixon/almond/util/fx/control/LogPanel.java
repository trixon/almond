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
package se.trixon.almond.util.fx.control;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class LogPanel extends TextArea {

    public LogPanel() {
        init();
    }

    public LogPanel(String text) {
        super(text);
        init();
    }

    synchronized public void println(final String string) {
        Platform.runLater(() -> {
            appendText(string + "\n");
        });
    }

    public void scrollToBottom() {
        setCaretPosition(getText().length());
    }

    public void scrollToTop() {
        setCaretPosition(0);
    }

    public void setMonospaced() {
        setFont(Font.font("monospaced", FxHelper.getScaledFontSize()));
    }

    private void init() {
        setEditable(false);
    }

    private void setCaretPosition(int pos) {
        Platform.runLater(() -> {
            setCaretPosition(pos);
        });
    }

}
