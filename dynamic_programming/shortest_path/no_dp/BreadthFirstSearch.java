// vamos a implementar nuestra b'usqueda por amplitud
// el ejemplo del hombre pajaro
//que podia ver cuantos pasos le tomaba ir de un lugar a otro
//BST no trabajo con grafos ponderados

import java.io.*;
import java.util.*;

class BreadthFirstSearch{
  int vNumber;
  LinkedList<Integer> adjacencies[];


  BreadthFirstSearch(int n){
    vNumber = n;
    adjacencies = new LinkedList[n];
    for(int i=0; i<n; i++){
      adjacencies[i] = new LinkedList();
    }
  }

  // registrando nuestras aristas
  // del source al destination
  void addEdge(int s, int d){
    adjacencies[s].add(d);
  }

  // recorrido del BFS
  void traversal(int s){

    // un estado de visitado para cada nodo, e inicialmente
    //vamos a colocar como no visitado a todos los nodos
    boolean visited[] = new boolean[vNumber];

    // nuestra cola para recorrer el BFS
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // nuestro primer nodo fue marcado como visitado
    visited[s] = true;
    queue.add(s);

    while(queue.size()!=0){

      //sacar un elemento de la cola
      s = queue.poll();
      System.out.println("recorrido "+s);

      Iterator<Integer> it = adjacencies[s].listIterator();

      while(it.hasNext()){
        int node = it.next();
        if(!visited[node]){
          visited[node] = true;
          queue.add(node);
        }
      }
    }
  }

  public static void main(String args[]){
    BreadthFirstSearch bfs = new BreadthFirstSearch(9);
    /**
            0
          /    \
         1      7
         |      |
     8 - 2      6
         |      |
         3      5
                \
                4

        **/
      bfs.addEdge(0,1);
      bfs.addEdge(0,7);
      bfs.addEdge(1,2);
      bfs.addEdge(2,3);
      bfs.addEdge(2,8);
      bfs.addEdge(2,3);
      bfs.addEdge(5,4);
      bfs.addEdge(6,5);
      bfs.addEdge(7,6);

      bfs.traversal(0);
  }

}
