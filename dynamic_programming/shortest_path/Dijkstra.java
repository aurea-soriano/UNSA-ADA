// vamos a implementar dijkstra
//pesos solo positivos
// vamos a usar una representacion de matriz
import java.io.*;
import java.util.*;

class Dijkstra{
  int vNumber;
  int matrixGraph[][];
  final static int INF = 99999;

  Dijkstra(int n, int[][]graph){
    vNumber = n;
    matrixGraph = graph;
  }


  int minDistance(int dist[], boolean includedPath[]){
  		// inicializar los valores minimos
  		int min = Integer.MAX_VALUE, min_index = -1;

  		for (int v = 0; v < vNumber; v++)
  			if (includedPath[v] == false && dist[v] <= min) {
  				min = dist[v];
  				min_index = v;
  			}

  		return min_index;
  	}


    // funcion utilitaria para imprimir la matriz de distancia
  	void printSolution(int dist[]){
  		System.out.println("Vertice \t\t Distancia del Origen");
  		for (int i = 0; i < vNumber; i++){
  			System.out.println(i + " \t\t " + dist[i]);
      }
  	}

    // funcion utilitaria para imprimir la matriz de distancia
    void printSolution(int dist[], int d)
    {
      System.out.println("Vertice \t\t Distancia del Origen");
      for (int i = 0; i < vNumber; i++){
        if(i==d){
          System.out.println(i + " \t\t " + dist[i]);
          return;

        }
      }
    }

  // recorrido de Dijkstra
  void traversal(int s, int d ){
    //s es el nodo source el nodo de origen desde el cual vamos a partir
    // el vector de salida, dist[i] va a contener la distancia
    //m'as corta de s hacia i
    int dist[] = new int[vNumber];

    // nos va a decir si un nodo i est'a contenido en la distancia
    // mas corta de s a nuestro i
    boolean includedPath[] = new boolean[vNumber];

    // inicializar todas las distancias como infinito
    // y nuestro vector de includedPath todos como falso
    for(int i=0; i<vNumber; i++){
      dist[i] = Integer.MAX_VALUE;
      includedPath[i] = false;
    }

    // la distancia del nodo al mismo nodo
    dist[s] = 0;

    //encontrar todas las minimas distancias
  for (int count = 0; count < vNumber - 1; count++) {
    // Escoger el v'ertice con minima distancias
    // del conjunto de vertices que no fueron procesados
    // en la primera iteracion u es igual al origen
    int u = minDistance(dist, includedPath);

    // Marcar el vertice como procesado
    includedPath[u] = true;

    // Actualizar los valores de distancia de los vertices
    //adyacentes del vertice escogido
    for (int v = 0; v < vNumber; v++)

      // Actualizar dist[v] solo si no est'a en includedPath
      //hay una arista entre u y v , y el peso total de la ruta
      // de s a v a traves de u es m'as pequenho que el valor actual de
        // dist[v]
      if (!includedPath[v] && matrixGraph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrixGraph[u][v] < dist[v])
        dist[v] = dist[u] + matrixGraph[u][v];
  }
  // imprimir la matriz de distancias encontrada
  if (d==-1){
    printSolution(dist);
  }else{
    printSolution(dist,d);
  }


  }

  public static void main(String args[]){
    int graph[][] = new int[][] { { INF, 4, INF, INF, INF, INF, INF, 8, INF },
                  { INF, INF, 12, INF, INF, INF, INF, INF, INF },
                  { INF, INF, INF, 19, INF, INF, INF, INF, 14 },
                  { INF, INF, INF, INF, INF, INF, INF, INF, INF },
                  { INF, INF, INF, INF, INF, INF, INF, INF, INF },
                  { INF, INF, INF, INF, 21, INF, INF, INF, INF },
                  { INF, INF, INF, INF, INF, 11, INF, INF, INF },
                  { INF, INF, INF, INF, INF, INF, 9, INF, INF },
                  { INF, INF, INF, INF, INF, INF, INF, INF, INF } };

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
        int source = 0;
        int destination = 4;
        Dijkstra dijkstra = new Dijkstra(9,graph);
        dijkstra.traversal(source, -1);
        System.out.println("Distancia entre el origen "+source+" y el destino "+destination);
        dijkstra.traversal(source, destination);
  }

}
