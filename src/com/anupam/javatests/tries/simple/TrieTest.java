package com.anupam.javatests.tries.simple;

import com.anupam.java.tries.simple.Trie;
import com.anupam.java.tries.simple.Trie.Node;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test case for {@link Trie}.
 */
public class TrieTest {

  private Node root;
  private Trie trie;
  private static final String[] words = {"word", "bird", "scatterman", "lemmings", "hastings"};

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    root = new Node();
    trie = new Trie();
    for (int i = 0; i < words.length; i++) {
      trie.addNode(root, words[i], "dummy");
    }
  }

  @Test
  public void testAddNode_Success() {
    assertTrue(trie.addNode(root, "test", "dummy"));
  }

  @Test
  public void testAddNodeMultipleWords_Success() {
    for (int i = 0; i < words.length; i++) {
      assertTrue(trie.addNode(root, words[i], "dummy"));
    }
  }

  @Test
  public void testAddNode_Failure() {
    assertFalse(trie.addNode(root, "$$", "dummy"));
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
