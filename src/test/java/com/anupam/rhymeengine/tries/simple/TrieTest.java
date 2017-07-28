package com.anupam.rhymeengine.tries.simple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link SimpleTrie}.
 */
public class TrieTest {

  private NodeEntry root;
  private SimpleTrie trie;
  private static final String[] words = {"word", "bird", "scatterman", "lemmings", "hastings"};

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    root = new NodeEntry();
    trie = new SimpleTrie();
    for (int i = 0; i < words.length; i++) {
      trie.insertWord(root, words[i], "dummy");
    }
  }

  @Test
  public void testAddNode_Success() {
    assertTrue(trie.insertWord(root, "test", "dummy"));
  }

  @Test
  public void testAddNodeMultipleWords_Success() {
    for (int i = 0; i < words.length; i++) {
      assertTrue(trie.insertWord(root, words[i], "dummy"));
    }
  }

  @Test
  public void testAddNode_Failure() {
    assertFalse(trie.insertWord(root, "$$", "dummy"));
  }

  @Test
  public void testSearchWord_Success() {
    for (int i = 0; i < words.length; i++) {
      assertTrue("dummy".equals(trie.searchWord(root, words[i])));
    }
  }

  @Test
  public void testSearchWord_Failure() {
    assertFalse("dummy".equals(trie.searchWord(root, "")));
    assertFalse("dummy".equals(trie.searchWord(root, null)));
    assertFalse("dummy".equals(trie.searchWord(root, "random")));
  }

  @Test
  public void testNodeCreation() {
    assertTrue(root.getChildern().size() == 256);
  }
}
