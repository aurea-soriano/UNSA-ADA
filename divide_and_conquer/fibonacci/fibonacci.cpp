#include <iostream>
using namespace std;
#include <chrono>
using namespace std::chrono;

int recursive_fib(int n){
  if(n<=1)
    return n;

  return recursive_fib(n-1) + recursive_fib(n-2);

}
int iterative_fib(int n){
  if(n<=0){          // O(max(1,0))
    return 0;
  }

  int fibo[n+1];    // O(1)
  fibo[0] = 0;      //O(1)
  fibo[1] = 1;      //O(1)

  //calcular los siguientes terminos
  for(int i=2 ; i<=n; i++){       // n-2
    fibo[i] = fibo[i-1] + fibo[i-2];    // O(4)
  }

  return fibo[n]; //O(1)
}

/*
O(1+1+1+1+4(n-2)) = O(4+4n-8) = O(n)
*/


int main(){
  int n=29;
  auto start = high_resolution_clock::now();
  cout<<"Resultado Recursivo: "<<recursive_fib(n)<<endl;
  auto stop = high_resolution_clock::now();
  auto duration = duration_cast<microseconds>(stop - start);
  cout <<"Tiempo Recursivo: "<< duration.count() << endl;


  start = high_resolution_clock::now();
  cout<<"Resultado Iterativo: "<<iterative_fib(n)<<endl;
  stop = high_resolution_clock::now();
  duration = duration_cast<microseconds>(stop - start);
  cout <<"Tiempo Iterativo: "<< duration.count() << endl;

}
