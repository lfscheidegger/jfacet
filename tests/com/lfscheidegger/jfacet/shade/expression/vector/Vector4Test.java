package com.lfscheidegger.jfacet.shade.expression.vector;

import com.google.common.collect.ImmutableList;
import com.lfscheidegger.jfacet.facet.AttribBuffer;
import com.lfscheidegger.jfacet.shade.GlSlQualifier;
import com.lfscheidegger.jfacet.shade.expression.Bool;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.Real;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@code Vector4}
 */
public class Vector4Test {

  private final Vector4 mVec = new Vector4(1, 2, 3, 4);

  @Test
  public void testConstructors() {
    Vector4 vec = new Vector4(1, 2, 3, 4);

    assertTrue(vec.getPrimitive().isPresent());
    assertEquals(vec.getPrimitive().get(), new Vector4.Primitive(1, 2, 3, 4));
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.of());

    Real x = new Real(1), y = new Real(2), z = new Real(3), w = new Real(4);
    vec = new Vector4(x, y, z, w);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z, w));

    vec = new Vector4(ImmutableList.<Expression>of(x, y, z, w), Expression.NodeType.CONS);

    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
    assertEquals(vec.getNodeType().get(), Expression.NodeType.CONS);
    assertEquals(vec.getParents(), ImmutableList.of(x, y, z, w));

    vec = new Vector4(new AttribBuffer(new float[] {0, 0, 1, 0, 1, 1}, 2));

    assertFalse(vec.getPrimitive().isPresent());
    assertTrue(vec.getAttribBuffer().isPresent());
    assertTrue(vec.getGlSlQualifier().isPresent());
    assertEquals(vec.getGlSlQualifier().get(), GlSlQualifier.ATTRIBUTE_T);
    assertFalse(vec.getNodeType().isPresent());
    assertEquals(vec.getParents(), ImmutableList.<Expression>of());
  }

  private void testGetter(
      Real component,
      int expectedComponent,
      ImmutableList<Expression> expectedParents) {

    assertFalse(component.getPrimitive().isPresent());
    assertFalse(component.getAttribBuffer().isPresent());
    assertFalse(component.getGlSlQualifier().isPresent());
    assertTrue(component.getNodeType().isPresent());
    assertTrue(component.getNodeType().get() instanceof Expression.NodeType.ComponentNodeType);

    assertEquals(((Expression.NodeType.ComponentNodeType)
        component.getNodeType().get()).getComponent(), expectedComponent);
    assertEquals(component.getParents(), expectedParents);
  }

  @Test
  public void testGetters() {
    Vector4 vec = new Vector4(1, 2, 3, 4);
    Real x = vec.getX();
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    Real y = vec.getY();
    testGetter(y, 1, ImmutableList.<Expression>of(vec));

    x = vec.get(0);
    testGetter(x, 0, ImmutableList.<Expression>of(vec));

    y = vec.get(1);
    testGetter(y, 1, ImmutableList.<Expression>of(vec));
  }

  private void testSwizzle1(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Real);
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle2(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector2);
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle3(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector3);
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle4(Expression swizzled, String expectedString) {
    assertTrue(swizzled instanceof Vector4);
    testSwizzle(swizzled, expectedString);
  }

  private void testSwizzle(Expression swizzled, String expectedString) {
    testNonLeafExpression(swizzled);
    assertEquals(swizzled.getParents(), ImmutableList.<Expression>of(mVec));
    assertTrue(swizzled.getNodeType().get() instanceof Expression.NodeType.SwizzleNodeType);
    Expression.NodeType.SwizzleNodeType nodeType = (Expression.NodeType.SwizzleNodeType)
        swizzled.getNodeType().get();
    assertEquals(nodeType.getSwizzleString(), expectedString);
  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleXYZW() {
    testSwizzle1(mVec.x().get(), "x");
    testSwizzle1(mVec.y().get(), "y");
    testSwizzle1(mVec.z().get(), "z");
    testSwizzle1(mVec.w().get(), "w");

    testSwizzle2(mVec.x().x().get(), "xx");
    testSwizzle2(mVec.x().y().get(), "xy");
    testSwizzle2(mVec.x().z().get(), "xz");
    testSwizzle2(mVec.x().w().get(), "xw");
    testSwizzle2(mVec.y().x().get(), "yx");
    testSwizzle2(mVec.y().y().get(), "yy");
    testSwizzle2(mVec.y().z().get(), "yz");
    testSwizzle2(mVec.y().w().get(), "yw");
    testSwizzle2(mVec.z().x().get(), "zx");
    testSwizzle2(mVec.z().y().get(), "zy");
    testSwizzle2(mVec.z().z().get(), "zz");
    testSwizzle2(mVec.z().w().get(), "zw");
    testSwizzle2(mVec.w().x().get(), "wx");
    testSwizzle2(mVec.w().y().get(), "wy");
    testSwizzle2(mVec.w().z().get(), "wz");
    testSwizzle2(mVec.w().w().get(), "ww");

    testSwizzle3(mVec.x().x().x().get(), "xxx");
    testSwizzle3(mVec.x().x().y().get(), "xxy");
    testSwizzle3(mVec.x().x().z().get(), "xxz");
    testSwizzle3(mVec.x().x().w().get(), "xxw");
    testSwizzle3(mVec.x().y().x().get(), "xyx");
    testSwizzle3(mVec.x().y().y().get(), "xyy");
    testSwizzle3(mVec.x().y().z().get(), "xyz");
    testSwizzle3(mVec.x().y().w().get(), "xyw");
    testSwizzle3(mVec.x().z().x().get(), "xzx");
    testSwizzle3(mVec.x().z().y().get(), "xzy");
    testSwizzle3(mVec.x().z().z().get(), "xzz");
    testSwizzle3(mVec.x().z().w().get(), "xzw");
    testSwizzle3(mVec.x().w().x().get(), "xwx");
    testSwizzle3(mVec.x().w().y().get(), "xwy");
    testSwizzle3(mVec.x().w().z().get(), "xwz");
    testSwizzle3(mVec.x().w().w().get(), "xww");
    testSwizzle3(mVec.y().x().x().get(), "yxx");
    testSwizzle3(mVec.y().x().y().get(), "yxy");
    testSwizzle3(mVec.y().x().z().get(), "yxz");
    testSwizzle3(mVec.y().x().w().get(), "yxw");
    testSwizzle3(mVec.y().y().x().get(), "yyx");
    testSwizzle3(mVec.y().y().y().get(), "yyy");
    testSwizzle3(mVec.y().y().z().get(), "yyz");
    testSwizzle3(mVec.y().y().w().get(), "yyw");
    testSwizzle3(mVec.y().z().x().get(), "yzx");
    testSwizzle3(mVec.y().z().y().get(), "yzy");
    testSwizzle3(mVec.y().z().z().get(), "yzz");
    testSwizzle3(mVec.y().z().w().get(), "yzw");
    testSwizzle3(mVec.y().w().x().get(), "ywx");
    testSwizzle3(mVec.y().w().y().get(), "ywy");
    testSwizzle3(mVec.y().w().z().get(), "ywz");
    testSwizzle3(mVec.y().w().w().get(), "yww");
    testSwizzle3(mVec.z().x().x().get(), "zxx");
    testSwizzle3(mVec.z().x().y().get(), "zxy");
    testSwizzle3(mVec.z().x().z().get(), "zxz");
    testSwizzle3(mVec.z().x().w().get(), "zxw");
    testSwizzle3(mVec.z().y().x().get(), "zyx");
    testSwizzle3(mVec.z().y().y().get(), "zyy");
    testSwizzle3(mVec.z().y().z().get(), "zyz");
    testSwizzle3(mVec.z().y().w().get(), "zyw");
    testSwizzle3(mVec.z().z().x().get(), "zzx");
    testSwizzle3(mVec.z().z().y().get(), "zzy");
    testSwizzle3(mVec.z().z().z().get(), "zzz");
    testSwizzle3(mVec.z().z().w().get(), "zzw");
    testSwizzle3(mVec.z().w().x().get(), "zwx");
    testSwizzle3(mVec.z().w().y().get(), "zwy");
    testSwizzle3(mVec.z().w().z().get(), "zwz");
    testSwizzle3(mVec.z().w().w().get(), "zww");
    testSwizzle3(mVec.w().x().x().get(), "wxx");
    testSwizzle3(mVec.w().x().y().get(), "wxy");
    testSwizzle3(mVec.w().x().z().get(), "wxz");
    testSwizzle3(mVec.w().x().w().get(), "wxw");
    testSwizzle3(mVec.w().y().x().get(), "wyx");
    testSwizzle3(mVec.w().y().y().get(), "wyy");
    testSwizzle3(mVec.w().y().z().get(), "wyz");
    testSwizzle3(mVec.w().y().w().get(), "wyw");
    testSwizzle3(mVec.w().z().x().get(), "wzx");
    testSwizzle3(mVec.w().z().y().get(), "wzy");
    testSwizzle3(mVec.w().z().z().get(), "wzz");
    testSwizzle3(mVec.w().z().w().get(), "wzw");
    testSwizzle3(mVec.w().w().x().get(), "wwx");
    testSwizzle3(mVec.w().w().y().get(), "wwy");
    testSwizzle3(mVec.w().w().z().get(), "wwz");
    testSwizzle3(mVec.w().w().w().get(), "www");

    testSwizzle4(mVec.x().x().x().x(), "xxxx");
    testSwizzle4(mVec.x().x().x().y(), "xxxy");
    testSwizzle4(mVec.x().x().x().z(), "xxxz");
    testSwizzle4(mVec.x().x().x().w(), "xxxw");
    testSwizzle4(mVec.x().x().y().x(), "xxyx");
    testSwizzle4(mVec.x().x().y().y(), "xxyy");
    testSwizzle4(mVec.x().x().y().z(), "xxyz");
    testSwizzle4(mVec.x().x().y().w(), "xxyw");
    testSwizzle4(mVec.x().x().z().x(), "xxzx");
    testSwizzle4(mVec.x().x().z().y(), "xxzy");
    testSwizzle4(mVec.x().x().z().z(), "xxzz");
    testSwizzle4(mVec.x().x().z().w(), "xxzw");
    testSwizzle4(mVec.x().x().w().x(), "xxwx");
    testSwizzle4(mVec.x().x().w().y(), "xxwy");
    testSwizzle4(mVec.x().x().w().z(), "xxwz");
    testSwizzle4(mVec.x().x().w().w(), "xxww");
    testSwizzle4(mVec.x().y().x().x(), "xyxx");
    testSwizzle4(mVec.x().y().x().y(), "xyxy");
    testSwizzle4(mVec.x().y().x().z(), "xyxz");
    testSwizzle4(mVec.x().y().x().w(), "xyxw");
    testSwizzle4(mVec.x().y().y().x(), "xyyx");
    testSwizzle4(mVec.x().y().y().y(), "xyyy");
    testSwizzle4(mVec.x().y().y().z(), "xyyz");
    testSwizzle4(mVec.x().y().y().w(), "xyyw");
    testSwizzle4(mVec.x().y().z().x(), "xyzx");
    testSwizzle4(mVec.x().y().z().y(), "xyzy");
    testSwizzle4(mVec.x().y().z().z(), "xyzz");
    testSwizzle4(mVec.x().y().z().w(), "xyzw");
    testSwizzle4(mVec.x().y().w().x(), "xywx");
    testSwizzle4(mVec.x().y().w().y(), "xywy");
    testSwizzle4(mVec.x().y().w().z(), "xywz");
    testSwizzle4(mVec.x().y().w().w(), "xyww");
    testSwizzle4(mVec.x().z().x().x(), "xzxx");
    testSwizzle4(mVec.x().z().x().y(), "xzxy");
    testSwizzle4(mVec.x().z().x().z(), "xzxz");
    testSwizzle4(mVec.x().z().x().w(), "xzxw");
    testSwizzle4(mVec.x().z().y().x(), "xzyx");
    testSwizzle4(mVec.x().z().y().y(), "xzyy");
    testSwizzle4(mVec.x().z().y().z(), "xzyz");
    testSwizzle4(mVec.x().z().y().w(), "xzyw");
    testSwizzle4(mVec.x().z().z().x(), "xzzx");
    testSwizzle4(mVec.x().z().z().y(), "xzzy");
    testSwizzle4(mVec.x().z().z().z(), "xzzz");
    testSwizzle4(mVec.x().z().z().w(), "xzzw");
    testSwizzle4(mVec.x().z().w().x(), "xzwx");
    testSwizzle4(mVec.x().z().w().y(), "xzwy");
    testSwizzle4(mVec.x().z().w().z(), "xzwz");
    testSwizzle4(mVec.x().z().w().w(), "xzww");
    testSwizzle4(mVec.x().w().x().x(), "xwxx");
    testSwizzle4(mVec.x().w().x().y(), "xwxy");
    testSwizzle4(mVec.x().w().x().z(), "xwxz");
    testSwizzle4(mVec.x().w().x().w(), "xwxw");
    testSwizzle4(mVec.x().w().y().x(), "xwyx");
    testSwizzle4(mVec.x().w().y().y(), "xwyy");
    testSwizzle4(mVec.x().w().y().z(), "xwyz");
    testSwizzle4(mVec.x().w().y().w(), "xwyw");
    testSwizzle4(mVec.x().w().z().x(), "xwzx");
    testSwizzle4(mVec.x().w().z().y(), "xwzy");
    testSwizzle4(mVec.x().w().z().z(), "xwzz");
    testSwizzle4(mVec.x().w().z().w(), "xwzw");
    testSwizzle4(mVec.x().w().w().x(), "xwwx");
    testSwizzle4(mVec.x().w().w().y(), "xwwy");
    testSwizzle4(mVec.x().w().w().z(), "xwwz");
    testSwizzle4(mVec.x().w().w().w(), "xwww");
    testSwizzle4(mVec.y().x().x().x(), "yxxx");
    testSwizzle4(mVec.y().x().x().y(), "yxxy");
    testSwizzle4(mVec.y().x().x().z(), "yxxz");
    testSwizzle4(mVec.y().x().x().w(), "yxxw");
    testSwizzle4(mVec.y().x().y().x(), "yxyx");
    testSwizzle4(mVec.y().x().y().y(), "yxyy");
    testSwizzle4(mVec.y().x().y().z(), "yxyz");
    testSwizzle4(mVec.y().x().y().w(), "yxyw");
    testSwizzle4(mVec.y().x().z().x(), "yxzx");
    testSwizzle4(mVec.y().x().z().y(), "yxzy");
    testSwizzle4(mVec.y().x().z().z(), "yxzz");
    testSwizzle4(mVec.y().x().z().w(), "yxzw");
    testSwizzle4(mVec.y().x().w().x(), "yxwx");
    testSwizzle4(mVec.y().x().w().y(), "yxwy");
    testSwizzle4(mVec.y().x().w().z(), "yxwz");
    testSwizzle4(mVec.y().x().w().w(), "yxww");
    testSwizzle4(mVec.y().y().x().x(), "yyxx");
    testSwizzle4(mVec.y().y().x().y(), "yyxy");
    testSwizzle4(mVec.y().y().x().z(), "yyxz");
    testSwizzle4(mVec.y().y().x().w(), "yyxw");
    testSwizzle4(mVec.y().y().y().x(), "yyyx");
    testSwizzle4(mVec.y().y().y().y(), "yyyy");
    testSwizzle4(mVec.y().y().y().z(), "yyyz");
    testSwizzle4(mVec.y().y().y().w(), "yyyw");
    testSwizzle4(mVec.y().y().z().x(), "yyzx");
    testSwizzle4(mVec.y().y().z().y(), "yyzy");
    testSwizzle4(mVec.y().y().z().z(), "yyzz");
    testSwizzle4(mVec.y().y().z().w(), "yyzw");
    testSwizzle4(mVec.y().y().w().x(), "yywx");
    testSwizzle4(mVec.y().y().w().y(), "yywy");
    testSwizzle4(mVec.y().y().w().z(), "yywz");
    testSwizzle4(mVec.y().y().w().w(), "yyww");
    testSwizzle4(mVec.y().z().x().x(), "yzxx");
    testSwizzle4(mVec.y().z().x().y(), "yzxy");
    testSwizzle4(mVec.y().z().x().z(), "yzxz");
    testSwizzle4(mVec.y().z().x().w(), "yzxw");
    testSwizzle4(mVec.y().z().y().x(), "yzyx");
    testSwizzle4(mVec.y().z().y().y(), "yzyy");
    testSwizzle4(mVec.y().z().y().z(), "yzyz");
    testSwizzle4(mVec.y().z().y().w(), "yzyw");
    testSwizzle4(mVec.y().z().z().x(), "yzzx");
    testSwizzle4(mVec.y().z().z().y(), "yzzy");
    testSwizzle4(mVec.y().z().z().z(), "yzzz");
    testSwizzle4(mVec.y().z().z().w(), "yzzw");
    testSwizzle4(mVec.y().z().w().x(), "yzwx");
    testSwizzle4(mVec.y().z().w().y(), "yzwy");
    testSwizzle4(mVec.y().z().w().z(), "yzwz");
    testSwizzle4(mVec.y().z().w().w(), "yzww");
    testSwizzle4(mVec.y().w().x().x(), "ywxx");
    testSwizzle4(mVec.y().w().x().y(), "ywxy");
    testSwizzle4(mVec.y().w().x().z(), "ywxz");
    testSwizzle4(mVec.y().w().x().w(), "ywxw");
    testSwizzle4(mVec.y().w().y().x(), "ywyx");
    testSwizzle4(mVec.y().w().y().y(), "ywyy");
    testSwizzle4(mVec.y().w().y().z(), "ywyz");
    testSwizzle4(mVec.y().w().y().w(), "ywyw");
    testSwizzle4(mVec.y().w().z().x(), "ywzx");
    testSwizzle4(mVec.y().w().z().y(), "ywzy");
    testSwizzle4(mVec.y().w().z().z(), "ywzz");
    testSwizzle4(mVec.y().w().z().w(), "ywzw");
    testSwizzle4(mVec.y().w().w().x(), "ywwx");
    testSwizzle4(mVec.y().w().w().y(), "ywwy");
    testSwizzle4(mVec.y().w().w().z(), "ywwz");
    testSwizzle4(mVec.y().w().w().w(), "ywww");
    testSwizzle4(mVec.z().x().x().x(), "zxxx");
    testSwizzle4(mVec.z().x().x().y(), "zxxy");
    testSwizzle4(mVec.z().x().x().z(), "zxxz");
    testSwizzle4(mVec.z().x().x().w(), "zxxw");
    testSwizzle4(mVec.z().x().y().x(), "zxyx");
    testSwizzle4(mVec.z().x().y().y(), "zxyy");
    testSwizzle4(mVec.z().x().y().z(), "zxyz");
    testSwizzle4(mVec.z().x().y().w(), "zxyw");
    testSwizzle4(mVec.z().x().z().x(), "zxzx");
    testSwizzle4(mVec.z().x().z().y(), "zxzy");
    testSwizzle4(mVec.z().x().z().z(), "zxzz");
    testSwizzle4(mVec.z().x().z().w(), "zxzw");
    testSwizzle4(mVec.z().x().w().x(), "zxwx");
    testSwizzle4(mVec.z().x().w().y(), "zxwy");
    testSwizzle4(mVec.z().x().w().z(), "zxwz");
    testSwizzle4(mVec.z().x().w().w(), "zxww");
    testSwizzle4(mVec.z().y().x().x(), "zyxx");
    testSwizzle4(mVec.z().y().x().y(), "zyxy");
    testSwizzle4(mVec.z().y().x().z(), "zyxz");
    testSwizzle4(mVec.z().y().x().w(), "zyxw");
    testSwizzle4(mVec.z().y().y().x(), "zyyx");
    testSwizzle4(mVec.z().y().y().y(), "zyyy");
    testSwizzle4(mVec.z().y().y().z(), "zyyz");
    testSwizzle4(mVec.z().y().y().w(), "zyyw");
    testSwizzle4(mVec.z().y().z().x(), "zyzx");
    testSwizzle4(mVec.z().y().z().y(), "zyzy");
    testSwizzle4(mVec.z().y().z().z(), "zyzz");
    testSwizzle4(mVec.z().y().z().w(), "zyzw");
    testSwizzle4(mVec.z().y().w().x(), "zywx");
    testSwizzle4(mVec.z().y().w().y(), "zywy");
    testSwizzle4(mVec.z().y().w().z(), "zywz");
    testSwizzle4(mVec.z().y().w().w(), "zyww");
    testSwizzle4(mVec.z().z().x().x(), "zzxx");
    testSwizzle4(mVec.z().z().x().y(), "zzxy");
    testSwizzle4(mVec.z().z().x().z(), "zzxz");
    testSwizzle4(mVec.z().z().x().w(), "zzxw");
    testSwizzle4(mVec.z().z().y().x(), "zzyx");
    testSwizzle4(mVec.z().z().y().y(), "zzyy");
    testSwizzle4(mVec.z().z().y().z(), "zzyz");
    testSwizzle4(mVec.z().z().y().w(), "zzyw");
    testSwizzle4(mVec.z().z().z().x(), "zzzx");
    testSwizzle4(mVec.z().z().z().y(), "zzzy");
    testSwizzle4(mVec.z().z().z().z(), "zzzz");
    testSwizzle4(mVec.z().z().z().w(), "zzzw");
    testSwizzle4(mVec.z().z().w().x(), "zzwx");
    testSwizzle4(mVec.z().z().w().y(), "zzwy");
    testSwizzle4(mVec.z().z().w().z(), "zzwz");
    testSwizzle4(mVec.z().z().w().w(), "zzww");
    testSwizzle4(mVec.z().w().x().x(), "zwxx");
    testSwizzle4(mVec.z().w().x().y(), "zwxy");
    testSwizzle4(mVec.z().w().x().z(), "zwxz");
    testSwizzle4(mVec.z().w().x().w(), "zwxw");
    testSwizzle4(mVec.z().w().y().x(), "zwyx");
    testSwizzle4(mVec.z().w().y().y(), "zwyy");
    testSwizzle4(mVec.z().w().y().z(), "zwyz");
    testSwizzle4(mVec.z().w().y().w(), "zwyw");
    testSwizzle4(mVec.z().w().z().x(), "zwzx");
    testSwizzle4(mVec.z().w().z().y(), "zwzy");
    testSwizzle4(mVec.z().w().z().z(), "zwzz");
    testSwizzle4(mVec.z().w().z().w(), "zwzw");
    testSwizzle4(mVec.z().w().w().x(), "zwwx");
    testSwizzle4(mVec.z().w().w().y(), "zwwy");
    testSwizzle4(mVec.z().w().w().z(), "zwwz");
    testSwizzle4(mVec.z().w().w().w(), "zwww");
    testSwizzle4(mVec.w().x().x().x(), "wxxx");
    testSwizzle4(mVec.w().x().x().y(), "wxxy");
    testSwizzle4(mVec.w().x().x().z(), "wxxz");
    testSwizzle4(mVec.w().x().x().w(), "wxxw");
    testSwizzle4(mVec.w().x().y().x(), "wxyx");
    testSwizzle4(mVec.w().x().y().y(), "wxyy");
    testSwizzle4(mVec.w().x().y().z(), "wxyz");
    testSwizzle4(mVec.w().x().y().w(), "wxyw");
    testSwizzle4(mVec.w().x().z().x(), "wxzx");
    testSwizzle4(mVec.w().x().z().y(), "wxzy");
    testSwizzle4(mVec.w().x().z().z(), "wxzz");
    testSwizzle4(mVec.w().x().z().w(), "wxzw");
    testSwizzle4(mVec.w().x().w().x(), "wxwx");
    testSwizzle4(mVec.w().x().w().y(), "wxwy");
    testSwizzle4(mVec.w().x().w().z(), "wxwz");
    testSwizzle4(mVec.w().x().w().w(), "wxww");
    testSwizzle4(mVec.w().y().x().x(), "wyxx");
    testSwizzle4(mVec.w().y().x().y(), "wyxy");
    testSwizzle4(mVec.w().y().x().z(), "wyxz");
    testSwizzle4(mVec.w().y().x().w(), "wyxw");
    testSwizzle4(mVec.w().y().y().x(), "wyyx");
    testSwizzle4(mVec.w().y().y().y(), "wyyy");
    testSwizzle4(mVec.w().y().y().z(), "wyyz");
    testSwizzle4(mVec.w().y().y().w(), "wyyw");
    testSwizzle4(mVec.w().y().z().x(), "wyzx");
    testSwizzle4(mVec.w().y().z().y(), "wyzy");
    testSwizzle4(mVec.w().y().z().z(), "wyzz");
    testSwizzle4(mVec.w().y().z().w(), "wyzw");
    testSwizzle4(mVec.w().y().w().x(), "wywx");
    testSwizzle4(mVec.w().y().w().y(), "wywy");
    testSwizzle4(mVec.w().y().w().z(), "wywz");
    testSwizzle4(mVec.w().y().w().w(), "wyww");
    testSwizzle4(mVec.w().z().x().x(), "wzxx");
    testSwizzle4(mVec.w().z().x().y(), "wzxy");
    testSwizzle4(mVec.w().z().x().z(), "wzxz");
    testSwizzle4(mVec.w().z().x().w(), "wzxw");
    testSwizzle4(mVec.w().z().y().x(), "wzyx");
    testSwizzle4(mVec.w().z().y().y(), "wzyy");
    testSwizzle4(mVec.w().z().y().z(), "wzyz");
    testSwizzle4(mVec.w().z().y().w(), "wzyw");
    testSwizzle4(mVec.w().z().z().x(), "wzzx");
    testSwizzle4(mVec.w().z().z().y(), "wzzy");
    testSwizzle4(mVec.w().z().z().z(), "wzzz");
    testSwizzle4(mVec.w().z().z().w(), "wzzw");
    testSwizzle4(mVec.w().z().w().x(), "wzwx");
    testSwizzle4(mVec.w().z().w().y(), "wzwy");
    testSwizzle4(mVec.w().z().w().z(), "wzwz");
    testSwizzle4(mVec.w().z().w().w(), "wzww");
    testSwizzle4(mVec.w().w().x().x(), "wwxx");
    testSwizzle4(mVec.w().w().x().y(), "wwxy");
    testSwizzle4(mVec.w().w().x().z(), "wwxz");
    testSwizzle4(mVec.w().w().x().w(), "wwxw");
    testSwizzle4(mVec.w().w().y().x(), "wwyx");
    testSwizzle4(mVec.w().w().y().y(), "wwyy");
    testSwizzle4(mVec.w().w().y().z(), "wwyz");
    testSwizzle4(mVec.w().w().y().w(), "wwyw");
    testSwizzle4(mVec.w().w().z().x(), "wwzx");
    testSwizzle4(mVec.w().w().z().y(), "wwzy");
    testSwizzle4(mVec.w().w().z().z(), "wwzz");
    testSwizzle4(mVec.w().w().z().w(), "wwzw");
    testSwizzle4(mVec.w().w().w().x(), "wwwx");
    testSwizzle4(mVec.w().w().w().y(), "wwwy");
    testSwizzle4(mVec.w().w().w().z(), "wwwz");
    testSwizzle4(mVec.w().w().w().w(), "wwww");

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleRGBA() {
    testSwizzle1(mVec.r().get(), "r");
    testSwizzle1(mVec.g().get(), "g");
    testSwizzle1(mVec.b().get(), "b");
    testSwizzle1(mVec.a().get(), "a");

    testSwizzle2(mVec.r().r().get(), "rr");
    testSwizzle2(mVec.r().g().get(), "rg");
    testSwizzle2(mVec.r().b().get(), "rb");
    testSwizzle2(mVec.r().a().get(), "ra");
    testSwizzle2(mVec.g().r().get(), "gr");
    testSwizzle2(mVec.g().g().get(), "gg");
    testSwizzle2(mVec.g().b().get(), "gb");
    testSwizzle2(mVec.g().a().get(), "ga");
    testSwizzle2(mVec.b().r().get(), "br");
    testSwizzle2(mVec.b().g().get(), "bg");
    testSwizzle2(mVec.b().b().get(), "bb");
    testSwizzle2(mVec.b().a().get(), "ba");
    testSwizzle2(mVec.a().r().get(), "ar");
    testSwizzle2(mVec.a().g().get(), "ag");
    testSwizzle2(mVec.a().b().get(), "ab");
    testSwizzle2(mVec.a().a().get(), "aa");

    testSwizzle3(mVec.r().r().r().get(), "rrr");
    testSwizzle3(mVec.r().r().g().get(), "rrg");
    testSwizzle3(mVec.r().r().b().get(), "rrb");
    testSwizzle3(mVec.r().r().a().get(), "rra");
    testSwizzle3(mVec.r().g().r().get(), "rgr");
    testSwizzle3(mVec.r().g().g().get(), "rgg");
    testSwizzle3(mVec.r().g().b().get(), "rgb");
    testSwizzle3(mVec.r().g().a().get(), "rga");
    testSwizzle3(mVec.r().b().r().get(), "rbr");
    testSwizzle3(mVec.r().b().g().get(), "rbg");
    testSwizzle3(mVec.r().b().b().get(), "rbb");
    testSwizzle3(mVec.r().b().a().get(), "rba");
    testSwizzle3(mVec.r().a().r().get(), "rar");
    testSwizzle3(mVec.r().a().g().get(), "rag");
    testSwizzle3(mVec.r().a().b().get(), "rab");
    testSwizzle3(mVec.r().a().a().get(), "raa");
    testSwizzle3(mVec.g().r().r().get(), "grr");
    testSwizzle3(mVec.g().r().g().get(), "grg");
    testSwizzle3(mVec.g().r().b().get(), "grb");
    testSwizzle3(mVec.g().r().a().get(), "gra");
    testSwizzle3(mVec.g().g().r().get(), "ggr");
    testSwizzle3(mVec.g().g().g().get(), "ggg");
    testSwizzle3(mVec.g().g().b().get(), "ggb");
    testSwizzle3(mVec.g().g().a().get(), "gga");
    testSwizzle3(mVec.g().b().r().get(), "gbr");
    testSwizzle3(mVec.g().b().g().get(), "gbg");
    testSwizzle3(mVec.g().b().b().get(), "gbb");
    testSwizzle3(mVec.g().b().a().get(), "gba");
    testSwizzle3(mVec.g().a().r().get(), "gar");
    testSwizzle3(mVec.g().a().g().get(), "gag");
    testSwizzle3(mVec.g().a().b().get(), "gab");
    testSwizzle3(mVec.g().a().a().get(), "gaa");
    testSwizzle3(mVec.b().r().r().get(), "brr");
    testSwizzle3(mVec.b().r().g().get(), "brg");
    testSwizzle3(mVec.b().r().b().get(), "brb");
    testSwizzle3(mVec.b().r().a().get(), "bra");
    testSwizzle3(mVec.b().g().r().get(), "bgr");
    testSwizzle3(mVec.b().g().g().get(), "bgg");
    testSwizzle3(mVec.b().g().b().get(), "bgb");
    testSwizzle3(mVec.b().g().a().get(), "bga");
    testSwizzle3(mVec.b().b().r().get(), "bbr");
    testSwizzle3(mVec.b().b().g().get(), "bbg");
    testSwizzle3(mVec.b().b().b().get(), "bbb");
    testSwizzle3(mVec.b().b().a().get(), "bba");
    testSwizzle3(mVec.b().a().r().get(), "bar");
    testSwizzle3(mVec.b().a().g().get(), "bag");
    testSwizzle3(mVec.b().a().b().get(), "bab");
    testSwizzle3(mVec.b().a().a().get(), "baa");
    testSwizzle3(mVec.a().r().r().get(), "arr");
    testSwizzle3(mVec.a().r().g().get(), "arg");
    testSwizzle3(mVec.a().r().b().get(), "arb");
    testSwizzle3(mVec.a().r().a().get(), "ara");
    testSwizzle3(mVec.a().g().r().get(), "agr");
    testSwizzle3(mVec.a().g().g().get(), "agg");
    testSwizzle3(mVec.a().g().b().get(), "agb");
    testSwizzle3(mVec.a().g().a().get(), "aga");
    testSwizzle3(mVec.a().b().r().get(), "abr");
    testSwizzle3(mVec.a().b().g().get(), "abg");
    testSwizzle3(mVec.a().b().b().get(), "abb");
    testSwizzle3(mVec.a().b().a().get(), "aba");
    testSwizzle3(mVec.a().a().r().get(), "aar");
    testSwizzle3(mVec.a().a().g().get(), "aag");
    testSwizzle3(mVec.a().a().b().get(), "aab");
    testSwizzle3(mVec.a().a().a().get(), "aaa");

    testSwizzle4(mVec.r().r().r().r(), "rrrr");
    testSwizzle4(mVec.r().r().r().g(), "rrrg");
    testSwizzle4(mVec.r().r().r().b(), "rrrb");
    testSwizzle4(mVec.r().r().r().a(), "rrra");
    testSwizzle4(mVec.r().r().g().r(), "rrgr");
    testSwizzle4(mVec.r().r().g().g(), "rrgg");
    testSwizzle4(mVec.r().r().g().b(), "rrgb");
    testSwizzle4(mVec.r().r().g().a(), "rrga");
    testSwizzle4(mVec.r().r().b().r(), "rrbr");
    testSwizzle4(mVec.r().r().b().g(), "rrbg");
    testSwizzle4(mVec.r().r().b().b(), "rrbb");
    testSwizzle4(mVec.r().r().b().a(), "rrba");
    testSwizzle4(mVec.r().r().a().r(), "rrar");
    testSwizzle4(mVec.r().r().a().g(), "rrag");
    testSwizzle4(mVec.r().r().a().b(), "rrab");
    testSwizzle4(mVec.r().r().a().a(), "rraa");
    testSwizzle4(mVec.r().g().r().r(), "rgrr");
    testSwizzle4(mVec.r().g().r().g(), "rgrg");
    testSwizzle4(mVec.r().g().r().b(), "rgrb");
    testSwizzle4(mVec.r().g().r().a(), "rgra");
    testSwizzle4(mVec.r().g().g().r(), "rggr");
    testSwizzle4(mVec.r().g().g().g(), "rggg");
    testSwizzle4(mVec.r().g().g().b(), "rggb");
    testSwizzle4(mVec.r().g().g().a(), "rgga");
    testSwizzle4(mVec.r().g().b().r(), "rgbr");
    testSwizzle4(mVec.r().g().b().g(), "rgbg");
    testSwizzle4(mVec.r().g().b().b(), "rgbb");
    testSwizzle4(mVec.r().g().b().a(), "rgba");
    testSwizzle4(mVec.r().g().a().r(), "rgar");
    testSwizzle4(mVec.r().g().a().g(), "rgag");
    testSwizzle4(mVec.r().g().a().b(), "rgab");
    testSwizzle4(mVec.r().g().a().a(), "rgaa");
    testSwizzle4(mVec.r().b().r().r(), "rbrr");
    testSwizzle4(mVec.r().b().r().g(), "rbrg");
    testSwizzle4(mVec.r().b().r().b(), "rbrb");
    testSwizzle4(mVec.r().b().r().a(), "rbra");
    testSwizzle4(mVec.r().b().g().r(), "rbgr");
    testSwizzle4(mVec.r().b().g().g(), "rbgg");
    testSwizzle4(mVec.r().b().g().b(), "rbgb");
    testSwizzle4(mVec.r().b().g().a(), "rbga");
    testSwizzle4(mVec.r().b().b().r(), "rbbr");
    testSwizzle4(mVec.r().b().b().g(), "rbbg");
    testSwizzle4(mVec.r().b().b().b(), "rbbb");
    testSwizzle4(mVec.r().b().b().a(), "rbba");
    testSwizzle4(mVec.r().b().a().r(), "rbar");
    testSwizzle4(mVec.r().b().a().g(), "rbag");
    testSwizzle4(mVec.r().b().a().b(), "rbab");
    testSwizzle4(mVec.r().b().a().a(), "rbaa");
    testSwizzle4(mVec.r().a().r().r(), "rarr");
    testSwizzle4(mVec.r().a().r().g(), "rarg");
    testSwizzle4(mVec.r().a().r().b(), "rarb");
    testSwizzle4(mVec.r().a().r().a(), "rara");
    testSwizzle4(mVec.r().a().g().r(), "ragr");
    testSwizzle4(mVec.r().a().g().g(), "ragg");
    testSwizzle4(mVec.r().a().g().b(), "ragb");
    testSwizzle4(mVec.r().a().g().a(), "raga");
    testSwizzle4(mVec.r().a().b().r(), "rabr");
    testSwizzle4(mVec.r().a().b().g(), "rabg");
    testSwizzle4(mVec.r().a().b().b(), "rabb");
    testSwizzle4(mVec.r().a().b().a(), "raba");
    testSwizzle4(mVec.r().a().a().r(), "raar");
    testSwizzle4(mVec.r().a().a().g(), "raag");
    testSwizzle4(mVec.r().a().a().b(), "raab");
    testSwizzle4(mVec.r().a().a().a(), "raaa");
    testSwizzle4(mVec.g().r().r().r(), "grrr");
    testSwizzle4(mVec.g().r().r().g(), "grrg");
    testSwizzle4(mVec.g().r().r().b(), "grrb");
    testSwizzle4(mVec.g().r().r().a(), "grra");
    testSwizzle4(mVec.g().r().g().r(), "grgr");
    testSwizzle4(mVec.g().r().g().g(), "grgg");
    testSwizzle4(mVec.g().r().g().b(), "grgb");
    testSwizzle4(mVec.g().r().g().a(), "grga");
    testSwizzle4(mVec.g().r().b().r(), "grbr");
    testSwizzle4(mVec.g().r().b().g(), "grbg");
    testSwizzle4(mVec.g().r().b().b(), "grbb");
    testSwizzle4(mVec.g().r().b().a(), "grba");
    testSwizzle4(mVec.g().r().a().r(), "grar");
    testSwizzle4(mVec.g().r().a().g(), "grag");
    testSwizzle4(mVec.g().r().a().b(), "grab");
    testSwizzle4(mVec.g().r().a().a(), "graa");
    testSwizzle4(mVec.g().g().r().r(), "ggrr");
    testSwizzle4(mVec.g().g().r().g(), "ggrg");
    testSwizzle4(mVec.g().g().r().b(), "ggrb");
    testSwizzle4(mVec.g().g().r().a(), "ggra");
    testSwizzle4(mVec.g().g().g().r(), "gggr");
    testSwizzle4(mVec.g().g().g().g(), "gggg");
    testSwizzle4(mVec.g().g().g().b(), "gggb");
    testSwizzle4(mVec.g().g().g().a(), "ggga");
    testSwizzle4(mVec.g().g().b().r(), "ggbr");
    testSwizzle4(mVec.g().g().b().g(), "ggbg");
    testSwizzle4(mVec.g().g().b().b(), "ggbb");
    testSwizzle4(mVec.g().g().b().a(), "ggba");
    testSwizzle4(mVec.g().g().a().r(), "ggar");
    testSwizzle4(mVec.g().g().a().g(), "ggag");
    testSwizzle4(mVec.g().g().a().b(), "ggab");
    testSwizzle4(mVec.g().g().a().a(), "ggaa");
    testSwizzle4(mVec.g().b().r().r(), "gbrr");
    testSwizzle4(mVec.g().b().r().g(), "gbrg");
    testSwizzle4(mVec.g().b().r().b(), "gbrb");
    testSwizzle4(mVec.g().b().r().a(), "gbra");
    testSwizzle4(mVec.g().b().g().r(), "gbgr");
    testSwizzle4(mVec.g().b().g().g(), "gbgg");
    testSwizzle4(mVec.g().b().g().b(), "gbgb");
    testSwizzle4(mVec.g().b().g().a(), "gbga");
    testSwizzle4(mVec.g().b().b().r(), "gbbr");
    testSwizzle4(mVec.g().b().b().g(), "gbbg");
    testSwizzle4(mVec.g().b().b().b(), "gbbb");
    testSwizzle4(mVec.g().b().b().a(), "gbba");
    testSwizzle4(mVec.g().b().a().r(), "gbar");
    testSwizzle4(mVec.g().b().a().g(), "gbag");
    testSwizzle4(mVec.g().b().a().b(), "gbab");
    testSwizzle4(mVec.g().b().a().a(), "gbaa");
    testSwizzle4(mVec.g().a().r().r(), "garr");
    testSwizzle4(mVec.g().a().r().g(), "garg");
    testSwizzle4(mVec.g().a().r().b(), "garb");
    testSwizzle4(mVec.g().a().r().a(), "gara");
    testSwizzle4(mVec.g().a().g().r(), "gagr");
    testSwizzle4(mVec.g().a().g().g(), "gagg");
    testSwizzle4(mVec.g().a().g().b(), "gagb");
    testSwizzle4(mVec.g().a().g().a(), "gaga");
    testSwizzle4(mVec.g().a().b().r(), "gabr");
    testSwizzle4(mVec.g().a().b().g(), "gabg");
    testSwizzle4(mVec.g().a().b().b(), "gabb");
    testSwizzle4(mVec.g().a().b().a(), "gaba");
    testSwizzle4(mVec.g().a().a().r(), "gaar");
    testSwizzle4(mVec.g().a().a().g(), "gaag");
    testSwizzle4(mVec.g().a().a().b(), "gaab");
    testSwizzle4(mVec.g().a().a().a(), "gaaa");
    testSwizzle4(mVec.b().r().r().r(), "brrr");
    testSwizzle4(mVec.b().r().r().g(), "brrg");
    testSwizzle4(mVec.b().r().r().b(), "brrb");
    testSwizzle4(mVec.b().r().r().a(), "brra");
    testSwizzle4(mVec.b().r().g().r(), "brgr");
    testSwizzle4(mVec.b().r().g().g(), "brgg");
    testSwizzle4(mVec.b().r().g().b(), "brgb");
    testSwizzle4(mVec.b().r().g().a(), "brga");
    testSwizzle4(mVec.b().r().b().r(), "brbr");
    testSwizzle4(mVec.b().r().b().g(), "brbg");
    testSwizzle4(mVec.b().r().b().b(), "brbb");
    testSwizzle4(mVec.b().r().b().a(), "brba");
    testSwizzle4(mVec.b().r().a().r(), "brar");
    testSwizzle4(mVec.b().r().a().g(), "brag");
    testSwizzle4(mVec.b().r().a().b(), "brab");
    testSwizzle4(mVec.b().r().a().a(), "braa");
    testSwizzle4(mVec.b().g().r().r(), "bgrr");
    testSwizzle4(mVec.b().g().r().g(), "bgrg");
    testSwizzle4(mVec.b().g().r().b(), "bgrb");
    testSwizzle4(mVec.b().g().r().a(), "bgra");
    testSwizzle4(mVec.b().g().g().r(), "bggr");
    testSwizzle4(mVec.b().g().g().g(), "bggg");
    testSwizzle4(mVec.b().g().g().b(), "bggb");
    testSwizzle4(mVec.b().g().g().a(), "bgga");
    testSwizzle4(mVec.b().g().b().r(), "bgbr");
    testSwizzle4(mVec.b().g().b().g(), "bgbg");
    testSwizzle4(mVec.b().g().b().b(), "bgbb");
    testSwizzle4(mVec.b().g().b().a(), "bgba");
    testSwizzle4(mVec.b().g().a().r(), "bgar");
    testSwizzle4(mVec.b().g().a().g(), "bgag");
    testSwizzle4(mVec.b().g().a().b(), "bgab");
    testSwizzle4(mVec.b().g().a().a(), "bgaa");
    testSwizzle4(mVec.b().b().r().r(), "bbrr");
    testSwizzle4(mVec.b().b().r().g(), "bbrg");
    testSwizzle4(mVec.b().b().r().b(), "bbrb");
    testSwizzle4(mVec.b().b().r().a(), "bbra");
    testSwizzle4(mVec.b().b().g().r(), "bbgr");
    testSwizzle4(mVec.b().b().g().g(), "bbgg");
    testSwizzle4(mVec.b().b().g().b(), "bbgb");
    testSwizzle4(mVec.b().b().g().a(), "bbga");
    testSwizzle4(mVec.b().b().b().r(), "bbbr");
    testSwizzle4(mVec.b().b().b().g(), "bbbg");
    testSwizzle4(mVec.b().b().b().b(), "bbbb");
    testSwizzle4(mVec.b().b().b().a(), "bbba");
    testSwizzle4(mVec.b().b().a().r(), "bbar");
    testSwizzle4(mVec.b().b().a().g(), "bbag");
    testSwizzle4(mVec.b().b().a().b(), "bbab");
    testSwizzle4(mVec.b().b().a().a(), "bbaa");
    testSwizzle4(mVec.b().a().r().r(), "barr");
    testSwizzle4(mVec.b().a().r().g(), "barg");
    testSwizzle4(mVec.b().a().r().b(), "barb");
    testSwizzle4(mVec.b().a().r().a(), "bara");
    testSwizzle4(mVec.b().a().g().r(), "bagr");
    testSwizzle4(mVec.b().a().g().g(), "bagg");
    testSwizzle4(mVec.b().a().g().b(), "bagb");
    testSwizzle4(mVec.b().a().g().a(), "baga");
    testSwizzle4(mVec.b().a().b().r(), "babr");
    testSwizzle4(mVec.b().a().b().g(), "babg");
    testSwizzle4(mVec.b().a().b().b(), "babb");
    testSwizzle4(mVec.b().a().b().a(), "baba");
    testSwizzle4(mVec.b().a().a().r(), "baar");
    testSwizzle4(mVec.b().a().a().g(), "baag");
    testSwizzle4(mVec.b().a().a().b(), "baab");
    testSwizzle4(mVec.b().a().a().a(), "baaa");
    testSwizzle4(mVec.a().r().r().r(), "arrr");
    testSwizzle4(mVec.a().r().r().g(), "arrg");
    testSwizzle4(mVec.a().r().r().b(), "arrb");
    testSwizzle4(mVec.a().r().r().a(), "arra");
    testSwizzle4(mVec.a().r().g().r(), "argr");
    testSwizzle4(mVec.a().r().g().g(), "argg");
    testSwizzle4(mVec.a().r().g().b(), "argb");
    testSwizzle4(mVec.a().r().g().a(), "arga");
    testSwizzle4(mVec.a().r().b().r(), "arbr");
    testSwizzle4(mVec.a().r().b().g(), "arbg");
    testSwizzle4(mVec.a().r().b().b(), "arbb");
    testSwizzle4(mVec.a().r().b().a(), "arba");
    testSwizzle4(mVec.a().r().a().r(), "arar");
    testSwizzle4(mVec.a().r().a().g(), "arag");
    testSwizzle4(mVec.a().r().a().b(), "arab");
    testSwizzle4(mVec.a().r().a().a(), "araa");
    testSwizzle4(mVec.a().g().r().r(), "agrr");
    testSwizzle4(mVec.a().g().r().g(), "agrg");
    testSwizzle4(mVec.a().g().r().b(), "agrb");
    testSwizzle4(mVec.a().g().r().a(), "agra");
    testSwizzle4(mVec.a().g().g().r(), "aggr");
    testSwizzle4(mVec.a().g().g().g(), "aggg");
    testSwizzle4(mVec.a().g().g().b(), "aggb");
    testSwizzle4(mVec.a().g().g().a(), "agga");
    testSwizzle4(mVec.a().g().b().r(), "agbr");
    testSwizzle4(mVec.a().g().b().g(), "agbg");
    testSwizzle4(mVec.a().g().b().b(), "agbb");
    testSwizzle4(mVec.a().g().b().a(), "agba");
    testSwizzle4(mVec.a().g().a().r(), "agar");
    testSwizzle4(mVec.a().g().a().g(), "agag");
    testSwizzle4(mVec.a().g().a().b(), "agab");
    testSwizzle4(mVec.a().g().a().a(), "agaa");
    testSwizzle4(mVec.a().b().r().r(), "abrr");
    testSwizzle4(mVec.a().b().r().g(), "abrg");
    testSwizzle4(mVec.a().b().r().b(), "abrb");
    testSwizzle4(mVec.a().b().r().a(), "abra");
    testSwizzle4(mVec.a().b().g().r(), "abgr");
    testSwizzle4(mVec.a().b().g().g(), "abgg");
    testSwizzle4(mVec.a().b().g().b(), "abgb");
    testSwizzle4(mVec.a().b().g().a(), "abga");
    testSwizzle4(mVec.a().b().b().r(), "abbr");
    testSwizzle4(mVec.a().b().b().g(), "abbg");
    testSwizzle4(mVec.a().b().b().b(), "abbb");
    testSwizzle4(mVec.a().b().b().a(), "abba");
    testSwizzle4(mVec.a().b().a().r(), "abar");
    testSwizzle4(mVec.a().b().a().g(), "abag");
    testSwizzle4(mVec.a().b().a().b(), "abab");
    testSwizzle4(mVec.a().b().a().a(), "abaa");
    testSwizzle4(mVec.a().a().r().r(), "aarr");
    testSwizzle4(mVec.a().a().r().g(), "aarg");
    testSwizzle4(mVec.a().a().r().b(), "aarb");
    testSwizzle4(mVec.a().a().r().a(), "aara");
    testSwizzle4(mVec.a().a().g().r(), "aagr");
    testSwizzle4(mVec.a().a().g().g(), "aagg");
    testSwizzle4(mVec.a().a().g().b(), "aagb");
    testSwizzle4(mVec.a().a().g().a(), "aaga");
    testSwizzle4(mVec.a().a().b().r(), "aabr");
    testSwizzle4(mVec.a().a().b().g(), "aabg");
    testSwizzle4(mVec.a().a().b().b(), "aabb");
    testSwizzle4(mVec.a().a().b().a(), "aaba");
    testSwizzle4(mVec.a().a().a().r(), "aaar");
    testSwizzle4(mVec.a().a().a().g(), "aaag");
    testSwizzle4(mVec.a().a().a().b(), "aaab");
    testSwizzle4(mVec.a().a().a().a(), "aaaa");

  }

  @Test
  @SuppressWarnings("all")
  public void testSwizzleSTPQ() {
    testSwizzle1(mVec.s().get(), "s");
    testSwizzle1(mVec.t().get(), "t");
    testSwizzle1(mVec.p().get(), "p");
    testSwizzle1(mVec.q().get(), "q");

    testSwizzle2(mVec.s().s().get(), "ss");
    testSwizzle2(mVec.s().t().get(), "st");
    testSwizzle2(mVec.s().p().get(), "sp");
    testSwizzle2(mVec.s().q().get(), "sq");
    testSwizzle2(mVec.t().s().get(), "ts");
    testSwizzle2(mVec.t().t().get(), "tt");
    testSwizzle2(mVec.t().p().get(), "tp");
    testSwizzle2(mVec.t().q().get(), "tq");
    testSwizzle2(mVec.p().s().get(), "ps");
    testSwizzle2(mVec.p().t().get(), "pt");
    testSwizzle2(mVec.p().p().get(), "pp");
    testSwizzle2(mVec.p().q().get(), "pq");
    testSwizzle2(mVec.q().s().get(), "qs");
    testSwizzle2(mVec.q().t().get(), "qt");
    testSwizzle2(mVec.q().p().get(), "qp");
    testSwizzle2(mVec.q().q().get(), "qq");

    testSwizzle3(mVec.s().s().s().get(), "sss");
    testSwizzle3(mVec.s().s().t().get(), "sst");
    testSwizzle3(mVec.s().s().p().get(), "ssp");
    testSwizzle3(mVec.s().s().q().get(), "ssq");
    testSwizzle3(mVec.s().t().s().get(), "sts");
    testSwizzle3(mVec.s().t().t().get(), "stt");
    testSwizzle3(mVec.s().t().p().get(), "stp");
    testSwizzle3(mVec.s().t().q().get(), "stq");
    testSwizzle3(mVec.s().p().s().get(), "sps");
    testSwizzle3(mVec.s().p().t().get(), "spt");
    testSwizzle3(mVec.s().p().p().get(), "spp");
    testSwizzle3(mVec.s().p().q().get(), "spq");
    testSwizzle3(mVec.s().q().s().get(), "sqs");
    testSwizzle3(mVec.s().q().t().get(), "sqt");
    testSwizzle3(mVec.s().q().p().get(), "sqp");
    testSwizzle3(mVec.s().q().q().get(), "sqq");
    testSwizzle3(mVec.t().s().s().get(), "tss");
    testSwizzle3(mVec.t().s().t().get(), "tst");
    testSwizzle3(mVec.t().s().p().get(), "tsp");
    testSwizzle3(mVec.t().s().q().get(), "tsq");
    testSwizzle3(mVec.t().t().s().get(), "tts");
    testSwizzle3(mVec.t().t().t().get(), "ttt");
    testSwizzle3(mVec.t().t().p().get(), "ttp");
    testSwizzle3(mVec.t().t().q().get(), "ttq");
    testSwizzle3(mVec.t().p().s().get(), "tps");
    testSwizzle3(mVec.t().p().t().get(), "tpt");
    testSwizzle3(mVec.t().p().p().get(), "tpp");
    testSwizzle3(mVec.t().p().q().get(), "tpq");
    testSwizzle3(mVec.t().q().s().get(), "tqs");
    testSwizzle3(mVec.t().q().t().get(), "tqt");
    testSwizzle3(mVec.t().q().p().get(), "tqp");
    testSwizzle3(mVec.t().q().q().get(), "tqq");
    testSwizzle3(mVec.p().s().s().get(), "pss");
    testSwizzle3(mVec.p().s().t().get(), "pst");
    testSwizzle3(mVec.p().s().p().get(), "psp");
    testSwizzle3(mVec.p().s().q().get(), "psq");
    testSwizzle3(mVec.p().t().s().get(), "pts");
    testSwizzle3(mVec.p().t().t().get(), "ptt");
    testSwizzle3(mVec.p().t().p().get(), "ptp");
    testSwizzle3(mVec.p().t().q().get(), "ptq");
    testSwizzle3(mVec.p().p().s().get(), "pps");
    testSwizzle3(mVec.p().p().t().get(), "ppt");
    testSwizzle3(mVec.p().p().p().get(), "ppp");
    testSwizzle3(mVec.p().p().q().get(), "ppq");
    testSwizzle3(mVec.p().q().s().get(), "pqs");
    testSwizzle3(mVec.p().q().t().get(), "pqt");
    testSwizzle3(mVec.p().q().p().get(), "pqp");
    testSwizzle3(mVec.p().q().q().get(), "pqq");
    testSwizzle3(mVec.q().s().s().get(), "qss");
    testSwizzle3(mVec.q().s().t().get(), "qst");
    testSwizzle3(mVec.q().s().p().get(), "qsp");
    testSwizzle3(mVec.q().s().q().get(), "qsq");
    testSwizzle3(mVec.q().t().s().get(), "qts");
    testSwizzle3(mVec.q().t().t().get(), "qtt");
    testSwizzle3(mVec.q().t().p().get(), "qtp");
    testSwizzle3(mVec.q().t().q().get(), "qtq");
    testSwizzle3(mVec.q().p().s().get(), "qps");
    testSwizzle3(mVec.q().p().t().get(), "qpt");
    testSwizzle3(mVec.q().p().p().get(), "qpp");
    testSwizzle3(mVec.q().p().q().get(), "qpq");
    testSwizzle3(mVec.q().q().s().get(), "qqs");
    testSwizzle3(mVec.q().q().t().get(), "qqt");
    testSwizzle3(mVec.q().q().p().get(), "qqp");
    testSwizzle3(mVec.q().q().q().get(), "qqq");

    testSwizzle4(mVec.s().s().s().s(), "ssss");
    testSwizzle4(mVec.s().s().s().t(), "ssst");
    testSwizzle4(mVec.s().s().s().p(), "sssp");
    testSwizzle4(mVec.s().s().s().q(), "sssq");
    testSwizzle4(mVec.s().s().t().s(), "ssts");
    testSwizzle4(mVec.s().s().t().t(), "sstt");
    testSwizzle4(mVec.s().s().t().p(), "sstp");
    testSwizzle4(mVec.s().s().t().q(), "sstq");
    testSwizzle4(mVec.s().s().p().s(), "ssps");
    testSwizzle4(mVec.s().s().p().t(), "sspt");
    testSwizzle4(mVec.s().s().p().p(), "sspp");
    testSwizzle4(mVec.s().s().p().q(), "sspq");
    testSwizzle4(mVec.s().s().q().s(), "ssqs");
    testSwizzle4(mVec.s().s().q().t(), "ssqt");
    testSwizzle4(mVec.s().s().q().p(), "ssqp");
    testSwizzle4(mVec.s().s().q().q(), "ssqq");
    testSwizzle4(mVec.s().t().s().s(), "stss");
    testSwizzle4(mVec.s().t().s().t(), "stst");
    testSwizzle4(mVec.s().t().s().p(), "stsp");
    testSwizzle4(mVec.s().t().s().q(), "stsq");
    testSwizzle4(mVec.s().t().t().s(), "stts");
    testSwizzle4(mVec.s().t().t().t(), "sttt");
    testSwizzle4(mVec.s().t().t().p(), "sttp");
    testSwizzle4(mVec.s().t().t().q(), "sttq");
    testSwizzle4(mVec.s().t().p().s(), "stps");
    testSwizzle4(mVec.s().t().p().t(), "stpt");
    testSwizzle4(mVec.s().t().p().p(), "stpp");
    testSwizzle4(mVec.s().t().p().q(), "stpq");
    testSwizzle4(mVec.s().t().q().s(), "stqs");
    testSwizzle4(mVec.s().t().q().t(), "stqt");
    testSwizzle4(mVec.s().t().q().p(), "stqp");
    testSwizzle4(mVec.s().t().q().q(), "stqq");
    testSwizzle4(mVec.s().p().s().s(), "spss");
    testSwizzle4(mVec.s().p().s().t(), "spst");
    testSwizzle4(mVec.s().p().s().p(), "spsp");
    testSwizzle4(mVec.s().p().s().q(), "spsq");
    testSwizzle4(mVec.s().p().t().s(), "spts");
    testSwizzle4(mVec.s().p().t().t(), "sptt");
    testSwizzle4(mVec.s().p().t().p(), "sptp");
    testSwizzle4(mVec.s().p().t().q(), "sptq");
    testSwizzle4(mVec.s().p().p().s(), "spps");
    testSwizzle4(mVec.s().p().p().t(), "sppt");
    testSwizzle4(mVec.s().p().p().p(), "sppp");
    testSwizzle4(mVec.s().p().p().q(), "sppq");
    testSwizzle4(mVec.s().p().q().s(), "spqs");
    testSwizzle4(mVec.s().p().q().t(), "spqt");
    testSwizzle4(mVec.s().p().q().p(), "spqp");
    testSwizzle4(mVec.s().p().q().q(), "spqq");
    testSwizzle4(mVec.s().q().s().s(), "sqss");
    testSwizzle4(mVec.s().q().s().t(), "sqst");
    testSwizzle4(mVec.s().q().s().p(), "sqsp");
    testSwizzle4(mVec.s().q().s().q(), "sqsq");
    testSwizzle4(mVec.s().q().t().s(), "sqts");
    testSwizzle4(mVec.s().q().t().t(), "sqtt");
    testSwizzle4(mVec.s().q().t().p(), "sqtp");
    testSwizzle4(mVec.s().q().t().q(), "sqtq");
    testSwizzle4(mVec.s().q().p().s(), "sqps");
    testSwizzle4(mVec.s().q().p().t(), "sqpt");
    testSwizzle4(mVec.s().q().p().p(), "sqpp");
    testSwizzle4(mVec.s().q().p().q(), "sqpq");
    testSwizzle4(mVec.s().q().q().s(), "sqqs");
    testSwizzle4(mVec.s().q().q().t(), "sqqt");
    testSwizzle4(mVec.s().q().q().p(), "sqqp");
    testSwizzle4(mVec.s().q().q().q(), "sqqq");
    testSwizzle4(mVec.t().s().s().s(), "tsss");
    testSwizzle4(mVec.t().s().s().t(), "tsst");
    testSwizzle4(mVec.t().s().s().p(), "tssp");
    testSwizzle4(mVec.t().s().s().q(), "tssq");
    testSwizzle4(mVec.t().s().t().s(), "tsts");
    testSwizzle4(mVec.t().s().t().t(), "tstt");
    testSwizzle4(mVec.t().s().t().p(), "tstp");
    testSwizzle4(mVec.t().s().t().q(), "tstq");
    testSwizzle4(mVec.t().s().p().s(), "tsps");
    testSwizzle4(mVec.t().s().p().t(), "tspt");
    testSwizzle4(mVec.t().s().p().p(), "tspp");
    testSwizzle4(mVec.t().s().p().q(), "tspq");
    testSwizzle4(mVec.t().s().q().s(), "tsqs");
    testSwizzle4(mVec.t().s().q().t(), "tsqt");
    testSwizzle4(mVec.t().s().q().p(), "tsqp");
    testSwizzle4(mVec.t().s().q().q(), "tsqq");
    testSwizzle4(mVec.t().t().s().s(), "ttss");
    testSwizzle4(mVec.t().t().s().t(), "ttst");
    testSwizzle4(mVec.t().t().s().p(), "ttsp");
    testSwizzle4(mVec.t().t().s().q(), "ttsq");
    testSwizzle4(mVec.t().t().t().s(), "ttts");
    testSwizzle4(mVec.t().t().t().t(), "tttt");
    testSwizzle4(mVec.t().t().t().p(), "tttp");
    testSwizzle4(mVec.t().t().t().q(), "tttq");
    testSwizzle4(mVec.t().t().p().s(), "ttps");
    testSwizzle4(mVec.t().t().p().t(), "ttpt");
    testSwizzle4(mVec.t().t().p().p(), "ttpp");
    testSwizzle4(mVec.t().t().p().q(), "ttpq");
    testSwizzle4(mVec.t().t().q().s(), "ttqs");
    testSwizzle4(mVec.t().t().q().t(), "ttqt");
    testSwizzle4(mVec.t().t().q().p(), "ttqp");
    testSwizzle4(mVec.t().t().q().q(), "ttqq");
    testSwizzle4(mVec.t().p().s().s(), "tpss");
    testSwizzle4(mVec.t().p().s().t(), "tpst");
    testSwizzle4(mVec.t().p().s().p(), "tpsp");
    testSwizzle4(mVec.t().p().s().q(), "tpsq");
    testSwizzle4(mVec.t().p().t().s(), "tpts");
    testSwizzle4(mVec.t().p().t().t(), "tptt");
    testSwizzle4(mVec.t().p().t().p(), "tptp");
    testSwizzle4(mVec.t().p().t().q(), "tptq");
    testSwizzle4(mVec.t().p().p().s(), "tpps");
    testSwizzle4(mVec.t().p().p().t(), "tppt");
    testSwizzle4(mVec.t().p().p().p(), "tppp");
    testSwizzle4(mVec.t().p().p().q(), "tppq");
    testSwizzle4(mVec.t().p().q().s(), "tpqs");
    testSwizzle4(mVec.t().p().q().t(), "tpqt");
    testSwizzle4(mVec.t().p().q().p(), "tpqp");
    testSwizzle4(mVec.t().p().q().q(), "tpqq");
    testSwizzle4(mVec.t().q().s().s(), "tqss");
    testSwizzle4(mVec.t().q().s().t(), "tqst");
    testSwizzle4(mVec.t().q().s().p(), "tqsp");
    testSwizzle4(mVec.t().q().s().q(), "tqsq");
    testSwizzle4(mVec.t().q().t().s(), "tqts");
    testSwizzle4(mVec.t().q().t().t(), "tqtt");
    testSwizzle4(mVec.t().q().t().p(), "tqtp");
    testSwizzle4(mVec.t().q().t().q(), "tqtq");
    testSwizzle4(mVec.t().q().p().s(), "tqps");
    testSwizzle4(mVec.t().q().p().t(), "tqpt");
    testSwizzle4(mVec.t().q().p().p(), "tqpp");
    testSwizzle4(mVec.t().q().p().q(), "tqpq");
    testSwizzle4(mVec.t().q().q().s(), "tqqs");
    testSwizzle4(mVec.t().q().q().t(), "tqqt");
    testSwizzle4(mVec.t().q().q().p(), "tqqp");
    testSwizzle4(mVec.t().q().q().q(), "tqqq");
    testSwizzle4(mVec.p().s().s().s(), "psss");
    testSwizzle4(mVec.p().s().s().t(), "psst");
    testSwizzle4(mVec.p().s().s().p(), "pssp");
    testSwizzle4(mVec.p().s().s().q(), "pssq");
    testSwizzle4(mVec.p().s().t().s(), "psts");
    testSwizzle4(mVec.p().s().t().t(), "pstt");
    testSwizzle4(mVec.p().s().t().p(), "pstp");
    testSwizzle4(mVec.p().s().t().q(), "pstq");
    testSwizzle4(mVec.p().s().p().s(), "psps");
    testSwizzle4(mVec.p().s().p().t(), "pspt");
    testSwizzle4(mVec.p().s().p().p(), "pspp");
    testSwizzle4(mVec.p().s().p().q(), "pspq");
    testSwizzle4(mVec.p().s().q().s(), "psqs");
    testSwizzle4(mVec.p().s().q().t(), "psqt");
    testSwizzle4(mVec.p().s().q().p(), "psqp");
    testSwizzle4(mVec.p().s().q().q(), "psqq");
    testSwizzle4(mVec.p().t().s().s(), "ptss");
    testSwizzle4(mVec.p().t().s().t(), "ptst");
    testSwizzle4(mVec.p().t().s().p(), "ptsp");
    testSwizzle4(mVec.p().t().s().q(), "ptsq");
    testSwizzle4(mVec.p().t().t().s(), "ptts");
    testSwizzle4(mVec.p().t().t().t(), "pttt");
    testSwizzle4(mVec.p().t().t().p(), "pttp");
    testSwizzle4(mVec.p().t().t().q(), "pttq");
    testSwizzle4(mVec.p().t().p().s(), "ptps");
    testSwizzle4(mVec.p().t().p().t(), "ptpt");
    testSwizzle4(mVec.p().t().p().p(), "ptpp");
    testSwizzle4(mVec.p().t().p().q(), "ptpq");
    testSwizzle4(mVec.p().t().q().s(), "ptqs");
    testSwizzle4(mVec.p().t().q().t(), "ptqt");
    testSwizzle4(mVec.p().t().q().p(), "ptqp");
    testSwizzle4(mVec.p().t().q().q(), "ptqq");
    testSwizzle4(mVec.p().p().s().s(), "ppss");
    testSwizzle4(mVec.p().p().s().t(), "ppst");
    testSwizzle4(mVec.p().p().s().p(), "ppsp");
    testSwizzle4(mVec.p().p().s().q(), "ppsq");
    testSwizzle4(mVec.p().p().t().s(), "ppts");
    testSwizzle4(mVec.p().p().t().t(), "pptt");
    testSwizzle4(mVec.p().p().t().p(), "pptp");
    testSwizzle4(mVec.p().p().t().q(), "pptq");
    testSwizzle4(mVec.p().p().p().s(), "ppps");
    testSwizzle4(mVec.p().p().p().t(), "pppt");
    testSwizzle4(mVec.p().p().p().p(), "pppp");
    testSwizzle4(mVec.p().p().p().q(), "pppq");
    testSwizzle4(mVec.p().p().q().s(), "ppqs");
    testSwizzle4(mVec.p().p().q().t(), "ppqt");
    testSwizzle4(mVec.p().p().q().p(), "ppqp");
    testSwizzle4(mVec.p().p().q().q(), "ppqq");
    testSwizzle4(mVec.p().q().s().s(), "pqss");
    testSwizzle4(mVec.p().q().s().t(), "pqst");
    testSwizzle4(mVec.p().q().s().p(), "pqsp");
    testSwizzle4(mVec.p().q().s().q(), "pqsq");
    testSwizzle4(mVec.p().q().t().s(), "pqts");
    testSwizzle4(mVec.p().q().t().t(), "pqtt");
    testSwizzle4(mVec.p().q().t().p(), "pqtp");
    testSwizzle4(mVec.p().q().t().q(), "pqtq");
    testSwizzle4(mVec.p().q().p().s(), "pqps");
    testSwizzle4(mVec.p().q().p().t(), "pqpt");
    testSwizzle4(mVec.p().q().p().p(), "pqpp");
    testSwizzle4(mVec.p().q().p().q(), "pqpq");
    testSwizzle4(mVec.p().q().q().s(), "pqqs");
    testSwizzle4(mVec.p().q().q().t(), "pqqt");
    testSwizzle4(mVec.p().q().q().p(), "pqqp");
    testSwizzle4(mVec.p().q().q().q(), "pqqq");
    testSwizzle4(mVec.q().s().s().s(), "qsss");
    testSwizzle4(mVec.q().s().s().t(), "qsst");
    testSwizzle4(mVec.q().s().s().p(), "qssp");
    testSwizzle4(mVec.q().s().s().q(), "qssq");
    testSwizzle4(mVec.q().s().t().s(), "qsts");
    testSwizzle4(mVec.q().s().t().t(), "qstt");
    testSwizzle4(mVec.q().s().t().p(), "qstp");
    testSwizzle4(mVec.q().s().t().q(), "qstq");
    testSwizzle4(mVec.q().s().p().s(), "qsps");
    testSwizzle4(mVec.q().s().p().t(), "qspt");
    testSwizzle4(mVec.q().s().p().p(), "qspp");
    testSwizzle4(mVec.q().s().p().q(), "qspq");
    testSwizzle4(mVec.q().s().q().s(), "qsqs");
    testSwizzle4(mVec.q().s().q().t(), "qsqt");
    testSwizzle4(mVec.q().s().q().p(), "qsqp");
    testSwizzle4(mVec.q().s().q().q(), "qsqq");
    testSwizzle4(mVec.q().t().s().s(), "qtss");
    testSwizzle4(mVec.q().t().s().t(), "qtst");
    testSwizzle4(mVec.q().t().s().p(), "qtsp");
    testSwizzle4(mVec.q().t().s().q(), "qtsq");
    testSwizzle4(mVec.q().t().t().s(), "qtts");
    testSwizzle4(mVec.q().t().t().t(), "qttt");
    testSwizzle4(mVec.q().t().t().p(), "qttp");
    testSwizzle4(mVec.q().t().t().q(), "qttq");
    testSwizzle4(mVec.q().t().p().s(), "qtps");
    testSwizzle4(mVec.q().t().p().t(), "qtpt");
    testSwizzle4(mVec.q().t().p().p(), "qtpp");
    testSwizzle4(mVec.q().t().p().q(), "qtpq");
    testSwizzle4(mVec.q().t().q().s(), "qtqs");
    testSwizzle4(mVec.q().t().q().t(), "qtqt");
    testSwizzle4(mVec.q().t().q().p(), "qtqp");
    testSwizzle4(mVec.q().t().q().q(), "qtqq");
    testSwizzle4(mVec.q().p().s().s(), "qpss");
    testSwizzle4(mVec.q().p().s().t(), "qpst");
    testSwizzle4(mVec.q().p().s().p(), "qpsp");
    testSwizzle4(mVec.q().p().s().q(), "qpsq");
    testSwizzle4(mVec.q().p().t().s(), "qpts");
    testSwizzle4(mVec.q().p().t().t(), "qptt");
    testSwizzle4(mVec.q().p().t().p(), "qptp");
    testSwizzle4(mVec.q().p().t().q(), "qptq");
    testSwizzle4(mVec.q().p().p().s(), "qpps");
    testSwizzle4(mVec.q().p().p().t(), "qppt");
    testSwizzle4(mVec.q().p().p().p(), "qppp");
    testSwizzle4(mVec.q().p().p().q(), "qppq");
    testSwizzle4(mVec.q().p().q().s(), "qpqs");
    testSwizzle4(mVec.q().p().q().t(), "qpqt");
    testSwizzle4(mVec.q().p().q().p(), "qpqp");
    testSwizzle4(mVec.q().p().q().q(), "qpqq");
    testSwizzle4(mVec.q().q().s().s(), "qqss");
    testSwizzle4(mVec.q().q().s().t(), "qqst");
    testSwizzle4(mVec.q().q().s().p(), "qqsp");
    testSwizzle4(mVec.q().q().s().q(), "qqsq");
    testSwizzle4(mVec.q().q().t().s(), "qqts");
    testSwizzle4(mVec.q().q().t().t(), "qqtt");
    testSwizzle4(mVec.q().q().t().p(), "qqtp");
    testSwizzle4(mVec.q().q().t().q(), "qqtq");
    testSwizzle4(mVec.q().q().p().s(), "qqps");
    testSwizzle4(mVec.q().q().p().t(), "qqpt");
    testSwizzle4(mVec.q().q().p().p(), "qqpp");
    testSwizzle4(mVec.q().q().p().q(), "qqpq");
    testSwizzle4(mVec.q().q().q().s(), "qqqs");
    testSwizzle4(mVec.q().q().q().t(), "qqqt");
    testSwizzle4(mVec.q().q().q().p(), "qqqp");
    testSwizzle4(mVec.q().q().q().q(), "qqqq");

  }

  private void testNonLeafVector(Vector4 vec) {
    assertFalse(vec.getPrimitive().isPresent());
    assertFalse(vec.getAttribBuffer().isPresent());
    assertFalse(vec.getGlSlQualifier().isPresent());
    assertTrue(vec.getNodeType().isPresent());
  }

  private void testNonLeafExpression(Expression exp) {
    assertFalse(exp.getPrimitive().isPresent());
    assertFalse(exp.getGlSlQualifier().isPresent());
    assertTrue(exp.getNodeType().isPresent());
  }

  private void testArithmetic(
      Vector4 vecWithFloat,
      Vector4 vecWithReal,
      Real real,
      Vector4 vecWithVec,
      Vector4 rhs,
      Expression.NodeType nodeType) {
    testNonLeafVector(vecWithFloat);
    assertEquals(vecWithFloat.getNodeType().get(), nodeType);
    assertEquals(vecWithFloat.getParents().size(), 2);
    assertEquals(vecWithFloat.getParents().get(0), mVec);

    testNonLeafVector(vecWithReal);
    assertEquals(vecWithReal.getNodeType().get(), nodeType);
    assertEquals(vecWithReal.getParents(), ImmutableList.<Expression>of(mVec, real));

    testNonLeafVector(vecWithVec);
    assertEquals(vecWithVec.getNodeType().get(), nodeType);
    assertEquals(vecWithVec.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testAdd() {
    Vector4 vec = mVec.add(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.add(real), real, mVec.add(rhs), rhs, Expression.NodeType.ADD);
  }

  @Test
  public void testSub() {
    Vector4 vec = mVec.sub(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.sub(real), real, mVec.sub(rhs), rhs, Expression.NodeType.SUB);
  }

  @Test
  public void testMul() {
    Vector4 vec = mVec.mul(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.mul(real), real, mVec.mul(rhs), rhs, Expression.NodeType.MUL);
  }

  @Test
  public void testDiv() {
    Vector4 vec = mVec.div(1);
    Real real = new Real(1);
    Vector4 rhs = new Vector4(1, 2, 3, 4);

    testArithmetic(vec, mVec.div(real), real, mVec.div(rhs), rhs, Expression.NodeType.DIV);
  }

  @Test
  public void testNeg() {
    Vector4 neg = mVec.neg();

    testNonLeafVector(neg);
    assertEquals(neg.getNodeType().get(), Expression.NodeType.NEG);
    assertEquals(neg.getParents(), ImmutableList.<Expression>of(mVec));
  }

  private void testFunction(
      Expression expression,
      String functionName) {
    assertTrue(expression.getNodeType().isPresent());
    assertTrue(expression.getNodeType().get() instanceof Expression.NodeType.FunctionNodeType);

    Expression.NodeType.FunctionNodeType nodeType =
        (Expression.NodeType.FunctionNodeType)expression.getNodeType().get();
    assertEquals(nodeType.getFunctionName(), functionName);
  }

  @Test
  public void testDot() {
    Vector4 vec = new Vector4(1, 2, 3, 4);
    Real dot = mVec.dot(vec);

    testNonLeafExpression(dot);
    testFunction(dot, "dot");
    assertEquals(dot.getParents(), ImmutableList.<Expression>of(mVec, vec));
  }

  @Test
  public void testNormalize() {
    Vector4 normalized = mVec.normalize();

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
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 lessThan = mVec.isLessThan(rhs);

    testNonLeafExpression(lessThan);
    testFunction(lessThan, "lessThan");
    assertEquals(lessThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsLessThanOrEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 lessThanOrEqual = mVec.isLessThanOrEqual(rhs);

    testNonLeafExpression(lessThanOrEqual);
    testFunction(lessThanOrEqual, "lessThanEqual");
    assertEquals(lessThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThan() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 greaterThan = mVec.isGreaterThan(rhs);

    testNonLeafExpression(greaterThan);
    testFunction(greaterThan, "greaterThan");
    assertEquals(greaterThan.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsGreaterThanOrEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 greaterThanOrEqual = mVec.isGreaterThanOrEqual(rhs);

    testNonLeafExpression(greaterThanOrEqual);
    testFunction(greaterThanOrEqual, "greaterThanEqual");
    assertEquals(greaterThanOrEqual.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqualComponentwise() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 equalComponentwise = mVec.isEqualComponentwise(rhs);

    testNonLeafExpression(equalComponentwise);
    testFunction(equalComponentwise, "equal");
    assertEquals(equalComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqualComponentwise() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    BVector4 notEqualComponentwise = mVec.isNotEqualComponentwise(rhs);

    testNonLeafExpression(notEqualComponentwise);
    testFunction(notEqualComponentwise, "notEqual");
    assertEquals(notEqualComponentwise.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
    Bool equal = mVec.isEqual(rhs);

    testNonLeafExpression(equal);
    assertTrue(equal.getNodeType().isPresent());
    assertEquals(equal.getNodeType().get(), Expression.NodeType.EQ);
    assertEquals(equal.getParents(), ImmutableList.<Expression>of(mVec, rhs));
  }

  @Test
  public void testIsNotEqual() {
    Vector4 rhs = new Vector4(1, 2, 3, 4);
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

    assertSame(mVec, fill);
  }
}