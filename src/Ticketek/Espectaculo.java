package src.Ticketek;

import java.util.List;

public class Espectaculo{
    private String nombre;
    private List<Funcion> funciones;

    public Espectaculo(String nombre) {
        this.nombre = nombre;
        this.funciones = new java.util.ArrayList<>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Funcion> getFunciones() {
        return this.funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

    @Override
    public String toString() {
        StringBuilder StringBuilder = new StringBuilder();
        StringBuilder.append("Espectaculo{");
        StringBuilder.append("nombre='").append(nombre).append('\'');
        StringBuilder.append('}');
        return StringBuilder.toString();
    } 

}