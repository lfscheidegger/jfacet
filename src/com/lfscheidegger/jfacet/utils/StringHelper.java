package com.lfscheidegger.jfacet.utils;

import com.google.common.collect.Lists;

import java.util.List;

public class StringHelper {

  private final List<String> mStrings;
  private final String mType;

  public StringHelper(String type) {
    mStrings = Lists.newArrayList();
    mType = type;
  }

  public StringHelper addValue(Object value) {
    mStrings.add(value.toString());

    return this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(mType).append("(");
    if (!mStrings.isEmpty()) {
      sb.append(mStrings.get(0));
    }

    for (int i = 1; i < mStrings.size(); i++) {
      sb.append(", ").append(mStrings.get(i));
    }

    return sb.append(")").toString();
  }
}
