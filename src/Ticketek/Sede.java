package src.Ticketek;

public abstract class Sede {
    protected String nombre;
    protected String direccion;
    protected Integer capacidad;
    
    public Sede(String nombre, String direccion, Integer capacidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
    }

    public Integer getCapacidad() {
        return this.capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract Sector getSectorPorNombre(String nombreSector);

    public abstract Double getConsumision();
    
    @Override
    public String toString() {
        StringBuilder StringBuilder = new StringBuilder();
        StringBuilder.append("Sede{");
        StringBuilder.append(", nombre='").append(nombre).append('\'');
        StringBuilder.append("direccion='").append(direccion).append('\'');
        StringBuilder.append(", capacidad='").append(capacidad).append('\'');
        StringBuilder.append('}');
        return StringBuilder.toString();
    }

}
