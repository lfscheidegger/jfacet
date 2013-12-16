package com.lfscheidegger.jfacet.compiler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.lfscheidegger.jfacet.shade.expression.Expression;

import java.util.Map;

public class CompilationHelper {

  private int mNameCounter = 0;
  private final Map<Expression, String> mExpressionNames;

  public CompilationHelper() {
    mExpressionNames = Maps.newHashMap();
  }

  public String getNameForExpression(Expression expression) {
    if (!mExpressionNames.containsKey(expression)) {
      mExpressionNames.put(expression, getUniqueName());
    }

    return mExpressionNames.get(expression);
  }

  private String getUniqueName() {
    return "var_" + mNameCounter++;
  }

  public String getVaryingName() {
    return "interp_" + mNameCounter++;
  }

  public void emitAttributeDeclaration(StringBuilder sb, Expression attributeExpression) {
    sb.append(String.format(
        "attribute %s %s;\n",
        attributeExpression.getGlSlTypeName(),
        getNameForExpression(attributeExpression)));
  }

  public void emitVaryingDeclaration(
      StringBuilder sb,
      Expression varyingExpression,
      String varyingName) {
    sb.append(String.format(
        "varying %s %s;\n",
        varyingExpression.getGlSlTypeName(),
        varyingName));
  }

  public void emitExpression(StringBuilder sb, Expression expression) {
    if (!expression.getParents().isEmpty()) {
      Expression.NodeType nodeType = (Expression.NodeType)expression.getNodeType().get();
      if (nodeType.equals(Expression.NodeType.CONS)) {
        emitConstructor(sb, expression);
      } else if (nodeType.equals(Expression.NodeType.TERNARY)) {
        emitTernary(sb, expression);
      } else if (nodeType instanceof Expression.NodeType.ComponentNodeType) {
        emitComponent(sb, expression);
      } else if (nodeType instanceof Expression.NodeType.OperatorNodeType) {
        emitOperator(sb, expression);
      }
    } else if (expression.getPrimitive().isPresent()) {
      emitPrimitive(sb, expression);
    }
  }

  private void emitConstructor(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();
    String parents = getParentsString(expression.getParents());
    sb.append(String.format(
        "%s %s = %s(%s);\n",
        typeName,
        getNameForExpression(expression),
        typeName,
        parents));
  }

  private String getParentsString(ImmutableList<Expression> parents) {
    StringBuilder sb = new StringBuilder();

    sb.append(getNameForExpression(parents.get(0)));
    for (int i = 1; i < parents.size(); i++) {
      sb.append(", ").append(getNameForExpression(parents.get(i)));
    }

    return sb.toString();
  }

  private void emitTernary(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();
    Expression
        condition = (Expression)expression.getParents().get(0),
        if_ = (Expression)expression.getParents().get(1),
        else_ = (Expression)expression.getParents().get(2);

    sb.append(String.format(
        "%s %s = %s ? %s : %s;\n",
        typeName,
        getNameForExpression(expression),
        getNameForExpression(condition),
        getNameForExpression(if_),
        getNameForExpression(else_)));
  }

  private void emitComponent(StringBuilder sb, Expression expression) {
    Expression.NodeType.ComponentNodeType nodeType =
        (Expression.NodeType.ComponentNodeType) expression.getNodeType().get();
    String typeName = expression.getGlSlTypeName();
    sb.append(String.format(
        "%s %s = %s;\n",
        typeName,
        getNameForExpression(expression),
        getComponentString(nodeType, (Expression)expression.getParents().get(0))));
  }

  private static String[] sComponents = new String[]{"x", "y", "z", "w"};
  private String getComponentString(
      Expression.NodeType.ComponentNodeType nodeType,
      Expression parent) {
    return String.format(
        "%s.%s",
        getNameForExpression(parent),
        sComponents[nodeType.getComponent()]);
  }

  private void emitPrimitive(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();
    sb.append(String.format(
        "%s %s = %s;\n",
        typeName,
        getNameForExpression(expression),
        expression.getPrimitive().get()));
  }

  private void emitOperator(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();
    Expression.NodeType.OperatorNodeType nodeType =
        (Expression.NodeType.OperatorNodeType)expression.getNodeType().get();
    Expression
        lhs = (Expression)expression.getParents().get(0),
        rhs = (Expression)expression.getParents().get(1);
    sb.append(String.format(
        "%s %s = %s %s %s;\n",
        typeName,
        getNameForExpression(expression),
        getNameForExpression(lhs),
        nodeType.getOperator(),
        getNameForExpression(rhs)));
  }

  public void emitAssignment(StringBuilder sb, String expressionName, Expression rhs) {
    sb.append(String.format(
        "%s = %s;\n",
        expressionName,
        getNameForExpression(rhs)));
  }
}
