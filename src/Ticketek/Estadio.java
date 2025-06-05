package src.Ticketek;
public class Estadio extends Sede {

    private final boolean campo = true;
    
    public Estadio(String nombre, String direccion, Integer capacidad) {
        super(nombre, direccion, capacidad);

    }

    public boolean isCampo() {
        return this.campo;
    }

    @Override
    public Double getConsumision(){
        return 0.0;
    }
    @Override
    public Sector getSectorPorNombre(String nombreSector) {        
        return null;
    }

}
