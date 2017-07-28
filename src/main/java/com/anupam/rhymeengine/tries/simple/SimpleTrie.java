package com.anupam.rhymeengine.tries.simple;

import com.anupam.rhymeengine.tries.Util;


/**
 * A class for creating Trie data structure from a list of words.
 */
public class SimpleTrie {

  private int countOfNodesAllocated = 0;

  public int getCountOfNodesAllocated() {
    return countOfNodesAllocated;
  }

  /**
   * Checks if child node exist at the specified index of parent node and adds a new node in case of
   * null value.
   *
   * @param parent Parent node which is to be examined.
   * @param index The index at which the check will be performed.
   */
  private NodeEntry checkAndInsertNode(NodeEntry parent, int index) {
    if (null == parent.getChildern().get(index)) {
      ++countOfNodesAllocated;
      parent.addChild(index, new NodeEntry());
    }
    return parent.getChild(index);
  }

  /**
   * Add a new node to the trie.
   *
   * @param root The immediate parent node.
   * @param word The remaining characters to be inserted in the trie.
   * @param meaning The meaning of the word, to be inserted when all the characters in a word have
   *        been entered.
   *
   * @return A flag to indicate the word addition operation's status.
   */
  public boolean insertWord(NodeEntry root, String word, String meaning) {
    int leftChar = Util.getLeftMostChar(word);
    if (Util.isValidAsciiChar(leftChar)) {
      return insertWord(
          checkAndInsertNode(root, leftChar), Util.substringAfterFirstIndex(word), meaning);
    } else if (leftChar == -1) {
      root.setMeaning(meaning);
      return true;
    }
    return false;
  }

  /**
   * Searches a particular word starting from root node.
   *
   * @param root The root node of the trie.
   * @param word The word to be searched.
   * @return The word's meaning or null value in case word is not found.
   */
  public String searchWord(NodeEntry root, String word) {
    int leftChar = Util.getLeftMostChar(word);
    if (Util.isValidAsciiChar(leftChar)) {
      // The check implies the character is present in the trie sub tree.
      if (null != root.getChild(leftChar)) {
        return searchWord(root.getChild(leftChar), Util.substringAfterFirstIndex(word));
      }
      return null;
    } else if (leftChar == -1) {
      // -1 indicates end of word, we reach this point after having found legitimate values
      // in the parent nodes for each character in the word. TODO (anupam) Will fail when user
      // searches for empty string.
      return root.getMeaning();
    }
    return null;
  }
}
