package com.anupam.java.tries.patricia;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * Utility class for common string manipulation functions.
 */
public class Util {

  /**
   * Generates a count of characters matching between two strings.
   *
   * @param to The first string.
   * @param from The second string.
   * @return The count of matched characters.
   */
  public static int getMatchingCharacterCount(String to, String from) {
    int matchingCharCount = 0;
    for (int i = 0; i < Math.min(to.length(), from.length()); i++) {
      if (to.charAt(i) != from.charAt(i)) {
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
