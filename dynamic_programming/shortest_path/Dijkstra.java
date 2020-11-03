// vamos a implementar dijkstra
//pesos solo positivos
// vamos a usar una representacion de matriz
import java.io.*;
import java.util.*;

class Dijkstra{
  int vNumber;
  int matrixGraph[][];


  Dijkstra(int n, graph){
    vNumber = n;
    matrixGraph = graph;
  }


  // recorrido de Dijkstra
  void traversal(int s){
    //s es el nodo source el nodo de origen desde el cual vamos a partir
    // el vector de salida, dist[i] va a contener la distancia
    //m'as corta de s hacia i
    int dist[] = new int[vNumber];

    // nos va a decir si un nodo i est'a contenido en la distancia
    // mas corta de s a nuestro i
    boolean includedPath = new boolean[vNumber];

    // inicializar todas las distancias como infinito
    // y nuestro vector de includedPath todos como falso
    for(int i=0; i<vNumber; i++){
      dist[i] = Integer.MAX_VALUE;
      includedPath[i] = false;
    }

    dist[s] = 0;

    //encontrar todas las minimas distancias


  }

  public static void main(String args[]){
    Dijkstra dijkstra = new Dijkstra(9);
    /**
            0
       (4)/    \ (8)
         1      7
    (12) |      | (9)
(14) 8 - 2      6
    (19) |      | (11)
         3      5
                \ (21)
                4

        **/

  }

}
