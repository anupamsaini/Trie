package com.anupam.java.tries.patricia;



import com.google.common.base.Strings;

import com.anupam.java.tries.Util;

import java.util.ArrayList;

/**
 * The memory efficient implementation of tries.
 */
public class PatriciaTrie {

  private final NodeEntry rootNode;
  private int maxRecursionLevel = 0;
  private int countOfAllocatedNodes = 0;

  public PatriciaTrie(NodeEntry root) {
    this.rootNode = root;
  }

  public NodeEntry getRoot() {
    return this.rootNode;
  }

  public int getCountOfAllocatedNodes() {
    return countOfAllocatedNodes;
  }

  /**
   * Searches the {@link NodeEntry} parameter's children for a child node that fully or partially
   * matches with the key to be inserted. There will be only one such child node.
   *
   * @param key The key to be inserted in the trie.
   * @param node The {@link NodeEntry} node whose immediate children would be searched.
   * @return The number of characters of key that matched with a child node or 0 if no such child
   *         node is found.
   */
  private MatchingNodeWrapper countMatchingChars(String key, NodeEntry node) {
    int charMatchCount = 0;
    for (NodeEntry child : node.getChildren()) {
      if ((charMatchCount = Util.getMatchingCharacterCount(child.getKey(), key)) > 0) {
        return new MatchingNodeWrapper(child, charMatchCount);
      }
    }
    return new MatchingNodeWrapper(null, 0);
  }

  /**
   * Insert a new child node to the node when none of the key's characters, match with the node's
   * key characters.
   *
   * @param parentNode The parent node at which the child node would be added.
   * @param key The child node's key.
   * @param value The child node's value.
   * @return boolean Indicates the insert operation's status.
   */
  private boolean insertWhenNoCharsMatch(NodeEntry parentNode, String key, String value) {
    ++countOfAllocatedNodes;
    return parentNode.addChildNode(new NodeEntry(key, value, true));
  }

  /**
   * Inserts a new node into the trie.
   *
   * @param key The key to be inserted.
   * @param value The value or meaning of the word.
   * @return The insert operation's status.
   */
  public boolean insertWord(String key, String value) {
    MatchingNodeWrapper matchingNodeWrapper = countMatchingChars(key, rootNode);
    if (matchingNodeWrapper.hasNode()) {
      return insertWord(rootNode, key, value, 1);
    }
    return insertWhenNoCharsMatch(rootNode, key, value);
  }

  private boolean insertWord(NodeEntry node, String key, String value, int recursionLevel) {
    MatchingNodeWrapper matchingNodeWrapper = countMatchingChars(key, node);
    int matchingChars = matchingNodeWrapper.getMatchingCharsCount();

    if (matchingChars == 0) {
      if (maxRecursionLevel < recursionLevel) {
        maxRecursionLevel = recursionLevel;
        // System.out.println("Tree depth " + recursionLevel);
      }
      return insertWhenNoCharsMatch(node, key, value);
    } else if (key.equalsIgnoreCase(matchingNodeWrapper.getNode().getKey())) {
      node.setValue(value);
      node.setIsWord(true);
      return true;
    }

    /*
     * Split the key into two parts, One containing the string with matching chars and the other
     * with remaining chars.
     */
    NodeEntry matchingNode = matchingNodeWrapper.getNode();
    String subString = matchingNode.getKey().substring(matchingChars);
    /*
     * In case of empty substring, insert the remaining portion of the key as the child of matching
     * node.
     */
    if (Strings.isNullOrEmpty(subString)) {
      return insertWord(matchingNode, key.substring(matchingChars), value, ++recursionLevel);
    }

    NodeEntry childNode = new NodeEntry(subString, matchingNode.getValue(), true);
    childNode.setChildren(matchingNode.getChildren());
    matchingNode.setKey(matchingNode.getKey().substring(0, matchingChars));
    matchingNode.setValue("");
    matchingNode.setChildren(new ArrayList<NodeEntry>());
    matchingNode.addChildNode(childNode);
    ++countOfAllocatedNodes;

    return insertWord(matchingNode, key.substring(matchingChars), value, ++recursionLevel);
  }

  /**
   * Searches the key in the dictonary.
   *
   * @param key The key to be searched.
   * @return The flag indicating search operation's status.
   */
  public boolean searchWord(String key) throws IllegalStateException {
    return searchWord(key, rootNode);
  }

  private boolean searchWord(String key, NodeEntry node) throws IllegalStateException {
    MatchingNodeWrapper matchingNodeWrapper = countMatchingChars(key, node);
    if (null == matchingNodeWrapper.getNode()) {
      return false;
    } else if (matchingNodeWrapper.getMatchingCharsCount() == key.length()) {
      if (!matchingNodeWrapper.hasNode()) {
        throw new IllegalStateException("Keys matched, but node is not ending a word.");
      }
      return true;
    } else {
      String subString = key.substring(matchingNodeWrapper.getMatchingCharsCount());
      return searchWord(subString, matchingNodeWrapper.getNode());
    }
  }

  /**
   * Wrapper for a node that partially or fully matches with the key to be inserted.
   */
  public static class MatchingNodeWrapper {

    // Node fully or partially matching the key.
    private final NodeEntry node;
    // The number of characters that matched.
    private final int matchingCharsCount;

    public MatchingNodeWrapper(NodeEntry node, int count) {
      this.node = node;
      this.matchingCharsCount = count;
    }

    public NodeEntry getNode() {
      return node;
    }

    public int getMatchingCharsCount() {
      return matchingCharsCount;
    }

    public boolean hasNode() {
      return null == node ? false : true;
    }

    @Override
    public String toString() {
      return "matchingChars : " + matchingCharsCount;
    }
  }
}
