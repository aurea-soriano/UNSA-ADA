//Main function
// C++ program para el problema de emparejamiento
#include <iostream>
#include <vector>
#define N  4


// Esta función devuelve Verdadero si una mujer 'm' prefiere al hombre 'h1' en vez del hombre 'h'
bool wPrefiereM1SobreM(int preferencias[2*N][N], int m, int h, int h1,  int nroParejas){
    // Verificar si m prefiere a h sobre su actual pareja h1
    for (int i = 0; i < nroParejas; ++i){ //---nroParejas
        // Si m1 viene antes en la lista de m, entonces m prefiere su pareja actual, entonces no hacer nada
        if (preferencias[m][i] == h1){   //O(max(1, max(1,0)))
            return true;
        }

        // Si m viene antes de h1 en la lista de m, entonces libera su pareja actual y empareja a m con h
        else{
          if (preferencias[m][i] == h){
            return false;
          }
        }
    }
}
// O(nroParejas)

// Imprimir emparejamiento estable para N hombres y N mujeres. Los hombres están enumerados de 0 a N-1.
// Las mujeres están enumeradas de N a 2N-1
// nroParejas Número de hombres y mujeres
void emparejamientoEstable(int preferencias[2*N][N],  int nroParejas){
    // wPareja Guarda las parejas de las mujeres. Este es nuestro arreglo de salida
    // que guarda información. El valor de wPareja[i] indica la pareja que fue
    // asignada a la mujer N+i.  Notar que la mujer va de N a 2*N-1. El valor -1
    // indica que la mujer N+i está libre


    // mLibre Guarda la disponibilidad de los hombres.  Si mLibre[i] es
    // falso, entonces el hombre 'i' está libre, caso contrario está emparejado.

    // Inicializamos todos los hombres y mujeres como libres
    int wPareja[nroParejas];
    bool mLibre[N];

    memset(wPareja, -1, sizeof(wPareja));
    memset(mLibre, false, sizeof(mLibre));

    int libreCount = nroParejas; // numero de hombres que están libres

    // Mientras haya hombres libres
    while (libreCount > 0){
        // Seleccionamos el primer hombre libre(podríamos escoger cualquiera)
        int h;
        for (h = 0; h < nroParejas; ++h){
            if (mLibre[h] == false){
                break;
            }
        }
        // Recorremos todas las mujeres una por una de acuerdo a la lista de preferencias de los hombres
        // Aquí h es seleccionado como un hombre libre
        for (int i = 0; i < nroParejas && mLibre[h] == false; ++i){
            int m = preferencias[h][i];

            // La mujer de preferencia está libre, m y h se convierten en pareja
            //(Notar que los emparejamientos pueden ser modificados después).
            // Entonces podemos decir que por lo pronto es una unión temporal.
            if (wPareja[m-nroParejas] == -1){
                wPareja[m-nroParejas] = h;
                mLibre[h] = true; // marcarlo como emparejado
                --libreCount;
            }

            else{  // Si m no está libre
                // Encontramos la actual pareja de m
                int h1 = wPareja[m-nroParejas];

                // Si m prefiere a h sobre su pareja actual h1,
                // entonces quiebra la relación entre  m y h1 y
                // empareja h com m.
                if (wPrefiereM1SobreM(preferencias, m, h, h1, nroParejas) == false){
                    wPareja[m-nroParejas] = h;
                    mLibre[h] = true;// hemos emparejado a h
                    mLibre[h1] = false; // hemos dejado a nuestro hombre anterior, lo volvemos libre
                }
            } // Fin del  Else
        } // Fin del for que va recorriendo todas las mujeres en la lista de h
    } // Fin del while principal


    // Imprimir la solución
    std::cout << "Mujer   Hombre" << std::endl;
    for (int i = 0; i < nroParejas; ++i){
        std::cout << " " << i+nroParejas << "\t" << wPareja[i] << std::endl;
    }
}


int main(){


    int preferencias[2*N][N]= {
        {7, 5, 6, 4},
        {5, 4, 6, 7},
        {4, 5, 6, 7},
        {4, 5, 6, 7},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
    };
    int nroParejas = 4;


    emparejamientoEstable(preferencias, nroParejas);

    return 0;
}
