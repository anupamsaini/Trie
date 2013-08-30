package com.anupam.javatests.tries.edit.distance;

import static org.junit.Assert.*;

import com.anupam.java.tries.edit.distance.LevenshteinDistance;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for {@link LevenshteinDistance}.
 */
public class LevenshteinDistanceTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {}

  @Test
  public void test_calculateEditDistance() {
    assertEquals(3, LevenshteinDistance.calculateEditDistance("kitten", "sitting"));
    assertEquals(3, LevenshteinDistance.calculateEditDistance("saturday", "sunday"));
    assertEquals(0, LevenshteinDistance.calculateEditDistance("sunday", "sunday"));
    assertEquals(3, LevenshteinDistance.calculateEditDistance("StackOverflow", "StackUnderflow"));
    assertEquals(1, LevenshteinDistance.calculateEditDistance("a", "b"));
    assertEquals(0, LevenshteinDistance.calculateEditDistance("", ""));
    assertEquals(6, LevenshteinDistance.calculateEditDistance("sunday", ""));
    assertEquals(6, LevenshteinDistance.calculateEditDistance("", "sunday"));
    // LevenshteinDistance.calculateEditDistance("stalwart", "acrimonious");
  }

  @Test
  public void test_incrementalLevDistance() {
    int[] frame = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    frame = LevenshteinDistance.incrementalLevDistance("s", "acrimonious", frame);
    assertEquals(10, frame[frame.length - 1]);
    frame = LevenshteinDistance.incrementalLevDistance("t", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    frame = LevenshteinDistance.incrementalLevDistance("a", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    frame = LevenshteinDistance.incrementalLevDistance("lwa", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    frame = LevenshteinDistance.incrementalLevDistance("rt", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    assertEquals(8, frame[0]);
  }

  @Test
  public void test_incrementalLevDistance_empty() {
    int [] frame = {0};
    frame = LevenshteinDistance.incrementalLevDistance("", "", frame);
    assertEquals(0, frame[0]);
  }
}
