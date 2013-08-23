package com.anupam.java.tries.patricia;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * Utility class for common string manipulation functions.
 */
public class Util {

  public static int getMatchingCharacterCount(String key1, String key2) {
    int matchingCharCount = 0;
    for (int i = 0; i < Math.min(key1.length(), key2.length()); i++) {
      if (key1.charAt(i) != key2.charAt(i)) {
        return matchingCharCount;
      }
      ++matchingCharCount;
    }
    return matchingCharCount;
  }

  public static void breadthFirstSearch(Queue<NodeEntry> parentQueue) {
    Queue<NodeEntry> childQueue = new ArrayDeque<NodeEntry>();
    StringBuilder sb = new StringBuilder();
    while (!parentQueue.isEmpty()) {
      NodeEntry head = parentQueue.poll();
      sb.append(head.getKey()).append(" : ");
      childQueue.addAll(head.getChildren());
    }
    System.out.println(sb);
    breadthFirstSearch(childQueue);
  }
}
