package com.anupam.rhymeengine.concurrent;

import com.anupam.rhymeengine.tries.patricia.NodeEntry;
import com.anupam.rhymeengine.tries.patricia.PatriciaTrie;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;

/**
 * Concurrently searches a subsection of the root node {@link NodeEntry} for the rhymes.
 */
public class ConcurrentRhymeEngine {

  private static final Logger log = Logger.getLogger(ConcurrentRhymeEngine.class);

  /**
   * The tree representation of the words dictionary.
   */
  private final PatriciaTrie pTrie;

  private final ExecutorService service;

  // TODO :anupam Processor core count affected by hyper threading, might not be right way on
  // Android phones.
  private final int poolSize = Runtime.getRuntime().availableProcessors();

  /**
   * The maximum allowed distance between two strings.
   */
  private int levDistance;

  public ConcurrentRhymeEngine(PatriciaTrie pTire, int levDistance) {
    this.pTrie = pTire;
    this.levDistance = levDistance;
    // TODO:anupam Won't work in a case of many concurrent requests. The work queue size should be
    // bounded.
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
    int childrenCount = this.pTrie.getRoot().getChildren().size();
    for (int i = 0; i < childrenCount; i = i + poolSize) {
      rhymes.add(service.submit(
          new RhymeWorker(pTrie.getRoot(), new SearchBatch(word, this.levDistance, i))));
    }

    List<String> collatedOutput = Lists.newArrayList();
    for (Future<List<String>> data : rhymes) {
      try {
        collatedOutput.addAll(data.get());
      } catch (InterruptedException | ExecutionException e) {
        log.error(e);
      }
    }
    return collatedOutput;
  }
}
