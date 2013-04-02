package com.lfscheidegger.jfacet.shade.compiler;

public abstract class AbstractCompiler {

  private final CompilationContext mContext;

  public AbstractCompiler() {
    this(new DefaultCompilationContext());
  }

  public AbstractCompiler(CompilationContext context) {
    mContext = context;
  }

  protected final CompilationContext getContext() {
    return mContext;
  }
}
