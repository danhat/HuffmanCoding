

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Encodes and decodes text from user input or file
 * @author Danielle Hatten
 */
public class HuffmanCoding {

  private String data;
  private LinkedHashMap<Character, Integer> frequencies; //sorted
  private HashMap<Character, String> codes;
  private String huffmanCode;

  /**
   * initialises variables needed from encoding given text
   */
  HuffmanCoding() {
    frequencies = new LinkedHashMap<Character, Integer>();
    codes = new HashMap<Character, String>();
    huffmanCode = "";
  }

  /**
   * read user input and store string as data
   */
  public void readUserInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter string delimited by two new lines: ");
    String s = "";

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String line2 = scanner.nextLine();

      if (line.isEmpty() && line2.isEmpty()) {
        break;
      }
      s = s + line + line2;

    }

    data = s;
  }

  /**
   * @todo
   * read text from text file entered by user and store as data
   */
  public void getAndReadFile() {
    System.out.println("reading from file");
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
   * create and store codes of each character recursively
   * @param n Node to create code for
   * @param code String to represent current code
   */
  public void setCodes(Node n, String code) {
    while (n.getLeft() == null && n.getRight() == null && n.isCharacter() == true) {
      codes.put(n.getCh(), code);
      return;
    }

    if (n.getLeft() != null) {
      setCodes(n.getLeft(), code + "0");
    }
    if (n.getRight() != null) {
      setCodes(n.getRight(), code + "1");
    }

  }

  /**
   * print codes HashMap
   */
  public void printCodesTable() {
    System.out.println("\nCharacter | Code");
    System.out.println("---------------------");

    for (var ch : codes.entrySet()) {
      System.out.println("\t " + ch.getKey() + " \t | \t\t" + ch.getValue());
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
   * decodes huffman code
   * @return decoded text
   */
  public String decode() {
    // reverse codes hashmap
    Map<String, Character> reversedCodes = new HashMap<>();
    for (Map.Entry<Character, String> entry : codes.entrySet()) {
      reversedCodes.put(entry.getValue(), entry.getKey());
    }

    String decodedText = "";

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

    return decodedText;
  }


  public static void main(String[] args) {
    System.out.println("Huffman Coding!\n");

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter 1 to enter a statement or 2 to enter a file: ");

    String choice = scanner.next();

    HuffmanCoding hf = new HuffmanCoding();

    if (choice.equals("1")) {
      hf.readUserInput();
    }
    else if (choice.equals("2")) {
      hf.getAndReadFile();
    }
    else {
      System.out.println("Input not recognized");
    }

    scanner.close();

    hf.getAndStoreFrequencies();
    hf.printFrequenciesTable();

    MinHeap heap = new MinHeap(hf.frequencies);

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

    hf.setCodes(heap.getNode(0), "");
    hf.printCodesTable();

    hf.setHuffmanCode();
    hf.printHuffmanCode();

    String decoded = hf.decode();
    System.out.println("\nThe decoded text is: " + decoded);



  }


}
