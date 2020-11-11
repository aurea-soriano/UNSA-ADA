/* vamos a implementar la version de programacion dinamica
para obtener nuestro valor optimo en el problema
de weighted interval scheduling O(nLogn)*/

import java.util.Arrays;
import java.util.Comparator;


public class Job{
  int startTime, finishTime, weight; // weight-> ganacia profit lucro

  Job(int start, int finish, int w){
    startTime = start;
    finishTime  = finish;
    weight  = w;
  }

  public static void main(String[] args){
    Job jobs[] = {new Job(1,4,50), new Job(3,5,20), new Job(0,6,100),
                  new Job(4,7,70), new Job(3,8,40), new Job(5,9,30),
                  new Job(6,10,60), new Job(4,11,200)};
    WIS wis = new WIS();
    System.out.println("Nuestro peso óptimo es: "+ wis.schedule(jobs));
  }
}

//esto va a servir para ordenar por los tiempos finales

class JobComparator implements  Comparator<Job>{
  public int compare(Job a, Job b){
    return a.finishTime< b.finishTime? -1: a.finishTime==b.finishTime?0:1;
  }
}

class WIS{
  /*buscar dentro los j-1 trabajos aquel trabajo
  que termine antes del j, y que su indice sea el ma's alto
  Nuestra funcion retornar'a -1 si todos los trabajos
  tienen conflicto con el trabajo del index.


  */
  int binarySearch(Job jobs[], int index){

    // vamos a tener dos variables lo y hi que van a servirnos
    // nuestra busqueda

    int lo=0, hi=index-1;

    // iterativamente vamos desarrollar nuestra busqueda binaria
    while(lo<=hi){
      int mid = (lo+hi)/2;
      if(jobs[mid].finishTime <= jobs[index].startTime){
        if(jobs[mid+1].finishTime <= jobs[index].startTime){
          lo = mid+1;
        }else{
          return mid;
        }

      }else{
        hi = mid-1;
      }

    }
    return -1;
  }


  // esta funcion retorna el maximo peso, lucro posible
  // dado el conjunto de jobs que entran
  public int schedule(Job jobs[]){

    //ordenar nuestros jobs de acuerdo a la finalizacion
    Arrays.sort(jobs, new JobComparator());

    //crear nuestra memo donde guardaremos nuestras
    // soluciones a los subproblemas
    // memo[i] va a guardar el lucro el peso para los trabajos
    // hasta los jobs[i]

    int n = jobs.length ;
    int memo[] = new int[n];
    memo[0] = jobs[0].weight;

    // vamoes a rellenar las entradas de nuestra memo
    for(int i=1; i<n; i++){

      // encontrar el peso incluyendo el job actual
      int inclWeight = jobs[i].weight;
      // este es el index de nuestro trabajo que
      // acaba antes de i y tiene el maximo index
      int maxJobIndex = binarySearch(jobs, i);

      if(maxJobIndex !=-1){
        inclWeight += memo[maxJobIndex];
      }

      //guardar el maximo de considerar o no nuestro i
      memo[i]= Math.max(inclWeight, memo[i-1]);


    }
    return memo[n-1];

  }
}
