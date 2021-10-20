import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * represents a min binary heap
 * @author Danielle Hatten
 */
public class MinHeap {
  private List<Node> heap = new ArrayList<Node>();
  private Node root;

  /**
   * creates a min heap from sorted frequencies LinkedHashMap
   * @param frequencies
   */
  public MinHeap(LinkedHashMap<Character, Integer> frequencies) {
    for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
      char ch_ = entry.getKey();
      int freq_ = entry.getValue();

      Node n = new Node(ch_, freq_);
      heap.add(n);
    }
  }

  /**
   * print a simple visualization of the min heap
   */
  public void printMinHeap() {
    for (int i = 0; i <= heap.size(); i++) {
      for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= heap.size(); j++) {
        System.out.print(heap.get(j + (int)Math.pow(2, i) - 1).getCh() + "-" + heap.get(j + (int)Math.pow(2, i) - 1).getFreq() + " ");
      }
      System.out.println();
    }
  }

  /**
   * insert a passed Node into the heap based on the Node's frequency
   * @param newNode
   */
  public void insertNode(Node newNode) {
    for (int i = 0; i < heap.size(); i++) {
      // if at end of heap, put newNode at end and break
      if (i == heap.size() - 1) {
        heap.add(newNode);
        break;
      }

      // found place where node should go, insert it there and break
      if ((heap.get(i).getFreq() <= newNode.getFreq()) && (heap.get(i + 1).getFreq() >= newNode.getFreq())) {
        // put newNode between these two and then break from loop
        int pos = i + 1;
        heap.add(pos, newNode);
        break;
      }
    }
  }

  /**
   * remove the front Node (the Node with the lowest frequency)
   */
  public void removeFrontNode() {
    heap.remove(0);
  }

  /**
   * get the size of the heap
   * @return int
   */
  public int getSize() {
    return heap.size();
  }

  /**
   * get node at given position
   * @param index int representing the position of node to get
   * @return Node
   */
  public Node getNode(int index) {
    return heap.get(index);
  }

  /**
   * set passed Node as the root
   * @param n
   */
  public void setRoot(Node n) {
    root = n;
  }


}
