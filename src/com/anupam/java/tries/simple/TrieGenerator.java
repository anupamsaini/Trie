package com.anupam.java.tries.simple;

import com.anupam.java.tries.simple.Trie.Node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Common methods for generating a English language based trie.
 */
public class TrieGenerator {

  public static List<String> readWords(String fName) {
    String LineSperator = System.getProperty("line.separator");
    String line;
    List<String> repo = new ArrayList<String>();
    FileReader fr = null;
    BufferedReader bfr = null;
    try {
      fr = new FileReader(fName);
      bfr = new BufferedReader(fr);
      int counter = 0;
      while (null != (line = bfr.readLine())) {
        repo.add(line);
      }

      fr.close();
      bfr.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      try {
        fr.close();
        bfr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return repo;
  }

  public static void populateTrie(Trie trie, Node root, String wordSource) {
    for (String word : readWords(wordSource)) {
      if (!trie.addNode(root, word, "no-mean")) {
        System.err.println("Unable to add word : " + word);
      }
    }
    // serializeTrie(root,serializedFileName);
  }

  public static void serializeTrie(Node root, String path) {
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(new FileOutputStream(path));
      oos.writeObject(root);
    } catch (FileNotFoundException e) {
      // TODO(anupams): Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO(anupams): Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (null != oos) {
        try {
          oos.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
