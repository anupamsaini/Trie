package com.anupam.javatests.tries.patricia.caliper;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

/**
 * TODO: Insert description here. (generated by anupams)
 */
public class FactorialBenchmark extends Benchmark{

  @Param({"5", "10", "20"}) int number;

  long recursive(int loop) {
    int number = this.number;
    long dummy = 0l;
    for(int i=0;i<loop;i++) {
      dummy |= Factiorial.rescursiveSolution(number);
    }
    return dummy;
  }

  public static void main(String... args) throws Exception {
    CaliperMain.main(FactorialBenchmark.class, args);
  }
}
