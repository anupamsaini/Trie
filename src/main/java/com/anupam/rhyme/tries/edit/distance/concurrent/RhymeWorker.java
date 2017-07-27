package com.anupam.rhyme.tries.edit.distance.concurrent;

import com.google.common.collect.Lists;

import com.anupam.rhyme.tries.edit.distance.RhymeEngine;
import com.anupam.rhyme.tries.patricia.NodeEntry;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * The worker for searching the subsection of a trie for rhymes.
 */
public class RhymeWorker implements Callable<List<String>> {

  private static final Logger log = Logger.getLogger(RhymeWorker.class);

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
    int indexInRoot = searchBatch.getIndexInRootNode();
    List<String> rhymes = Lists.newLinkedList();
    for (int i = indexInRoot; i < indexInRoot + Runtime.getRuntime().availableProcessors()
        && i < rootNode.getChildren().size(); i++) {
      long time = System.currentTimeMillis();
      rhymeEngine.setRootNode(rootNode.getChildren().get(i));
      rhymes = rhymeEngine.generateRhymes(searchBatch.getWord());
      log.debug("Worked on the tree path : " + rootNode.getChildren().get(i).getKey()
          + ", Time consumed : " + (System.currentTimeMillis() - time));
    }
    return rhymes;
  }
}
