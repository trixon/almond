package se.trixon.almond.swing;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import se.trixon.almond.util.AString;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
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
