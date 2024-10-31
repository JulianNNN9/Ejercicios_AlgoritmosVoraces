package co.edu.uniquindio.voraces.diapo25;

// Clase que implementa el sistema de conjuntos disjuntos para unir y encontrar raíces de municipios
public class UnionEncontrar {

    private int[] padre;
    private int[] rango;

    public UnionEncontrar(int numMunicipios) {
        padre = new int[numMunicipios];
        rango = new int[numMunicipios];

        // Inicializar cada municipio como su propio representante
        for (int i = 0; i < numMunicipios; i++) {
            padre[i] = i;
            rango[i] = 0;
        }
    }

    // Encuentra el representante del conjunto de un municipio (con compresión de caminos)
    public int encontrar(int municipio) {
        if (padre[municipio] != municipio) {
            padre[municipio] = encontrar(padre[municipio]); // Optimiza la búsqueda futura
        }
        return padre[municipio];
    }

    // Une dos conjuntos si están separados, evita ciclos
    public boolean unir(int municipio1, int municipio2) {
        int raiz1 = encontrar(municipio1);
        int raiz2 = encontrar(municipio2);

        if (raiz1 == raiz2) {
            return false; // Ya están conectados, no se necesita unir
        }

        // Unión por rango para optimizar la estructura
        if (rango[raiz1] > rango[raiz2]) {
            padre[raiz2] = raiz1;
        } else if (rango[raiz1] < rango[raiz2]) {
            padre[raiz1] = raiz2;
        } else {
            padre[raiz2] = raiz1;
            rango[raiz1]++;
        }
        return true;
    }
}
