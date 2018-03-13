package JOC.Celes;
import JOC.Objectes.Objeto;

import java.util.List;

/**
 * Created by jordi on 05/03/2018.
 */
public class Cofre extends Celda {
    public String getSimbolo() {
        return "X";
    }

    private List<Objeto> contenido;
    public int getPisable() {
        return 0;
    }
    public int getInteractuable() {
        return 1;
    }
    public List<Objeto> getContenido(){
        return this.contenido;
    }
    public void abrir(){getContenido();}
}
