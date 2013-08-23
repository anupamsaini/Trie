package com.anupam.java.tries.patricia;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * A node in the patricia tree.
 */
public class NodeEntry {

  private List<NodeEntry> children = new ArrayList<NodeEntry>();

  // TODO as an optimization move this to a child class , and only nodes with value would be of that
  // type.
  private String value = "";
  private String key = "";
  private int treeDepthLevel = 0;

  public NodeEntry() {}

  public NodeEntry(String key, String value) {
    Preconditions.checkNotNull(value);
    Preconditions.checkNotNull(key);
    this.key = key;
    this.value = value;
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
