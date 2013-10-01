package com.anupam.java.tries.edit.distance.concurrent;


/**
 * The wrapper class defining the task to be performed by {@link RhymeWorker}.
 */
public class SearchBatch {

  /**
   * The word whose rhymes are to be searched.
   */
  private final String word;

  /**
   * The index in the childern list of root node. It basically represents the subpath that would be
   * queried for rhymes.
   */
  private final int indexInRootNode;

  /**
   * The maximum levenshtein distance between words.
   */
  private final int levDistance;

  public SearchBatch(String word, int levDist, int indexInRootNode) {
    this.word = word;
    this.indexInRootNode = indexInRootNode;
    this.levDistance = levDist;
  }

  public String getWord() {
    return word;
  }

  public int getIndexInRootNode() {
    return indexInRootNode;
  }

  public int getLevDistance() {
    return levDistance;
  }
}
