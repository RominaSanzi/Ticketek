package src.Ticketek;

public class Funcion {
    private String sede;
    private String fecha;
    private double precioBase;

    public Funcion(String sede, String fecha, double precioBase){
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
    }


    public String getSede() {
        return this.sede;
    }

    public String getFecha() {
        return this.fecha;
    }

    public double getPrecioBase() {
        return this.precioBase;
    }

}

