package com.lfscheidegger.jfacet.shade.transform;

public interface Transform<MATRIX_T, VECTOR_T> {

  public VECTOR_T apply(VECTOR_T exp);

  public Transform<MATRIX_T, VECTOR_T> apply(Transform<MATRIX_T, VECTOR_T> other);

  public MATRIX_T getMatrix();
}
