package src.Ticketek;

public class Sector {
    private String nombre;                  // Por ejemplo: "Platea VIP"
    private int capacidad;                  // Capacidad total del sector
    private int porcentajeAdicional;        // Porcentaje adicional aplicado
    private int asientosPorFila;            // Cantidad de asientos por fila

    public Sector(String nombre, int capacidad, int porcentajeAdicional, int asientosPorFila) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.asientosPorFila = asientosPorFila;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getPorcentajeAdicional() {
        return porcentajeAdicional;
    }

    public void setPorcentajeAdicional(int porcentajeAdicional) {
        this.porcentajeAdicional = porcentajeAdicional;
    }

    public int getAsientosPorFila() {
        return asientosPorFila;
    }

    public void setAsientosPorFila(int asientosPorFila) {
        this.asientosPorFila = asientosPorFila;
    }

    public int getCantidadFilas() {
        if (asientosPorFila == 0) return 0;
        return capacidad / asientosPorFila;
    }

    public int getCapacidadTotalConAdicional() {
        return capacidad + (capacidad * porcentajeAdicional / 100);
    }

    public double aplicarRecargo(double precioBase) {
    return precioBase * (1 + (porcentajeAdicional / 100.0));
}

}
