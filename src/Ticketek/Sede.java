package src.Ticketek;

public abstract class Sede {
    protected Integer capacidad;
    protected String direccion;
    protected String nombre;

    public Sede(Integer capacidad, String direccion, String nombre){
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.nombre = nombre;
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


    

}
