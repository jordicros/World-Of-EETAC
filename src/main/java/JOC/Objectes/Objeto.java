package JOC.Objectes;

/**
 * Created by jordi on 23/02/2018.
 */
public abstract class Objeto {
    private int id;
    private String nombre;
    private String descripcion;
    private int tipo;

    public int getID(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public int getTipo(){
        return this.tipo;
    } //Arma, pocion, etc

    public Objeto(int identificador,String nombre, String descripcion, int tipo)
    {
        this.id=identificador;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.tipo=tipo;
    }
}
