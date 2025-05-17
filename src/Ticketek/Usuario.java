package src.Ticketek;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private List<Entrada> entradas;

    public Usuario(String email, String nombre, String apellido, String contrasenia, List<Entrada> entradas){
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.entradas = entradas;
    }



    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Entrada> getEntradas() {
        return this.entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }



}
