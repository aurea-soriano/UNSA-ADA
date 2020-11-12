/* vamos a implementar la version de pd para obtener nuestro valor optimo
en el problema de programacion de intervalos ponderados
O(nlogn)*/

import java.util.Arrays;
import java.util.Comparator;



public class Job{

    int startTime, finishTime, weight;//weight-> ganancia lucro

    Job(int start, int finish, int profit){
      startTime = start;
      finishTime = finish;
      weight = profit;
    }

    public static void main(String[] args){

      Job jobs[] = {new Job(1,4,50), new Job(3,5,20), new Job(0,6,100),
          new Job(4,7,70), new Job(3,8,40), new Job(5,9,30),
          new Job(6,10,60), new Job(4,11,200)};

      WIS wis = new WIS();
      System.out.println("Nuestro peso(ganancia) optimo es :"+ wis.schedule(jobs));
    }
}


// una forma de comparar para ordenar los tiempos finales

class JobComparator implements Comparator<Job>{
  public int compare(Job a, Job b){
    return a.finishTime<b.finishTime?-1:a.finishTime==b.finishTime?0:1;
  }
}

class WIS{

    /** buscar dentro de los j-1 trabajos aquel trabajo que termine antes
    del j y que su indice sea el mas alto. Nuestra funcion tendria que
    retornar -1 si todos los trabajos tienen conflicto o sea se sobreponen con
    el trabajo index**/
    int binarySearch(Job jobs[], int index){

        int lo=0, hi=index-1;

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


    /* vamos a retornar el maximo lucro, ganacia posible
    dado el conjunto de jobs que entran*/
    public int schedule(Job jobs[]){

      //ordenar los jobs por tiempo de finalizacion
      Arrays.sort(jobs, new JobComparator());

      int n = jobs.length;
      // M es nuestra memo donde guardaremos nuestras soluciones
      // a los problemas ya resueltos
      //M[i] va a ser el lucro, la ganancia para los trabajos hasta los jobs[i]
      int M[] = new int[n];

      M[0] = jobs[0].weight;


      //vamos a rellenar las entradas de M
      for(int i=1; i<n; i++){

        //encontrar el peso incluyendo el job actual
        int inclWeight = jobs[i].weight;

        // es el indiex de nuestro job que acaba antes de index y tiene el maximo index
        int maxJobIndex = binarySearch(jobs,i);


        if(maxJobIndex!=-1){
          inclWeight+=M[maxJobIndex];
        }

        // guardar el maximo de considerar el i o no
        M[i] = Math.max(inclWeight, M[i-1]);

      }
      return M[n-1];
    }
}
