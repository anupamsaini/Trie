package com.anupam.java.tries.edit.distance;

import com.anupam.java.tries.edit.distance.concurrent.ConcurrentRhymeEngine;
import com.anupam.java.tries.patricia.NodeEntry;
import com.anupam.java.tries.patricia.PatriciaTrie;
import com.anupam.java.tries.patricia.PatriciaTrieManager;
import com.anupam.java.tries.simple.SimpleTrieGenerator;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for generating rhymes of a word.
 */
public class RhymeEngineManager {

  private static final Logger log = Logger.getLogger(RhymeEngineManager.class);

  private static final String FILE_PATH =
      "/usr/local/google/home/anupams/beltway-workspace_22APril/SomethingCreative/words/randomWords";

  public static void main(String[] args) {
    List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);
    PatriciaTrie trie = new PatriciaTrie(new NodeEntry("", "", false));
    PatriciaTrieManager.loadWords(trie, words);
    String mode;
    if (args.length > 1) {
      mode = args[1];
      if (mode.equalsIgnoreCase("a")) {
        allWordsSearchMode(words, trie, Integer.valueOf(args[0]));
      } else if (mode.equalsIgnoreCase("c")) {
        concurrentMode(trie, Integer.valueOf(args[0]));
      }
    } else {
      scannerMode(trie, Integer.valueOf(args[0]));
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
    String value;
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
