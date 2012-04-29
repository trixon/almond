package se.trixon.almond.swing;

import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTabbedPane;
import se.trixon.almond.util.AString;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
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
