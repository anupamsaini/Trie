package com.anupam.rhymeengine;

import com.anupam.rhymeengine.concurrent.ConcurrentRhymeEngine;
import com.anupam.rhymeengine.tries.patricia.NodeEntry;
import com.anupam.rhymeengine.tries.patricia.PatriciaTrie;
import com.anupam.rhymeengine.tries.patricia.PatriciaTrieManager;
import com.anupam.rhymeengine.tries.simple.SimpleTrieGenerator;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Entry point for generating rhymes of a word.
 */
public class RhymeEngineManager {

  private static final Logger log = Logger.getLogger(RhymeEngineManager.class);

  private static final String FILE_PATH =
      "/usr/local/google/home/anupams/wlist_match1";

  public static void main(String[] args) throws IOException {
    CliArgsParser arguments = new CliArgsParser(args);

    List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);
    PatriciaTrie trie = new PatriciaTrie(new NodeEntry("", "", false));
    PatriciaTrieManager.loadWords(trie, words);
    switch (arguments.mode) {
      case 's':
        scannerMode(trie, arguments.editDistance);
        break;
      case 'a':
        allWordsSearchMode(words, trie, arguments.editDistance);
        break;
      case 'c':
        concurrentMode(trie, arguments.editDistance);
      default:
        throw new IllegalArgumentException(" No mode of operation provided");
    }
  }

  private static void concurrentMode(PatriciaTrie trie, int levDistance) {
    ConcurrentRhymeEngine concRhymeEngine = new ConcurrentRhymeEngine(trie, levDistance);
    long time = System.currentTimeMillis();
    Scanner in = new Scanner(System.in);
    String value;
    while (true) {
      value = in.next();
      log.info("Fetch suggetions for : " + value);
      if (value.equalsIgnoreCase("x")) {
        in.close();
        concRhymeEngine.shutDown();
        System.exit(1);
      } else {
        time = System.currentTimeMillis();
        List<String> rhymes = concRhymeEngine.generateRhymes(value);
        log.info(rhymes);
        time = System.currentTimeMillis() - time;
        log.info(" time taken :: " + time);
      }
    }
  }

  private static void allWordsSearchMode(List<String> words, PatriciaTrie trie, int levDistance) {
    ConcurrentRhymeEngine concRhymeEngine = new ConcurrentRhymeEngine(trie, levDistance);
    long time = System.currentTimeMillis();
    for (String word : words) {
      time = System.currentTimeMillis();
      concRhymeEngine.generateRhymes(word);
      log.info(word + " :: " + (System.currentTimeMillis() - time) + "ms");
    }
    log.info(" total time :: " + (System.currentTimeMillis() - time));
  }

  private static void scannerMode(PatriciaTrie trie, int levDistance) {
    RhymeEngine suggest = new RhymeEngine(trie.getRoot());
    suggest.setLevDistance(levDistance);
    long time = System.currentTimeMillis();
    Scanner in = new Scanner(System.in);
    String value;
    while (true) {
      value = in.next();
      log.info("Fetch suggetions for : " + value);
      if (value.equalsIgnoreCase("x")) {
        in.close();
        System.exit(1);
      } else {
        time = System.currentTimeMillis();
        suggest.setNoOfNodesTraversed(0);
        List<String> rhymes = suggest.generateRhymes(value);
        time = System.currentTimeMillis() - time;
        log.info(value + " --> " + rhymes);
        log.info(" time taken :: " + time + "  Total nodes traversed : "
            + suggest.getNoOfNodesTraversed());
      }
    }
  }
}
