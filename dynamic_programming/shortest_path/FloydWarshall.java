// vamos a implementar floyd warshall
// vamos a usar una representacion de matriz
import java.io.*;
import java.util.*;

class FloydWarshall{
  int vNumber;
  int matrixGraph[][];
  final static int INF = 99999;

  FloydWarshall(int n, int[][]graph){
    vNumber = n;
    matrixGraph = graph;
  }

    // funcion utilitaria para imprimir la matriz de distancia
    void printSolution(int dist[][]){
        System.out.println("La siguiente matriz muestra las"+
         "distancias más cortas entre cada par de vértices");
        for (int i=0; i<vNumber; ++i){
            for (int j=0; j<vNumber; ++j){
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }


    void traversal(){
         int dist[][] = new int[vNumber][vNumber];
         int i, j, k;

         /* Inicialice la matriz de solución igual que la
         matriz del gráfico de entrada. O podemos decir que
         los valores iniciales de las distancias más cortas
         se basan en las rutas más cortas sin considerar
         ningún vértice intermedio.*/
         for (i = 0; i < vNumber; i++)
             for (j = 0; j < vNumber; j++)
                 dist[i][j] = matrixGraph[i][j];

         /* Suma todos los vértices uno por uno al
         conjunto de vértices intermedios.
           ---> Antes del inicio de una iteración, tenemos
           distancias más cortas entre todos los pares de
           vértices, de modo que las distancias más cortas
           consideran solo los vértices en el conjunto {0, 1, 2, .. k-1}
           como vértices intermedios.
           ----> Después del final de una iteración, el vértice no. k
           se agrega al conjunto de vértices intermedios y el conjunto
           se convierte en {0, 1, 2, .. k} */
         for (k = 0; k < vNumber; k++)
         {
             // Elija todos los vértices como origen uno por uno
             for (i = 0; i < vNumber; i++)
             {
                 //Elija todos los vértices como destino para el origen
                 //seleccionado anteriormente
                 for (j = 0; j < vNumber; j++)
                 {
                     // Si el vértice k está en la ruta más corta de i a j,
                     //actualice el valor de dist [i] [j]
                     if (dist[i][k] + dist[k][j] < dist[i][j])
                         dist[i][j] = dist[i][k] + dist[k][j];
                 }
             }
         }


  // imprimir la matriz de distancias encontrada
    printSolution(dist);

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
        //int source = 0;
        //int destination = 4;
        FloydWarshall floydWarshall = new FloydWarshall(9,graph);
        floydWarshall.traversal();
  }

}
