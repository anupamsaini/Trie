package com.anupam.rhyme.tries;

import com.anupam.rhyme.tries.simple.NodeEntry;

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

  public static void prettyPrint(NodeEntry root) {
    Queue<NodeEntry> toBePrinted = new LinkedList<NodeEntry>();
    toBePrinted.add(root);
    while (!toBePrinted.isEmpty()) {
      NodeEntry node = toBePrinted.poll();
      if (null != node) {
        System.out.println(node);
        for (NodeEntry childNode : node.getChildern()) {
          toBePrinted.offer(childNode);
        }
      }
    }
  }

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
}