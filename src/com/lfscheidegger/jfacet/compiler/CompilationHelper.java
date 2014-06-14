// Copyright (c) 2013- Luiz Fernando Scheidegger
package com.lfscheidegger.jfacet.compiler;

import com.google.common.collect.*;
import com.lfscheidegger.jfacet.shade.expression.Expression;
import com.lfscheidegger.jfacet.shade.expression.NodeType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CompilationHelper {

  private final Map<Expression, String> mExpressionNames;

  private static int sNameCounter = 0;

  public CompilationHelper() {
    mExpressionNames = Maps.newHashMap();
  }

  public ImmutableList<Expression> extractUniformExpressions(Expression root) {
    final Set<Expression> uniformExpressions = Sets.newLinkedHashSet();
    new ExpressionVisitor(root) {
      @Override
      public void visit(Expression expression) {
        if (expression.getNodeType() instanceof NodeType.UniformNodeType) {
          uniformExpressions.add(expression);
        }
      }
    }.run();
    return ImmutableList.copyOf(uniformExpressions);
  }

  public ImmutableList<Expression> extractAttributeExpressions(Expression root) {
    final Set<Expression> attributeExpressions = Sets.newLinkedHashSet();
    new ExpressionVisitor(root) {
      @Override
      public void visit(Expression expression) {
        if (expression.getNodeType() instanceof NodeType.AttributeNodeType) {
          attributeExpressions.add(expression);
        }
      }
    }.run();
    return ImmutableList.copyOf(attributeExpressions);
  }

  public String getNameForExpression(Expression expression) {
    if (!mExpressionNames.containsKey(expression)) {
      mExpressionNames.put(expression, "var_" + sNameCounter++);
    }

    return mExpressionNames.get(expression);
  }

  public String getVaryingName() {
    return "interp_" + sNameCounter++;
  }

  public void emitAttributeDeclaration(StringBuilder sb, Expression attributeExpression) {
    emitDeclaration("attribute", sb, attributeExpression);
  }

  public void emitUniformDeclaration(StringBuilder sb, Expression uniformExpression) {
    emitDeclaration("uniform", sb, uniformExpression);
  }

  public void emitVaryingDeclaration(
      StringBuilder sb,
      Expression varyingExpression,
      String varyingName) {
    sb.append(String.format("varying %s %s;\n", varyingExpression.getGlSlTypeName(), varyingName));
  }

  public void emitExpression(StringBuilder sb, Expression expression) {
    NodeType nodeType = expression.getNodeType();
    if (nodeType.equals(NodeType.CONS)) {
      emitConstructor(sb, expression);
    } else if (nodeType.equals(NodeType.TERNARY)) {
      emitTernary(sb, expression);
    } else if (nodeType instanceof NodeType.ComponentNodeType) {
      emitComponent(sb, expression);
    } else if (nodeType instanceof NodeType.OperatorNodeType) {
      emitOperator(sb, expression);
    } else if (nodeType instanceof NodeType.UnaryNodeType) {
      emitUnary(sb, expression);
    } else if (nodeType instanceof NodeType.FunctionNodeType) {
      emitFunctionCall(sb, expression);
    } else if (nodeType instanceof NodeType.PrimitiveNodeType) {
      emitPrimitive(sb, expression);
    } else if (nodeType instanceof NodeType.SwizzleNodeType) {
      emitSwizzle(sb, expression);
    }
  }

  public void emitAssignment(StringBuilder sb, String expressionName, Expression rhs) {
    sb.append(String.format(
        "%s = %s;\n",
        expressionName,
        getNameForExpression(rhs)));
  }

  private void emitDeclaration(String qualifier, StringBuilder sb, Expression expression) {
    sb.append(String.format(
        "%s %s %s;\n",
        qualifier,
        expression.getGlSlTypeName(),
        getNameForExpression(expression)));
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
        condition = expression.getParents().get(0),
        if_ = expression.getParents().get(1),
        else_ = expression.getParents().get(2);

    sb.append(String.format(
        "%s %s = %s ? %s : %s;\n",
        typeName,
        getNameForExpression(expression),
        getNameForExpression(condition),
        getNameForExpression(if_),
        getNameForExpression(else_)));
  }

  private void emitComponent(StringBuilder sb, Expression expression) {
    NodeType.ComponentNodeType nodeType =
        (NodeType.ComponentNodeType) expression.getNodeType();
    String typeName = expression.getGlSlTypeName();
    sb.append(String.format(
        "%s %s = %s;\n",
        typeName,
        getNameForExpression(expression),
        getComponentString(nodeType, expression.getParents().get(0))));
  }

  private static String[] sComponents = new String[]{"x", "y", "z", "w"};
  private String getComponentString(
      NodeType.ComponentNodeType nodeType,
      Expression parent) {
    return String.format(
        "%s.%s",
        getNameForExpression(parent),
        sComponents[nodeType.getComponent()]);
  }

  private void emitPrimitive(StringBuilder sb, Expression expression) {
    Object primitive =
        ((NodeType.PrimitiveNodeType) expression.getNodeType()).getPrimitive();

    String typeName = expression.getGlSlTypeName();
    sb.append(String.format(
        "%s %s = %s;\n",
        typeName,
        getNameForExpression(expression),
        primitive));
  }

  private void emitSwizzle(StringBuilder sb, Expression expression) {
    NodeType.SwizzleNodeType nodeType =
        (NodeType.SwizzleNodeType)expression.getNodeType();
    Expression parent = expression.getParents().get(0);

    String typeName = expression.getGlSlTypeName();
    sb.append(String.format(
        "%s %s = %s.%s;\n",
        typeName,
        getNameForExpression(expression),
        getNameForExpression(parent),
        nodeType.getSwizzleString()));
  }

  private void emitOperator(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();
    NodeType.OperatorNodeType nodeType =
        (NodeType.OperatorNodeType)expression.getNodeType();
    Expression
        lhs = expression.getParents().get(0),
        rhs = expression.getParents().get(1);
    sb.append(String.format(
        "%s %s = %s %s %s;\n",
        typeName,
        getNameForExpression(expression),
        getNameForExpression(lhs),
        nodeType.getOperator(),
        getNameForExpression(rhs)));
  }

  private void emitUnary(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();
    NodeType.UnaryNodeType nodeType =
        (NodeType.UnaryNodeType)expression.getNodeType();
    Expression lhs = expression.getParents().get(0);
    sb.append(String.format(
        "%s %s = %s%s;\n",
        typeName,
        getNameForExpression(expression),
        nodeType.getOperator(),
        getNameForExpression(lhs)));
  }

  private void emitFunctionCall(StringBuilder sb, Expression expression) {
    String typeName = expression.getGlSlTypeName();
    NodeType.FunctionNodeType nodeType =
        (NodeType.FunctionNodeType)expression.getNodeType();

    String parentsString = getParentsString(expression.getParents());
    sb.append(String.format(
        "%s %s = %s(%s);\n",
        typeName,
        getNameForExpression(expression),
        nodeType.getFunctionName(),
        parentsString));
  }
}
