package src.Ticketek;

public class Entrada {
    private String codigo;
    private Espectaculo espectaculo;
    private Funcion funcion;
    private Ubicacion ubicacion;
    private double precioFinal;

    public Entrada(String codigo,Funcion funcion, Espectaculo espectaculo,Ubicacion ubicacion, double precioFinal){
        this.codigo = codigo;
        this.funcion = funcion;
        this.espectaculo = espectaculo;
        this.ubicacion = ubicacion;
        this.precioFinal = precioFinal;
    }


    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Espectaculo getEspectaculo() {
        return this.espectaculo;
    }

    public void setEspectaculo(Espectaculo espectaculo) {
        this.espectaculo = espectaculo;
    }

    public double getPrecioFinal() {
        return this.precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Funcion getFuncion() {
        return this.funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

}

