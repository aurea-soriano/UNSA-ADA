public class MatrixMultiplication{

    //constructor
    public MatrixMultiplication(){

    }

    //traditional method
    public int[][] traditionalMultiplication(int[][] matrixA, int[][] matrixB){
      int n = matrixA.length;     // O(2)
      int matrixC[][] = new int[n][n]; // O(1)

      for(int i=0; i<n; i++){//n
        for(int j=0; j<n; j++){//n
          matrixC[i][j] = 0; //O(1)
          for(int k=0; k<n;k++){ //n
            matrixC[i][j]+=(matrixA[i][k]* matrixB[k][j]); // O(5)
          }
        }
      }
      return matrixC;
    }

    /**
    O(2) + O(1) + n*n(1+ n*5)= n^2+ 5*n^3 = O(n^3)
    **/


    //print matrix
    public static void printMatrix(int[][] matrixA){
      int n = matrixA.length; // O(2)
      System.out.println("Our result:"); //O(1)

      for(int i=0; i<n; i++){ //n
        for(int j=0; j<n; j++){ // n
          System.out.print(matrixA[i][j]+"\t"); // O(2)
        }
        System.out.println();
      }
      System.out.println();
    }

    /**
    O(2) + O(1)  + n*n*O(2) = O(2*n^2) = O(n^2)
    **/




    //strassen method
    public int[][] strassenMultiplication(int[][] matrixA, int[][] matrixB){
      int n = matrixA.length;
      int matrixC[][] = new int[n][n];

      if(n==1){
        matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
      }
      else{
        //division for Matrix A
        int[][] a = new int[n/2][n/2];
        int[][] b = new int[n/2][n/2];
        int[][] c = new int[n/2][n/2];
        int[][] d = new int[n/2][n/2];

        //division for Matrix B
        int[][] e = new int[n/2][n/2];
        int[][] f = new int[n/2][n/2];
        int[][] g = new int[n/2][n/2];
        int[][] h = new int[n/2][n/2];

        //dividing matrix A into 4 parts
        divideArray(matrixA, a, 0, 0);
        divideArray(matrixA, b, 0, n/2);
        divideArray(matrixA, c, n/2, 0);
        divideArray(matrixA, d, n/2, n/2);


        //dividing matrix B into 4 parts
        divideArray(matrixB, e, 0, 0);
        divideArray(matrixB, f, 0, n/2);
        divideArray(matrixB, g, n/2, 0);
        divideArray(matrixB, h, n/2, n/2);

        int[][] M1 = strassenMultiplication(sumMatrices(a,d), sumMatrices(e,h));
        int[][] M2 = strassenMultiplication(sumMatrices(c,d), e);
        int[][] M3 = strassenMultiplication(a, subMatrices(f,h));
        int[][] M4 = strassenMultiplication(d, subMatrices(g,e));
        int[][] M5 = strassenMultiplication(sumMatrices(a,b), h);
        int[][] M6 = strassenMultiplication(subMatrices(c,a), sumMatrices(e,f));
        int[][] M7 = strassenMultiplication(subMatrices(b,d), sumMatrices(g,h));


        int[][] C11 = sumMatrices(subMatrices(sumMatrices(M1,M4),M5),M7);
        int[][] C12 = sumMatrices(M3,M5);
        int[][] C21 = sumMatrices(M2,M4);
        int[][] C22 = sumMatrices(subMatrices(sumMatrices(M1,M3),M2),M6);


        //ensamble all parts

        copySubArray(C11, matrixC, 0, 0);
        copySubArray(C12, matrixC, 0, n/2);
        copySubArray(C21, matrixC, n/2, 0);
        copySubArray(C22, matrixC, n/2, n/2);

      }

      return matrixC;
    }


    //divide the matrices
   public static void divideArray(int[][]P, int[][] C, int iB, int jB){
     for(int i1=0, i2=iB; i1<C.length; i1++,i2++){
       for(int j1=0, j2=jB; j1<C.length; j1++,j2++){
         C[i1][j1] = P[i2][j2];
       }
     }
   }

   //copy the matrices
   public static void copySubArray(int[][]C, int[][] P, int iB, int jB){
     for(int i1=0, i2=iB; i1<C.length; i1++,i2++){
       for(int j1=0, j2=jB; j1<C.length; j1++,j2++){
         P[i2][j2] = C[i1][j1];
       }
     }
   }



    //sum matrices
    public static int[][] sumMatrices(int[][] matrixA, int[][] matrixB){
      int n = matrixA.length;   // O(2)
      int matrixC[][] = new int[n][n]; //O(1)

      for(int i=0; i<n; i++){ // n
        for(int j=0; j<n; j++){ // n
          matrixC[i][j] = matrixA[i][j] + matrixB[i][j]; //O(4)
        }
      }
      return matrixC;
    }

    /**
    O(2) + O(1) + n*n*O(4) = O(n^2)
    **/

    //sub matrices
    public static int[][] subMatrices(int[][] matrixA, int[][] matrixB){
      int n = matrixA.length;   // O(2)
      int matrixC[][] = new int[n][n]; //O(1)

      for(int i=0; i<n; i++){ // n
        for(int j=0; j<n; j++){ // n
          matrixC[i][j] = matrixA[i][j] - matrixB[i][j]; //O(4)
        }
      }
      return matrixC;
    }

    /**
    O(2) + O(1) + n*n*O(4) = O(n^2)
    **/


    public static void main(String[] args){
       MatrixMultiplication multiplication = new MatrixMultiplication();

       int matrixA[][] = {{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}}; //4x4
       int matrixB[][] = {{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}}; //4x4

       int matrixC1[][] = multiplication.traditionalMultiplication(matrixA, matrixB);
       multiplication.printMatrix(matrixC1);


       int matrixC2[][] = multiplication.strassenMultiplication(matrixA, matrixB);
       multiplication.printMatrix(matrixC2);
       System.out.println("Ada es super divertido");

    }
}
