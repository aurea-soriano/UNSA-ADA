//TreeMap -> Red Black TreeMap
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class RedBlackTree {
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  class Node{
     int key;
     Node left, right;
     boolean color; // el color del padre
     int size; // el tamanho del subarbol

     public Node(int key, boolean color, int size){
       this.key = key;
       this.right = this.left = null;
       this.color = color;
       this.size = size;

     }
  }

  Node root;

  RedBlackTree(){
    root = null;
  }

  // funciones utilitarias

  boolean isRed(Node x){
    if(x == null) return false;
    return x.color == RED;
  }

  int size(Node x){
    if(x == null) return 0;
    return x.size;
  }

  int size(){
    return size(root);
  }

  boolean isEmpty(){
    return root == null;
  }


  Node rightRotation(Node h){
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = x.right.color;
    x.right.color = RED;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;
  }

  Node leftRotation(Node h){
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = x.left.color;
    x.left.color = RED;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;

  }

  void flipColors(Node h){
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
  }


  Node redLeftMovement(Node h){
    flipColors(h);
    if (isRed(h.right.left)){
      h.right = rightRotation(h.right);
      h = leftRotation(h);
      flipColors(h);
    }
    return h;
  }


  Node redRightMovement(Node h){
    flipColors(h);
    if (isRed(h.left.left)){
      h = rightRotation(h);
      flipColors(h);
    }
    return h;
  }


  Node balance(Node h) {
        if (isRed(h.right))                      h = leftRotation(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rightRotation(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
  }


  void add(int key){
    root = addRecursive(root, key);
  }

  Node addRecursive(Node node, int key){
    if(node == null){
      node = new Node(key, RED, 1);
      return  node;
    }

    if(key < node.key){
      node.left  = addRecursive(node.left, key);
    }
    else if(key > node.key){
      node.right = addRecursive(node.right, key);
    }
    // fix-up any right-leaning links
      if (isRed(node.right) && !isRed(node.left))      node = leftRotation(node);
      if (isRed(node.left)  &&  isRed(node.left.left)) node = rightRotation(node);
      if (isRed(node.left)  &&  isRed(node.right))     flipColors(node);
      node.size = size(node.left) + size(node.right) + 1;

      return node;
  }


  void printInOrder(){
    printInOrderRecursive(root);
  }

  void printInOrderRecursive(Node node){
    if(node != null){
      printInOrderRecursive(node.left);
      System.out.println(node.key);
      printInOrderRecursive(node.right);
    }
  }

  int maxLevel(Node node) {
      if (node == null)
          return 0;

      return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
  }
  void printNode() {
     int maxLevel = maxLevel(root);

     printNodeInternal(Collections.singletonList(root), 1, maxLevel);
 }
boolean isAllElementsNull(List<Node> list) {
      for (Node node : list) {
          if (node != null)
              return false;
      }

      return true;
  }
  void printWhitespaces(int count) {
      for (int i = 0; i < count; i++)
          System.out.print(" ");
  }
 void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
      if (nodes.isEmpty() || isAllElementsNull(nodes))
          return;

      int floor = maxLevel - level;
      int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
      int firstSpaces = (int) Math.pow(2, (floor)) - 1;
      int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

      printWhitespaces(firstSpaces);

      List<Node> newNodes = new ArrayList<Node>();
      for (Node node : nodes) {
          if (node != null) {
              System.out.print(node.key);
              newNodes.add(node.left);
              newNodes.add(node.right);
          } else {
              newNodes.add(null);
              newNodes.add(null);
              System.out.print(" ");
          }

          printWhitespaces(betweenSpaces);
      }
      System.out.println("");

      for (int i = 1; i <= endgeLines; i++) {
          for (int j = 0; j < nodes.size(); j++) {
              printWhitespaces(firstSpaces - i);
              if (nodes.get(j) == null) {
                  printWhitespaces(endgeLines + endgeLines + i + 1);
                  continue;
              }

              if (nodes.get(j).left != null)
                  System.out.print("/");
              else
                  printWhitespaces(1);

              printWhitespaces(i + i - 1);

              if (nodes.get(j).right != null)
                  System.out.print("\\");
              else
                  printWhitespaces(1);

              printWhitespaces(endgeLines + endgeLines - i);
          }

          System.out.println("");
      }

      printNodeInternal(newNodes, level + 1, maxLevel);
  }





  public static void main(String[] args){
    RedBlackTree tree = new RedBlackTree();

    /* creando el siguiente arbol

            100
          /     \
        60      140
      /   \     /  \
    40    80  120  160
    */

    tree.add(100);
    tree.add(60);
    tree.add(40);
    tree.add(80);
    tree.add(140);
    tree.add(120);
    tree.add(160);

    tree.printInOrder();
    tree.printNode();

    System.out.println("*************");


    /* creando el siguiente arbol

            40
               \
                 60
                  \
                  80
                    \
                      100
                        \
                        120
                         \
                          140
                            \
                            160
    */
    tree = new RedBlackTree();

    tree.add(40);
    tree.add(60);
    tree.add(80);
    tree.add(100);
    tree.add(120);
    tree.add(140);
    tree.add(160);

    tree.printInOrder();
    tree.printNode();
  }




}