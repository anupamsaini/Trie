package com.anupam.rhyme.tries.patricia;

import com.google.common.base.Preconditions;

import com.anupam.rhyme.tries.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A node in the patricia tree.
 */
public class NodeEntry implements Node {

  private List<NodeEntry> children = new ArrayList<NodeEntry>();

  // TODO as an optimization move this to a child class , and only nodes with value would be of that
  // type.
  private String value = "";
  private String key = "";
  private int treeDepthLevel = 0;
  private boolean isWord;

  public NodeEntry() {}

  public NodeEntry(String key, String value, boolean isWord) {
    Preconditions.checkNotNull(value);
    Preconditions.checkNotNull(key);
    this.key = key;
    this.value = value;
    this.isWord = isWord;
  }

  public void setIsWord(boolean isWord) {
    this.isWord = isWord;
  }

  public boolean hasWord() {
    return isWord;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    Preconditions.checkNotNull(value);
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    Preconditions.checkNotNull(key);
    this.key = key;
  }

  public List<NodeEntry> getChildren() {
    return children;
  }

  public void setChildren(List<NodeEntry> children) {
    this.children = children;
  }

  public boolean addChildNode(NodeEntry childNode) {
    Preconditions.checkNotNull(childNode);
    return this.children.add(childNode);
  }

  public int getTreeDepthLevel() {
    return this.treeDepthLevel;
  }

  public void incTreeDepthLevel() {
    treeDepthLevel = treeDepthLevel + 1;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("childern->");
    for (NodeEntry child : children) {
      sb.append(child.key).append(",");
    }
    return sb.append(" key->").append(key).toString();
  }
}
