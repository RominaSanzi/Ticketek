package src.Ticketek;

import java.util.ArrayList;
import java.util.List;

public class Ticketek{
    private List<Usuario> usuarios;
    private List<Espectaculo> espectaculos;
    private List<Sede> sedes;

    public Ticketek(){
        usuarios = new ArrayList<>();
        espectaculos = new ArrayList<>();
        sedes = new ArrayList<>();
    }

    //#region usuarios
    public void registrarUsuario(String email, String nombre, String apellido, String contrasenia){
        // validar si existe el usuario
        if(existeUsuario(email, contrasenia)){
            throw new IllegalArgumentException("Error: Ya existe un usuario con ese email.");
        }
        Usuario nuevoUsuario = new Usuario(email, nombre, apellido, contrasenia);        
        usuarios.add(nuevoUsuario);
    }

    private Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    // private Usuario buscarUsuarioPorContrasenia(String contrasenia) {
    //     for (Usuario usuario : usuarios) {
    //         if (usuario.getContrasenia().equals(contrasenia)) {
    //             return usuario;
    //         }
    //     }
    //     throw new RuntimeException("contraseña incorrecta");
    // }

    private Usuario buscarUsuarioPorContraseniaYEntrada(IEntrada eEntrada, String contrasenia) {
        if(!existeUsuario(contrasenia)){
            throw new IllegalArgumentException("Error: no existe un usuario con esa contraseña.");
        }
        for (Usuario usuario : usuarios) {
            for (List<Entrada> entradas : usuario.getEntradas().values()) {
                for (Entrada entrada : entradas) {
                    if(entrada.equals(eEntrada)){
                        return usuario;
                    }
                    
                }
            }
        }
        return null;
    }

    public Sector obtenerSectorPorNombre(String nombreSector){
        for(Sede sede : sedes){
            Sector sector = buscarSectorEnSede(sede.nombre, nombreSector);
            if(sector != null){
                return sector;
            }
        }
        return null;
    }
    
    public boolean existeUsuario(String email, String contrasenia){
        for(Usuario usuario : usuarios){
            if(usuario.getEmail().equalsIgnoreCase(email) && usuario.getContrasenia().equalsIgnoreCase(contrasenia)){
                return true;
            }
        }
        return false;
    }

    public boolean existeUsuario(String contrasenia){
        for(Usuario usuario : usuarios){
            if(usuario.getContrasenia().equalsIgnoreCase(contrasenia)){
                return true;
            }
        }
        return false;
    }
    //#endregion

    //#region entradas
    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {        
            if (!existeUsuario(email, contrasenia)) {
                throw new IllegalArgumentException("Usuario no encontrado.");
            }
            Usuario usuario = buscarUsuarioPorEmail(email);
            List<Entrada> entradasUsuarios = usuario.listarTodasLasEntradas(email, contrasenia);
            return new ArrayList<IEntrada>(entradasUsuarios);                
    }

