class BinarySearchTree{

    class Node{
       int key;
       Node left, right;

       public Node(int key){
         this.key = key;
         this.right = this.left = null;
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
        return  node;
      }

      if(key < node.key){
        node.left  = addRecursive(node.left, key);
      }
      else if(key > node.key){
        node.right = addRecursive(node.right, key);
      }
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


    public static void main(String[] args){
      BinarySearchTree tree = new BinarySearchTree();

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
      tree = new BinarySearchTree();

      tree.add(40);
      tree.add(60);
      tree.add(80);
      tree.add(100);
      tree.add(120);
      tree.add(140);
      tree.add(160);

      tree.printInOrder();

    }

}
