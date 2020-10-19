
public class MatrixMultiplication{

    //Constructor
    public MatrixMultiplication(){

    }


    //traditional method
    public int[][] traditionalMultiplication(int[][] matrixA, int[][] matrixB){
        int n = matrixA.length;           // O(1)
        int matrixC[][] = new int[n][n]; //nxn O(1)
        for(int i=0; i<n; i++){//n
          for(int j=0; j<n; j++){  //n
            matrixC[i][j] = 0;    //  O(1)
            for(int k=0; k<n; k++){      //n
              matrixC[i][j]+=(matrixA[i][k]* matrixB[k][j]); // O(5)
            }
          }
        }
        return matrixC;
    }
    //  O(1) + O(1) + n*n*(O(1) + n*O(5))= O(2) + n^2(O(1)+ O(5n))=
    //  O(2)  + O(n^2*1) + O(5*n^3)=  O(n^2) + O(n^3) = O(n^3)



    //strassen method
    public int[][] strassenMultiplication(int[][] matrixA, int[][] matrixB){
        int n = matrixA.length;
        int matrixC[][] = new int[n][n];

        if(n==1){
          matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
        }
        else{
          // first matrix
          int[][] a = new int[n/2][n/2];
          int[][] b = new int[n/2][n/2];
          int[][] c = new int[n/2][n/2];
          int[][] d = new int[n/2][n/2];


          // second matrix
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

          // M1, M2, M3, M4, M5, M6 y M7

          /**
          A = [[a,b]
               [c,d]]
          B = [[e,f]
              [g,h]]
          */


          int[][] M1 = strassenMultiplication(sumMatrix(a,d), sumMatrix(e,h));
          int[][] M2 = strassenMultiplication(sumMatrix(c,d), e);
          int[][] M3 = strassenMultiplication(a, subMatrix(f,h));
          int[][] M4 = strassenMultiplication(d, subMatrix(g,e));
          int[][] M5 = strassenMultiplication(sumMatrix(a,b), h);
          int[][] M6 = strassenMultiplication(subMatrix(c,a), sumMatrix(e,f));
          int[][] M7 = strassenMultiplication(subMatrix(b,d), sumMatrix(g,h));

          int[][] C11 = sumMatrix(subMatrix(sumMatrix(M1,M4),M5),M7);
          int[][] C12 = sumMatrix(M3,M5);
          int[][] C21 = sumMatrix(M2,M4);
          int[][] C22 = sumMatrix(subMatrix(sumMatrix(M1,M3),M2),M6);


          /**
          C = [C11 c12]
              [C21  C22]
          */

          copySubArray(C11, matrixC, 0, 0);
          copySubArray(C12, matrixC, 0, n/2);
          copySubArray(C21, matrixC, n/2, 0);
          copySubArray(C22, matrixC, n/2, n/2);

        }
        return matrixC;
    }

    //sum of matrices
    public static int[][] sumMatrix(int[][] matrixA, int[][] matrixB){
      int n = matrixA.length; //  O(1)
      int[][] matrixC = new int [n][n];//n*n   O(1)
      for(int i=0; i<n; i++){//n
        for(int j=0; j<n; j++){  // n
           matrixC[i][j] = matrixA[i][j] + matrixB[i][j]; //O(4)
        }
      }
      return matrixC;
    }
    // O(1) + O(1) + n*n*(O(4))= O(n^2)

    //substraction of matrices
    public static int[][] subMatrix(int[][] matrixA, int[][] matrixB){
      int n = matrixA.length; //  O(1)
      int[][] matrixC = new int [n][n];//n*n   O(1)
      for(int i=0; i<n; i++){//n
        for(int j=0; j<n; j++){  // n
           matrixC[i][j] = matrixA[i][j] - matrixB[i][j]; //O(4)
        }
      }
      return matrixC;
    }
    // O(1) + O(1) + n*n*(O(4))= O(n^2)


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

    //print matrix
    public static void printMatrix(int[][] matrixA){
      int n = matrixA.length;
      System.out.println("Result:");
      for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
          System.out.print(matrixA[i][j]+"\t");
        }
        System.out.println();
      }
      System.out.println();
    }

    public static void main(String[] args){
        MatrixMultiplication multiplication = new MatrixMultiplication();

        int matrixA[][]={{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}}; //4x4
        int matrixB[][]={{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}}; //4x4

        int matrixC[][] = multiplication.traditionalMultiplication(matrixA, matrixB);
        multiplication.printMatrix(matrixC);

        int matrixC2[][] = multiplication.strassenMultiplication(matrixA, matrixB);
        multiplication.printMatrix(matrixC2);

    }

}
