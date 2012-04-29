package se.trixon.almond.util;

import java.text.DecimalFormat;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AString {

  static public final boolean APPEND = true;
  static public final boolean PREPEND = false;

  static public String appendString(String aString, String aFillString, int aValue) {

    for (int i = 0; i < aValue; i++) {
      aString = aString + aFillString;
    }

    return aString;
  }

  static public String convertDoubleToString(double aValue, int aNumOfDecimals) {
    String decimalFormatString = appendString("0.", "0", aNumOfDecimals);
    DecimalFormat decimalFormat = new DecimalFormat(decimalFormatString);
    decimalFormat.setPositivePrefix("+");
    String sResult = decimalFormat.format(aValue);

    if (aValue > 1000000000 || aValue < -1000000000) {
      return "&#177;&#8734;";
    } else {
      return sResult;
    }
  }

  static public int countCharInString(String arg2, String arg1) {
    //FIXME
    int count = 0;
    int index = 0;

    while ((index = arg1.indexOf(arg2, index)) != -1) {
      ++index;
      ++count;
    }

    return count;
  }

  static public String fillString(String aString, String aFillString, int aStopValue) {
    int startValue = aString.length();

    for (int i = startValue; i < aStopValue; i++) {
      aString = aFillString + aString;
    }

    return aString;
  }

  static public String fillString(String aString, String aFillString, int aStopValue, boolean aPosition) {
    int startValue = aString.length();

    for (int i = startValue; i < aStopValue; i++) {
      if (aPosition == APPEND) {
        aString = aString + aFillString;
      } else {
        aString = aFillString + aString;
      }
    }

    return aString;
  }

  static public String prependString(String aString, String aFillString, int aValue) {

    for (int i = 0; i < aValue; i++) {
      aString = aFillString + aString;
    }

    return aString;
  }

  public static String removeCharAt(String aString, int aPosition) {
    return aString.substring(0, aPosition) + aString.substring(aPosition + 1);
  }
}
