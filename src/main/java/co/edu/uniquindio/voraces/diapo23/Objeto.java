package co.edu.uniquindio.voraces.diapo23;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Objeto {

    private String nombre;
    private Double peso;
    private Double valor;
    private Integer cantidad;

    public double getValorPorUnidadPeso() {
        return valor / peso;
    }

    public void reducirCantidad(int cantidadUsada) {
        this.cantidad -= cantidadUsada;
    }
}
