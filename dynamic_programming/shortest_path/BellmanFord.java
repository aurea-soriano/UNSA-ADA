// vamos a implementar dijkstra
//pesos solo positivos
// vamos a usar una representacion de matriz
import java.io.*;
import java.util.*;

class BellmanFord{
  int V, E;
	Edge edge[];

  class Edge {
    int src, dest, weight;
    Edge()
    {
      src = dest = weight = 0;
    }
  };

  BellmanFord(int v, int e){
    V = v;
    E = e;
    edge = new Edge[e];
    for (int i = 0; i < e; ++i)
      edge[i] = new Edge();
  }

  // funcion utilitaria para imprimir la matriz de distancia
  void printSolution(int dist[]){
    System.out.println("Vertice \t\t Distancia del Origen");
    for (int i = 0; i < V; i++){
      System.out.println(i + " \t\t " + dist[i]);
    }
  }

  // funcion utilitaria para imprimir la matriz de distancia
  void printSolution(int dist[], int d){
    System.out.println("Vertice \t\t Distancia del Origen");
    for (int i = 0; i < V; i++){
      if(i==d){
        System.out.println(i + " \t\t " + dist[i]);
        return;

      }
    }
  }

  // recorrido de BellmanFord
  void traversal(int s, int d){
    //s es el nodo source el nodo de origen desde el cual vamos a partir
    // el vector de salida, dist[i] va a contener la distancia
    //m'as corta de s hacia i
    int V = this.V, E = this.E;
		int dist[] = new int[V];


    // 1) inicializar todas las distancias como infinito
    // y nuestro vector de includedPath todos como falso
    for (int i = 0; i < V; ++i)
			dist[i] = Integer.MAX_VALUE;


    // la distancia del nodo al mismo nodo
    dist[s] = 0;

    // Paso 2: Relaje todos los bordes | V | - 1 vez.
    // Una ruta simple más corta desde src a cualquier otro
    //vértice puede tener como máximo | V | - 1 aristas
    for (int i = 1; i < V; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = this.edge[j].src;
				int v = this.edge[j].dest;
				int weight = this.edge[j].weight;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
					dist[v] = dist[u] + weight;
			}
		}

    // Paso 3: verificar si hay pesos negativos.
    for (int j = 0; j < E; ++j) {
			int u = this.edge[j].src;
			int v = this.edge[j].dest;
			int weight = this.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
				System.out.println("Graph contains negative weight cycle");
				return;
			}
		}

  // imprimir la matriz de distancias encontrada
  if (d==-1){
    printSolution(dist);
  }else{
    printSolution(dist,d);
  }


  }

  public static void main(String args[]){

                  /**


                        0(0)
                      /         \
                     1(4)       7 (8)
                     |          |
            (14) 8 - 2(12)      6(-9)
                     |           |
                     3(19)      5(11)
                                \
                                4(-21)

            **/

        int V = 9; // Number of vertices in graph
        int E = 8; // Number of edges in graph

        BellmanFord bellmanFord = new BellmanFord(V, E);

        bellmanFord.edge[0].src = 0;
        bellmanFord.edge[0].dest = 1;
        bellmanFord.edge[0].weight = 4;

        bellmanFord.edge[1].src = 0;
        bellmanFord.edge[1].dest = 7;
        bellmanFord.edge[1].weight = 8;

        bellmanFord.edge[2].src = 1;
        bellmanFord.edge[2].dest = 2;
        bellmanFord.edge[2].weight = 12;


        bellmanFord.edge[3].src = 2;
        bellmanFord.edge[3].dest = 3;
        bellmanFord.edge[3].weight = 19;

        bellmanFord.edge[4].src = 2;
        bellmanFord.edge[4].dest = 8;
        bellmanFord.edge[4].weight = 14;

        bellmanFord.edge[5].src = 7;
        bellmanFord.edge[5].dest = 6;
        bellmanFord.edge[5].weight = -9;


        bellmanFord.edge[6].src = 6;
        bellmanFord.edge[6].dest = 5;
        bellmanFord.edge[6].weight = 11;


        bellmanFord.edge[7].src = 5;
        bellmanFord.edge[7].dest = 4;
        bellmanFord.edge[7].weight = -21;



        int source = 0;
        int destination = 4;
        bellmanFord.traversal(source, -1);
        System.out.println("Distancia entre el origen "+source+" y el destino "+destination);
        bellmanFord.traversal(source, destination);




  }

}
