package com.ekros.lab2;

public class Main {

  private static final int HOURS = 2616;
  private static final int MULTIPLICITY = 2;

  /**
   * Input schema (table of links)
   */
  private static final int[][] SCHEMA = {
      {0, 0, 1, 0, 1, 0, 0, 0},
      {0, 0, 0, 1, 0, 0, 1, 0},
      {0, 0, 0, 1, 1, 0, 0, 0},
      {0, 0, 1, 0, 0, 0, 1, 0},
      {0, 0, 0, 0, 0, 1, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1},
      {0, 0, 0, 0, 0, 0, 0, 0}};

  /**
   * Probabilities trouble-free operation for each top
   */
  private static final double[] P = {0.41, 0.30, 0.59, 0.44, 0.51, 0.63, 0.72, 0.48};

  /**
   * Indexes of input
   */
  private static final int[] INPUT = {0, 1};

  /**
   * indexes of output
   */
  private static final int[] OUTPUT = {5, 7};

//  private static final int HOURST = 1000;
//  private static final int MULTIPLICITYT = 1;
//  /**
//   * Input schema (table of links)
//   */
//  private static final int[][] SCHEMAT = {
//      {0, 1, 1, 0, 0, 0, 0, 0},
//      {0, 0, 0, 1, 1, 0, 0, 0},
//      {0, 0, 0, 1, 0, 1, 0, 1},
//      {0, 0, 0, 0, 1, 1, 0, 1},
//      {0, 0, 0, 0, 0, 1, 1, 0},
//      {0, 0, 0, 0, 0, 0, 1, 1},
//      {0, 0, 0, 0, 0, 0, 0, 0},
//      {0, 0, 0, 0, 0, 0, 0, 0}};
//
//  /**
//   * Probabilities trouble-free operation for each top
//   */
//  private static final double[] PT = {0.50, 0.60, 0.70, 0.80, 0.85, 0.90, 0.92, 0.94};
//
//  /**
//   * Indexes of input
//   */
//  private static final int[] INPUTT = {0};
//
//  /**
//   * indexes of output
//   */
//  private static final int[] OUTPUTT = {6, 7};

  public static void main(String[] args) {
    SchemaReserve schema = new SchemaReserve(SCHEMA, P, INPUT, OUTPUT, HOURS, MULTIPLICITY);

    schema.calcNotLoadedGeneral();
    schema.printData();
    schema.calcLoadedGeneral();
    schema.printData();
    schema.calcLoadedSeparate();
    schema.printData();
    schema.calcNotLoadedSeparate();
    schema.printData();
  }

}
