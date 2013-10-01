package com.anupam.java.tries.edit.distance;

import com.anupam.java.tries.edit.distance.concurrent.ConcurrentRhymeEngine;
import com.anupam.java.tries.patricia.NodeEntry;
import com.anupam.java.tries.patricia.PatriciaTrie;
import com.anupam.java.tries.patricia.PatriciaTrieManager;
import com.anupam.java.tries.simple.SimpleTrieGenerator;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for generating rhymes of a word.
 */
public class RhymeEngineManager {

  private static final String FILE_PATH =
      "/usr/local/google/home/anupams/beltway-workspace_22APril/SomethingCreative/words/wlist_match1";

  public static void main(String[] args) {
    List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);
    PatriciaTrie trie = new PatriciaTrie(new NodeEntry("", "", false));
    PatriciaTrieManager.loadWords(trie, words);
    if (args.length > 2) {
      RhymeEngine rhymeEngine = new RhymeEngine(trie.getRoot());
      rhymeEngine.setLevDistance(Integer.valueOf(args[0]));
      allWordsSearchMode(words, rhymeEngine);
    } else if (args.length > 0 && args.length < 2) {
      concurrentMode(trie, Integer.valueOf(args[0]));
    } else {
      RhymeEngine rhymeEngine = new RhymeEngine(trie.getRoot());
      rhymeEngine.setLevDistance(Integer.valueOf(args[0]));
      scannerMode(rhymeEngine);
    }
  }

  private static void concurrentMode(PatriciaTrie trie, int levDistance) {
    ConcurrentRhymeEngine concRhymeEngine = new ConcurrentRhymeEngine(trie, levDistance);
    long time = System.currentTimeMillis();
    Scanner in = new Scanner(System.in);
    String value;
    while (true) {
      value = in.next();
      System.out.println("Fetch suggetions for : " + value);
      if (value.equalsIgnoreCase("x")) {
        in.close();
        concRhymeEngine.shutDown();
        System.exit(1);
      } else {
        time = System.currentTimeMillis();
        System.out.println(concRhymeEngine.generateRhymes(value));
        time = System.currentTimeMillis() - time;
        System.out.println(" time taken :: " + time);
      }
    }
  }

  private static void allWordsSearchMode(List<String> words, RhymeEngine suggest) {
    long time = System.currentTimeMillis();
    String value;
    for (String word : words) {
      time = System.currentTimeMillis();
      suggest.setNoOfNodesTraversed(0);
      List<String> rhymes = suggest.generateRhymes(word);
      time = System.currentTimeMillis() - time;
      System.out.println(word + " --> " + rhymes);
      System.out.println(" time taken :: " + time + "  Total nodes traversed : "
          + suggest.getNoOfNodesTraversed());
    }
  }

  private static void scannerMode(RhymeEngine suggest) {
    long time = System.currentTimeMillis();
    Scanner in = new Scanner(System.in);
    String value;
    while (true) {
      value = in.next();
      System.out.println("Fetch suggetions for : " + value);
      if (value.equalsIgnoreCase("x")) {
        in.close();
        System.exit(1);
      } else {
        time = System.currentTimeMillis();
        suggest.setNoOfNodesTraversed(0);
        List<String> rhymes = suggest.generateRhymes(value);
        time = System.currentTimeMillis() - time;
        System.out.println(value + " --> " + rhymes);
        System.out.println(" time taken :: " + time + "  Total nodes traversed : "
            + suggest.getNoOfNodesTraversed());
      }
    }
  }
}
