package src.Ticketek;
public class Estadio extends Sede {
    private boolean campo;
    
    public Estadio(Integer capacidad, String direccion, String nombre, boolean campo){
        super(capacidad, direccion, nombre);
        this.campo = campo;

    }

 
    public boolean isCampo() {
        return this.campo;
    }

    public boolean getCampo() {
        return this.campo;
    }

    public void setCampo(boolean campo) {
        this.campo = campo;
    }

}
