package com.anupam.java.tries.edit.distance;

import com.google.common.collect.Lists;

import com.anupam.java.tries.patricia.NodeEntry;
import com.anupam.java.tries.patricia.PatriciaTrie;
import com.anupam.java.tries.patricia.PatriciaTrieManager;

import java.util.List;
import java.util.Scanner;

/**
 * Finds words rhyming with a given word.
 */
public class WordSuggest {

  /**
   * The tree representation of the words dictionary.
   */
  private final PatriciaTrie pTrie;
  /**
   * The maximum allowed distance between two strings.
   */
  private int levDistance = 1;

  // Used for maintaining stats on number of nodes travered while searching for rhymes of a word.
  private int noOfNodesTraversed = 0;

  public void setLevDistance(int levDistance) {
    this.levDistance = levDistance;
  }

  public WordSuggest(PatriciaTrie pTire) {
    this.pTrie = pTire;
  }

  /**
   * Construtor for {@link WordSuggest}.
   *
   * @param pTire The trie representation of the word list.
   * @param levDistance The maximum Levenshtein distance between two words.
   */
  public WordSuggest(PatriciaTrie pTire, int levDistance) {
    this.pTrie = pTire;
    this.levDistance = levDistance;
  }

  public static void main(String[] args) {
    WordSuggest suggest = new WordSuggest(new PatriciaTrie(new NodeEntry("", "", false)));
    PatriciaTrieManager.loadTrie(suggest.pTrie);

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
        suggest.setLevDistance(2);
        suggest.noOfNodesTraversed = 0;
        List<String> rhymes = suggest.wordSuggestion(value);
        System.out.println(" :: " + rhymes);
        System.out.println(" time taken :: " + (System.currentTimeMillis() - time));
        System.out.println("Total nodes traversed : " + suggest.noOfNodesTraversed);
      }
    }
  }

  /**
   * Searches the words rhyming with the given word.
   *
   * @param word The word whose rhymes are to be found.
   * @return A list of words that rhyme with the given word.
   */
  public List<String> wordSuggestion(String word) {
    StringBuilderWithRemove sb = new StringBuilderWithRemove(new StringBuilder());
    List<String> rhymingWords = Lists.newLinkedList();
    int frame[] = new int[word.length()+1];
    for(int i=0;i<=word.length();i++) 
      frame[i] = i;
    wordSuggestionAlternateImpl(word, pTrie.getRoot(), sb, rhymingWords, word.length(),frame);
    return rhymingWords;
  }

  /**
   * Recursively searches the words rhyming with the given word
   *
   * @param toSearch The word to search.
   * @param node The node whose key would be searched for possible rhyme.
   * @param sb The buffer for appending & removing keys at each recursion step.
   * @param rhymingWords The words rhyming with the given word.
   * @param levDistLimit
   */
  private void wordSuggestion(String toSearch, NodeEntry node, StringBuilderWithRemove sb,
      List<String> rhymingWords, int levDistLimit) {
    sb.append(node.getKey());
    ++noOfNodesTraversed;
    // System.out.println(
    // sb.toString() + "->" + LevenshteinDistance.calculateEditDistance(toSearch, sb.toString()));
    int dist = LevenshteinDistance.calculateEditDistance(toSearch, sb.toString());
    if (dist <= levDistLimit) {
      if (dist <= this.levDistance && node.hasWord()) {
        rhymingWords.add(sb.toString());
      }
      for (NodeEntry childNode : node.getChildren()) {
        wordSuggestion(toSearch, childNode, sb, rhymingWords, dist);
      }
    }
    sb.discardAt(node.getKey());
  }

  /**
   * Recursively searches the words rhyming with the given word
   *
   * @param toSearch The word to search.
   * @param node The node whose key would be searched for possible rhyme.
   * @param sb The buffer for appending & removing keys at each recursion step.
   * @param rhymingWords The words rhyming with the given word.
   * @param levDistLimit
   */
  private void wordSuggestionAlternateImpl(String toSearch,
      NodeEntry node,
      StringBuilderWithRemove sb,
      List<String> rhymingWords,
      int levDistLimit,
      int[] frame) {
    sb.append(node.getKey());
    ++noOfNodesTraversed;
    // System.out.println(
    // sb.toString() + "->" + LevenshteinDistance.calculateEditDistance(toSearch, sb.toString()));
    int[] dist = LevenshteinDistance.incrementalLevDistance(sb.toString(), toSearch, frame);
    System.out.println(dist[dist.length-1] + " " + sb);
    if (dist[dist.length - 1] <= levDistLimit) {
      if (dist[dist.length - 1] <= this.levDistance && node.hasWord()) {
        rhymingWords.add(sb.toString());
      }
      for (NodeEntry childNode : node.getChildren()) {
        wordSuggestionAlternateImpl(toSearch,
            childNode,
            sb,
            rhymingWords,
            dist[dist.length - 1],
            dist);
      }
    }
    sb.discardAt(node.getKey());
  }
}
