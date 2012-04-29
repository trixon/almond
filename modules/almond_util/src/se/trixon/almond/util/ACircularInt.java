package se.trixon.almond.util;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ACircularInt {

  private int max;
  private int min;
  private int value;

  public ACircularInt(int min, int max) {
    this.max = max;
    this.min = min;
    this.value = 0;
  }

  public ACircularInt(int min, int max, int value) {
    this.max = max;
    this.min = min;
    this.value = value;
  }

  public int get() {
    return value;
  }

  public int inc() {
    value = peekNext();
    return value;
  }

  public int dec() {
    value = peekPrev();
    return value;
  }

  public int peekNext() {
    int peek = value + 1;
    if (peek > max) {
      peek = 0;
    }
    return peek;
  }

  public int peekPrev() {
    int peek = value - 1;
    if (peek < min) {
      peek = max;
    }
    return peek;
  }

  public void set(int aValue) {
    value = Math.min(max, Math.max(min, aValue));
  }
}
