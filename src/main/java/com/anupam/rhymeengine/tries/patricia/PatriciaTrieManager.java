package com.anupam.rhymeengine.tries.patricia;

import com.anupam.rhymeengine.tries.simple.SimpleTrieGenerator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Class for managing common trie operations.
 */
public class PatriciaTrieManager {

  private static final Logger log = Logger.getLogger(PatriciaTrieManager.class);

  private static final String FILE_PATH =
      "<Path to file>";

  public static final void loadWords(PatriciaTrie pTrie, List<String> words) {
    long time = System.currentTimeMillis();
    for (String word : words) {
      pTrie.insertWord(word, "");
    }
    log.info("Time  taken " + (System.currentTimeMillis() - time));
  }

  public static final void searchWords(PatriciaTrie pTrie, List<String> words) {
    long time = System.currentTimeMillis();
    int i = 0;
    for (String word : words) {
      ++i;
      if (!pTrie.searchWord(word)) {
        log.info("not found " + word + " index " + i);
      }
    }
    log.info("Time  taken " + (System.currentTimeMillis() - time));
  }

  public static void main(String args[]) {
    List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);
    PatriciaTrie pTrie = new PatriciaTrie(new NodeEntry());
    loadWords(pTrie, words);
    log.info("Total allocated nodes :: " + pTrie.getCountOfAllocatedNodes());
    log.info(
        "Ratio of nodes to words :: " + (float) pTrie.getCountOfAllocatedNodes() / words.size());
    searchWords(pTrie, words);
  }
}
