package src.Ticketek;

import java.util.HashMap;
import java.util.Map;

public class Ubicacion {
    public enum TipoUbicacion {
        CAMPO,
        PLATEA
    }
    
    private TipoUbicacion tipoUbicacion;
    private Sector sector; // puede ser null
    private Integer numeroFila; // puede ser null
    private Integer numeroAsiento; // puede ser null
    private Map<String, Double> recargosPorSector;

    public Ubicacion(TipoUbicacion tipoUbicacion, Integer numeroFila, Integer numeroAsiento, Sector sector){
        this.tipoUbicacion = tipoUbicacion;
        this.sector = sector;
        this.numeroFila = numeroFila;
        this.numeroAsiento = numeroAsiento;

         recargosPorSector = new HashMap<>();
        // Ejemplos de recargos
        recargosPorSector.put("VIP", 500.0);
        recargosPorSector.put("Platea", 300.0);
        recargosPorSector.put("General", 0.0);
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

    public double recargoPorSector(String sector) {
    if (this.sector != null) {
        return recargosPorSector.getOrDefault(sector, 0.0);
    }
    return 0.0;
}
}
