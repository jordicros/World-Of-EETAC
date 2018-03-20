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
    private List<Usuario> usuarios;


    public static Dades getInstance(List<Escena> esc,List<Usuario> usuarios) {
        if(ourInstance==null)
        {
            ourInstance = new Dades(esc,usuarios);
        return ourInstance;}
        else
            return ourInstance;
    }

    private Dades(List<Escena> esc,List<Usuario> usuarios) {
        this.escenas = esc;
        this.usuarios=usuarios;

    }

    public List<Escena> getEscenas() {
        return this.escenas;
    }
    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }
}
