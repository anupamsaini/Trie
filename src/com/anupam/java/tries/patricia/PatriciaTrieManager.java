package com.anupam.java.tries.patricia;

import com.anupam.java.tries.simple.SimpleTrieGenerator;

import java.util.List;

/**
 * Class for managing common trie operations.
 */
public class PatriciaTrieManager {
  
  private static final String FILE_PATH =
      "/usr/local/google/home/anupams/beltway-workspace_22APril/SomethingCreative/words/wlist_match1";

  public static final void loadWords(PatriciaTrie pTrie, List<String> words) {
    long time = System.currentTimeMillis();
    for (String word : words) {
      pTrie.insertWord(word, "");
    }
    System.out.println("Time  taken " + (System.currentTimeMillis() - time));
  }

  public static final void searchWords(PatriciaTrie pTrie, List<String> words) {
    long time = System.currentTimeMillis();
    int i = 0;
    for (String word : words) {
      ++i;
      if (!pTrie.searchWord(word)) {
        System.out.println("not found " + word + " index " + i);
      }
    }
    System.out.println("Time  taken " + (System.currentTimeMillis() - time));
  }

  public static void main(String args[]) {
    List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);
    PatriciaTrie pTrie = new PatriciaTrie(new NodeEntry());
    loadWords(pTrie,words);
    System.out.println("Total allocated nodes :: " + pTrie.getCountOfAllocatedNodes());
    System.out.println(
        "Ratio of nodes to words :: " + (float) pTrie.getCountOfAllocatedNodes() / words.size());
    searchWords(pTrie,words);
  }
}
