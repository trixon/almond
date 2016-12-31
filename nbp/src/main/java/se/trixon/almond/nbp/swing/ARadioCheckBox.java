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
package se.trixon.almond.nbp.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

/**
 *
 * @author Patrik Karlsson
 */
public class ARadioCheckBox extends JCheckBox {

  private int buttonGroupID = 0;

  public ARadioCheckBox() {
    super();
  }

  public ARadioCheckBox(Icon icon) {
    super(icon);
  }

  public ARadioCheckBox(Icon icon, boolean selected) {
    super(icon, selected);
  }

  public ARadioCheckBox(String text) {
    super(text);
  }

  public ARadioCheckBox(Action a) {
    super(a);
  }

  public ARadioCheckBox(String text, boolean selected) {
    super(text, selected);
  }

  public ARadioCheckBox(String text, Icon icon) {
    super(text, icon);
  }

  public ARadioCheckBox(String text, Icon icon, boolean selected) {
    super(text, icon, selected);
  }

  public int getButtonGroupID() {
    return buttonGroupID;
  }

  public void setButtonGroupID(int buttonGroup) {
    this.buttonGroupID = buttonGroup;
  }
}
