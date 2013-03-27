package com.lfscheidegger.jfacet.shade.compiler;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Util {

  private static String COMMON_PREFIX = "glsl_name_";
  private static int sCounter = 0;
  private static Lock sLock = new ReentrantLock();

  public static String getUniqueName() {
    sLock.lock();
    try {
      return COMMON_PREFIX + sCounter++;
    } finally {
      sLock.unlock();
    }
  }
}
