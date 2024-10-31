package co.edu.uniquindio.voraces.diapo25;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Clase principal para conectar municipios minimizando el costo total
public class RedFibraOptica {

    public static void main(String[] args) {

        int numeroMunicipios = 6; // Número de municipios en el departamento

        // Lista de conexiones posibles (aristas) con su costo estimado
        List<Conexion> conexionesPosibles = new ArrayList<>();
        conexionesPosibles.add(new Conexion(0, 1, 4));
        conexionesPosibles.add(new Conexion(0, 2, 3));
        conexionesPosibles.add(new Conexion(1, 2, 2));
        conexionesPosibles.add(new Conexion(1, 3, 6));
        conexionesPosibles.add(new Conexion(2, 3, 5));
        conexionesPosibles.add(new Conexion(2, 4, 7));
        conexionesPosibles.add(new Conexion(3, 4, 8));
        conexionesPosibles.add(new Conexion(3, 5, 9));

        // Obtener la lista de conexiones seleccionadas para la red de menor costo
        List<Conexion> resultado = calcularRedMinima(numeroMunicipios, conexionesPosibles);

        // Mostrar el resultado de las conexiones seleccionadas y el costo total
        System.out.println("Conexiones seleccionadas para la red de fibra óptica:");
        int costoTotal = 0;
        for (Conexion conexion : resultado) {
            System.out.println("Municipio " + conexion.municipio1 + " - Municipio " + conexion.municipio2 +
                    " con costo: " + conexion.costo);
            costoTotal += conexion.costo;
        }
        System.out.println("Costo total de la red: " + costoTotal + " pesos colombianos");
    }

    // Método que calcula la red de menor costo usando el algoritmo de Kruskal
    public static List<Conexion> calcularRedMinima(int numeroMunicipios, List<Conexion> conexiones) {
        // 1. Ordenar las conexiones por costo en orden ascendente
        conexiones.sort(Comparator.comparingInt(c -> c.costo));

        // 2. Inicializar Union-Find para controlar las conexiones y evitar ciclos
        UnionEncontrar unionFind = new UnionEncontrar(numeroMunicipios);

        // Lista para almacenar las conexiones seleccionadas en el árbol de expansión mínimo
        List<Conexion> redMinima = new ArrayList<>();

        // 3. Recorrer todas las conexiones y añadir las más baratas que no formen ciclos
        for (Conexion conexion : conexiones) {
            // Unir solo si no se forma un ciclo
            if (unionFind.unir(conexion.municipio1, conexion.municipio2)) {
                redMinima.add(conexion); // Añadir la conexión a la red de menor costo
            }

            // Terminar cuando ya tengamos numeroMunicipios - 1 conexiones en la red
            if (redMinima.size() == numeroMunicipios - 1) {
                break;
            }
        }

        return redMinima; // Retorna la lista de conexiones seleccionadas
    }

}
