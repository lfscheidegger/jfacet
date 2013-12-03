package com.lfscheidegger.jfacet.utils;

/**
 * Utilities for dealing with Strings
 */
public final class StringUtils {

  public static class StringHelper {

    private final StringBuilder mStringBuilder;
    private int mValueCount = 0;

    public StringHelper(String type) {
      mStringBuilder = new StringBuilder();
      mStringBuilder.append(type).append("(");
    }

    public StringHelper addValue(Object value) {
      if (mValueCount != 0) {
        mStringBuilder.append(", ");
      }

      mStringBuilder.append(value.toString());
      mValueCount++;
      return this;
    }

    public String toString() {
      return mStringBuilder.append(")").toString();
    }
  }

  public static StringHelper toStringHelper(String type) {
    return new StringHelper(type);
  }
}
