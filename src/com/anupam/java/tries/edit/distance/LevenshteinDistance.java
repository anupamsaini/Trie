package com.anupam.java.tries.edit.distance;

/**
 * Calculates the edit distance between two strings.
 * <p>
 * The edit distance between two strings is the minimum number of deletion, insertion or
 * substitution operations required to convert one string to another.
 * </p>
 */
public class LevenshteinDistance {

  /**
   * Calculates the edit distance between two strings.
   *
   * @param target The target string.
   * @param other The other string to be compared with.
   * @return The edit distance between the strings.
   */
  public static int calculateEditDistance(String target, String other) {
    int l1 = target.length();
    int l2 = other.length();
    int arr[][] = new int[l1 + 1][l2 + 1];

    for (int i = 0; i < l1 + 1; i++) {
      arr[i][0] = i;
    }
    for (int j = 0; j < l2 + 1; j++) {
      arr[0][j] = j;
    }

    for (int i = 1; i < l1 + 1; i++) {
      for (int j = 1; j < l2 + 1; j++) {
        char c1 = target.charAt(i - 1);
        char c2 = other.charAt(j - 1);

        if (c1 == c2) {
          arr[i][j] = arr[i - 1][j - 1];
        } else {
          arr[i][j] = Math.min(Math.min(arr[i - 1][j], arr[i][j - 1]), arr[i - 1][j - 1]) + 1;
        }
      }
    }
    return arr[l1][l2];
  }
}
