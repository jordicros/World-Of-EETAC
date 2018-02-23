import static org.junit.Assert.*;

public class Test {

    private Mundo mundo;

    @org.junit.Before
    public void setUp(){
        mundo=new Mundo();
        Usuario u = new Usuario("Marc","Marcp",2);
        mundo.crearUsuario(u);
        u = new Usuario("Juan","Juanp",1);
        mundo.crearUsuario(u);
    }

    @org.junit.Test
    public void testCrearUser(){
        Usuario u = new Usuario("Maria","Mariap",1);
        assertEquals(true, mundo.crearUsuario(u));
        u = new Usuario("Marc","Mp",3);
        assertEquals(false, mundo.crearUsuario(u));

    }

    @org.junit.Test
    public void testBorrarUser(){
        assertEquals(true, mundo.eliminarUsuario("Juan"));
        assertEquals(false, mundo.eliminarUsuario("Maria"));
    }


    @org.junit.After
    public void tearDown(){
    }
}
