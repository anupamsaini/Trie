package com.anupam.rhymeengine.tries;

import com.google.common.base.Strings;

/**
 * Wrapper class over {@link StringBuilder}, Implements removal/discard of sections of string from a
 * {@link StringBuilder}
 */
public final class StringBuilderWithRemove {

  private final StringBuilder sb;

  public StringBuilderWithRemove(StringBuilder sb) {
    this.sb = sb;
  }

  public StringBuilderWithRemove append(String toAppend) {
    sb.append(toAppend);
    return this;
  }

  public StringBuilderWithRemove discardAt(String str) {
    if (!Strings.isNullOrEmpty(str)) {
      int startIndex = sb.lastIndexOf(str);
      sb.replace(startIndex, sb.length(), "");
    }
    return this;
  }

  public StringBuilderWithRemove discardAfter(String str) {
    if (!Strings.isNullOrEmpty(str)) {
      int startIndex = sb.lastIndexOf(str);
      sb.replace(startIndex + str.length(), sb.length(), "");
    }
    return this;
  }

  @Override
  public String toString() {
    return sb.toString();
  }
}
