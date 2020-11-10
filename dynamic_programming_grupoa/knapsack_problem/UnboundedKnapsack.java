/* vamos a implementar la versio'n de programaci'on din'amica
de la mochila ilimitada*/

class UnboundedKnapsack{
    int weights[];
    int values[];
    int W;
    int n;

    UnboundedKnapsack(int ws[], int vs[], int capacity, int itemsN){
      weights = ws;
      values = vs;
      W = capacity;
      n = itemsN;
    }

    //funcion que calcula el maximo valor con la capacidad W
    int getOptimalValue(){

      //K va a almacenar el valor maximo en cada capacidad X -> memo
      int K[] = new int[W+1];

      for(int x=0; x<=W; x++){ //x es nuestra mini capacidad actual (minimochila)
        for(int i=0; i<n; i++){
            if(weights[i] <= x){
              K[x] = max(K[x], K[x-weights[i]]+values[i]);
            }
        }
      }
      return K[W];
    }

    //funcion utilitaria para sacar el maximo

    int max(int a, int b){
      return (a>b)? a:b;
    }




    public static void main(String[] args){
      int W = 4;
      int values[] = {1,4,6};
      int weights[] = {1,2,3};
      int n = values.length;

      UnboundedKnapsack knapsack = new UnboundedKnapsack(weights, values, W, n);
      System.out.println("Nuestro valor m'aximo dada la capacidad es "+knapsack.getOptimalValue());
    }


}
