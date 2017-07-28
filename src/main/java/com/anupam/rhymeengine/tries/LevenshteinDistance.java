package com.anupam.rhymeengine.tries;


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
   * @param toSearch The target string.
   * @param probableMatch The other string to be compared with.
   * @return The edit distance between the strings.
   */
  public static int editDistance(String toSearch, String probableMatch) {
    int l1 = toSearch.length();
    int l2 = probableMatch.length();
    int arr[][] = new int[l1 + 1][l2 + 1];

    for (int i = 0; i <= l1; i++) {
      arr[i][0] = i;
    }
    for (int j = 0; j <= l2; j++) {
      arr[0][j] = j;
    }

    for (int i = 1; i <= l1; i++) {
      for (int j = 1; j <= l2; j++) {
        char c1 = toSearch.charAt(i - 1);
        char c2 = probableMatch.charAt(j - 1);

        if (c1 == c2) {
          arr[i][j] = arr[i - 1][j - 1];
        } else {
          arr[i][j] = Math.min(
              Math.min(arr[i - 1][j], arr[i][j - 1]),
              arr[i - 1][j - 1]) + 1;
        }
      }
    }
    return arr[l1][l2];
  }

  private static byte min(byte a, byte b) {
    return (a <= b) ? a : b;
  }

  /**
   * To calculate the edit distance of a character, Immediate last row in the eit distance matrix is
   * needed. Thus the need for maintainig a matrix of edit distances for each character is
   * eliminated.
   * <p>
   * For instance incremental edit distance between "abh" and "test" at chracter 'a' would be
   * [0,1,2,3,4], at the next 'b' it would be [1,1,2,3,4]. The edit distance for 'b' used the edit
   * distances calculated for the 'a' character. Similarily the edit distance for character 'h' only
   * used the edit distance calculated for 'b'.
   * </p>
   *
   * @param partialMatch The partial string that is to be matched against a word.
   * @param toSearch The word to be matched against.
   * @param frame The int array containing edit distance of last character of previously matched
   *        part of the string.
   * @return A byte array containing the edit distance at the last character in the partial word to
   *         be matched.
   */
  public static byte[] editDistance(String partialMatch, String toSearch, byte[] frame) {

    int lengthSuffixString = partialMatch.length();
    if (lengthSuffixString == 0) {
      return frame;
    }
    byte[] frameCopy = new byte[frame.length];
    System.arraycopy(frame, 0, frameCopy, 0, frame.length);
    byte[] nextFrame = new byte[frameCopy.length];

    for (int i = 1; i <= lengthSuffixString; i++) {
      nextFrame[0] = ++frameCopy[0];
      for (int j = 1; j <= toSearch.length(); j++) {
        char c1 = partialMatch.charAt(i - 1);
        char c2 = toSearch.charAt(j - 1);
        if (c1 == c2) {
          nextFrame[j] = frameCopy[j - 1];
        } else {
          byte result = min(min(nextFrame[j - 1], frameCopy[j]), frameCopy[j - 1]);
          nextFrame[j] = ++result;
        }
      }
      System.arraycopy(nextFrame, 0, frameCopy, 0, frameCopy.length);
    }
    return nextFrame;
  }

  /**
   * Uses int as frame type instead of byte.
   */
  public static int[] editDistance(String partialMatch, String toSearch, int[] frame) {

    int lengthSuffixString = partialMatch.length();
    if (lengthSuffixString == 0) {
      return frame;
    }
    int[] frameCopy = new int[frame.length];
    System.arraycopy(frame, 0, frameCopy, 0, frame.length);
    int[] nextFrame = new int[frameCopy.length];

    for (int i = 1; i <= lengthSuffixString; i++) {
      nextFrame[0] = ++frameCopy[0];
      for (int j = 1; j <= toSearch.length(); j++) {
        char c1 = partialMatch.charAt(i - 1);
        char c2 = toSearch.charAt(j - 1);
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
