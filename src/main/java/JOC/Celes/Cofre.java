package JOC.Celes;
import JOC.Objectes.Objeto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jordi on 05/03/2018.
 */
public class Cofre extends Celda {
    public String getSimbolo() {
        return "X";
    }
    private List<Objeto> contenido;
    public int getPisablePersonaje() {
        return 0;
    }
    public int getPisableZombie() {
        return 0;
    }
    public int getInteractuable() {
        return 1;
    }
    public List<Objeto> getContenido(){
        return this.contenido;
    }
    public Cofre(List<Objeto> contenido){
        this.contenido=contenido;
    }
    public void abrir(){getContenido();}
    public void addObject(Objeto objeto){
        contenido.add(objeto);
    }

    public Cofre() {
    }
}
