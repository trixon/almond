package se.trixon.almond.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
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