        public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {        
            if (!existeUsuario(email, contrasenia)) {
                throw new IllegalArgumentException("Usuario no encontrado.");
            }
            Usuario usuario = buscarUsuarioPorEmail(email);
            List<Entrada> entradasUsuarios = usuario.listarTodasLasEntradasFuturas(email, contrasenia); 
            return new ArrayList<IEntrada>(entradasUsuarios);                
    }

    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas){
        if (!existeUsuario(email, contrasenia)){
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        if(existeEspectaculo(nombreEspectaculo)){
            Usuario usuario = buscarUsuarioPorEmail(email);
            if(usuario != null){
                Funcion funcion = buscarFuncion(nombreEspectaculo, fecha);
                if (funcion == null) {
                    throw new IllegalArgumentException("No se encontró la función.");
                }
                String nombreSede = funcion.getSede();
                Sede sede = buscarSedePorNombre(nombreSede);
                List<Entrada> entradas = usuario.agregarEntrada(nombreEspectaculo, fecha, email, cantidadEntradas,funcion, sede);

                return new ArrayList<IEntrada>(entradas);
            }          
        }
        return new ArrayList<>();
    }

    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
        if (!existeUsuario(email, contrasenia)){
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        if(!existeEspectaculo(nombreEspectaculo)){
            throw new IllegalArgumentException("Error: No se encontró el Espectaculo." + nombreEspectaculo);
        }
        Usuario usuario = buscarUsuarioPorEmail(email);
        if(usuario != null){
            Funcion funcion = buscarFuncion(nombreEspectaculo, fecha);
            if (funcion == null) {
                throw new IllegalArgumentException("No se encontró la función.");
            }
            String nombreSede = funcion.getSede();
            Sede sede = buscarSedePorNombre(nombreSede);

            List<Entrada> entradas = usuario.agregarEntradaSector(nombreEspectaculo, fecha, email,sector, asientos, funcion, sede);
            
            return new ArrayList<IEntrada>(entradas);        
        
        }  
        
        return new ArrayList<>();
    
    }

    public boolean anularEntrada(IEntrada entrada, String contraseniaComprador) {
    if (entrada != null && contraseniaComprador != null){
        Usuario usuario = buscarUsuarioPorContraseniaYEntrada(entrada, contraseniaComprador);
        if (usuario != null) {
            usuario.quitarEntrada(entrada);
            return true;
        }
        else{
            throw new RuntimeException("la entrada no pertenece al usuario");
        }
    }
    else{
        throw new RuntimeException("Entrada no puede ser null");
    }    
}


    public Sector buscarSectorEnSede(String nombreSede, String nombreSector) {
        Sede sede = buscarSede(nombreSede); 
        if (sede == null) return null;

        return sede.getSectorPorNombre(nombreSector);
    }    
    
        public Sede buscarSede(String nombreSede) {
        for (Sede s : sedes) {
            if (s.getNombre().equalsIgnoreCase(nombreSede)) {
                return s;
            }
        }
        return null;
    }

    public double costoEntrada(String nombreEspectaculo, String fechaFuncion, String nombreSector){
         Funcion funcion = buscarFuncion(nombreEspectaculo, fechaFuncion);
        if (funcion == null) {
            throw new IllegalArgumentException("Función no encontrada.");
        }

        Sector sector = buscarSectorEnSede(funcion.getSede(), nombreSector);
        if (sector == null) {
            throw new IllegalArgumentException("Sector no encontrado en sede.");
        }

        return sector.aplicarRecargo(funcion.getPrecioBase());        
    }

    public Double costoEntrada(String nombreEspectaculo, String fechaFuncion) {        
        if (existeEspectaculo(nombreEspectaculo)) {
            Funcion funcion = buscarFuncion(nombreEspectaculo, fechaFuncion);
            if (funcion != null) {
                return funcion.getPrecioBase();
            }
        }
        throw new IllegalArgumentException("Ocurrió un error.");
    }

    private int calcularEntradasVendidasEstadio(Funcion funcion){
        int totalEntradasVendidas = 0;
        for (Usuario usuario : usuarios) {
            for (List<Entrada> entrada : usuario.getEntradas().values()) {
                for (Entrada entrada2 : entrada) {
                    if(entrada2.getFuncion().equals(funcion)){
                        totalEntradasVendidas++;
                    }            
                }
            }    
        }
        return totalEntradasVendidas;
    }


    private int contarEntradasVendidasPorSector(Funcion funcion, Sector sector){
        int totalEntradasVendidas = 0;
        for (Usuario usuario : usuarios) {
            for (List<Entrada> entrada : usuario.getEntradas().values()) {
                for (Entrada entrada2 : entrada) {
                    if(entrada2.getFuncion().equals(funcion) && entrada2.getUbicacion().getSector().equals(sector)){
                        totalEntradasVendidas++;
                    }
                }
            }
        }            
        return totalEntradasVendidas;
    }

    // Estadio
    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String nuevaFecha) {
        Usuario usuario = buscarUsuarioPorContraseniaYEntrada(entrada, contrasenia);
        if (usuario != null && entrada instanceof Entrada) {
            Entrada entradaOriginal = (Entrada) entrada;

            if (entradaOriginal.getUbicacion().getTipoUbicacion() == Ubicacion.TipoUbicacion.CAMPO) {
                usuario.quitarEntrada(entradaOriginal);

                Entrada nuevaEntrada = new Entrada(
                    entradaOriginal.getNombreEspectaculo(),
                    nuevaFecha,
                    entradaOriginal.getAsiento(),
                    entradaOriginal.getSector(),
                    entradaOriginal.getFuncion(),
                    entradaOriginal.getUbicacion()                    
                );
                String nombreSede = entradaOriginal.getFuncion().getSede();
                Sede sede = buscarSedePorNombre(nombreSede);
                usuario.agregarEntrada(entradaOriginal.getNombreEspectaculo(), nuevaFecha, usuario.getEmail(), 1, entradaOriginal.getFuncion(), sede);
                
                return (IEntrada) nuevaEntrada;
            } else {
                throw new IllegalArgumentException("No es una entrada de estadio");
            }
        }
        return null;
    }

    // Estadio
    public void cambiarEntrada(IEntrada entrada, String contrasenia, String nuevaFecha, String sector, int asiento) {
        Usuario usuario = buscarUsuarioPorContraseniaYEntrada(entrada,contrasenia);
        if (usuario != null && entrada instanceof Entrada) {
            Entrada entradaOriginal = (Entrada) entrada;

            if (entradaOriginal.getUbicacion().getTipoUbicacion() == Ubicacion.TipoUbicacion.PLATEA) {
                usuario.quitarEntrada(entradaOriginal);

                Entrada nuevaEntrada = new Entrada(
                    entradaOriginal.getNombreEspectaculo(),
                    nuevaFecha,
                    entradaOriginal.getAsiento(),
                    entradaOriginal.getSector(),
                    entradaOriginal.getFuncion(),
                    entradaOriginal.getUbicacion()                    
                );
                int[] asientos = {asiento};
                String nombreSede = entradaOriginal.getFuncion().getSede();
                Sede sede = buscarSedePorNombre(nombreSede);
                usuario.agregarEntradaSector(entradaOriginal.getNombreEspectaculo(), nuevaFecha, usuario.getEmail(), entradaOriginal.getSector(), asientos, entradaOriginal.getFuncion(), sede);
            } else {
                throw new IllegalArgumentException("No es una entrada de estadio");
            }
        }
    }

    //#endregion

    //#region Sedes

    // --------------------- SEDE ---------------------

    public boolean existeSede(String nombre){
        for (Sede sede : sedes){
            if (sede.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    // Sedes Estadio
    public void registrarSede(String nombre, String direccion, Integer capacidad) {
        if (existeSede(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
        }

        Sede nuevaSede = new Estadio(nombre, direccion, capacidad);
        sedes.add(nuevaSede);
    }

    public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede){
        if (!existeSede(nombreSede)) {
            throw new IllegalArgumentException("La sede " + nombreSede + " no existe.");
        }
        double totalRecaudado = 0;
        for (Usuario usuario : usuarios) {
            for (List<Entrada> entradas : usuario.getEntradas().values()) {
                for (Entrada entrada : entradas) {
                    if (entrada.getNombreEspectaculo().equals(nombreEspectaculo) && entrada.getFuncion().getSede().equals(nombreSede)) {
                        totalRecaudado += entrada.getPrecioFinal();
                    }
                }
            }
        }
        return totalRecaudado;
    }

    // Sedes Teatro
    public void registrarSede(String nombre, String direccion, Integer capacidad, int asientosPorFila,
            String[] nombresSectores, int[] capacidadesSectores, int[] porcentajeAdicionales) {
        if (existeSede(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
        }

        Teatro teatro = new Teatro(capacidad, direccion, nombre, asientosPorFila, nombresSectores, capacidadesSectores, porcentajeAdicionales);

        List<Sector> sectores = new ArrayList<>();
        for (int i = 0; i < nombresSectores.length; i++) {
            Sector sector = new Sector(nombresSectores[i], capacidadesSectores[i], porcentajeAdicionales[i], asientosPorFila);
            sectores.add(sector);
        }

        teatro.setSectores(sectores);
        sedes.add(teatro);
    }

    // Sedes Miniestadio
    public void registrarSede(String nombre, String direccion, Integer capacidad, int asientosPorFila,
            int puestosComida, double consumicion,
            String[] nombresSectores, int[] capacidadesSectores, int[] porcentajeAdicionales) {
        if (existeSede(nombre)) {
            throw new IllegalArgumentException("Ya existe una sede con ese nombre.");
        }

        Miniestadio mini = new Miniestadio(capacidad, direccion, nombre, puestosComida, consumicion);

        List<Sector> sectores = new ArrayList<>();
        for (int i = 0; i < nombresSectores.length; i++) {
            Sector sector = new Sector(nombresSectores[i], capacidadesSectores[i], porcentajeAdicionales[i], asientosPorFila);
            sectores.add(sector);
        }

        mini.setSectores(sectores);
        sedes.add(mini);
    }

    private Sede buscarSedePorNombre(String nombre) {
        for (Sede s : sedes) {
            if (s.getNombre().equalsIgnoreCase(nombre)) {
                return s;
            }
        }
            return null;
    }
    

//#endregion

    //#region Espectaculos
    // --------------------- ESPECTACULOS ---------------------
    public void registrarEspectaculo(String nombre) {
        Espectaculo nuevoEspectaculo = new Espectaculo(nombre);
        espectaculos.add(nuevoEspectaculo);
    }

    public boolean existeEspectaculo(String nombre) {
        for (Espectaculo espectaculo : espectaculos) {
            if (espectaculo.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public double totalRecaudado(String nombreEspectaculo) {
        double totalRecaudado = 0;
        if (!existeEspectaculo(nombreEspectaculo)) {
            throw new IllegalArgumentException("El espectaculo " + nombreEspectaculo + " no existe.");
        }
        for (Usuario usuario : usuarios) {
            for (List<Entrada> entradas : usuario.getEntradas().values()) {
                for (Entrada entrada : entradas) {
                    if (entrada.getNombreEspectaculo().equals(nombreEspectaculo)) {                        
                        totalRecaudado += entrada.getPrecioFinal();
                    }
                }
            }
        }
        return totalRecaudado;
        //System.out.println("Total recaudado por el espectaculo " + nombre + ": " + totalRecaudado);
    }

    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        List<IEntrada> entradasEspectaculo = new ArrayList<>();
        if (!existeEspectaculo(nombreEspectaculo)) {
            throw new IllegalArgumentException("El espectaculo " + nombreEspectaculo + " no existe.");
        }
        for (Usuario usuario : usuarios) {
            for (List<Entrada> entradas : usuario.getEntradas().values()) {
                for (Entrada entrada : entradas) {
                    if (entrada.getNombreEspectaculo().equals(nombreEspectaculo)) {
                        entradasEspectaculo.add(entrada);
                    }
                }
            }
        }
        return entradasEspectaculo;
    }

//#endregion

    //#region Funciones
    // --------------------- FUNCIONES ---------------------

    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precio) {
        if (!existeEspectaculo(nombreEspectaculo)) {
            throw new IllegalArgumentException("El espectaculo " + nombreEspectaculo + " no existe.");
        }
        for (Espectaculo espectaculo : espectaculos) {
            if (espectaculo.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
                Funcion nuevaFuncion = new Funcion(sede, fecha, precio);
                espectaculo.getFunciones().add(nuevaFuncion);
                return;
            }
        }
    }

    public void imprimirFunciones(String nombreEspectaculo) {
        if (!existeEspectaculo(nombreEspectaculo)) {
            throw new IllegalArgumentException("El espectaculo " + nombreEspectaculo + " no existe.");
        }
        for (Espectaculo espectaculo : espectaculos) {
            if (espectaculo.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
                System.out.println("Funciones de " + nombreEspectaculo + ":");
                for (Funcion funcion : espectaculo.getFunciones()) {
                    System.out.println(" - " + funcion.getFecha() + " en " + funcion.getSede() + " a $" + funcion.getPrecioBase());
                }
                return;
            }
        }
    }

        
    public Funcion buscarFuncion(String nombreEspectaculo, String fecha) {
        for (Espectaculo e : espectaculos) {
            if (e.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
                for (Funcion f : e.getFunciones()) {
                    if (f.getFecha().equals(fecha)) {
                        return f;
                    }
                }
            }
        }
        return null;
    }
    
    public String listarFunciones(String nombreEspectaculo) {
        StringBuilder sb = new StringBuilder();
        for (Espectaculo e : espectaculos) {
            if (e.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
                for (Funcion f : e.getFunciones()) {
                    Sede sede = buscarSedePorNombre(f.getSede());
                    if (sede instanceof Estadio) {
                        int entradasVendidas = calcularEntradasVendidasEstadio(f);
                        sb.append(" - (")
                                .append(f.getFecha()).append(") ")
                                .append(sede.getNombre())
                                .append(" - ")
                                .append(entradasVendidas)
                                .append("/")
                                .append(sede.getCapacidad())
                                .append("\n");
                    } else if (sede instanceof Miniestadio || sede instanceof Teatro) {
                        List<Sector> sectores;
                        if (sede instanceof Miniestadio miniestadio) {
                            sectores = miniestadio.getSectores();
                        } else {
                            sectores = ((Teatro) sede).getSectores();
                        }
                        sb.append(" - (")
                                .append(f.getFecha())
                                .append(") ")
                                .append(sede.getNombre())
                                .append(" - ");
                        for (int i = 0; i < sectores.size(); i++) {
                            Sector s = sectores.get(i);
                            sb.append(s.getNombre()).append(": ")
                                    .append(contarEntradasVendidasPorSector(f, s)).append("/")
                                    .append(s.getCapacidad());
                            if (i != sectores.size() - 1) {
                                sb.append(" | ");
                            }
                        }
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }    

    //#endregion
    // auxiliar:
    public void imprimirUsuarios(){
             for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public void imprimirSedes(){
             for (Sede sede : sedes) {
            System.out.println(sede);
        }
    }

    public void imprimirEspectaculo(){
             for (Espectaculo espectaculo : espectaculos) {
            System.out.println(espectaculo);
        }
    }

    @Override
    public String toString(){
        return "Ticketek prueba entradas V4";
    }

}