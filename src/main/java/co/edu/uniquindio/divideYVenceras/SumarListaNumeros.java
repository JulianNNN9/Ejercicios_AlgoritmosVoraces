package co.edu.uniquindio.divideYVenceras;

public class SumarListaNumeros {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int totalSum = sumArray(numbers, 0, numbers.length - 1);
        System.out.println("La suma total es: " + totalSum);
    }

    public static int sumArray(int[] arr, int inicio, int fin) {
        // Caso base: si el arreglo tiene solo un elemento, lo retornamos
        if (inicio == fin) {
            return arr[inicio];
        }

        // Dividimos el arreglo en dos mitades
        int medio = (inicio + fin) / 2;

        // Calculamos la suma de cada mitad de forma recursiva
        int sumaIzq = sumArray(arr, inicio, medio);
        int sumaDer = sumArray(arr, medio + 1, fin);

        // Combinamos las dos mitades sumándolas
        return sumaIzq + sumaDer;
    }

}
