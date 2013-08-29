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
  }

}
