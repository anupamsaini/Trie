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
public class RhymeEngine {

  /**
   * The tree representation of the words dictionary.
   */
  private final PatriciaTrie pTrie;
  /**
   * The maximum allowed distance between two strings.
   */
  private int levDistance = 1;

  // Stats on number of nodes travered while searching for rhymes.
  private int noOfNodesTraversed = 0;

  public RhymeEngine(PatriciaTrie pTire) {
    this.pTrie = pTire;
  }

  public PatriciaTrie getPTrie() {
    return this.pTrie;
  }

  public int getNoOfNodesTraversed() {
    return noOfNodesTraversed;
  }

  public void setNoOfNodesTraversed(int value) {
    this.noOfNodesTraversed = value;
  }

  public void setLevDistance(int levDistance) {
    this.levDistance = levDistance;
  }

  /**
   * Generates a list of words rhyming with the given word.
   *
   * @param word The word whose rhymes are to be generated.
   * @return A list of words rhyming with the given word.
   */
  public List<String> generateRhymes(String word) {
    StringBuilderWithRemove sb = new StringBuilderWithRemove(new StringBuilder());
    List<String> rhymingWords = Lists.newLinkedList();
    byte frame[] = new byte[word.length() + 1];
    for (int i = 0; i <= word.length(); i++)
      frame[i] = (byte)i;
    generateRhymes(word, pTrie.getRoot(), sb, rhymingWords, frame);
    // generateRhymes(word,pTrie.getRoot(),sb,rhymingWords,word.length());
    return rhymingWords;
  }

  /**
   * Recursively searches the words rhyming with the given word.
   *
   * @param toSearch The word whose rhymes are to be generated.
   * @param node The node whose key would be searched for possible rhyme.
   * @param keyBuffer The buffer for appending & removing keys at each step of recursion.
   * @param rhymingWords A list of words rhyming with the given word.
   * @param levDistLimit The maximum possible delta in edit distances.
   */
  private void generateRhymes(String toSearch, NodeEntry node, StringBuilderWithRemove keyBuffer,
      List<String> rhymingWords, int levDistLimit) {
    keyBuffer.append(node.getKey());
    setNoOfNodesTraversed(getNoOfNodesTraversed() + 1);
    int dist = LevenshteinDistance.editDistance(toSearch, keyBuffer.toString());
    if (dist <= levDistLimit) {
      if (dist <= this.levDistance && node.hasWord()) {
        rhymingWords.add(keyBuffer.toString());
      }
      for (NodeEntry childNode : node.getChildren()) {
        generateRhymes(toSearch, childNode, keyBuffer, rhymingWords, dist);
      }
    }
    keyBuffer.discardAt(node.getKey());
  }

  /**
   * Recursively searches the words rhyming with the given word
   *
   * @param toSearch The word to search.
   * @param node The node whose key would be searched for possible rhyme.
   * @param keyBuffer The buffer for appending & removing keys at each recursion step.
   * @param rhymingWords The words rhyming with the given word.
   * @param frame The edit distance at the immediate parent.
   */
  private void generateRhymes(String toSearch, NodeEntry node, StringBuilderWithRemove keyBuffer,
      List<String> rhymingWords, byte[] frame) {
    keyBuffer.append(node.getKey());
    setNoOfNodesTraversed(getNoOfNodesTraversed() + 1);
    byte[] dist = LevenshteinDistance.editDistance(node.getKey(), toSearch, frame);
    if (dist[dist.length - 1] <= frame[frame.length - 1]) {
      if (dist[dist.length - 1] <= this.levDistance && node.hasWord()) {
        rhymingWords.add(keyBuffer.toString());
      }
      for (NodeEntry childNode : node.getChildren()) {
        generateRhymes(toSearch, childNode, keyBuffer, rhymingWords, dist);
      }
    }
    keyBuffer.discardAt(node.getKey());
  }
}
