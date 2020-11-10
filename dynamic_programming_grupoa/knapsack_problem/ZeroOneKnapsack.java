/* vamos a implementar la versio'n de programaci'on din'amica
de la mochila 0/1*/

class ZeroOneKnapsack{
    int weights[];
    int values[];
    int W;
    int n;

    ZeroOneKnapsack(int ws[], int vs[], int capacity, int itemsN){
      weights = ws;
      values = vs;
      W = capacity;
      n = itemsN;
    }

    //funcion que calcula el maximo valor con la capacidad W
    int getOptimalValue(){

      //K va a almacenar el valor maximo en cada capacidad X -> memo
      int K[][] = new int[n+1][W+1];

      //vamos a rellenar nuestro K de forma recursiva

      for(int j=0; j<=n; j++){
         for(int x =0; x<=W ; x++){
           if(x==0 || j==0){
             K[j][x] = 0;
           }
           else if(weights[j-1]<=x){
               K[j][x] = max(K[j-1][x], K[j-1][x-weights[j-1]]+values[j-1]);
             }
           else{
             K[j][x] = K[j-1][x];
           }
         }
      }
      return K[n][W];
    }

    //funcion utilitaria para sacar el maximo

    int max(int a, int b){
      return (a>b)? a:b;
    }




    public static void main(String[] args){
      int W = 3;
      int values[] = {1,4,6};
      int weights[] = {1,2,3};
      int n = values.length;

      ZeroOneKnapsack knapsack = new ZeroOneKnapsack(weights, values, W, n);
      System.out.println("Nuestro valor m'aximo dada la capacidad es "+knapsack.getOptimalValue());
    }


}
