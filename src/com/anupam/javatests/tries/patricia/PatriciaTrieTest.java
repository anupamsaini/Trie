package com.anupam.javatests.tries.patricia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.anupam.java.tries.patricia.NodeEntry;
import com.anupam.java.tries.patricia.PatriciaTrie;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link PatriciaTrie}.
 */
public class PatriciaTrieTest {

  PatriciaTrie pTrie;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    pTrie = new PatriciaTrie(new NodeEntry());
    pTrie.insertNode("\"!exclamation-point", "dummy");
    pTrie.insertNode("\"close-quote", "dummy");
    pTrie.insertNode("\"double-quote", "dummy");
    pTrie.insertNode("\"end-of-quote", "dummy");
    pTrie.insertNode("\"end-quote", "dummy");
    pTrie.insertNode("\"in-quotes", "dummy");
    pTrie.insertNode("rats", "dummy");
  }

  @Test
  public void testInsert() {
    assertTrue(pTrie.insertNode("tuborg", "danish"));
    assertTrue(pTrie.insertNode("", ""));
    assertTrue(pTrie.insertNode("a", "aaas"));
    assertTrue(pTrie.insertNode("zwduuuuuuuuuuuuuuuuuuuuuuufkajsdgf", "aaas"));
  }

  @Test
  public void testSearch() {
    assertTrue(pTrie.searchKey("\"end-quote"));
  }

  @Test
  public void testSearch_NotFound() {
    assertFalse(pTrie.searchKey("sdjfbsdjfv"));
    assertFalse(pTrie.searchKey(""));
    assertFalse(pTrie.searchKey("ratz"));
  }
}
