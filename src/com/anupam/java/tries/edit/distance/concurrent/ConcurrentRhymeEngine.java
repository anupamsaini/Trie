package com.anupam.java.tries.edit.distance.concurrent;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import com.anupam.java.tries.patricia.NodeEntry;
import com.anupam.java.tries.patricia.PatriciaTrie;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Concurrently searches a subsection of the root node {@link NodeEntry} for the rhymes.
 */
public class ConcurrentRhymeEngine {

  /**
   * The tree representation of the words dictionary.
   */
  private final PatriciaTrie pTrie;

  private final ExecutorService service;

  // TODO : Processor core count affected by hyper threading, might not be right way on Android
  // phones.
  private final int poolSize = Runtime.getRuntime().availableProcessors();
  /**
   * The maximum allowed distance between two strings.
   */
  private int levDistance;

  public ConcurrentRhymeEngine(PatriciaTrie pTire, int levDistance) {
    this.pTrie = pTire;
    this.levDistance = levDistance;
    service = Executors.newFixedThreadPool(poolSize, new ThreadFactoryBuilder().setDaemon(false)
        .setNameFormat(ConcurrentRhymeEngine.class.getSimpleName() + " -%s").build());
  }

  public void setLevDistance(int levDistance) {
    this.levDistance = levDistance;
  }

  /*
   * Shuts down the executor.
   */
  public void shutDown() {
    service.shutdown();
  }

  /**
   * Generates a list of words rhyming with the given word.
   *
   * @param word The word whose rhymes are to be generated.
   * @return A list of words rhyming with the given word.
   */
  public List<String> generateRhymes(String word) {
    List<Future<List<String>>> rhymes = Lists.newArrayList();

    for (int i = 0; i < this.pTrie.getRoot().getChildren().size(); i++) {
      rhymes.add(service.submit(
          new RhymeWorker(pTrie.getRoot(), new SearchBatch(word, this.levDistance, i))));
    }

    List<String> collatedOutput = Lists.newArrayList();
    for (Future<List<String>> data : rhymes) {
      try {
        collatedOutput.addAll(data.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    return collatedOutput;
  }
}