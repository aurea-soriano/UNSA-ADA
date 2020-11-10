/* vamos a inmplementar la versio'n de programacio'n
dina'mica de obtener la subsecuencia com'un m'as larga*/

class LCS{

  static int lcs(char[] X, char[] Y, int m, int n){

    int C[][] = new int[m+1][n+1];

    for(int i=0; i<=m; i++){
      for(int j=0; j<=n; j++){
        if(i==0 || j==0)
          C[i][j] = 0;
        else if(X[i-1] == Y[j-1])
          C[i][j] = C[i-1][j-1] +1;
        else
          C[i][j] = max(C[i-1][j], C[i][j-1]);
      }
    }
    return C[m][n];

  }

  // funcion utilitaria para retornar el maximo entre dos numeros
  static int max(int a, int b){
    return (a>b)?a:b;
  }



  public static void main(String[] args){
    String s1 = "AGGTAB";
    String s2 = "GXTXAYB";

    // GTAB

    char[] X = s1.toCharArray();
    char[] Y = s2.toCharArray();

    int m = X.length;
    int n = Y.length;

    int metric = lcs(X,Y,m,n);

    System.out.println("Nuestros resultado de plagio es "+ metric);

    s1 = "ANALISISYDISENODEALGORITMOS";
    s2 = "LISISESTRUCTRITMOS";

    //LISISERITMOS

    X = s1.toCharArray();
    Y = s2.toCharArray();

    m = X.length;
    n = Y.length;

    metric = lcs(X,Y,m,n);

    System.out.println("Nuestros resultado de plagio es "+ metric);


  }
}
