package com.ekros.lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schema {

  protected final int[][] schema;
  protected final double[] p;
  protected final int[] input;
  protected final int[] output;
  protected double pSystem;
  protected List<int[]> workableStates;
  protected List<Double> pStates;


  public Schema(int[][] schema, double[] p, int[] input, int[] output) {
    this.schema = schema;
    this.p = p;
    this.input = input;
    this.output = output;
  }

  public void calcPSystem() {
    calcWorkable();
    calcPStates();
    pSystem = 0;
    for (double state : pStates) {
      pSystem += state;
    }
  }

  public void printWorkableStates() {
    System.out.println("Workable states (state - p)");
    for (int i = 0; i < workableStates.size(); i++) {
      System.out.println(Arrays.toString(workableStates.get(i)) + " P(state) = " + pStates.get(i));
    }
  }

  public void printPSystem() {
    System.out.println("P(system) = " + pSystem);
  }

  public void printSchema() {
    System.out.println("Schema");
    for (int i = 0; i < schema.length; i++) {
      System.out.println(Arrays.toString(schema[i]) + " P(" + (i + 1) + ") = " + p[i]);
    }
  }

  private int[][] calcStates() {
    int[][] states = new int[schema.length][(int) Math.pow(2, schema.length)];
    for (int i = 0; i < states.length; i++) {
      int a = getPeriod(i + 1);
      boolean isInvert = true;
      for (int j = 0, k = 0; j < states[i].length; j++, k++) {
        if (k == a) {
          isInvert = !isInvert;
          k = 0;
        }
        states[i][j] = isInvert
            ? 0
            : 1;
      }
    }
    return states;
  }

  private void calcWorkable() {
    workableStates = new ArrayList<>();
    int[][] states = calcStates();
    for (int i = 0; i < states[0].length; i++) {
      int[] state = new int[states.length];
      for (int j = 0; j < state.length; j++) {
        state[j] = states[j][i];
      }
      if (isWorkable(state)) {
        workableStates.add(state);
      }
    }
  }

  private void calcPStates() {
    pStates = new ArrayList<>();
    for (int[] state : workableStates) {
      pStates.add(pState(state));
    }
  }

  private double pState(int[] state) {
    double pState = 1;
    for (int i = 0; i < state.length; i++) {
      pState *= state[i] == 0
          ? 1.0 - p[i]
          : p[i];
    }
    return pState;
  }

  private boolean isWorkable(int[] states) {
    for (int k : input) {
      for (int i : output) {
        List<Integer> path = findPath(k, i, states, new ArrayList<>());
        if (!path.isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  private List<Integer> findPath(int from, int to, int[] states, List<Integer> prev) {
    List<Integer> path = new ArrayList<>();

    if (states[from] == 0 || states[to] == 0) {
      return path;
    }

    if (schema[from][to] == 1) {
      path.add(from);
      path.add(to);
      return path;
    }

    for (int i = 0; i < schema[from].length; i++) {
      if (schema[from][i] == 1 && states[i] == 1 && !prev.contains(i)) {
        List<Integer> nPrev = new ArrayList<>(prev);
        nPrev.add(i);
        List<Integer> p = findPath(i, to, states, nPrev);
        if (!p.isEmpty()) {
          path.add(from);
          path.addAll(p);
          return path;
        }
      }
    }

    return path;
  }

  private int getPeriod(int column) {
    return ((int) Math.pow(2, column)) / 2;
  }
}
