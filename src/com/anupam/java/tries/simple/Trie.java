package com.anupam.java.tries.simple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for creating Trie data structure from a list of words.
 */
public class Trie {

  /**
   * Checks if child node exist at the specified index of parent node and adds a new node in case of
   * null value.
   *
   * @param parent Parent node which is to be examined.
   * @param index The index at which the check will be performed.
   */
  private Node checkAndInsertNode(Node parent, int index) {
    if (null == parent.childern.get(index)) {
      parent.addChild(index, new Node());
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
  public boolean addNode(Node root, String word, String meaning) {
    int leftChar = Util.getLeftMostChar(word);
    if (Util.isValidAsciiChar(leftChar)) {
      return addNode(
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
  public String searchWord(Node root, String word) {
    int leftChar = Util.getLeftMostChar(word);
    if (Util.isValidAsciiChar(leftChar)) {
      // The check implies the character is present in the trie sub tree.
      if (null != root.getChild(leftChar)) {
        return searchWord(root.getChild(leftChar), Util.substringAfterFirstIndex(word));
      }
      return null;
    } else if (leftChar == -1) {
      // -1 indicates end of word, we reach this point after having found legitimate values
      // in the parent nodes for each character in the word. TODO (anupam) It will fail when user
      // searches for empty string.
      return root.getMeaning();
    }
    return null;
  }

  /**
   * Class representing a node in the trie. Each node's index in the parent's children array
   * represents the character value.
   */
  public static class Node implements Serializable {
    public Node() {
      initNode();
    }

    private final List<Node> childern = new ArrayList<Node>();
    private String meaning;

    private void initNode() {
      for (int i = 0; i < 256; i++) {
        this.childern.add(i, null);
      }
    }

    private void setMeaning(String meaning) {
      this.meaning = meaning;
    }

    public String getMeaning() {
      return this.meaning;
    }

    public void addChild(int index, Node node) {
      this.childern.set(index, node);
    }

    public Node getChild(int index) {
      return childern.get(index);
    }

    public List<Node> getChildern() {
      return childern;
    }

    @Override
    public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append(" start hash : ").append(this.hashCode());
      for (int i = 0; i < childern.size(); i++) {
        if (null != childern.get(i)) {
          sb.append(" index : " + i + " : ").append((char) (i)).append(", ");
        }
      }
      sb.append(" meaning : ").append(this.meaning).append(", end hash : ").append(this.hashCode());
      return sb.toString();
    }
  }
}
