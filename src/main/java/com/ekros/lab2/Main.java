package com.ekros.lab2;

public class Main {

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

  public static void main(String[] args) {
    Schema schema = new Schema(SCHEMA, P, INPUT, OUTPUT);
    schema.calcPSystem();
    schema.printSchema();
    schema.printWorkableStates();
    schema.printPSystem();
  }

}
