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
package se.trixon.almond.nbp.swing;

import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTabbedPane;
import se.trixon.almond.nbp.util.AString;

/**
 *
 * @author Patrik Karlström
 */
public class ATabbedPane extends JTabbedPane {

  private int activeTabIndex;
  private String activeTabName;

  public ATabbedPane() {
  }

  public ATabbedPane(int tabPlacement) {
    super(tabPlacement);
  }

  public ATabbedPane(int tabPlacement, int tabLayoutPolicy) {
    super(tabPlacement, tabLayoutPolicy);
  }

  public int getActiveTab() {
    return getModel().getSelectedIndex();
  }

  public void setTitleAt(Component c, String s) {
    int i = indexOfComponent(c);
    if (i > -1) {
      setTitleAt(i, s);
    }
  }

  public void recallActiveTabByIndex() {
    getModel().setSelectedIndex(activeTabIndex);
  }

  public void recallActiveTabByName() {
    getModel().setSelectedIndex(0);
    for (int i = 0; i < getTabCount(); i++) {
      if (activeTabName.equalsIgnoreCase(getComponentAt(i).getName())) {
        getModel().setSelectedIndex(i);
      }
    }
  }

  public void rememberActiveTabByIndex() {
    activeTabIndex = getModel().getSelectedIndex();
  }

  public void rememberActiveTabByName() {
    activeTabName = getComponentAt(getModel().getSelectedIndex()).getName();
  }

  public void setTextAndMnemonic() {

    for (int i = 0; i < getTabCount(); i++) {
      String text = getTitleAt(i);

      int mnemonicPosition = 0;
      setMnemonicAt(i, 0);
      Pattern p = Pattern.compile("&[^ ]");
      Matcher m = p.matcher(text);
      if (m.find()) {
        mnemonicPosition = m.start() + 1;
        char mnemonic = text.charAt(mnemonicPosition);
        setMnemonicAt(i, mnemonic);
        text = AString.removeCharAt(text, mnemonicPosition - 1);
      }
      setTitleAt(i, text);
    }
  }
}
