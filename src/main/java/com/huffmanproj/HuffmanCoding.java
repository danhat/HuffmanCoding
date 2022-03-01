package com.huffmanproj;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Encodes and decodes text from user input or file
 * @author Danielle Hatten
 */
public class HuffmanCoding {

  private String data;
  private LinkedHashMap<Character, Integer> frequencies; //sorted
  private HashMap<Character, String> codes;
  private String huffmanCode;
  private String decodedText;



  /**
   * initialises variables needed from encoding given text
   */
  HuffmanCoding() {
    frequencies = new LinkedHashMap<Character, Integer>();
    codes = new HashMap<Character, String>();
    huffmanCode = "";
    decodedText = "";
  }



  /**
   * iterate through data and count and store frequecy of each character,
   * and then store the characters and corresponding frequencies in increasing
   * order of frequency
   */
  public void getAndStoreFrequencies() {

    Map<Character, Integer> unsortedFrequencies = new HashMap<Character, Integer>();

    int i = 0;
    while (i < data.length()) {
      char ch = data.charAt(i);
      Integer freq = unsortedFrequencies.get(ch);

      if (freq != null) {
        unsortedFrequencies.put(ch, freq + 1);
      }
      else {
        unsortedFrequencies.put(ch, 1);
      }
      i++;
    }

    // sort frequencies in increasing order
    unsortedFrequencies.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .forEachOrdered(x -> frequencies.put(x.getKey(), x.getValue()));


  }



  /**
   * print chacracters and frequencies
   */
  public void printFrequenciesTable() {
    System.out.println("\nCharacter | Frequency");
    System.out.println("---------------------");

    for (var ch : frequencies.entrySet()) {
       System.out.println("\t " + ch.getKey() + " \t | \t\t" + ch.getValue());
    }
  }


  /**
   *
   */
  public void setCodes() {
    MinHeap heap = new MinHeap(frequencies);

    // loop until one node is left in heap
    while (heap.getSize() > 1) {
      Node n1 = heap.getNode(0);
      Node n2 = heap.getNode(1);

      int newFreq = n1.getFreq() + n2.getFreq();

      Node newNode = new Node(newFreq, n1, n2);

      heap.insertNode(newNode);

      // remove two lowest nodes
      heap.removeFrontNode();
      heap.removeFrontNode();
    }

    setCodesHelper(heap.getNode(0), "");

  }



  /**
   * create and store codes of each character recursively
   * @param n com.huffmanproj.Node to create code for
   * @param code String to represent current code
   */
  public void setCodesHelper(Node n, String code) {
    while (n.getLeft() == null && n.getRight() == null && n.isCharacter() == true) {
      codes.put(n.getCh(), code);
      return;
    }

    if (n.getLeft() != null) {
      setCodesHelper(n.getLeft(), code + "0");
    }
    if (n.getRight() != null) {
      setCodesHelper(n.getRight(), code + "1");
    }

  }



  /**
   * print codes HashMap
   */
  public void printCodesTable() {
    System.out.println("\nCharacter | Code");
    System.out.println("----------------------------");

    for (var ch : codes.entrySet()) {
      if (ch.getKey() == '\n') {
        System.out.printf("%5s     | %-13s%n", "\\n", ch.getValue());
      }
      else if (ch.getKey() == '\t') {
        System.out.printf("%5s     | %-13s%n", "\\t", ch.getValue());
      }
      else {
        System.out.printf("%5s     | %-13s%n", ch.getKey(), ch.getValue());
      }
    }
  }



  /**
   * store huffman code of text
   */
  public void setHuffmanCode() {
    int i = 0;
    while (i < data.length()) {
      char ch = data.charAt(i);
      huffmanCode += codes.get(ch);
      i++;
    }
  }



  /**
   * print huffman code
   */
  public void printHuffmanCode() {
    System.out.println("\nHuffman Code is: " + huffmanCode);
  }



  /**
   * decodes huffman code and stores it in decodedText
   */
  public void decode() {
    // reverse codes hashmap
    Map<String, Character> reversedCodes = new HashMap<>();
    for (Map.Entry<Character, String> entry : codes.entrySet()) {
      reversedCodes.put(entry.getValue(), entry.getKey());
    }

    int i = 0;
    String code = "";
    while (i < huffmanCode.length()) {
      char ch = huffmanCode.charAt(i);
      code = code + ch;

      if (reversedCodes.containsKey(code)) {
        decodedText = decodedText + reversedCodes.get(code);
        code = "";
      }

      i++;
    }
  }


  /**
   *
   */
  public void printDecodedText() {
    System.out.println("\nThe decoded text is: " + decodedText);
  }



  public static void main(String[] args) {
    System.out.println("Huffman Coding!\n");

    ReadInput reader = new ReadInput();
    HuffmanCoding hf = new HuffmanCoding();
    hf.data = reader.readFromUser();

    hf.getAndStoreFrequencies();
    //hf.printFrequenciesTable();

    hf.setCodes();
    hf.printCodesTable();

    hf.setHuffmanCode();
    hf.printHuffmanCode();

    hf.decode();
    hf.printDecodedText();

    reader.close();

  }


}
