/* vamos a implementar la version de programacion dinamica
de la mochila ilimitada*/

class UnboundedKnapsackDP{
  int values[];
  int weights[];
  int W;
  int n;

  UnboundedKnapsackDP(int v[],int w[],int nW, int iN){
    values = v;
    weights = w;
    W = nW;
    n = iN;
  }


  // retornar el maximo valor con capacidad W
  int getOptimalValue(){

    // K va a almacenar el valor maximo con capacidad i
    int K[] = new int[W+1];

    // vamos a rellenar nuestro K de forma recursiva
    for(int x =0; x<=W ; x++){
      for(int i=0; i< n; i++){
        if(weights[i]<=x){
          K[x] = max(K[x], K[x-weights[i]]+values[i]);

        }
      }
    }
    return K[W];
  }

  // funcion utilitaria para sacar el maximo
  int max(int a, int b){
    return (a>b)? a:b;
  }


  public static void main(String[] args){

      int W = 4;
      int values[] = {1, 4, 6};
      int weights[] = {1, 2, 3};
      int n = values.length;
      UnboundedKnapsackDP knapsack = new UnboundedKnapsackDP(values, weights, W,n);
      System.out.println(knapsack.getOptimalValue());
  }
}
