package src.Ticketek;
import java.util.ArrayList;
import java.util.List;

public class Teatro extends Sede {
    private List<Sector> sectores = new ArrayList<>();

    public Teatro(int capacidad, String direccion, String nombre,
                  int asientosPorFila, String[] nombresSectores, int[] capacidades, int[] porcentajesAdicionales) {
        super(nombre, direccion, capacidad);
        
        for (int i = 0; i < nombresSectores.length; i++) {
        String nombreSector = nombresSectores[i];
        int capacidadSector = capacidades[i];
        int porcentaje = porcentajesAdicionales[i];
        int recargo = super.capacidad * porcentaje / 100;
        Sector sector = new Sector(nombreSector, capacidadSector, recargo, asientosPorFila);
        this.sectores.add(sector);
    }
    
    
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    @Override
    public Double getConsumision(){
        return 0.0;
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
