package co.edu.uniquindio.voraces.diapo22;

import java.util.HashMap;
import java.util.Map;

public class Cajero {

    // Cantidad de billetes disponibles por denominación
    private static final Map<Integer, Integer> billetesDisponibles = new HashMap<>();

    static {
        billetesDisponibles.put(100000, 50);
        billetesDisponibles.put(50000, 100);
        billetesDisponibles.put(20000, 200);
        billetesDisponibles.put(10000, 300);
    }

    public static void main(String[] args) {

        int montoSolicitado = 620000;  // Monto que desea retirar

        Map<Integer, Integer> resultado = entregarDinero(montoSolicitado);

        if (resultado == null) {
            System.out.println("No hay suficiente cantidad de billetes para entregar el monto solicitado.");
        } else {
            System.out.println("Billetes entregados:");
            for (Map.Entry<Integer, Integer> entry : resultado.entrySet()) {
                System.out.println("Billetes de " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public static Map<Integer, Integer> entregarDinero(int montoSolicitado) {

        if (montoSolicitado % 10000 != 0) {
            System.out.println("El monto solicitado debe ser divisible por 10000.");
            return null;
        }

        Map<Integer, Integer> billetesEntregados = new HashMap<>();
        int montoRestante = montoSolicitado;

        for (int denominacion : billetesDisponibles.keySet()) {
            int cantidadDisponible = billetesDisponibles.get(denominacion);
            int cantidadEntregar = Math.min(montoRestante / denominacion, cantidadDisponible);

            if (cantidadEntregar > 0) {
                billetesEntregados.put(denominacion, cantidadEntregar);
                montoRestante -= cantidadEntregar * denominacion;
            }
        }

        if (montoRestante > 0) {
            return null; // No hay suficientes billetes para cubrir el monto solicitado
        }

        // Actualizamos la cantidad de billetes disponibles
        for (Map.Entry<Integer, Integer> entry : billetesEntregados.entrySet()) {
            int denominacion = entry.getKey();
            int cantidad = entry.getValue();
            billetesDisponibles.put(denominacion, billetesDisponibles.get(denominacion) - cantidad);
        }

        return billetesEntregados;
    }
}

