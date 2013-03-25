package com.lfscheidegger.jfacet.activity.jfacet.shade;

/**
 * Namespace for some convenient static methods
 */
public class Shade {

  /**
   * Adds a varying number of {@code Expression} objects together;
   * @param args - varargs for expressions to be added together;
   *
   * @return - an {@code Expression} for the sum of the given arguments
   */
  public static Expression add(Expression... args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("add must take at least one argument");
    }

    if (args.length == 1) {
      return args[0];
    }

    return null;
  }
}
