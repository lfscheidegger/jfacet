package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.lfscheidegger.jfacet.shade.expression.ExpressionTestUtils.*;

/**
 * Unit tests for {@code Vector3}
 */
public class Vector3Test {

  private final Vector3 mVec = new Vector3(1, 2, 3);

  @Test
  public void testConstructors() {
    Vector3 vec = new Vector3(1, 2, 3);

    assertTrue(vec.getPrimitive().isPresent());
    assertEquals(vec.getPrimitive().get(), new Vector3.Primitive(1, 2, 3));
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.of());

    Real x = new Real(1), y = new Real(2), z = new Real(3);
    vec = new Vector3(x, y, z);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z));

    vec = new Vector3(ImmutableList.<Expression>of(x, y, z), Expression.NodeType.CONS);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z));

    vec = new Vector3(new AttribBuffer(new float[] {0, 0, 1, 0, 1, 1}, 2));

    assertFalse(vec.getPrimitive().isPresent());
    assertTrue(vec.getAttribBuffer().isPresent());
    assertTrue(vec.getGlSlQualifier().isPresent());
    assertEquals(vec.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.<Expression>of());
  }

  @Test
  public void testGetters() {
    Vector3 vec = new Vector3(1, 2, 3);
    Real x = vec.getX();
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    Real y = vec.getY();
    testGetter(y, 1, ImmutableList.<Expression>of(vec));

    Real z = vec.getZ();
    testGetter(z, 2, ImmutableList.<Expression>of(vec));

    x = vec.get(0);
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    y = vec.get(1);
    testGetter(y, 1, ImmutableList.<Expression>of(vec));

    z = vec.get(2);
    testGetter(z, 2, ImmutableList.<Expression>of(vec));
  }

  private void testSwizzle1(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Real);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle2(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector2);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle3(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector3);
    testSwizzle(mVec, swizzled, expectedString);
  }

  private void testSwizzle4(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector4);
    testSwizzle(mVec, swizzled, expectedString);
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleXYZW() {
    testSwizzle1(mVec.x().get(), "x");
    testSwizzle1(mVec.y().get(), "y");
    testSwizzle1(mVec.z().get(), "z");

    testSwizzle2(mVec.x().x().get(), "xx");
    testSwizzle2(mVec.x().y().get(), "xy");
    testSwizzle2(mVec.x().z().get(), "xz");
    testSwizzle2(mVec.y().x().get(), "yx");
    testSwizzle2(mVec.y().y().get(), "yy");
    testSwizzle2(mVec.y().z().get(), "yz");
    testSwizzle2(mVec.z().x().get(), "zx");
    testSwizzle2(mVec.z().y().get(), "zy");
    testSwizzle2(mVec.z().z().get(), "zz");

    testSwizzle3(mVec.x().x().x().get(), "xxx");
    testSwizzle3(mVec.x().x().y().get(), "xxy");
    testSwizzle3(mVec.x().x().z().get(), "xxz");
    testSwizzle3(mVec.x().y().x().get(), "xyx");
    testSwizzle3(mVec.x().y().y().get(), "xyy");
    testSwizzle3(mVec.x().y().z().get(), "xyz");
    testSwizzle3(mVec.x().z().x().get(), "xzx");
    testSwizzle3(mVec.x().z().y().get(), "xzy");
    testSwizzle3(mVec.x().z().z().get(), "xzz");
    testSwizzle3(mVec.y().x().x().get(), "yxx");
    testSwizzle3(mVec.y().x().y().get(), "yxy");
    testSwizzle3(mVec.y().x().z().get(), "yxz");
    testSwizzle3(mVec.y().y().x().get(), "yyx");
    testSwizzle3(mVec.y().y().y().get(), "yyy");
    testSwizzle3(mVec.y().y().z().get(), "yyz");
    testSwizzle3(mVec.y().z().x().get(), "yzx");
    testSwizzle3(mVec.y().z().y().get(), "yzy");
    testSwizzle3(mVec.y().z().z().get(), "yzz");
    testSwizzle3(mVec.z().x().x().get(), "zxx");
    testSwizzle3(mVec.z().x().y().get(), "zxy");
    testSwizzle3(mVec.z().x().z().get(), "zxz");
    testSwizzle3(mVec.z().y().x().get(), "zyx");
    testSwizzle3(mVec.z().y().y().get(), "zyy");
    testSwizzle3(mVec.z().y().z().get(), "zyz");
    testSwizzle3(mVec.z().z().x().get(), "zzx");
    testSwizzle3(mVec.z().z().y().get(), "zzy");
    testSwizzle3(mVec.z().z().z().get(), "zzz");

    testSwizzle4(mVec.x().x().x().x(), "xxxx");
    testSwizzle4(mVec.x().x().x().y(), "xxxy");
    testSwizzle4(mVec.x().x().x().z(), "xxxz");
    testSwizzle4(mVec.x().x().y().x(), "xxyx");
    testSwizzle4(mVec.x().x().y().y(), "xxyy");
    testSwizzle4(mVec.x().x().y().z(), "xxyz");
    testSwizzle4(mVec.x().x().z().x(), "xxzx");
    testSwizzle4(mVec.x().x().z().y(), "xxzy");
    testSwizzle4(mVec.x().x().z().z(), "xxzz");
    testSwizzle4(mVec.x().y().x().x(), "xyxx");
    testSwizzle4(mVec.x().y().x().y(), "xyxy");
    testSwizzle4(mVec.x().y().x().z(), "xyxz");
    testSwizzle4(mVec.x().y().y().x(), "xyyx");
    testSwizzle4(mVec.x().y().y().y(), "xyyy");
    testSwizzle4(mVec.x().y().y().z(), "xyyz");
    testSwizzle4(mVec.x().y().z().x(), "xyzx");
    testSwizzle4(mVec.x().y().z().y(), "xyzy");
    testSwizzle4(mVec.x().y().z().z(), "xyzz");
    testSwizzle4(mVec.x().z().x().x(), "xzxx");
    testSwizzle4(mVec.x().z().x().y(), "xzxy");
    testSwizzle4(mVec.x().z().x().z(), "xzxz");
    testSwizzle4(mVec.x().z().y().x(), "xzyx");
    testSwizzle4(mVec.x().z().y().y(), "xzyy");
    testSwizzle4(mVec.x().z().y().z(), "xzyz");
    testSwizzle4(mVec.x().z().z().x(), "xzzx");
    testSwizzle4(mVec.x().z().z().y(), "xzzy");
    testSwizzle4(mVec.x().z().z().z(), "xzzz");
    testSwizzle4(mVec.y().x().x().x(), "yxxx");
    testSwizzle4(mVec.y().x().x().y(), "yxxy");
    testSwizzle4(mVec.y().x().x().z(), "yxxz");
    testSwizzle4(mVec.y().x().y().x(), "yxyx");
    testSwizzle4(mVec.y().x().y().y(), "yxyy");
    testSwizzle4(mVec.y().x().y().z(), "yxyz");
    testSwizzle4(mVec.y().x().z().x(), "yxzx");
    testSwizzle4(mVec.y().x().z().y(), "yxzy");
    testSwizzle4(mVec.y().x().z().z(), "yxzz");
    testSwizzle4(mVec.y().y().x().x(), "yyxx");
    testSwizzle4(mVec.y().y().x().y(), "yyxy");
    testSwizzle4(mVec.y().y().x().z(), "yyxz");
    testSwizzle4(mVec.y().y().y().x(), "yyyx");
    testSwizzle4(mVec.y().y().y().y(), "yyyy");
    testSwizzle4(mVec.y().y().y().z(), "yyyz");
    testSwizzle4(mVec.y().y().z().x(), "yyzx");
    testSwizzle4(mVec.y().y().z().y(), "yyzy");
    testSwizzle4(mVec.y().y().z().z(), "yyzz");
    testSwizzle4(mVec.y().z().x().x(), "yzxx");
    testSwizzle4(mVec.y().z().x().y(), "yzxy");
    testSwizzle4(mVec.y().z().x().z(), "yzxz");
    testSwizzle4(mVec.y().z().y().x(), "yzyx");
    testSwizzle4(mVec.y().z().y().y(), "yzyy");
    testSwizzle4(mVec.y().z().y().z(), "yzyz");
    testSwizzle4(mVec.y().z().z().x(), "yzzx");
    testSwizzle4(mVec.y().z().z().y(), "yzzy");
    testSwizzle4(mVec.y().z().z().z(), "yzzz");
    testSwizzle4(mVec.z().x().x().x(), "zxxx");
    testSwizzle4(mVec.z().x().x().y(), "zxxy");
    testSwizzle4(mVec.z().x().x().z(), "zxxz");
    testSwizzle4(mVec.z().x().y().x(), "zxyx");
    testSwizzle4(mVec.z().x().y().y(), "zxyy");
    testSwizzle4(mVec.z().x().y().z(), "zxyz");
    testSwizzle4(mVec.z().x().z().x(), "zxzx");
    testSwizzle4(mVec.z().x().z().y(), "zxzy");
    testSwizzle4(mVec.z().x().z().z(), "zxzz");
    testSwizzle4(mVec.z().y().x().x(), "zyxx");
    testSwizzle4(mVec.z().y().x().y(), "zyxy");
    testSwizzle4(mVec.z().y().x().z(), "zyxz");
    testSwizzle4(mVec.z().y().y().x(), "zyyx");
    testSwizzle4(mVec.z().y().y().y(), "zyyy");
    testSwizzle4(mVec.z().y().y().z(), "zyyz");
    testSwizzle4(mVec.z().y().z().x(), "zyzx");
    testSwizzle4(mVec.z().y().z().y(), "zyzy");
    testSwizzle4(mVec.z().y().z().z(), "zyzz");
    testSwizzle4(mVec.z().z().x().x(), "zzxx");
    testSwizzle4(mVec.z().z().x().y(), "zzxy");
    testSwizzle4(mVec.z().z().x().z(), "zzxz");
    testSwizzle4(mVec.z().z().y().x(), "zzyx");
    testSwizzle4(mVec.z().z().y().y(), "zzyy");
    testSwizzle4(mVec.z().z().y().z(), "zzyz");
    testSwizzle4(mVec.z().z().z().x(), "zzzx");
    testSwizzle4(mVec.z().z().z().y(), "zzzy");
    testSwizzle4(mVec.z().z().z().z(), "zzzz");

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    testSwizzle1(mVec.r().get(), "r");
    testSwizzle1(mVec.g().get(), "g");
    testSwizzle1(mVec.b().get(), "b");

    testSwizzle2(mVec.r().r().get(), "rr");
    testSwizzle2(mVec.r().g().get(), "rg");
    testSwizzle2(mVec.r().b().get(), "rb");
    testSwizzle2(mVec.g().r().get(), "gr");
    testSwizzle2(mVec.g().g().get(), "gg");
    testSwizzle2(mVec.g().b().get(), "gb");
    testSwizzle2(mVec.b().r().get(), "br");
    testSwizzle2(mVec.b().g().get(), "bg");
    testSwizzle2(mVec.b().b().get(), "bb");

    testSwizzle3(mVec.r().r().r().get(), "rrr");
    testSwizzle3(mVec.r().r().g().get(), "rrg");
    testSwizzle3(mVec.r().r().b().get(), "rrb");
    testSwizzle3(mVec.r().g().r().get(), "rgr");
    testSwizzle3(mVec.r().g().g().get(), "rgg");
    testSwizzle3(mVec.r().g().b().get(), "rgb");
    testSwizzle3(mVec.r().b().r().get(), "rbr");
    testSwizzle3(mVec.r().b().g().get(), "rbg");
    testSwizzle3(mVec.r().b().b().get(), "rbb");
    testSwizzle3(mVec.g().r().r().get(), "grr");
    testSwizzle3(mVec.g().r().g().get(), "grg");
    testSwizzle3(mVec.g().r().b().get(), "grb");
    testSwizzle3(mVec.g().g().r().get(), "ggr");
    testSwizzle3(mVec.g().g().g().get(), "ggg");
    testSwizzle3(mVec.g().g().b().get(), "ggb");
    testSwizzle3(mVec.g().b().r().get(), "gbr");
    testSwizzle3(mVec.g().b().g().get(), "gbg");
    testSwizzle3(mVec.g().b().b().get(), "gbb");
    testSwizzle3(mVec.b().r().r().get(), "brr");
    testSwizzle3(mVec.b().r().g().get(), "brg");
    testSwizzle3(mVec.b().r().b().get(), "brb");
    testSwizzle3(mVec.b().g().r().get(), "bgr");
    testSwizzle3(mVec.b().g().g().get(), "bgg");
    testSwizzle3(mVec.b().g().b().get(), "bgb");
    testSwizzle3(mVec.b().b().r().get(), "bbr");
    testSwizzle3(mVec.b().b().g().get(), "bbg");
    testSwizzle3(mVec.b().b().b().get(), "bbb");

    testSwizzle4(mVec.r().r().r().r(), "rrrr");
    testSwizzle4(mVec.r().r().r().g(), "rrrg");
    testSwizzle4(mVec.r().r().r().b(), "rrrb");
    testSwizzle4(mVec.r().r().g().r(), "rrgr");
    testSwizzle4(mVec.r().r().g().g(), "rrgg");
    testSwizzle4(mVec.r().r().g().b(), "rrgb");
    testSwizzle4(mVec.r().r().b().r(), "rrbr");
    testSwizzle4(mVec.r().r().b().g(), "rrbg");
    testSwizzle4(mVec.r().r().b().b(), "rrbb");
    testSwizzle4(mVec.r().g().r().r(), "rgrr");
    testSwizzle4(mVec.r().g().r().g(), "rgrg");
    testSwizzle4(mVec.r().g().r().b(), "rgrb");
    testSwizzle4(mVec.r().g().g().r(), "rggr");
    testSwizzle4(mVec.r().g().g().g(), "rggg");
    testSwizzle4(mVec.r().g().g().b(), "rggb");
    testSwizzle4(mVec.r().g().b().r(), "rgbr");
    testSwizzle4(mVec.r().g().b().g(), "rgbg");
    testSwizzle4(mVec.r().g().b().b(), "rgbb");
    testSwizzle4(mVec.r().b().r().r(), "rbrr");
    testSwizzle4(mVec.r().b().r().g(), "rbrg");
    testSwizzle4(mVec.r().b().r().b(), "rbrb");
    testSwizzle4(mVec.r().b().g().r(), "rbgr");
    testSwizzle4(mVec.r().b().g().g(), "rbgg");
    testSwizzle4(mVec.r().b().g().b(), "rbgb");
    testSwizzle4(mVec.r().b().b().r(), "rbbr");
    testSwizzle4(mVec.r().b().b().g(), "rbbg");
    testSwizzle4(mVec.r().b().b().b(), "rbbb");
    testSwizzle4(mVec.g().r().r().r(), "grrr");
    testSwizzle4(mVec.g().r().r().g(), "grrg");
    testSwizzle4(mVec.g().r().r().b(), "grrb");
    testSwizzle4(mVec.g().r().g().r(), "grgr");
    testSwizzle4(mVec.g().r().g().g(), "grgg");
    testSwizzle4(mVec.g().r().g().b(), "grgb");
    testSwizzle4(mVec.g().r().b().r(), "grbr");
    testSwizzle4(mVec.g().r().b().g(), "grbg");
    testSwizzle4(mVec.g().r().b().b(), "grbb");
    testSwizzle4(mVec.g().g().r().r(), "ggrr");
    testSwizzle4(mVec.g().g().r().g(), "ggrg");
    testSwizzle4(mVec.g().g().r().b(), "ggrb");
    testSwizzle4(mVec.g().g().g().r(), "gggr");
    testSwizzle4(mVec.g().g().g().g(), "gggg");
    testSwizzle4(mVec.g().g().g().b(), "gggb");
    testSwizzle4(mVec.g().g().b().r(), "ggbr");
    testSwizzle4(mVec.g().g().b().g(), "ggbg");
    testSwizzle4(mVec.g().g().b().b(), "ggbb");
    testSwizzle4(mVec.g().b().r().r(), "gbrr");
    testSwizzle4(mVec.g().b().r().g(), "gbrg");
    testSwizzle4(mVec.g().b().r().b(), "gbrb");
    testSwizzle4(mVec.g().b().g().r(), "gbgr");
    testSwizzle4(mVec.g().b().g().g(), "gbgg");
    testSwizzle4(mVec.g().b().g().b(), "gbgb");
    testSwizzle4(mVec.g().b().b().r(), "gbbr");
    testSwizzle4(mVec.g().b().b().g(), "gbbg");
    testSwizzle4(mVec.g().b().b().b(), "gbbb");
    testSwizzle4(mVec.b().r().r().r(), "brrr");
    testSwizzle4(mVec.b().r().r().g(), "brrg");
    testSwizzle4(mVec.b().r().r().b(), "brrb");
    testSwizzle4(mVec.b().r().g().r(), "brgr");
    testSwizzle4(mVec.b().r().g().g(), "brgg");
    testSwizzle4(mVec.b().r().g().b(), "brgb");
    testSwizzle4(mVec.b().r().b().r(), "brbr");
    testSwizzle4(mVec.b().r().b().g(), "brbg");
    testSwizzle4(mVec.b().r().b().b(), "brbb");
    testSwizzle4(mVec.b().g().r().r(), "bgrr");
    testSwizzle4(mVec.b().g().r().g(), "bgrg");
    testSwizzle4(mVec.b().g().r().b(), "bgrb");
    testSwizzle4(mVec.b().g().g().r(), "bggr");
    testSwizzle4(mVec.b().g().g().g(), "bggg");
    testSwizzle4(mVec.b().g().g().b(), "bggb");
    testSwizzle4(mVec.b().g().b().r(), "bgbr");
    testSwizzle4(mVec.b().g().b().g(), "bgbg");
    testSwizzle4(mVec.b().g().b().b(), "bgbb");
    testSwizzle4(mVec.b().b().r().r(), "bbrr");
    testSwizzle4(mVec.b().b().r().g(), "bbrg");
    testSwizzle4(mVec.b().b().r().b(), "bbrb");
    testSwizzle4(mVec.b().b().g().r(), "bbgr");
    testSwizzle4(mVec.b().b().g().g(), "bbgg");
    testSwizzle4(mVec.b().b().g().b(), "bbgb");
    testSwizzle4(mVec.b().b().b().r(), "bbbr");
    testSwizzle4(mVec.b().b().b().g(), "bbbg");
    testSwizzle4(mVec.b().b().b().b(), "bbbb");

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    testSwizzle1(mVec.s().get(), "s");
    testSwizzle1(mVec.t().get(), "t");
    testSwizzle1(mVec.p().get(), "p");

    testSwizzle2(mVec.s().s().get(), "ss");
    testSwizzle2(mVec.s().t().get(), "st");
    testSwizzle2(mVec.s().p().get(), "sp");
    testSwizzle2(mVec.t().s().get(), "ts");
    testSwizzle2(mVec.t().t().get(), "tt");
    testSwizzle2(mVec.t().p().get(), "tp");
    testSwizzle2(mVec.p().s().get(), "ps");
    testSwizzle2(mVec.p().t().get(), "pt");
    testSwizzle2(mVec.p().p().get(), "pp");

    testSwizzle3(mVec.s().s().s().get(), "sss");
    testSwizzle3(mVec.s().s().t().get(), "sst");
    testSwizzle3(mVec.s().s().p().get(), "ssp");
    testSwizzle3(mVec.s().t().s().get(), "sts");
    testSwizzle3(mVec.s().t().t().get(), "stt");
    testSwizzle3(mVec.s().t().p().get(), "stp");
    testSwizzle3(mVec.s().p().s().get(), "sps");
    testSwizzle3(mVec.s().p().t().get(), "spt");
    testSwizzle3(mVec.s().p().p().get(), "spp");
    testSwizzle3(mVec.t().s().s().get(), "tss");
    testSwizzle3(mVec.t().s().t().get(), "tst");
    testSwizzle3(mVec.t().s().p().get(), "tsp");
    testSwizzle3(mVec.t().t().s().get(), "tts");
    testSwizzle3(mVec.t().t().t().get(), "ttt");
    testSwizzle3(mVec.t().t().p().get(), "ttp");
    testSwizzle3(mVec.t().p().s().get(), "tps");
    testSwizzle3(mVec.t().p().t().get(), "tpt");
    testSwizzle3(mVec.t().p().p().get(), "tpp");
    testSwizzle3(mVec.p().s().s().get(), "pss");
    testSwizzle3(mVec.p().s().t().get(), "pst");
    testSwizzle3(mVec.p().s().p().get(), "psp");
    testSwizzle3(mVec.p().t().s().get(), "pts");
    testSwizzle3(mVec.p().t().t().get(), "ptt");
    testSwizzle3(mVec.p().t().p().get(), "ptp");
    testSwizzle3(mVec.p().p().s().get(), "pps");
    testSwizzle3(mVec.p().p().t().get(), "ppt");
    testSwizzle3(mVec.p().p().p().get(), "ppp");

    testSwizzle4(mVec.s().s().s().s(), "ssss");
    testSwizzle4(mVec.s().s().s().t(), "ssst");
    testSwizzle4(mVec.s().s().s().p(), "sssp");
    testSwizzle4(mVec.s().s().t().s(), "ssts");
    testSwizzle4(mVec.s().s().t().t(), "sstt");
    testSwizzle4(mVec.s().s().t().p(), "sstp");
    testSwizzle4(mVec.s().s().p().s(), "ssps");
    testSwizzle4(mVec.s().s().p().t(), "sspt");
    testSwizzle4(mVec.s().s().p().p(), "sspp");
    testSwizzle4(mVec.s().t().s().s(), "stss");
    testSwizzle4(mVec.s().t().s().t(), "stst");
    testSwizzle4(mVec.s().t().s().p(), "stsp");
    testSwizzle4(mVec.s().t().t().s(), "stts");
    testSwizzle4(mVec.s().t().t().t(), "sttt");
    testSwizzle4(mVec.s().t().t().p(), "sttp");
    testSwizzle4(mVec.s().t().p().s(), "stps");
    testSwizzle4(mVec.s().t().p().t(), "stpt");
    testSwizzle4(mVec.s().t().p().p(), "stpp");
    testSwizzle4(mVec.s().p().s().s(), "spss");
    testSwizzle4(mVec.s().p().s().t(), "spst");
    testSwizzle4(mVec.s().p().s().p(), "spsp");
    testSwizzle4(mVec.s().p().t().s(), "spts");
    testSwizzle4(mVec.s().p().t().t(), "sptt");
    testSwizzle4(mVec.s().p().t().p(), "sptp");
    testSwizzle4(mVec.s().p().p().s(), "spps");
    testSwizzle4(mVec.s().p().p().t(), "sppt");
    testSwizzle4(mVec.s().p().p().p(), "sppp");
    testSwizzle4(mVec.t().s().s().s(), "tsss");
    testSwizzle4(mVec.t().s().s().t(), "tsst");
    testSwizzle4(mVec.t().s().s().p(), "tssp");
    testSwizzle4(mVec.t().s().t().s(), "tsts");
    testSwizzle4(mVec.t().s().t().t(), "tstt");
    testSwizzle4(mVec.t().s().t().p(), "tstp");
    testSwizzle4(mVec.t().s().p().s(), "tsps");
    testSwizzle4(mVec.t().s().p().t(), "tspt");
    testSwizzle4(mVec.t().s().p().p(), "tspp");
    testSwizzle4(mVec.t().t().s().s(), "ttss");
    testSwizzle4(mVec.t().t().s().t(), "ttst");
    testSwizzle4(mVec.t().t().s().p(), "ttsp");
    testSwizzle4(mVec.t().t().t().s(), "ttts");
    testSwizzle4(mVec.t().t().t().t(), "tttt");
    testSwizzle4(mVec.t().t().t().p(), "tttp");
    testSwizzle4(mVec.t().t().p().s(), "ttps");
    testSwizzle4(mVec.t().t().p().t(), "ttpt");
    testSwizzle4(mVec.t().t().p().p(), "ttpp");
    testSwizzle4(mVec.t().p().s().s(), "tpss");
    testSwizzle4(mVec.t().p().s().t(), "tpst");
    testSwizzle4(mVec.t().p().s().p(), "tpsp");
    testSwizzle4(mVec.t().p().t().s(), "tpts");
    testSwizzle4(mVec.t().p().t().t(), "tptt");
    testSwizzle4(mVec.t().p().t().p(), "tptp");
    testSwizzle4(mVec.t().p().p().s(), "tpps");
    testSwizzle4(mVec.t().p().p().t(), "tppt");
    testSwizzle4(mVec.t().p().p().p(), "tppp");
    testSwizzle4(mVec.p().s().s().s(), "psss");
    testSwizzle4(mVec.p().s().s().t(), "psst");
    testSwizzle4(mVec.p().s().s().p(), "pssp");
    testSwizzle4(mVec.p().s().t().s(), "psts");
    testSwizzle4(mVec.p().s().t().t(), "pstt");
    testSwizzle4(mVec.p().s().t().p(), "pstp");
    testSwizzle4(mVec.p().s().p().s(), "psps");
    testSwizzle4(mVec.p().s().p().t(), "pspt");
    testSwizzle4(mVec.p().s().p().p(), "pspp");
    testSwizzle4(mVec.p().t().s().s(), "ptss");
    testSwizzle4(mVec.p().t().s().t(), "ptst");
    testSwizzle4(mVec.p().t().s().p(), "ptsp");
    testSwizzle4(mVec.p().t().t().s(), "ptts");
    testSwizzle4(mVec.p().t().t().t(), "pttt");
    testSwizzle4(mVec.p().t().t().p(), "pttp");
    testSwizzle4(mVec.p().t().p().s(), "ptps");
    testSwizzle4(mVec.p().t().p().t(), "ptpt");
    testSwizzle4(mVec.p().t().p().p(), "ptpp");
    testSwizzle4(mVec.p().p().s().s(), "ppss");
    testSwizzle4(mVec.p().p().s().t(), "ppst");
    testSwizzle4(mVec.p().p().s().p(), "ppsp");
    testSwizzle4(mVec.p().p().t().s(), "ppts");
    testSwizzle4(mVec.p().p().t().t(), "pptt");
    testSwizzle4(mVec.p().p().t().p(), "pptp");
    testSwizzle4(mVec.p().p().p().s(), "ppps");
    testSwizzle4(mVec.p().p().p().t(), "pppt");
    testSwizzle4(mVec.p().p().p().p(), "pppp");

  }

  private void testNonLeafVector(Vector3 vec) {
    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
  }

  @Test
  public void testAdd() {
    Vector3 vec = mVec.add(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(mVec, vec, mVec.add(real), real, mVec.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Vector3 vec = mVec.sub(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(mVec, vec, mVec.sub(real), real, mVec.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Vector3 vec = mVec.mul(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(mVec, vec, mVec.mul(real), real, mVec.mul(rhs), rhs, Expression.NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Vector3 vec = mVec.div(1);
    Real real = new Real(1);
    Vector3 rhs = new Vector3(1, 2, 3);

    testArithmetic(mVec, vec, mVec.div(real), real, mVec.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Vector3 neg = mVec.neg();

    testNonLeafVector(neg);
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testDot() {
    Vector3 vec = new Vector3(1, 2, 3);
    Real dot = mVec.dot(vec);

    testNonLeafExpression(dot);
    testFunction(dot, "dot");
    assertEquals(dot.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testCross() {
    Vector3 vec = new Vector3(2, 3, 4);
    Vector3 cross = mVec.cross(vec);

    testNonLeafVector(cross);
    testFunction(cross, "cross");
    assertEquals(cross.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testNormalize() {
    Vector3 normalized = mVec.normalize();

    testNonLeafVector(normalized);
    testFunction(normalized, "normalize");
    assertEquals(normalized.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testLength() {
    Real length = mVec.length();

    testNonLeafExpression(length);
    testFunction(length, "length");
    assertEquals(length.getParents(), ImmutableList.<Expression>of(mVec));
  }

  @Test
  public void testIsLessThan() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 lessThan = mVec.isLessThan(rhs);

    testNonLeafExpression(lessThan);
    testFunction(lessThan, "lessThan");
    assertEquals(lessThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsLessThanOrEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 lessThanOrEqual = mVec.isLessThanOrEqual(rhs);

    testNonLeafExpression(lessThanOrEqual);
    testFunction(lessThanOrEqual, "lessThanEqual");
    assertEquals(lessThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThan() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 greaterThan = mVec.isGreaterThan(rhs);

    testNonLeafExpression(greaterThan);
    testFunction(greaterThan, "greaterThan");
    assertEquals(greaterThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThanOrEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 greaterThanOrEqual = mVec.isGreaterThanOrEqual(rhs);

    testNonLeafExpression(greaterThanOrEqual);
    testFunction(greaterThanOrEqual, "greaterThanEqual");
    assertEquals(greaterThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqualComponentwise() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 equalComponentwise = mVec.isEqualComponentwise(rhs);

    testNonLeafExpression(equalComponentwise);
    testFunction(equalComponentwise, "equal");
    assertEquals(equalComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqualComponentwise() {
    Vector3 rhs = new Vector3(1, 2, 3);
    BVector3 notEqualComponentwise = mVec.isNotEqualComponentwise(rhs);

    testNonLeafExpression(notEqualComponentwise);
    testFunction(notEqualComponentwise, "notEqual");
    assertEquals(notEqualComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    Bool equal = mVec.isEqual(rhs);

    testNonLeafExpression(equal);
    assertTrue(equal.getNodeType().isPresent());
    assertEquals(equal.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(equal.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Vector3 rhs = new Vector3(1, 2, 3);
    Bool notEqual = mVec.isNotEqual(rhs);

    testNonLeafExpression(notEqual);
    assertTrue(notEqual.getNodeType().isPresent());
    assertEquals(notEqual.getNodeType().get(), Expression.NodeType.NEQ);
    assertEquals(notEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testFill() {
    Vector4 defaultValues = new Vector4(1, 2, 3, 4);
    Vector4 fill = mVec.fill(defaultValues);

    testNonLeafExpression(fill);
    assertEquals(fill.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(fill.getParents().size(), 4);
  }
}