package JOC.Objectes;

import JOC.Celes.Cofre;
import JOC.Celes.Hierba;
import JOC.Celes.Puerta;
import JOC.Celes.Rio;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by jordi on 23/02/2018.
 */
/*@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=ObjetoConsumible.class, name="consumible"),
        @JsonSubTypes.Type(value=ObjetoEquipable.class, name="equipable"),
})*/
public class Objeto {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Objeto(int id, String nombre, String descripcion, int tipo)
    {
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.tipo=tipo;
    }
    /*public Objeto(String id,String nombre, String descripcion, String tipo){
        this.id=Integer.parseInt(id);
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.tipo=Integer.parseInt(tipo);;
    }*/
    public Objeto(){
    }

    /*@Override
    public String toString() {
        return "Objeto [id=" + id + ", nombre=" + nombre +  ", descripcion=" + descripcion + ", tipo=" + tipo +  "]";
    }*/

}
