package src.Ticketek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Map<String,List<Entrada>> entradas;
    

    public Usuario(String email, String nombre, String apellido, String contrasenia){
        validarUsuario(email, nombre, apellido, contrasenia);
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.entradas = new HashMap<>();
    }

    //#region Atributos
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

    public Map<String, List<Entrada>> getEntradas() {
        return this.entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = (Map<String,List<Entrada>>) entradas;
    }
    //#endregion 

    //#region metodos
    
    private static void validarUsuario(String email, String nombre, String apellido, String contrasenia){
        if(email==null || contrasenia==null || nombre==null || apellido==null){
            throw new IllegalArgumentException("Ocurrio un error al validar el usuario - Datos Vacios");
        }
    }

    public List<Entrada> listarTodasLasEntradas(String email, String contrasenia) {
        if (this.email.equalsIgnoreCase(email) && this.contrasenia.equals(contrasenia)) {
        List<Entrada> todasEntradas = new ArrayList<>();

        Iterator<List<Entrada>> iterator = entradas.values().iterator();
        while (iterator.hasNext()) {
            List<Entrada> lista = iterator.next();
            Iterator<Entrada> entradaIterator = lista.iterator();
            while (entradaIterator.hasNext()) {
                todasEntradas.add(entradaIterator.next());
            }
        }
        return todasEntradas;
    }

        throw new IllegalArgumentException("Email o contraseña incorrectos.");
    }

    public List<Entrada> listarTodasLasEntradasFuturas(String email, String contrasenia) {
    if (this.email.equalsIgnoreCase(email) && this.contrasenia.equals(contrasenia)) {
        List<Entrada> todasEntradas = new ArrayList<>();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate hoy = LocalDate.now();

        for (List<Entrada> lista : entradas.values()) {
            for (Entrada entrada : lista) {
                LocalDate fechaEntrada = LocalDate.parse(entrada.getFecha(), formato);
                if (!fechaEntrada.isBefore(hoy)) {
                    todasEntradas.add(entrada);
                }                
            }
        }

        return todasEntradas;
    }

    throw new IllegalArgumentException("Email o contraseña incorrectos.");
}

    public List<Entrada> agregarEntrada(String nombreEspectaculo, String fecha, String email, int cantidad, Funcion funcion, Sede sede) {
        List<Entrada> entradasVendidas = new ArrayList<>();

         Sector sectorObj = sede.getSectorPorNombre("CAMPO");
        if (sectorObj == null) {
            sectorObj = new Sector("CAMPO", cantidad, 0, 0);
        }
        
        for (int i = 1; i <= cantidad; i++) {            
            int fila = 0;


            Integer asientoGenerado = i;

            Ubicacion ubicacion = new Ubicacion(Ubicacion.TipoUbicacion.CAMPO, fila, asientoGenerado, sectorObj);
            Entrada nuevaEntrada = new Entrada(nombreEspectaculo, fecha, asientoGenerado, "CAMPO", funcion, ubicacion);
            entradasVendidas.add(nuevaEntrada);
        }                
        
       if (!entradas.containsKey(email)) {
        entradas.put(email, entradasVendidas);
        } 
        else {
        entradas.get(email).addAll(entradasVendidas);
        }           
        return entradasVendidas;
    }

    public List<Entrada> agregarEntradaSector(String nombreEspectaculo, String fecha, String email, String sector, int[] asientos, Funcion funcion, Sede sede) {
        List<Entrada> entradasVendidas = new ArrayList<>();        
        Sector sectorObj = sede.getSectorPorNombre(sector);
        if (sectorObj == null) {
            throw new IllegalArgumentException("No se encontró el sector indicado.");
        }
        int asientosPorFila = sectorObj.getAsientosPorFila();
        
        for (int i = 0; i <= asientos.length - 1; i++) {
            int numeroAsiento = asientos[i];
            int fila = numeroAsiento / asientosPorFila;

            int asientoGenerado = asientos[i];
            funcion.setConsumoAdicional(sede.getConsumision());
            Ubicacion ubicacion = new Ubicacion(Ubicacion.TipoUbicacion.PLATEA, fila, asientoGenerado, sectorObj);
            Entrada nuevaEntrada = new Entrada(nombreEspectaculo, fecha, asientoGenerado, sector, funcion,ubicacion);
            entradasVendidas.add(nuevaEntrada);
        }                
        
       if (!entradas.containsKey(email)) {
        entradas.put(email, entradasVendidas);
        } 
        else {
        entradas.get(email).addAll(entradasVendidas);
        }           
        return entradasVendidas;
    }
    
    public void quitarEntrada(IEntrada entrada) {
        List<Entrada> listaEntradas = entradas.get(email);
        if (listaEntradas == null || listaEntradas.isEmpty()) {
            System.out.println("No hay entradas para el usuario con email: " + email);
            return;
        }

        if (listaEntradas == null || !listaEntradas.remove(entrada)) {
            throw new IllegalArgumentException("La entrada no fue encontrada o ya fue anulada.");
        }
    }

    //#endregion



    @Override
    public String toString() {
        StringBuilder StringBuilder = new StringBuilder();
        StringBuilder.append("Usuario{");
        StringBuilder.append("email='").append(email).append('\'');
        StringBuilder.append(", nombre='").append(nombre).append('\'');
        StringBuilder.append(", apellido='").append(apellido).append('\'');
        StringBuilder.append('}');
        return StringBuilder.toString();
    } 

}
