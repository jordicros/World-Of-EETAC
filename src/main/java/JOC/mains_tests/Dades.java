package JOC.mains_tests;

import JOC.Mon.Escena;
import JOC.Mon.Mundo;
import JOC.Mon.Usuario;
import JOC.Objectes.Objeto;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jordi on 17/03/2018.
 */
public class Dades {
    private static Dades ourInstance;

    private Mundo mon;


    public static Dades getInstance() throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException {
        if(ourInstance==null)
        {
            ourInstance = new Dades();
        return ourInstance;}
        else
            return ourInstance;
    }

    private Dades() throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException {
            this.mon = new Mundo();
    }

    public Mundo getMundo(){
        return this.mon;
    }
}
