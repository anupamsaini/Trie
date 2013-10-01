package com.anupam.java.tries.edit.distance.concurrent;

import com.anupam.java.tries.edit.distance.RhymeEngine;
import com.anupam.java.tries.patricia.NodeEntry;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * The worker for searching the subsection of a trie for rhymes.
 */
public class RhymeWorker implements Callable<List<String>> {

  private final NodeEntry rootNode;
  private final SearchBatch searchBatch;

  public RhymeWorker(NodeEntry rootNode, SearchBatch searchBatch) {
    this.rootNode = rootNode;
    this.searchBatch = searchBatch;
  }

  @Override
  public List<String> call() {
    RhymeEngine rhymeEngine = new RhymeEngine();
    rhymeEngine.setLevDistance(searchBatch.getLevDistance());
    rhymeEngine.setRootNode(rootNode.getChildren().get(searchBatch.getIndexInRootNode()));
    //long time = System.nanoTime();
    List<String> rhymes = rhymeEngine.generateRhymes(searchBatch.getWord());
    // System.out.println("time in this segment " + Thread.currentThread().getName() + " : " +
    // + (System.nanoTime() - time));
    return rhymes;

  }
}
