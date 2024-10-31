package co.edu.uniquindio.dinamica.diapo31;

import java.util.HashMap;
import java.util.Map;

public class Mochila {

    // Mapa de memorización para almacenar los resultados de subproblemas
    private Map<String, Integer> memo;

    // Constructor para inicializar el mapa de memorización
    public Mochila() {
        this.memo = new HashMap<>();
    }

    // Método principal para resolver el problema de la mochila
    public int resolverMochila(int pesoMaximo, int[] pesos, int[] valores, int n) {
        // Caso base: Si no hay objetos o el peso máximo es 0, el valor máximo es 0
        if (n == 0 || pesoMaximo == 0) {
            return 0;
        }

        // Crear una clave única para este subproblema
        String clave = n + "," + pesoMaximo;

        // Si el resultado ya está calculado, devolverlo del mapa de memorización
        if (memo.containsKey(clave)) {
            return memo.get(clave);
        }

        int resultado;

        // Si el peso del objeto actual es mayor al peso permitido, se omite
        if (pesos[n - 1] > pesoMaximo) {
            resultado = resolverMochila(pesoMaximo, pesos, valores, n - 1);
        } else {
            // Caso contrario: elegir el máximo entre incluir o no el objeto
            int incluir = valores[n - 1] + resolverMochila(pesoMaximo - pesos[n - 1], pesos, valores, n - 1);
            int excluir = resolverMochila(pesoMaximo, pesos, valores, n - 1);
            resultado = Math.max(incluir, excluir);
        }

        // Almacenar el resultado en el mapa de memorización
        memo.put(clave, resultado);
        return resultado;
    }

    public static void main(String[] args) {
        // Definición del problema
        int pesoMaximo = 8;
        int[] pesos = {1, 3, 4, 5, 7};       // Pesos de los objetos A, B, C, D, E
        int[] valores = {2, 5, 10, 14, 15};  // Valores de los objetos A, B, C, D, E
        int n = valores.length;

        // Crear instancia de la clase y resolver el problema
        Mochila mochila = new Mochila();
        int valorMaximo = mochila.resolverMochila(pesoMaximo, pesos, valores, n);

        System.out.println("El valor máximo que se puede obtener es: " + valorMaximo);
    }
}

