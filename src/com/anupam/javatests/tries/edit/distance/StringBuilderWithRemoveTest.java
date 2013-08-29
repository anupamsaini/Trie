package com.anupam.javatests.tries.edit.distance;

import static org.junit.Assert.*;

import com.anupam.java.tries.edit.distance.StringBuilderWithRemove;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for {@link StringBuilderWithRemove}
 */
public class StringBuilderWithRemoveTest {

  private StringBuilderWithRemove sb;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    sb = new StringBuilderWithRemove(new StringBuilder());
  }

  @Test
  public void testDiscard() {
    sb.append("test").append("fest").append("best");
    assertEquals("test", sb.discardAt("fest").toString());
  }

  @Test
  public void testDiscardEmptyString() {
    sb.append("");
    assertEquals("", sb.discardAt("").toString());
    sb.append("test");
    assertEquals("test", sb.discardAt("").toString());
    assertEquals("test", sb.discardAt(null).toString());
  }

  @Test
  public void testDiscardAfter() {
    sb.append("test").append("fest").append("best");
    assertEquals("testfest", sb.discardAfter("fest").toString());
  }

  @Test
  public void testDiscardAfterEmptyString() {
    sb.append("");
    assertEquals("", sb.discardAfter("").toString());
    sb.append("test");
    assertEquals("test", sb.discardAfter("").toString());
    sb.append("fest");
    assertEquals("testfest", sb.discardAfter(null).toString());
  }
}
