// vamos a implementar nuestra b'usqueda por amplitud
// el ejemplo del hombre pajaro
//que podia ver cuantos pasos le tomaba ir de un lugar a otro
//BST no trabajo con grafos ponderados

import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")
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


    // recorrido del BFS - recibe el origen el punto de partida
    void traversal(int s, int d){

      // un estado de visitado para cada nodo, e inicialmente
      //vamos a colocar como no visitado a todos los nodos
      boolean visited[] = new boolean[vNumber];

      // nuestra cola para recorrer el BFS
      LinkedList<LinkedList<Integer>> queue = new LinkedList<LinkedList<Integer>>();


      // nuestro primer nodo fue marcado como visitado
      visited[s] = true;
      int steps = 0;

      // no le vamos a pasar solo el nodo sino que le vamos a pasar tambien
      //el numero de pasos
      // mi lista mas pequenha contiene el nombre del nodo y el numero de pasos

      LinkedList<Integer> nodeInfo = new LinkedList<Integer>();
      nodeInfo.add(s);
      nodeInfo.add(steps);
      queue.add(nodeInfo);

      while(queue.size()!=0){

        //sacar un elemento de la cola
        nodeInfo  = queue.poll();
        s = nodeInfo.get(0);
        steps = nodeInfo.get(1);

        if(d==s || d==-1){
          System.out.println(s+" : "+steps+" steps.");
        }

        Iterator<Integer> it = adjacencies[s].listIterator();

        while(it.hasNext()){
          int node = it.next();
          if(!visited[node]){
            visited[node] = true;
            nodeInfo = new LinkedList<Integer>();
            nodeInfo.add(node);
            nodeInfo.add(steps+1);
            queue.add(nodeInfo);
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


      int source = 0;
      bfs.traversal(source, -1);
      int destination = 4;
      System.out.println("Desde el nodo origen "+source+" hasta el nodo destino "+destination);
      bfs.traversal(source, destination);


  }

}
