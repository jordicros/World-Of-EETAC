package JOC.mains_tests;

import JOC.Mon.Escena;
import JOC.Mon.Usuario;
import JOC.Objectes.Objeto;

import java.util.List;

/**
 * Created by jordi on 17/03/2018.
 */
public class Dades {
    private static Dades ourInstance;

    private List<Escena> escenas;


    public static Dades getInstance(List<Escena> esc) {
        if(ourInstance==null)
        {
            ourInstance = new Dades(esc);
        return ourInstance;}
        else
            return ourInstance;
    }

    private Dades(List<Escena> esc) {
        this.escenas = esc;

    }

    public List<Escena> getEscenas() {
        return escenas;
    }
}
