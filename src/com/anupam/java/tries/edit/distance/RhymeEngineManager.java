package com.anupam.java.tries.edit.distance;

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
    RhymeEngine suggest = new RhymeEngine(new PatriciaTrie(new NodeEntry("", "", false)));
    List<String> words = SimpleTrieGenerator.readWords(FILE_PATH);
    suggest.setLevDistance(2);
    PatriciaTrieManager.loadWords(suggest.getPTrie(), words);
    // Scanner in = new Scanner(System.in);
    long time = System.currentTimeMillis();
    String value;
    for (String word : words) {
      time = System.currentTimeMillis();
      suggest.setNoOfNodesTraversed(0);
      List<String> rhymes = suggest.generateRhymes(word);
      System.out.println(" :: " + rhymes.size());
      System.out.println(" time taken :: " + (System.currentTimeMillis() - time));
      System.out.println("Total nodes traversed : " + suggest.getNoOfNodesTraversed());
    }
    // while (true) {
    // value = in.next();
    // System.out.println("Fetch suggetions for : " + value);
    // if (value.equalsIgnoreCase("x")) {
    // in.close();
    // System.exit(1);
    // } else {
    // time = System.currentTimeMillis();
    // suggest.setNoOfNodesTraversed(0);
    // List<String> rhymes = suggest.generateRhymes(value);
    // System.out.println(" :: " + rhymes.size());
    // System.out.println(" time taken :: " + (System.currentTimeMillis() - time));
    // System.out.println("Total nodes traversed : " + suggest.getNoOfNodesTraversed());
    // }
    // }
  }
}
