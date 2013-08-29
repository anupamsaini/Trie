package com.anupam.java.tries.patricia;

import com.anupam.java.tries.simple.SimpleTrieGenerator;

import java.util.List;

/**
 * Class for managing common trie operations.
 */
public class PatriciaTrieManager {

  private static final String FILE_PATH = "enter the word list path here";

  private static final List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);

  public static final void loadTrie(PatriciaTrie pTrie) {
    long time = System.currentTimeMillis();
    for (String word : words) {
      pTrie.insertNode(word, "");
    }
    System.out.println("Time  taken " + (System.currentTimeMillis() - time));
  }

  public static final void searchTrie(PatriciaTrie pTrie) {
    long time = System.currentTimeMillis();
    int i = 0;
    for (String word : words) {
      ++i;
      if (!pTrie.searchKey(word)) {
        System.out.println("not found " + word + " index " + i);
      }
    }
    System.out.println("Time  taken " + (System.currentTimeMillis() - time));
  }

  public static void main(String args[]) {
    PatriciaTrie pTrie = new PatriciaTrie(new NodeEntry());
    loadTrie(pTrie);
    System.out.println("Total allocated nodes :: " + pTrie.getCountOfAllocatedNodes());
    System.out.println(
        "Ratio of nodes to words :: " + (float) pTrie.getCountOfAllocatedNodes() / words.size());
    searchTrie(pTrie);
  }
}
