package com.anupam.java.tries.simple;

import java.util.List;


/**
 * Entry point for the Trie generation.
 */
public class SimpleTrieManager {

  private static final String FILE_PATH =
      "/usr/local/google/home/anupams/beltway-workspace_22APril/SomethingCreative/words/wordsList";

  public static void main(String args[]) {
    List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);
    SimpleTrie trie = new SimpleTrie();
    NodeEntry root = new NodeEntry();
    SimpleTrieGenerator.populateTrie(trie, root, FILE_PATH);
    System.out.println(root);
    System.out.println(trie.searchWord(root, "anupam"));
    System.out.println(trie.searchWord(root, "bastards"));
    System.out.println(trie.searchWord(root, "ba$tards"));
  }
}
