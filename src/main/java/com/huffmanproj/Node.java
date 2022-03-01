package com.huffmanproj;

/**
 * Represents a heap node
 * @author Danielle Hatten
 */
public class Node {
  private char ch;
  private int freq;
  //private com.huffmanproj.Node parent;
  private Node left;
  private Node right;
  private Boolean isChar;
  //private int val; // 0 or 1
  private String name;



  /**
   * creates a com.huffmanproj.Node with the specified character and frequency of the character
   * @param c Nodes's character
   * @param f com.huffmanproj.Node's frequency
   */
  public Node(char c, int f) {
    ch = c;
    freq = f;
    isChar = true;
  }

  /**
   * create a com.huffmanproj.Node for internal nodes (nodes without a char)
   * @param f com.huffmanproj.Node's new frequency (sum of left and right nodes)
   * @param l left com.huffmanproj.Node
   * @param r right com.huffmanproj.Node
   */
  public Node(int f, Node l, Node r) {
    freq = f;
    left = l;
    right = r;
    isChar = false;
  }

  /**
   * set the frequency of the node
   * @param f
   */
  //public void setFreq(int f) {
    //freq = f;
  //}

  /**
   * get the character of the node
   * @return a char representing the com.huffmanproj.Node's character value
   */
  public char getCh() {
    return ch;
  }

  /**
   * get frequency of the node
   * @return int representing the com.huffmanproj.Node's frequency
   */
  public int getFreq() {
    return freq;
  }

  //public void setLeft(com.huffmanproj.Node n) { left = n; }
  //public void setRight(com.huffmanproj.Node n) { right = n; }

  /**
   * get com.huffmanproj.Node's left com.huffmanproj.Node
   * @return com.huffmanproj.Node
   */
  public Node getLeft() {
    return left;
  }

  /**
   * get com.huffmanproj.Node's right com.huffmanproj.Node
   * @return com.huffmanproj.Node
   */
  public Node getRight() {
    return right;
  }

  /**
   * set the name of the com.huffmanproj.Node, to be used when printing tree
   * @param n
   */
  public void setName(String n) {
    name = n;
  }

  /**
   * get the name of the com.huffmanproj.Node, to be used when printing tree
   * @return
   */
  public String getName() {
    return name;
  }

  /*public void setVal(int i) { val = i; }
  public int getVal() { return val; }*/

  /**
   * get whether the com.huffmanproj.Node has a char value
   * @return true or false
   */
  public boolean isCharacter() {
    return isChar;
  }

}
