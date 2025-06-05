package src.Ticketek;
import java.util.List;

public class Miniestadio extends Sede {
    
    private final int puestosComida;
    private int puestosMerch;
    private final Double consumicion;
    private List<Sector> sectores;

    public Miniestadio(Integer capacidad, String direccion, String nombre, int puestosComida, Double consumicion){
        super(nombre, direccion, capacidad);
        this.puestosComida = puestosComida;
        this.consumicion = consumicion;
    }

    public int getPuestosComida() {
        return this.puestosComida;
    }
    public int getPuestosMerch() {
        return this.puestosMerch;
    }
    // public Double getConsumicion() {
    //     return this.consumicion;
    // }
    public List<Sector> getSectores() {
        return this.sectores;
    }
    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    @Override
    public Double getConsumision(){    
        return this.consumicion;
    }

    @Override
    public Sector getSectorPorNombre(String nombreSector) {
        for (Sector s : this.sectores) {
            if (s.getNombre().equalsIgnoreCase(nombreSector)) {
                return s;
            }
        }
        return null;
    }
}

