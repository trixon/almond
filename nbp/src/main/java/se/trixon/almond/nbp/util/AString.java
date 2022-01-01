/* 
 * Copyright 2022 Patrik Karlström.
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
package se.trixon.almond.nbp.util;

import java.text.DecimalFormat;

/**
 *
 * @author Patrik Karlström
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
