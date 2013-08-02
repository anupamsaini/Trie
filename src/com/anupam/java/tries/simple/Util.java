package com.anupam.java.tries.simple;

import com.anupam.java.tries.simple.Trie.Node;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Utility class for String manipulation functions.
 */
public class Util {

  /**
   * Extracts the first character in a string.
   *
   * @param word The string from which the character is to be fetched.
   * @return The integer value of the char & -1 in case of null or empty string.
   */
  public static int getLeftMostChar(String word) {
    if (null == word || word.isEmpty()) {
      return -1;
    }
    return word.toCharArray()[0];
  }

  /**
   * Validates if the provided value is a legal ASCII English character.
   *
   * @param value The value to be validated
   * @return The boolean result of the check.
   */
  public static boolean isValidAsciiChar(int value) {
    return (value > 64 && value < 91) || (value > 96 && value < 123) ? true : false;
  }

  /**
   * Omits the first character and returns the remaining string.
   *
   * @param value The string to be split.
   * @return The extracted string.
   */
  public static String substringAfterFirstIndex(String value) {
    if (null == value || value.isEmpty()) {
      return "";
    }
    return value.substring(1);
  }

  public static void prettyPrint(Node root) {
    Queue<Node> toBePrinted = new LinkedList<Node>();
    toBePrinted.add(root);
    while (!toBePrinted.isEmpty()) {
      Node node = toBePrinted.poll();
      if (null != node) {
        System.out.println(node);
        for (Node childNode : node.getChildern()) {
          toBePrinted.offer(childNode);
        }
      }
    }
  }
}
