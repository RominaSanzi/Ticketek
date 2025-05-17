package src.Ticketek;

public class Sector {
    public enum TipoSector{
        VIP,
        COMUN,
        BAJA,
        ALTA
    }
    private TipoSector tipoSector;
    private Integer cantidadDeFilas;
    private Integer asientosPorFila;

    public Sector(TipoSector tipoSector, Integer cantidadDeFilas, Integer asientosPorFila){
        this.tipoSector = tipoSector;
        this.cantidadDeFilas = cantidadDeFilas;
        this.asientosPorFila = asientosPorFila;
    }


    public TipoSector getTipoSector() {
        return this.tipoSector;
    }

    public void setTipoSector(TipoSector tipoSector) {
        this.tipoSector = tipoSector;
    }

    public Integer getCantidadDeFilas() {
        return this.cantidadDeFilas;
    }

    public void setCantidadDeFilas(Integer cantidadDeFilas) {
        this.cantidadDeFilas = cantidadDeFilas;
    }

    public Integer getAsientosPorFila() {
        return this.asientosPorFila;
    }

    public void setAsientosPorFila(Integer asientosPorFila) {
        this.asientosPorFila = asientosPorFila;
    }


}
