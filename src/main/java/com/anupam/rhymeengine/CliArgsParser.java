package com.anupam.rhymeengine;

import java.io.IOException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;

/**
 * Container for command line arguments.
 */
public class CliArgsParser {

  @Option(name = "-m",
      required = false,
      usage = "Represents the mode of operation"
          + "1) 's' Single threaded mode "
          + "2) 'c' Multi threaded, thread count based on processor cores"
          + "3) 'a' Test mode for generating rhymes for words used as trie data")
  protected char mode = 'c';

  @Option(name = "-e", aliases = "edit-distance",
      usage = "The edit distance of the generated rhymes.")
  protected int editDistance = 2;

  public CliArgsParser(String[] args) throws IOException {
    doMain(args);
  }

  private void doMain(String[] args) throws IOException {
    CmdLineParser parser = new CmdLineParser(this, ParserProperties.defaults().withUsageWidth(100));

    try {
      parser.parseArgument(args);
    } catch (CmdLineException e) {
      System.err.println(e.getMessage());
      System.err.println("java SampleMain [options...] arguments...");
      // print the list of available options
      parser.printUsage(System.err);
      System.err.println();
    }
  }
}
