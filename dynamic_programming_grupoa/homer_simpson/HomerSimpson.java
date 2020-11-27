/* vamos a implementar la versio'n de programaci'on din'amica
de la mochila ilimitada*/

class HomeSimpson{
    int m;
    int n;
    int t;
    int K[];

    HomeSimpson(int m, int n, int t){
      this.m = m;
      this.n = n;
      this.t = t;
      this.K = new int[t+1];
    }

    //funcion que calcula el maximo valor con la capacidad W
    int getOptimalValue(){

      for(int x=0; x<=t; x++){ //x es nuestra mini capacidad actual (minimochila)
        if(m < x){
            K[x] = Math.max(K[x], K[x-m]+1);
        }
        if(n < x){
            K[x] = Math.max(K[x], K[x-n]+1);
        }
      }
      return K[t];
    }



    void printK(){
        for(int x=0; x<t+1; x++){
          System.out.print(this.K[x]+" ");
        }

    }


    public static void main(String[] args){
      HomeSimpson simpson = new HomeSimpson(3, 5, 55);
      System.out.println(simpson.getOptimalValue());
      simpson.printK();

    }


}
