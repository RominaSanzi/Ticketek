package src.Ticketek;

import java.util.List;

public class Espectaculo{
    private String codigo;
    private String nombre;
    private List<Funcion> funciones;

    public Espectaculo(String codigo, String nombre, List<Funcion> funciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.funciones = funciones;
    }


    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
    


}