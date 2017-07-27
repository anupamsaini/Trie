package com.anupam.rhyme.tries.patricia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.anupam.rhyme.tries.Node;
import com.anupam.rhyme.tries.patricia.NodeEntry;
import com.anupam.rhyme.tries.patricia.PatriciaTrie;

import com.anupam.rhyme.tries.patricia.NodeEntry;
import com.anupam.rhyme.tries.patricia.PatriciaTrie;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link Node}.
 */
public class PatriciaTrieTest {

  PatriciaTrie pTrie;

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    pTrie = new PatriciaTrie(new NodeEntry());
    pTrie.insertWord("\"!exclamation-point", "dummy");
    pTrie.insertWord("\"close-quote", "dummy");
    pTrie.insertWord("\"double-quote", "dummy");
    pTrie.insertWord("\"end-of-quote", "dummy");
    pTrie.insertWord("\"end-quote", "dummy");
    pTrie.insertWord("\"in-quotes", "dummy");
    pTrie.insertWord("rats", "dummy");
  }

  @Test
  public void testInsert() {
    assertTrue(pTrie.insertWord("tuborg", "danish"));
    assertTrue(pTrie.insertWord("", ""));
    assertTrue(pTrie.insertWord("a", "aaas"));
    assertTrue(pTrie.insertWord("zwduuuuuuuuuuuuuuuuuuuuuuufkajsdgf", "aaas"));
  }

  @Test
  public void testSearch() {
    assertTrue(pTrie.searchWord("\"end-quote"));
  }

  @Test
  public void testSearch_NotFound() {
    assertFalse(pTrie.searchWord("sdjfbsdjfv"));
    assertFalse(pTrie.searchWord(""));
    assertFalse(pTrie.searchWord("ratz"));
  }
}
