package com.anupam.javatests.tries.edit.distance;

import static org.junit.Assert.assertEquals;

import com.anupam.java.tries.edit.distance.LevenshteinDistance;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

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
    assertEquals(3, LevenshteinDistance.editDistance("kitten", "sitting"));
    assertEquals(3, LevenshteinDistance.editDistance("saturday", "sunday"));
    assertEquals(0, LevenshteinDistance.editDistance("sunday", "sunday"));
    assertEquals(3, LevenshteinDistance.editDistance("StackOverflow", "StackUnderflow"));
    assertEquals(1, LevenshteinDistance.editDistance("a", "b"));
    assertEquals(0, LevenshteinDistance.editDistance("", ""));
    assertEquals(6, LevenshteinDistance.editDistance("sunday", ""));
    assertEquals(6, LevenshteinDistance.editDistance("", "sunday"));
    // LevenshteinDistance.calculateEditDistance("stalwart", "acrimonious");
  }

  @Test
  public void test_incrementalLevDistance() {
    byte[] frame = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    frame = LevenshteinDistance.editDistance("s", "acrimonious", frame);
    assertEquals(10, frame[frame.length - 1]);
    frame = LevenshteinDistance.editDistance("t", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    frame = LevenshteinDistance.editDistance("a", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    frame = LevenshteinDistance.editDistance("lwa", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    frame = LevenshteinDistance.editDistance("rt", "acrimonious", frame);
    assertEquals(11, frame[frame.length - 1]);
    assertEquals(8, frame[0]);
  }

  private byte[] recIncrement(byte frame[], Queue<String> splitWords, String toSearch) {
    String toMatch = splitWords.poll();
    if (null == toMatch) {
      return frame;
    }
    StringBuffer sb = new StringBuffer();
    for(int i=0;i<frame.length;i++)
      sb.append(frame[i]).append(",");
    frame = LevenshteinDistance.editDistance(toMatch, toSearch, frame);
    System.out.println(sb);
    return recIncrement(frame, splitWords, toSearch);
  }

  @Test
  public void test_recursiveIncrementingLevDistance() {
    byte frame[] = {0, 1, 2, 3, 4};
    Queue<String> splitWord = new LinkedList<String>();
    splitWord.add("a");
    splitWord.add("b");
    splitWord.add("h");
    String toSearch = "test";
    frame = recIncrement(frame, splitWord, toSearch);
    assertEquals(4, frame[frame.length - 1]);
  }

  @Test
  public void test_incrementalLevDistance_empty() {
    byte[] frame = {0};
    frame = LevenshteinDistance.editDistance("", "", frame);
    assertEquals(0, frame[0]);
  }
}
