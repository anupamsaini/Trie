package com.anupam.java.tries.simple;

/**
 * Entry point for the Trie generation.
 */
public class SimpleTrieManager {

  private static final String FILE_PATH =
      "<path to file>";

  public static void main(String args[]) {
    SimpleTrie trie = new SimpleTrie();
    NodeEntry root = new NodeEntry();
    SimpleTrieGenerator.populateTrie(trie, root, FILE_PATH);
    System.out.println(root);
    System.out.println(trie.searchWord(root, "anupam"));
    System.out.println(trie.searchWord(root, "bastards"));
    System.out.println(trie.searchWord(root, "ba$tards"));
  }
}
