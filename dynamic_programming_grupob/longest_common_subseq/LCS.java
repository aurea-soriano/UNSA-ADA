/* vamos a implementar la version de programacion dinamica
de  obtener la subsecuencia comun mas larga*/

class LCS{

  static int lcs(char X[], char Y[], int m, int n){
    /* construyendo nuestro memo*/
    int C[][] = new int[m+1][n+1];

    for(int i=0; i<=m;i++){
      for(int j=0; j<=n;j++){//!!!!!!!! el error estaba aqu'i
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

static int max(int a, int b){
   return (a > b)? a : b;
 }


  public static void main(String[] args){


      String s1 = "AGGTAB";
      String s2 = "GXTXAYB";

      char[] X=s1.toCharArray();
      char[] Y=s2.toCharArray();
      int m = X.length;
      int n = Y.length;

      System.out.println(lcs(X, Y, m, n));
  }
}
