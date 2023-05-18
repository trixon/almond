/* 
 * Copyright 2023 Patrik Karlström.
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
package se.trixon.almond.util.swing.dialogs;

import java.awt.Component;
import javax.swing.JOptionPane;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class Message {

    public static void error(Component component, String title, String message) {
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void html(Component component, String title, String message) {
        HtmlPanel validatorPanel = new HtmlPanel(message);
        SwingHelper.makeWindowResizable(validatorPanel);

        JOptionPane.showOptionDialog(component,
                validatorPanel,
                title,
                JOptionPane.CLOSED_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
    }

    public static void information(Component component, String title, String message) {
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warning(Component component, String title, String message) {
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
