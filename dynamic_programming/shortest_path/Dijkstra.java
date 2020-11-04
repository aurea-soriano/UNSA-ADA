// vamos a implementar dijkstra en una representacion de
//matriz
// solo recibe pesos positivos
import java.io.*;
import java.util.*;

class Dijkstra{
  int vNumber;
  int graphMatrix[][];
  final static int INF = 99999;


  Dijkstra(int n, int[][] graph){
    vNumber = n;
    graphMatrix = graph;
  }

  //funcion utilitaria para retornar el nodo con la minima distancia
  int minDistance(int dist[], boolean includedPath[]){
    //inicializar nuestro valores minimos
    int min = INF, min_index = -1;

    for (int i=0; i<vNumber; i++){
      if(includedPath[i]==false && dist[i]<=min){
        min = dist[i];
        min_index = i;
      }
    }
    return min_index;
  }

//una funcion utilitaria para imprimir nuestros resultados
  void printSolution(int dist[], int d){
    System.out.println("Nodo \t\t Distancia al origen");

    for(int i=0; i<vNumber; i++){
      if(d==-1){
        System.out.println(i+"\t\t"+dist[i]);
      }else{
        if(d==i){
            System.out.println(i+"\t\t"+dist[i]);
            return;
        }
      }
    }
  }


  // recorrido de Dijkstra
  void traversal(int s, int d){
    // s es el nodo que es punto de partida y d es el nodo de destino
    // si d es -1 retornar'a todos los destinos a partir de ese nodo s

    // un vector de salida que va a contener todas las distancias
    // mas cortas de ese s para todo ese d
    int dist[] = new int[vNumber];


    //vamos a tener un array donde vamos a colocar si un nodo es
    //parte de nuestro camino mas corto
    boolean includedPath[] = new boolean[vNumber];


    //inicializar todas las distancias con infinito
    // y todos nuestros includedPath como falso
    for(int i=0; i<vNumber; i++){
      dist[i] = INF;
      includedPath[i] = false;
    }

      //la distancia del nodo hacia ese mismo nodo
      dist[s] = 0;

      //vamos a encontrar todas las minimas distancias
      for(int count=0; count< vNumber-1; count++){

          //Escoger el nodo con la distancia minima
          // del conjunto de nodos que no fueron procesados
          int u = minDistance(dist, includedPath);//minima distancia

          //marcar ese nodo u como procesado
          includedPath[u] = true;


          //actualizar los velores de las distancias de los nodos
          //adyacentes del nodo escogido

          for(int v=0; v<vNumber; v++){

            // actualizar dist[v] si y solo si no esta' en includedPath
            // hay una arista entre u y v, y el peso total de la ruta
            //entre s y  v a traves de u es m'as pequenho que el valor
            //actual de dist[v]

            if(!includedPath[v] && graphMatrix[u][v]!=INF && dist[u]!=INF && dist[u]+graphMatrix[u][v]<dist[v] ){
              dist[v] = dist[u] + graphMatrix[u][v];
            }



          }
      }

      //imprimir el resultado
      printSolution(dist, d);
  }

  public static void main(String args[]){

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


      int graph[][] = new int[][] { { INF, 4, INF, INF, INF, INF, INF, 8, INF },
                          { INF, INF, 12, INF, INF, INF, INF, INF, INF },
                          { INF, INF, INF, 19, INF, INF, INF, INF, 14 },
                          { INF, INF, INF, INF, INF, INF, INF, INF, INF },
                          { INF, INF, INF, INF, INF, INF, INF, INF, INF },
                          { INF, INF, INF, INF, 21, INF, INF, INF, INF },
                          { INF, INF, INF, INF, INF, 11, INF, INF, INF },
                          { INF, INF, INF, INF, INF, INF, 9, INF, INF },
                          { INF, INF, INF, INF, INF, INF, INF, INF, INF } };

      Dijkstra dijkstra = new Dijkstra(9, graph);
      int source = 0;
      dijkstra.traversal(source, -1);
      int destination = 4;
      System.out.println("Desde el nodo origen "+source+" hasta el nodo destino "+destination);
      dijkstra.traversal(source, destination);


  }


}
