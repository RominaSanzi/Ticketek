package src.Ticketek;

public class Ubicacion {
    public enum TipoUbicacion {
        CAMPO,
        PLATEA
    }
    
    private TipoUbicacion tipoUbicacion;
    private Sector sector; // puede ser null
    private Integer numeroFila; // puede ser null
    private Integer numeroAsiento; // puede ser null

    public Ubicacion(TipoUbicacion tipoUbicacion, Integer numeroFila, Integer numeroAsiento, Sector sector){
        this.tipoUbicacion = tipoUbicacion;
        this.sector = sector;
        this.numeroFila = numeroFila;
        this.numeroAsiento = numeroAsiento;
    }


    public Sector getSector() {
        return this.sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public TipoUbicacion getTipoUbicacion() {
        return this.tipoUbicacion;
    }

    public void setTipoUbicacion(TipoUbicacion tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    public Integer getNumeroFila() {
        return this.numeroFila;
    }

    public void setNumeroFila(Integer numeroFila) {
        this.numeroFila = numeroFila;
    }

    public Integer getNumeroAsiento() {
        return this.numeroAsiento;
    }

    public void setNumeroAsiento(Integer numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

}
