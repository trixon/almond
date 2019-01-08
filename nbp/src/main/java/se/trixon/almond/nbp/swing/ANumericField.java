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

import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author Patrik Karlström
 */
public class ANumericField extends JTextField {

  private boolean selectedByMouse;

  public ANumericField() {
    init();
  }

  public ANumericField(int aValue) {
    init();
    setText(String.valueOf(aValue));
  }

  public ANumericField(double aValue) {
    init();
    setText(String.valueOf(aValue));
  }

  private ANumericField(String text) {
  }

  private ANumericField(String text, int columns) {
  }

  private ANumericField(Document doc, String text, int columns) {
  }

  public double getValue() throws NumberFormatException {
    return Double.parseDouble(getText().replace(",", "."));
  }

  private void init() {
    setHorizontalAlignment(JTextField.TRAILING);
    setText("0");
    setDragEnabled(true);

    addKeyListener(new KeyAdapter() {

      @Override
      public void keyTyped(KeyEvent evt) {
        if (isNumericInput(evt) == false) {
          evt.consume();
        }
      }
    });

    addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent me) {
//                selectedByMouse = true;
      }
    });

    addFocusListener(new FocusAdapter() {

      @Override
      public void focusGained(FocusEvent fe) {
        if (selectedByMouse) {
          selectedByMouse = false;
        } else {
//                    selectAll();
        }
      }
    });
  }

  private boolean isNumericInput(KeyEvent evt) {
    char c = evt.getKeyChar();
    String text = ((JTextField) evt.getComponent()).getText();

    boolean isNumeric = false;
    boolean decimalSignTaken = text.contains(".") || text.contains(",");

    if (((Character.isDigit(c)
            || (c == '.')
            || (c == ',')
            || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE)
            || ((c == KeyEvent.VK_MINUS) && (text.isEmpty()))))) {
      isNumeric = true;

      if ((c == '.') || (c == ',')) {
        if (decimalSignTaken) {
          isNumeric = false;
        }
      }
    }

    return isNumeric;
  }
}
