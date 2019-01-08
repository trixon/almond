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
package se.trixon.almond.nbp.swing;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import se.trixon.almond.nbp.util.AString;

/**
 *
 * @author Patrik Karlström
 */
public class ASwing {

  public static int getSelectedIndex(JRadioButtonMenuItem[] aMenuItemArray) {
    int index = -1;

    for (int i = 0; i < aMenuItemArray.length; i++) {
      if (aMenuItemArray[i].isSelected() == true) {
        index = i;
        break;
      }
    }

    return index;
  }

  public static boolean isNumericTextField(java.awt.event.KeyEvent evt) {
    char c = evt.getKeyChar();
    boolean isNumeric = false;
    boolean decimalSignTaken = ((JTextField) evt.getComponent()).getText().contains(".") || ((JTextField) evt.getComponent()).getText().contains(",");

    if (((Character.isDigit(c)
            || (c == '.')
            || (c == ',')
            || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE)))) {
      isNumeric = true;

      if ((c == '.') || (c == ',')) {
        if (decimalSignTaken) {
          isNumeric = false;
        }
      }

    }

    return isNumeric;
  }

  public static void setTextAndMnemonic(AbstractButton abstractButton) {
    if (abstractButton != null) {
      String text = abstractButton.getText();
      int mnemonicPosition = 0;
      abstractButton.setMnemonic(0);
      Pattern p = Pattern.compile("&[^ ]");
      Matcher m = p.matcher(text);
      if (m.find()) {
        mnemonicPosition = m.start() + 1;
        char mnemonic = text.charAt(mnemonicPosition);
        abstractButton.setMnemonic(mnemonic);
        text = AString.removeCharAt(text, mnemonicPosition - 1);
      }
      abstractButton.setText(text);
    }
  }

  public static AbstractAction mergeActions(AbstractAction anAction, AbstractAction anActionWithValues) {
    Object[] objects = anActionWithValues.getKeys();
    for (Object object : objects) {
      anAction.putValue(object.toString(), anActionWithValues.getValue(object.toString()));
    }
    return anAction;
  }
}
