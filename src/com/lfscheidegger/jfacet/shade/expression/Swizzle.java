// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.shade.expression;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Swizzle {

  private Swizzle() {}

  public static class Swizzle21XYZW<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle21XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle22XYZW<VEC2_T, VEC3_T, VEC4_T> x() {
      return new Swizzle22XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "x", mVector);
    }
    public Swizzle22XYZW<VEC2_T, VEC3_T, VEC4_T> y() {
      return new Swizzle22XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "y", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle22XYZW<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle22XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle23XYZW<VEC3_T, VEC4_T> x() {
      return new Swizzle23XYZW<VEC3_T, VEC4_T>(mSwizzleString + "x", mVector);
    }
    public Swizzle23XYZW<VEC3_T, VEC4_T> y() {
      return new Swizzle23XYZW<VEC3_T, VEC4_T>(mSwizzleString + "y", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle23XYZW<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle23XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T x() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "x");
    }
    @SuppressWarnings("all")
    public VEC4_T y() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "y");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle31XYZW<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle31XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle32XYZW<VEC2_T, VEC3_T, VEC4_T> x() {
      return new Swizzle32XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "x", mVector);
    }
    public Swizzle32XYZW<VEC2_T, VEC3_T, VEC4_T> y() {
      return new Swizzle32XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "y", mVector);
    }
    public Swizzle32XYZW<VEC2_T, VEC3_T, VEC4_T> z() {
      return new Swizzle32XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "z", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle32XYZW<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle32XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle33XYZW<VEC3_T, VEC4_T> x() {
      return new Swizzle33XYZW<VEC3_T, VEC4_T>(mSwizzleString + "x", mVector);
    }
    public Swizzle33XYZW<VEC3_T, VEC4_T> y() {
      return new Swizzle33XYZW<VEC3_T, VEC4_T>(mSwizzleString + "y", mVector);
    }
    public Swizzle33XYZW<VEC3_T, VEC4_T> z() {
      return new Swizzle33XYZW<VEC3_T, VEC4_T>(mSwizzleString + "z", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle33XYZW<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle33XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T x() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "x");
    }
    @SuppressWarnings("all")
    public VEC4_T y() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "y");
    }
    @SuppressWarnings("all")
    public VEC4_T z() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "z");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle41XYZW<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle41XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T> x() {
      return new Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "x", mVector);
    }
    public Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T> y() {
      return new Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "y", mVector);
    }
    public Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T> z() {
      return new Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "z", mVector);
    }
    public Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T> w() {
      return new Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "w", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle42XYZW<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle42XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle43XYZW<VEC3_T, VEC4_T> x() {
      return new Swizzle43XYZW<VEC3_T, VEC4_T>(mSwizzleString + "x", mVector);
    }
    public Swizzle43XYZW<VEC3_T, VEC4_T> y() {
      return new Swizzle43XYZW<VEC3_T, VEC4_T>(mSwizzleString + "y", mVector);
    }
    public Swizzle43XYZW<VEC3_T, VEC4_T> z() {
      return new Swizzle43XYZW<VEC3_T, VEC4_T>(mSwizzleString + "z", mVector);
    }
    public Swizzle43XYZW<VEC3_T, VEC4_T> w() {
      return new Swizzle43XYZW<VEC3_T, VEC4_T>(mSwizzleString + "w", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle43XYZW<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle43XYZW(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T x() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "x");
    }
    @SuppressWarnings("all")
    public VEC4_T y() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "y");
    }
    @SuppressWarnings("all")
    public VEC4_T z() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "z");
    }
    @SuppressWarnings("all")
    public VEC4_T w() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "w");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle21RGBA<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle21RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle22RGBA<VEC2_T, VEC3_T, VEC4_T> r() {
      return new Swizzle22RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "r", mVector);
    }
    public Swizzle22RGBA<VEC2_T, VEC3_T, VEC4_T> g() {
      return new Swizzle22RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "g", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle22RGBA<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle22RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle23RGBA<VEC3_T, VEC4_T> r() {
      return new Swizzle23RGBA<VEC3_T, VEC4_T>(mSwizzleString + "r", mVector);
    }
    public Swizzle23RGBA<VEC3_T, VEC4_T> g() {
      return new Swizzle23RGBA<VEC3_T, VEC4_T>(mSwizzleString + "g", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle23RGBA<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle23RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T r() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "r");
    }
    @SuppressWarnings("all")
    public VEC4_T g() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "g");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle31RGBA<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle31RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle32RGBA<VEC2_T, VEC3_T, VEC4_T> r() {
      return new Swizzle32RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "r", mVector);
    }
    public Swizzle32RGBA<VEC2_T, VEC3_T, VEC4_T> g() {
      return new Swizzle32RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "g", mVector);
    }
    public Swizzle32RGBA<VEC2_T, VEC3_T, VEC4_T> b() {
      return new Swizzle32RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "b", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle32RGBA<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle32RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle33RGBA<VEC3_T, VEC4_T> r() {
      return new Swizzle33RGBA<VEC3_T, VEC4_T>(mSwizzleString + "r", mVector);
    }
    public Swizzle33RGBA<VEC3_T, VEC4_T> g() {
      return new Swizzle33RGBA<VEC3_T, VEC4_T>(mSwizzleString + "g", mVector);
    }
    public Swizzle33RGBA<VEC3_T, VEC4_T> b() {
      return new Swizzle33RGBA<VEC3_T, VEC4_T>(mSwizzleString + "b", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle33RGBA<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle33RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T r() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "r");
    }
    @SuppressWarnings("all")
    public VEC4_T g() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "g");
    }
    @SuppressWarnings("all")
    public VEC4_T b() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "b");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle41RGBA<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle41RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T> r() {
      return new Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "r", mVector);
    }
    public Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T> g() {
      return new Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "g", mVector);
    }
    public Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T> b() {
      return new Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "b", mVector);
    }
    public Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T> a() {
      return new Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "a", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle42RGBA<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle42RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle43RGBA<VEC3_T, VEC4_T> r() {
      return new Swizzle43RGBA<VEC3_T, VEC4_T>(mSwizzleString + "r", mVector);
    }
    public Swizzle43RGBA<VEC3_T, VEC4_T> g() {
      return new Swizzle43RGBA<VEC3_T, VEC4_T>(mSwizzleString + "g", mVector);
    }
    public Swizzle43RGBA<VEC3_T, VEC4_T> b() {
      return new Swizzle43RGBA<VEC3_T, VEC4_T>(mSwizzleString + "b", mVector);
    }
    public Swizzle43RGBA<VEC3_T, VEC4_T> a() {
      return new Swizzle43RGBA<VEC3_T, VEC4_T>(mSwizzleString + "a", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle43RGBA<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle43RGBA(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T r() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "r");
    }
    @SuppressWarnings("all")
    public VEC4_T g() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "g");
    }
    @SuppressWarnings("all")
    public VEC4_T b() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "b");
    }
    @SuppressWarnings("all")
    public VEC4_T a() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "a");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle21STPQ<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle21STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle22STPQ<VEC2_T, VEC3_T, VEC4_T> s() {
      return new Swizzle22STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "s", mVector);
    }
    public Swizzle22STPQ<VEC2_T, VEC3_T, VEC4_T> t() {
      return new Swizzle22STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "t", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle22STPQ<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle22STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle23STPQ<VEC3_T, VEC4_T> s() {
      return new Swizzle23STPQ<VEC3_T, VEC4_T>(mSwizzleString + "s", mVector);
    }
    public Swizzle23STPQ<VEC3_T, VEC4_T> t() {
      return new Swizzle23STPQ<VEC3_T, VEC4_T>(mSwizzleString + "t", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle23STPQ<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle23STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T s() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "s");
    }
    @SuppressWarnings("all")
    public VEC4_T t() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "t");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle31STPQ<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle31STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle32STPQ<VEC2_T, VEC3_T, VEC4_T> s() {
      return new Swizzle32STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "s", mVector);
    }
    public Swizzle32STPQ<VEC2_T, VEC3_T, VEC4_T> t() {
      return new Swizzle32STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "t", mVector);
    }
    public Swizzle32STPQ<VEC2_T, VEC3_T, VEC4_T> p() {
      return new Swizzle32STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "p", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle32STPQ<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle32STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle33STPQ<VEC3_T, VEC4_T> s() {
      return new Swizzle33STPQ<VEC3_T, VEC4_T>(mSwizzleString + "s", mVector);
    }
    public Swizzle33STPQ<VEC3_T, VEC4_T> t() {
      return new Swizzle33STPQ<VEC3_T, VEC4_T>(mSwizzleString + "t", mVector);
    }
    public Swizzle33STPQ<VEC3_T, VEC4_T> p() {
      return new Swizzle33STPQ<VEC3_T, VEC4_T>(mSwizzleString + "p", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle33STPQ<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle33STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T s() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "s");
    }
    @SuppressWarnings("all")
    public VEC4_T t() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "t");
    }
    @SuppressWarnings("all")
    public VEC4_T p() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "p");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle41STPQ<REAL_T, VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle41STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T> s() {
      return new Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "s", mVector);
    }
    public Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T> t() {
      return new Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "t", mVector);
    }
    public Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T> p() {
      return new Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "p", mVector);
    }
    public Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T> q() {
      return new Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T>(mSwizzleString + "q", mVector);
    }
    @SuppressWarnings("all")
    public REAL_T get() {
      return (REAL_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle42STPQ<VEC2_T, VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle42STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    public Swizzle43STPQ<VEC3_T, VEC4_T> s() {
      return new Swizzle43STPQ<VEC3_T, VEC4_T>(mSwizzleString + "s", mVector);
    }
    public Swizzle43STPQ<VEC3_T, VEC4_T> t() {
      return new Swizzle43STPQ<VEC3_T, VEC4_T>(mSwizzleString + "t", mVector);
    }
    public Swizzle43STPQ<VEC3_T, VEC4_T> p() {
      return new Swizzle43STPQ<VEC3_T, VEC4_T>(mSwizzleString + "p", mVector);
    }
    public Swizzle43STPQ<VEC3_T, VEC4_T> q() {
      return new Swizzle43STPQ<VEC3_T, VEC4_T>(mSwizzleString + "q", mVector);
    }
    @SuppressWarnings("all")
    public VEC2_T get() {
      return (VEC2_T)getInstance(mVector, mSwizzleString);
    }
  }
  public static class Swizzle43STPQ<VEC3_T, VEC4_T> {
    private final Object mVector;
    private final String mSwizzleString;
    public Swizzle43STPQ(String swizzleString, Object vector ) {
      mVector = vector;
      mSwizzleString = swizzleString;
    }
    @SuppressWarnings("all")
    public VEC4_T s() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "s");
    }
    @SuppressWarnings("all")
    public VEC4_T t() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "t");
    }
    @SuppressWarnings("all")
    public VEC4_T p() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "p");
    }
    @SuppressWarnings("all")
    public VEC4_T q() {
      return (VEC4_T)getInstance(mVector, mSwizzleString + "q");
    }
    @SuppressWarnings("all")
    public VEC3_T get() {
      return (VEC3_T)getInstance(mVector, mSwizzleString);
    }
  }

  private static Object getInstance(Object parent, String swizzleString) {
    if (parent instanceof Expression) {
      ImmutableList<Expression> parents = ImmutableList.of((Expression) parent);
      NodeType nodeType = NodeType.SwizzleNodeType.forSwizzle(swizzleString);
      if (parent instanceof Vec2 || parent instanceof Vec3 || parent instanceof Vec4) {
        switch(swizzleString.length()) {
          case 1: return new Real(parents, nodeType);
          case 2: return new Vec2(parents, nodeType);
          case 3: return new Vec3(parents, nodeType);
          case 4: return new Vec4(parents, nodeType);
        }
      } else if (
          parent instanceof BVec2 ||
              parent instanceof BVec3 ||
              parent instanceof BVec4) {
        switch(swizzleString.length()) {
          case 1: return new Bool(parents, nodeType);
          case 2: return new BVec2(parents, nodeType);
          case 3: return new BVec3(parents, nodeType);
          case 4: return new BVec4(parents, nodeType);
        }
      }
    } else if (parent instanceof BVecPrimitive) {
      BVecPrimitive vec = (BVecPrimitive) parent;
      switch(swizzleString.length()) {
        case 1: return vec.get(getIndexForSwizzle(swizzleString.charAt(0)));
        case 2: return new BVec2.Primitive(
            vec.get(getIndexForSwizzle(swizzleString.charAt(0))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(1))));
        case 3: return new BVec3.Primitive(
            vec.get(getIndexForSwizzle(swizzleString.charAt(0))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(1))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(2))));
        case 4: return new BVec4.Primitive(
            vec.get(getIndexForSwizzle(swizzleString.charAt(0))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(1))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(2))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(3))));
      }
    } else if (parent instanceof VecPrimitive) {
      VecPrimitive vec = (VecPrimitive) parent;
      switch(swizzleString.length()) {
        case 1: return vec.get(getIndexForSwizzle(swizzleString.charAt(0)));
        case 2: return new Vec2.Primitive(
            vec.get(getIndexForSwizzle(swizzleString.charAt(0))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(1))));
        case 3: return new Vec3.Primitive(
            vec.get(getIndexForSwizzle(swizzleString.charAt(0))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(1))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(2))));
        case 4: return new Vec4.Primitive(
            vec.get(getIndexForSwizzle(swizzleString.charAt(0))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(1))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(2))),
            vec.get(getIndexForSwizzle(swizzleString.charAt(3))));
      }
    }

    throw new IllegalArgumentException("Can't swizzle object of type " + parent.getClass());
  }

  private static final ImmutableMap<Character, Integer> INDEX_MAP = new ImmutableMap.Builder<Character, Integer>()
      .put('x', 0).put('y', 1).put('z', 2).put('w', 3)
      .put('r', 0).put('g', 1).put('b', 2).put('a', 3)
      .put('s', 0).put('t', 1).put('p', 2).put('q', 3)
      .build();

  private static int getIndexForSwizzle(char c) {
    return INDEX_MAP.get(c);
  }
}