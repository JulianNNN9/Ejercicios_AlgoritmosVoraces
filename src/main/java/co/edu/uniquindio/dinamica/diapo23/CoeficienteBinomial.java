package co.edu.uniquindio.dinamica.diapo23;

import java.util.HashMap;
import java.util.Map;

public class CoeficienteBinomial {

    // Mapa de memorización para almacenar los resultados ya calculados
    private Map<String, Long> memo;

    // Constructor para inicializar el mapa
    public CoeficienteBinomial() {
        this.memo = new HashMap<>();
    }

    // Método para calcular el coeficiente binomial C(n, k)
    public long calcularCoeficienteBinomial(int n, int k) {
        // Caso base: C(n, 0) = C(n, n) = 1
        if (k == 0 || k == n) {
            return 1;
        }

        // Crear clave única para memorización
        String clave = n + "," + k;

        // Si el valor ya está calculado, retornarlo del mapa de memorización
        if (memo.containsKey(clave)) {
            return memo.get(clave);
        }

        // Calcular C(n, k) = C(n-1, k-1) + C(n-1, k) y almacenarlo en el mapa
        long resultado = calcularCoeficienteBinomial(n - 1, k - 1) + calcularCoeficienteBinomial(n - 1, k);
        memo.put(clave, resultado); // Guardar en memorización

        return resultado;
    }

    public static void main(String[] args) {

        CoeficienteBinomial coefBin = new CoeficienteBinomial();

        int n = 5;
        int k = 2;
        System.out.println("El coeficiente binomial C(" + n + ", " + k + ") es: " + coefBin.calcularCoeficienteBinomial(n, k));
    }
}

