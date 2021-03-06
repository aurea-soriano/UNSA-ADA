// vamos a implementar bellmanford, crear un objeto arista
// objeto arista-> origen, fin y peso
// acepta pesos en negativo

import java.io.*;
import java.util.*;

class BellmanFord{
    final static int INF = 99999;
    int vNumber; // numero de vertices
    int eNumber; //numero de aristas
    Edge edge[];

    class Edge{
      int source, destination, weight;
      Edge(){
        source = destination = weight = INF;
      }
    };

    BellmanFord(int v, int e){
      vNumber  = v;
      eNumber = e;
      edge = new Edge[e];
      for(int i=0; i<e; i++){
        edge[i] = new Edge();
      }
    }

    //funcion utilitaria para imprimir nuestros resultados
    void printResult(int dist[], int d){
      System.out.println("Nodo \t\t Distancia al origen");

      for(int i=0; i<vNumber; i++){
        if(d==-1 || d==i){
           System.out.println(i+"\t\t"+dist[i]);
        }
      }
    }

    // recorrido de BellmanFord
    void traversal(int s, int d){
      // si d es igual a -1, vamos a calcular la menor distancia
      // desde el punto de origen hacia todos los otros nodos


      // vector que va a contener nuestras distancias mas cortas de ese s para ese default:
      int dist[] = new int[vNumber];

      //1) inicializar todas las distancias como infinito
      for(int i =0 ; i<vNumber; i++){
        dist[i] = INF;
      }

      //la distancia del nodo al mismo nodo
      dist[s] = 0;

      //2) recorrer para encontrar una ruta mas corta desde el punto
      // de partida hacia cualquier otro nodo.

      for(int i=0; i<vNumber-1; i++){
        for(int j=0; j<eNumber; j++){
          int u = edge[j].source;
          int v = edge[j].destination;
          int weight = edge[j].weight;

          //si existe una distancia o sea si hay una conexion
          // si nuestro nuevo peso es menor al peso de destination
          if( dist[u]!=INF && dist[u]+weight < dist[v]){
              dist[v] = dist[u] + weight;
          }
        }
      }

      //3) identificar si hay ciclos negativos infinitos
      for(int j=0; j<eNumber; j++){
        int u = edge[j].source;
        int v = edge[j].destination;
        int weight = edge[j].weight;

        if( dist[u]!=INF && dist[u]+weight < dist[v]){
            System.out.println("Se ha detectado un ciclo negativo");
            return;
        }

      }

      //imprimimos el resultado
      printResult(dist,d);

    }

      public static void main(String args[]){

        /**
                      0
                 (4)/    \ (8)
                   1      7
              (12) |      | (-9)
          (14) 8 - 2      6
              (19) |      | (11)
                   3      5
                          \ (-21)
                          4
                  **/
        int vNumber = 9;
        int eNumber = 8;


        BellmanFord bellmanFord = new BellmanFord(vNumber, eNumber);

        bellmanFord.edge[0].source = 0;
        bellmanFord.edge[0].destination = 1;
        bellmanFord.edge[0].weight = 4;

        bellmanFord.edge[1].source = 0;
        bellmanFord.edge[1].destination = 7;
        bellmanFord.edge[1].weight = 8;

        bellmanFord.edge[2].source = 1;
        bellmanFord.edge[2].destination = 2;
        bellmanFord.edge[2].weight = 12;

        bellmanFord.edge[3].source = 2;
        bellmanFord.edge[3].destination = 3;
        bellmanFord.edge[3].weight = 19;

        bellmanFord.edge[4].source = 2;
        bellmanFord.edge[4].destination = 8;
        bellmanFord.edge[4].weight = 14;

        bellmanFord.edge[5].source = 7;
        bellmanFord.edge[5].destination = 6;
        bellmanFord.edge[5].weight = -9;

        bellmanFord.edge[6].source = 6;
        bellmanFord.edge[6].destination = 5;
        bellmanFord.edge[6].weight = 11;

        bellmanFord.edge[7].source = 5;
        bellmanFord.edge[7].destination = 4;
        bellmanFord.edge[7].weight = -21;

        int source = 0;
        bellmanFord.traversal(source, -1);
        int destination = 4;
        System.out.println("Desde el nodo origen "+source+" hasta el nodo destino "+destination);
        bellmanFord.traversal(source, destination);


      }

}
