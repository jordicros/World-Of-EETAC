package mains_tests;

import Objectes.Objeto;
import Objectes.ObjetoEquipable;
import org.junit.Assert;
import Mon.*;
import java.util.List;

import static org.junit.Assert.*;

public class Test {

    private Mundo mundo;

    @org.junit.Before
    public void setUp()throws java.io.IOException{
        mundo=new Mundo();
        Usuario u = new Usuario("Marc","Marcp",2);
        mundo.crearUsuario(u);
        u = new Usuario("Juan","Juanp",1);
        mundo.crearUsuario(u);
        mundo.añadirObjetoAUsuario(u,new ObjetoEquipable(1,"Daño","Pocion",2));
    }

    @org.junit.Test
    public void testCrearUser(){
        Usuario u = new Usuario("Maria","Mariap",1);
        Assert.assertEquals(true, mundo.crearUsuario(u));
        u = new Usuario("Marc","Mp",3);
        Assert.assertEquals(false, mundo.crearUsuario(u));

    }

    @org.junit.Test
    public void testBorrarUser(){
        Assert.assertEquals(true, mundo.eliminarUsuario("Juan"));
        Assert.assertEquals(false, mundo.eliminarUsuario("Maria"));
    }

    @org.junit.Test
    public void testConsultarUser(){
        Usuario user = mundo.consultarUsuario("Marc");
        Assert.assertEquals("Marc",user.getNickname());
        Assert.assertEquals("Marcp",user.getPassword());
        Assert.assertEquals(2,user.getProfession());
        Assert.assertEquals(null,mundo.consultarUsuario("Pepito"));
    }

    @org.junit.Test
    public void testAñadirConsultarObjetos(){
        Usuario user = mundo.consultarUsuario("Marc");
        ObjetoEquipable objeto= new ObjetoEquipable(1,"Salud","Pocion",2);
        mundo.añadirObjetoAUsuario(user,objeto);
        List<Objeto> objetos=mundo.consultarObjetosDeUsuario(user);
        assertEquals(objeto,objetos.get(0));
    }

    @org.junit.Test
    public void testConsultarObjeto(){
        Usuario user = mundo.consultarUsuario("Marc");
        ObjetoEquipable objeto= new ObjetoEquipable(1,"Salud","Pocion",2);
        mundo.añadirObjetoAUsuario(user,objeto);
        Objeto obj=mundo.consultarObjetoDeUsuario(user,"Salud");
        assertEquals(objeto,obj);
    }

    @org.junit.Test
    public void testEliminarObjeto(){
        Usuario user = mundo.consultarUsuario("Juan");
        Objeto obj=mundo.consultarObjetoDeUsuario(user,"Daño");
        boolean res= mundo.eliminarObjetosDeUsuario(user,obj);
        assertEquals(res,true);
        res= mundo.eliminarObjetosDeUsuario(user,obj);
        assertEquals(false,res);
    }

    @org.junit.Test
    public void testTransferirObjeto(){
        Usuario user = mundo.consultarUsuario("Juan");
        Usuario user2 = mundo.consultarUsuario("Marc");
        Objeto objeto=mundo.consultarObjetoDeUsuario(user,"Daño");
        mundo.transferirObjetoEntreUsuarios(user,user2,objeto);
        Objeto obj=mundo.consultarObjetoDeUsuario(user,"Daño");
        assertEquals(null,obj);
        obj=mundo.consultarObjetoDeUsuario(user2,"Daño");
        assertEquals(obj,objeto);
    }

    @org.junit.After
    public void tearDown(){
    }
}
