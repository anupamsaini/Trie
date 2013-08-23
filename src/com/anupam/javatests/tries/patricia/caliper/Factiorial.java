package com.anupam.javatests.tries.patricia.caliper;

/**
 * TODO: Insert description here. (generated by anupams)
 */
public class Factiorial {

  public static long rescursiveSolution(int val) {

    switch (val) {
      case 0:
        return 1l;
      default:
        return rescursiveSolution(val - 1) * val;
    }
  }

  public static long interativeSolution(int var) {
    long result = 1;
    for (int i = 1; i <= var; i++) {
      result = i * result;
    }
    return result;
  }
}