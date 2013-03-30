package com.lfscheidegger.jfacet.shade.utils;

import com.lfscheidegger.jfacet.shade.Type;

/**
 * Utilities for dealing with Strings
 */
public class StringUtils {

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

  public static StringHelper toStringHelper(Type type) {
    return new StringHelper(type.toString());
  }

  public static String wrapFloat(float t) {
    return Type.FLOAT_T + "(" + t + ")";
  }
}
