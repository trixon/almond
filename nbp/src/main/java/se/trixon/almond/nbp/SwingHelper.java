/* 
 * Copyright 2016 Patrik Karlsson.
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
package se.trixon.almond.nbp;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JMenu;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class SwingHelper {

    public static void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    public static void setComponentsFont(Container container, Font font) {
        Component[] components = container.getComponents();
        container.setFont(font);
        
        for (Component component : components) {
            component.setFont(font);
            if (component instanceof JMenu) {
                setComponentsFont((Container) component, font);
            }
        }
    }
}
