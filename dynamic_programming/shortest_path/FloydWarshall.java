// vamos a implementar FloydWarshall
// a diferencia de los otros nos va a dar todas las rutas
// mas cortas entre todos los nodos

// si acepta pesos en negativo

import java.io.*;
import java.util.*;


class FloydWarshall{
  int vNumber; // nu'mero de ve'rtices
  int graphMatrix[][];
  final static int INF = 99999;

  FloydWarshall(int n, int[][] graph){
    vNumber  = n;
    graphMatrix = graph;
  }

   //funcion utilitaria para imprimir nuestros resultados
   void printResult(int dist[][]){
     System.out.println("Matriz de distancias m'as cortas");

     for(int i=0; i<vNumber; i++){
       for(int j=0; j<vNumber; j++){
         if(dist[i][j] == INF){
            System.out.print("INF ");
         }
         else{
            System.out.print(dist[i][j]+" ");
         }
       }
       System.out.println();
     }
   }


  //recorrido de FloydWarshall
  void traversal(){

    // memorizacion va a ser una matriz donde acumularemos
    // las distancias
    int dist[][] = new int[vNumber][vNumber];


    //vamos a inicializar nuestras distancias con los pesos
    //originales del grafo
    for(int i=0; i<vNumber; i++){
      for(int j=0; j<vNumber; j++){
        dist[i][j] = graphMatrix[i][j];
      }
    }


    /**
    Suma todos los nodos uno por uno al conjunto de vertices
    intermedio
    --> antes del inicio de una iteracion, tenemos las
    distancias mas cortas entre todos los pares de nodos,
    de modo que las distancias mas cortas consideran solo
    los vertices en el conjunto de {0,1,2,...,k-1} como vertices
    intermedios

    --> despues de cada iteracion, el vertice numero k
    es adicionado al conjunto de vertices intermedios y el
    conjunto se convierte en {0,1,2,...,k}
    **/

    for(int k=0; k<vNumber; k++){
      //escoger todos los vertices como origen uno por uno
      for(int i=0; i<vNumber; i++){
        //elegir todos los vertices como destino para el
        //vertice que fue seleccionado como origen
        for(int j=0; j<vNumber; j++){
          //si es que el vertice de k esta en la ruta mas corta de i a j,
          // entonces actualizamos el valor de dist[i][j]
          if(dist[i][k]+dist[k][j]< dist[i][j]){
            dist[i][j] = dist[i][k]+dist[k][j];
          }

        }
      }
    }



    //imprimir el resultado
    printResult(dist);

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

    FloydWarshall floydWarshall = new FloydWarshall(9, graph);
    floydWarshall.traversal();
  }
}
