package co.edu.uniquindio.dinamica.diapo18;

import java.util.HashMap;
import java.util.Map;

public class SucesionLucas {

    // Mapa de memorización para almacenar los resultados ya calculados
    private Map<Integer, Long> memo;

    // Constructor para inicializar el mapa
    public SucesionLucas() {
        this.memo = new HashMap<>();
    }

    // Método para calcular el término n de la sucesión de Lucas
    public long calcularLucas(int n) {
        // Caso base: L(0) = 2 y L(1) = 1
        if (n == 0) return 2;
        if (n == 1) return 1;

        // Si el valor ya está calculado, retornarlo del mapa de memorización
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Calcular L(n) = L(n-1) + L(n-2) y almacenarlo en el mapa
        long resultado = calcularLucas(n - 1) + calcularLucas(n - 2);
        memo.put(n, resultado); // Guardar en memorización

        return resultado;
    }

    public static void main(String[] args) {

        SucesionLucas sucesionLucas = new SucesionLucas();

        int n = 10;
        System.out.println("El término L(" + n + ") de la sucesión de Lucas es: " + sucesionLucas.calcularLucas(n));
    }
}
