package com.anupam.java.tries.edit.distance;

import com.anupam.java.tries.patricia.NodeEntry;
import com.anupam.java.tries.patricia.PatriciaTrie;
import com.anupam.java.tries.patricia.PatriciaTrieManager;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for generating rhymes of a word.
 */
public class RhymeEngineManager {

  public static void main(String[] args) {
    RhymeEngine suggest = new RhymeEngine(new PatriciaTrie(new NodeEntry("", "", false)));
    suggest.setLevDistance(2);
    PatriciaTrieManager.loadTrie(suggest.getPTrie());
    Scanner in = new Scanner(System.in);
    long time = System.currentTimeMillis();
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
        System.out.println(" :: " + rhymes);
        System.out.println(" time taken :: " + (System.currentTimeMillis() - time));
        System.out.println("Total nodes traversed : " + suggest.getNoOfNodesTraversed());
      }
    }
  }

}
