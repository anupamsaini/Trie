package com.anupam.java.tries.patricia;

import com.anupam.java.tries.simple.TrieGenerator;

import java.util.List;

/**
 * Class for managing common trie operations.
 */
public class PatriciaTrieManager {

  private static final PatriciaTrie pTrie = new PatriciaTrie(new NodeEntry());
  private static final String FILE_PATH =
      "/usr/local/google/home/anupams/beltway-workspace_22APril/SomethingCreative/words/wordsList";

  private static final List<String> words = TrieGenerator.readWords(FILE_PATH);

  private static final void loadTrie() {
    long time = System.currentTimeMillis();
    for (String word : words) {
      pTrie.insertNode(word, "");
    }
    System.out.println("Time  taken " + (System.currentTimeMillis() - time));
  }

  private static final void searchTrie() {
    long time = System.currentTimeMillis();
    int i = 0 ;
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
    loadTrie();
    searchTrie();
  }
}
