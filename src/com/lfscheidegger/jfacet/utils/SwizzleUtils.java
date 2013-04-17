package com.lfscheidegger.jfacet.utils;

import com.google.common.collect.ImmutableMap;

public final class SwizzleUtils {

  private static final ImmutableMap<Character, Integer> sIndexMap = new ImmutableMap.Builder<Character, Integer>()
      .put('x', 0).put('y', 1).put('z', 2).put('w', 3)
      .put('r', 0).put('g', 1).put('b', 2).put('a', 3)
      .put('s', 0).put('t', 1).put('p', 2).put('q', 3)
      .build();

  public static int getIndexForSwizzle(char c) {
    return sIndexMap.get(c);
  }
}
