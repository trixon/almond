/* 
 * Copyright 2014 Patrik Karlsson.
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
package se.trixon.almond.swing;

import java.awt.Color;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import se.trixon.almond.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ColorChooserButton extends JButton {

    private static final String COLOR_SYMBOL = "█";

    private Color mColor = Color.BLACK;
    private String mText;
    private Color mTextColor = Color.BLACK;

    public ColorChooserButton() {
        super();
        init();
    }

    public ColorChooserButton(String text) {
        super(text);
        init();
    }

    public ColorChooserButton(Action action) {
        super(action);
        init();
    }

    public ColorChooserButton(Icon icon) {
        super(icon);
        init();
    }

    public ColorChooserButton(String text, Icon icon) {
        super(text, icon);
        init();
    }

    public Color getColor() {
        return mColor;
    }

    public Color getTextColor() {
        return mTextColor;
    }

    public void setColor(Color color) {
        mColor = color;
        setText(mText);
    }

    @Override
    public void setText(String text) {
        mText = text;
        String color = GraphicsHelper.colorToHex(mColor);
        String textColor = GraphicsHelper.colorToHex(mTextColor);
        super.setText(String.format("<html><font color=%s>%s <font color=%s>%s</html>", color, COLOR_SYMBOL, textColor, text));
    }

    public void setTextColor(Color textColor) {
        mTextColor = textColor;
    }

    private void init() {
        setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
    }
}
