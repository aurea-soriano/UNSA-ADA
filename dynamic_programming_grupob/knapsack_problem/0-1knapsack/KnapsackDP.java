/* vamos a implementar la version de programacion dinamica
de la mochila  0/1*/

class KnapsackDP{
  int values[];
  int weights[];
  int W;
  int n;

  KnapsackDP(int v[],int w[],int nW, int iN){
    values = v;
    weights = w;
    W = nW;
    n = iN;
  }


  // retornar el maximo valor con capacidad W
  int getOptimalValue(){

    // K va a almacenar el valor maximo con capacidad i
    int K[][] = new int[n+1][W+1];

    // vamos a rellenar nuestro K de forma recursiva (bottom up)
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

  // funcion utilitaria para sacar el maximo
  int max(int a, int b){
    return (a>b)? a:b;
  }


  public static void main(String[] args){

      int W = 3;
      int values[] = {1, 4, 6};
      int weights[] = {1, 2, 3};

      int n = values.length;
      KnapsackDP knapsack = new KnapsackDP(values, weights, W,n);
      System.out.println(knapsack.getOptimalValue());
  }
}
