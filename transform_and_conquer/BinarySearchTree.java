import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BinarySearchTree{

    class Node{
      int key;
      Node left, right;

      public Node(int key){
        this.key = key;
        this.left = this.right = null;
      }
    }

    Node root;

    BinarySearchTree(){
      root = null;
    }

    void add(int key){
      root = addRecursive(root, key);
    }

    Node addRecursive(Node node, int key){
      if(node == null){
        node = new Node(key);
        return node;
      }
      else{
        if(key < node.key){
          node.left = addRecursive(node.left, key);
        }
        else if(key > node.key){
          node.right = addRecursive(node.right, key);
        }
        return node;
      }
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

    int maxLevel(Node node){
        if (node == null){
          return 0;
        }
        return Math.max(maxLevel(node.left), maxLevel(node.right))+1;
    }


    void printNode(){
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
        BinarySearchTree tree = new BinarySearchTree();

        /* creando el siguiente arbol

                100
              /     \
            60      140
          /   \     / \
        40    80  120 160
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

        System.out.println("*********");

        tree = new BinarySearchTree();

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
