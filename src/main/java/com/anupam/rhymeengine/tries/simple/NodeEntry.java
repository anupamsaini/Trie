package com.anupam.rhymeengine.tries.simple;

import com.anupam.rhymeengine.tries.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Node for storing the data.
 */
public class NodeEntry implements Node{

  public NodeEntry() {
    initNode();
  }

  private final List<NodeEntry> childern = new ArrayList<NodeEntry>();
  private String meaning;

  private void initNode() {
    for (int i = 0; i < 256; i++) {
      this.childern.add(i, null);
    }
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public String getMeaning() {
    return this.meaning;
  }

  public void addChild(int index, NodeEntry node) {
    this.childern.set(index, node);
  }

  public NodeEntry getChild(int index) {
    return childern.get(index);
  }

  public List<NodeEntry> getChildern() {
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
