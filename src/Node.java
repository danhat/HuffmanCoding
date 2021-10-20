/**
 * Represents a heap node
 * @author Danielle Hatten
 */
public class Node {
  private char ch;
  private int freq;
  //private Node parent;
  private Node left;
  private Node right;
  private Boolean isChar;
  //private int val; // 0 or 1
  private String name;


  //public Node() {

  //}

  /**
   * creates a Node with the specified character and frequency of the character
   * @param c Nodes's character
   * @param f Node's frequency
   */
  public Node(char c, int f) {
    ch = c;
    freq = f;
    isChar = true;
  }

  /**
   * create a Node for internal nodes (nodes without a char)
   * @param f Node's new frequency (sum of left and right nodes)
   * @param l left Node
   * @param r right Node
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
   * @return a char representing the Node's character value
   */
  public char getCh() {
    return ch;
  }

  /**
   * get frequency of the node
   * @return int representing the Node's frequency
   */
  public int getFreq() {
    return freq;
  }

  //public void setLeft(Node n) { left = n; }
  //public void setRight(Node n) { right = n; }

  /**
   * get Node's left Node
   * @return Node
   */
  public Node getLeft() {
    return left;
  }

  /**
   * get Node's right Node
   * @return Node
   */
  public Node getRight() {
    return right;
  }

  /**
   * set the name of the Node, to be used when printing tree
   * @param n
   */
  public void setName(String n) {
    name = n;
  }

  /**
   * get the name of the Node, to be used when printing tree
   * @return
   */
  public String getName() {
    return name;
  }

  /*public void setVal(int i) { val = i; }
  public int getVal() { return val; }*/

  /**
   * get whether the Node has a char value
   * @return true or false
   */
  public boolean isCharacter() {
    return isChar;
  }

}
