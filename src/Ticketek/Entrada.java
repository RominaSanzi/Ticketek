package src.Ticketek;

import java.util.Objects;

public class Entrada implements IEntrada{
    private String nombreEspectaculo;
    private String fecha;
    private int asiento;
    private String sector;
    private double precioFinal;
    private Funcion funcion;
    private Ubicacion ubicacion;

    public Entrada(String nombreEspectaculo, String fecha, int asiento, String sector, Funcion funcion, Ubicacion ubicacion){ 
        validarEntrada(nombreEspectaculo,fecha,asiento,sector);       
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
        this.asiento = asiento;
        this.sector = sector;
        this.funcion = funcion;
        this.ubicacion = ubicacion;
        this.precioFinal = funcion.getPrecioBase() + ubicacion.recargoPorSector(sector);        
        // this.funcion = funcion;
        // this.espectaculo = espectaculo;
        // this.ubicacion = ubicacion;
        
    }

    //#region atributos
    public String getNombreEspectaculo() {
        return this.nombreEspectaculo;
    }

    public void setNombreEspectaculo(String codigo) {
        this.nombreEspectaculo = codigo;
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

        public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAsiento() {
        return this.asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }




    //#endregion
    
    //#region metodos
    public void validarEntrada(String nombreEspectaculo, String fecha, int asiento, String sector){
        if(nombreEspectaculo == null || fecha==null || asiento==0 || sector==null){
            throw new IllegalArgumentException("Error: datos incorrectos");
        }
    }

    public boolean esIgual(IEntrada otra) {
        if (otra == null) return false;
        if (!(otra instanceof Entrada)) return false;
        Entrada e = (Entrada) otra;
        return this.nombreEspectaculo.equals(e.getNombreEspectaculo()) &&
               this.fecha.equals(e.getFecha()) &&
               this.sector.equals(e.getSector()) &&
               this.asiento == e.getAsiento();
    }

    //#endregion

    @Override
    public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Entrada{");
    stringBuilder.append(", nombre del espectaculo='").append(nombreEspectaculo).append('\'');
    stringBuilder.append(", fecha='").append(fecha).append('\'');
    stringBuilder.append(", Sector='").append(sector).append('\'');
    stringBuilder.append(", Asiento='").append(asiento).append('\'');
    stringBuilder.append('}');
    return stringBuilder.toString();
}


    @Override
    public double precio() {
        return this.precioFinal;
    }


    @Override
    public String ubicacion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ubicacion'");
    }

@Override
public boolean equals(Object objeto) {
    if (this == objeto) return true;
    if (!(objeto instanceof Entrada)) return false;

    Entrada otra = (Entrada) objeto;

    return this.nombreEspectaculo.equals(otra.nombreEspectaculo)
            && this.fecha.equals(otra.fecha)
            && this.sector.equals(otra.sector)
            && this.asiento == otra.asiento;
}

@Override
public int hashCode() {
    return Objects.hash(nombreEspectaculo, fecha, sector, asiento);
}
}

