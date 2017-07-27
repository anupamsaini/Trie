package com.anupam.rhyme.tries.edit.distance;

import java.io.IOException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;

/**
 * Container for command line arguments.
 */
public class CliArguments {

  @Option(name = "-m",
      required = false,
      usage = "Represents the mode of operation"
          + "1) Single threaded with value 's' "
          + "2) Concurrent with value 'c'"
          + "3) All mode with value 'a', generate ryhmes for all the words in seed words list")
  protected char mode = 'c';

  @Option(name = "-e", aliases = "edit-distance",
      usage = "The edit distance of the generated rhymes.")
  protected int editDistance = 2;

  public CliArguments(String[] args) throws IOException {
    doMain(args);
  }

  private void doMain(String[] args) throws IOException {
    CmdLineParser parser = new CmdLineParser(this, ParserProperties.defaults().withUsageWidth(100));

    try {
      // parse the arguments.
      parser.parseArgument(args);
    } catch (CmdLineException e) {
      // if there's a problem in the command line,
      // you'll get this exception. this will report
      // an error message.
      System.err.println(e.getMessage());
      System.err.println("java SampleMain [options...] arguments...");
      // print the list of available options
      parser.printUsage(System.err);
      System.err.println();
    }
  }
}
