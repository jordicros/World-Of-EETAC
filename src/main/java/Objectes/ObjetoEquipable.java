package Objectes;

public class ObjetoEquipable implements Objeto {
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

    public ObjetoEquipable(int identificador,String nombre, String descripcion, int tipo)
    {
        this.id=identificador;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.tipo=tipo;
    }

}