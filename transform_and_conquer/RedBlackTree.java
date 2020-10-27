//TreeMap es una implementacion RedBlackTree

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RedBlackTree{

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node{
      int key;
      Node left, right;
      boolean color;
      int size; // tamanho de su subarbol


      public Node(int key, boolean color, int size){
        this.key = key;
        this.left = this.right = null;
        this.color = color;
        this.size = size;
      }
    }

    Node root;

    RedBlackTree(){
      root = null;
    }

    // verificacion de nodo si es rojo
    boolean isRed(Node x){
      if(x == null){
        return false;
      }
      return x.color == RED;
    }

    // tamanho del arbol entero
    int size(){
      return size(root);
    }

    //tamanho del subarbol considerando el nodo x
    int size(Node x){
      if(x == null){
        return 0;
      }
      return x.size;
    }

    // verificando si el arbol esta vacio
    boolean isEmpty(){
      return root == null;
    }


    // rotando para la derecha
    Node rightRotation(Node node){
      Node x = node.left;
      node.left = x .right;
      x.right = node;
      x.color = x.right.color;
      x.right.color = RED;
      x.size = node.size;
      node.size = size(node.left) + size(node.right) + 1;
      return x;
    }

    // rotando para la izquierda
    Node leftRotation(Node node){
      Node x = node.right;
      node.right = x .left;
      x.left = node;
      x.color = x.left.color;
      x.left.color = RED;
      x.size = node.size;
      node.size = size(node.left) + size(node.right) + 1;
      return x;
    }


    // si nuestro nodo es rojo y nodo.left y nodo.left.left
    //son negros, vamos a hacer node.left u otro de sus hijos
    //sean rojos

    Node redLeftMovement(Node node){
      changeColors(node);
      if(isRed(node.right.left)){
        node.right = rightRotation(node.right);
        node = leftRotation(node);
        changeColors(node);
      }
      return node;
    }

    // si nodo es rojo y node.right y nodo.right.left
    // son negros, vamos a hacer nodo.right o alguno de sus
    //hijos rojos
    Node redRightMovement(Node node){
      changeColors(node);
      if(isRed(node.left.left)){
        node = rightRotation(node);
        changeColors(node);
      }
      return node;
    }

    void changeColors(Node node){
      node.color = !node.color;
      node.left.color = !node.left.color;
      node.right.color = !node.right.color;
    }

    Node balance(Node node){

      if(isRed(node.right)){
        node = leftRotation(node);
      }
      if(isRed(node.left) && isRed(node.left.left)){
        node = rightRotation(node);
      }
      if(isRed(node.left) && isRed(node.right)){
        changeColors(node);
      }

      node.size = size(node.left)+ size(node.right)+1;
      return node;

    }

    void add(int key){
      root = addRecursive(root, key);
    }

    Node addRecursive(Node node, int key){
      if(node == null){
        node = new Node(key, RED, 1);
        return node;
      }
      if(key < node.key){
          node.left = addRecursive(node.left, key);
      }
      else if(key > node.key){
        node.right = addRecursive(node.right, key);
      }

      if (isRed(node.right) && !isRed(node.left)){
        node = leftRotation(node);
      }
      if (isRed(node.left) && isRed(node.left.left)){
        node = rightRotation(node);
      }
      if (isRed(node.left) && isRed(node.right)){
        changeColors(node);
      }

      node.size = size(node.left) + size(node.right) +1;

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
        RedBlackTree tree = new RedBlackTree();

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
