package co.edu.uniquindio.dinamica.diapo31;

public class Mochila {

    // Método para resolver el problema de la mochila usando programación dinámica
    public static int resolverMochila(int pesoMaximo, int[] pesos, int[] valores) {

        int n = valores.length;
        int[][] dp = new int[n + 1][pesoMaximo + 1];

        // Llenado de la tabla DP
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= pesoMaximo; w++) {
                if (pesos[i - 1] <= w) {
                    // Tomamos el máximo entre incluir el objeto o no incluirlo
                    dp[i][w] = Math.max(dp[i - 1][w], valores[i - 1] + dp[i - 1][w - pesos[i - 1]]);
                } else {
                    // Si el peso del objeto es mayor que el peso actual, no lo incluimos
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][pesoMaximo];
    }

    // Método para obtener los elementos seleccionados que maximizaron el valor
    public static void obtenerElementosSeleccionados(int pesoMaximo, int[] pesos, int[] valores, int[][] dp) {
        int n = valores.length;
        int w = pesoMaximo;

        System.out.println("Objetos seleccionados:");
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Objeto " + i + " (valor: " + valores[i - 1] + ", peso: " + pesos[i - 1] + ")");
                w -= pesos[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        int pesoMaximo = 8;
        int[] pesos = {1, 3, 4, 5, 7};
        int[] valores = {2, 5, 10, 14, 15};

        int n = valores.length;
        int[][] dp = new int[n + 1][pesoMaximo + 1];

        // Llenado de la tabla DP y obtener el valor máximo
        int valorMaximo = resolverMochila(pesoMaximo, pesos, valores);
        System.out.println("El valor máximo que se puede obtener es: " + valorMaximo);

        // Obtener los elementos seleccionados
        obtenerElementosSeleccionados(pesoMaximo, pesos, valores, dp);
    }
}

