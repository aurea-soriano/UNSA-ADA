#include <iostream>
using namespace std;
#include <chrono>
using namespace std::chrono;
#include <vector>
/**
Fibonacci recursivo
*/
int recursive_fib(int n){
  if(n<=1){        // O(max(1,0)) = O(1)
    return n;
  }
  return recursive_fib(n-1) + recursive_fib(n-2); //T(n-1)+T(n-2)
}

/* T(n) = T(n-1) + T(n-2) + O(1)
puede ser resuelto por el metodo del arbol
T(n-1) = O(2^(n-1))
T(n-2) = O(2^(n-2))

T(n) = O(2^(n-1)) + O(2^(n-2)) + O(1) = O(2^n)
*/



/**
Fibonacci iterativo
*/
int iterative_fib(int n){
  if(n<=0){        // O(max(1,0)) = O(1)
    return 0;
  }

  std::vector<int> fibo(n+1);
  fibo[0] = 0;    // O(1)
  fibo[1] = 1;    // O(1)

  //calcular los siguientes terminos
  for(int i=2; i<=n; i++){         // n-2  -------
    fibo[i] = fibo[i-1] + fibo[i-2];   // O(4)---- O(4(n-2)) = O(n)
  }
  return fibo[n];  // O(1)
}

//O(1)+O(1)+O(1)+O(1)+O(n)+O(1)= O(n)


int main(){

  int n = 29;
  auto start = high_resolution_clock::now();
  cout<<"Resultado recursivo: "<<recursive_fib(n)<<endl;
  auto stop = high_resolution_clock::now();
  auto duration = duration_cast<microseconds>(stop - start);
  cout<<"Tiempo recursivo: "<<duration.count()<<endl;

  start = high_resolution_clock::now();
  cout<<"Resultado iterativo: "<<iterative_fib(n)<<endl;
  stop = high_resolution_clock::now();
  duration = duration_cast<microseconds>(stop - start);
  cout<<"Tiempo iterativo: "<<duration.count()<<endl;

}
