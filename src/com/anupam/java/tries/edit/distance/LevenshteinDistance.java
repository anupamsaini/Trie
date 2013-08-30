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

    for (int i = 0; i <= l1; i++) {
      arr[i][0] = i;
    }
    for (int j = 0; j <= l2; j++) {
      arr[0][j] = j;
    }

    for (int i = 1; i <= l1; i++) {
      for (int j = 1; j <= l2; j++) {
        char c1 = target.charAt(i - 1);
        char c2 = other.charAt(j - 1);

        if (c1 == c2) {
          arr[i][j] = arr[i - 1][j - 1];
        } else {
          arr[i][j] = Math.min(Math.min(arr[i - 1][j], arr[i][j - 1]), arr[i - 1][j - 1]) + 1;
        }
      }
    }
    // StringBuilder sb = new StringBuilder();
    // for(int i = 0 ; i <= l1;i++) {
    // for(int j=0;j<=l2;j++) {
    // sb.append(arr[i][j]).append(",");
    // }
    // sb.append("\n");
    // }
    // System.out.println(sb);
    return arr[l1][l2];
  }

  public static int[] incrementalLevDistance(
      String suffixString, String wordToSearch, int[] frame) {
    int[] frameCopy = new int[frame.length];
    System.arraycopy(frame, 0, frameCopy, 0, frame.length);
    int[] nextFrame = new int[frameCopy.length];
    int lengthSuffixString = suffixString.length();

    for (int i = 1; i <= lengthSuffixString; i++) {
      nextFrame[0] = frameCopy[0] + 1;
      for (int j = 1; j <= wordToSearch.length(); j++) {
        char c1 = suffixString.charAt(i - 1);
        char c2 = wordToSearch.charAt(j - 1);
        if (c1 == c2) {
          nextFrame[j] = frameCopy[j - 1];
        } else {
          nextFrame[j] = Math.min(Math.min(nextFrame[j - 1], frameCopy[j]), frameCopy[j - 1]) + 1;
        }
      }
      System.arraycopy(nextFrame, 0, frameCopy, 0, frameCopy.length);
    }
    return nextFrame;
  }
}
