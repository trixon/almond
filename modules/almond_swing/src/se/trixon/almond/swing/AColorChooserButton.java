package se.trixon.almond.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AColorChooserButton extends JButton {

  private JLabel label = new JLabel();
  private JPanel panel = new JPanel();

  public AColorChooserButton() {
    init();
  }

  public AColorChooserButton(String text) {
    init();
    label.setText(text);
  }

  public AColorChooserButton(Action anAction) {
    super(anAction);
    init();
  }

  public AColorChooserButton(Icon anIcon) {
    super(anIcon);
    init();
  }

  public AColorChooserButton(String aText, Icon anIcon) {
    super(aText, anIcon);
    init();
  }

  public Color getColor() {
    return panel.getBackground();
  }

  public void setColor(Color aColor) {
    panel.setBackground(aColor);
  }

  @Override
  public void setText(String aText) {
    label.setText(aText);
  }

  private void init() {
    panel.setMinimumSize(new Dimension(15, 0));
    panel.setBackground(Color.BLACK);
    setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
    add(panel);
    add(label);
  }
}
