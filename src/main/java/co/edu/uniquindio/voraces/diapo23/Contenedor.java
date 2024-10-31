package co.edu.uniquindio.voraces.diapo23;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
public class Contenedor {

    private List<Objeto> objetos;
    private double pesoMaximo;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el peso máximo soportado por el contenedor: ");
        double pesoMaximo = scanner.nextDouble();

        Contenedor contenedor = new Contenedor(new ArrayList<>(), pesoMaximo);

        // Agregar objetos (nombre, peso, valor, cantidad)
        contenedor.agregarObjeto(new Objeto("Objeto A", 10.0, 60.0, 5));
        contenedor.agregarObjeto(new Objeto("Objeto B", 20.0, 100.0, 3));
        contenedor.agregarObjeto(new Objeto("Objeto C", 30.0, 120.0, 2));
        contenedor.agregarObjeto(new Objeto("Objeto D", 40.0, 240.0, 1));

        // Seleccionar heurística
        System.out.println("Seleccione la heurística:");
        System.out.println("1. Seleccionar el objeto más valioso");
        System.out.println("2. Seleccionar el objeto más ligero");
        System.out.println("3. Seleccionar el objeto con el mayor valor por unidad de peso");
        int heuristica = scanner.nextInt();

        // Llenar el contenedor
        contenedor.llenarContenedor(heuristica);
    }

    public void agregarObjeto(Objeto objeto) {
        objetos.add(objeto);
    }

    public void llenarContenedor(int heuristica) {
        List<Objeto> seleccionados = new ArrayList<>();
        double pesoActual = 0;
        double valorTotal = 0;

        // Ordenar según la heurística seleccionada
        switch (heuristica) {
            case 1:
                objetos.sort(Comparator.comparingDouble(Objeto::getValor).reversed());
                break;
            case 2:
                objetos.sort(Comparator.comparingDouble(Objeto::getPeso));
                break;
            case 3:
                objetos.sort(Comparator.comparingDouble(Objeto::getValorPorUnidadPeso).reversed());
                break;
            default:
                System.out.println("Heurística no válida.");
                return;
        }

        for (Objeto objeto : objetos) {
            while (pesoActual < pesoMaximo && objeto.getCantidad() > 0) {
                if (pesoActual + objeto.getPeso() <= pesoMaximo) {
                    seleccionados.add(objeto);
                    pesoActual += objeto.getPeso();
                    valorTotal += objeto.getValor();
                    objeto.reducirCantidad(1); // Usar una unidad del objeto
                } else {
                    // Fraccionar el objeto
                    double pesoRestante = pesoMaximo - pesoActual;
                    double valorFraccionado = objeto.getValor() * (pesoRestante / objeto.getPeso());
                    seleccionados.add(new Objeto(objeto + " (Fraccionado)", pesoRestante, valorFraccionado, 1));
                    pesoActual += pesoRestante; // Llenar el contenedor
                    valorTotal += valorFraccionado;
                    break; // Salir del bucle, ya que el contenedor está lleno
                }
            }
        }

        // Mostrar resultados
        mostrarResultados(seleccionados, valorTotal);
    }

    private void mostrarResultados(List<Objeto> seleccionados, double valorTotal) {
        System.out.println("Objetos seleccionados:");
        for (Objeto objeto : seleccionados) {
            System.out.println(objeto);
        }
        System.out.println("Valor total de la carga: " + valorTotal);
    }

}
