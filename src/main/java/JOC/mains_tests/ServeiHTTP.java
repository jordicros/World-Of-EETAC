package JOC.mains_tests;

import JOC.Mon.Escena;
import JOC.Mon.Mundo;
import JOC.Mon.Usuario;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by jordi on 20/03/2018.
 */
@Path("service")
public class ServeiHTTP {

    private  Mundo mon;
    public ServeiHTTP() throws IOException{
        mon = Dades.getInstance().getMundo();
    }
    @GET
    @Path("/escenaris/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Escena enviarEscenario(@PathParam("id") int id){
        return mon.obtenerEscena(id);

    }
    @GET
    @Path("/usuari/reg/{nom}/{pass}/{proff}")
    @Produces (MediaType.APPLICATION_JSON)
    public Usuario regUsuario(@PathParam("nom")String nom,@PathParam("pass") String pass,@PathParam("proff") int proff){
        Usuario temp = new Usuario( nom, pass, proff);
        mon.crearUsuario(temp);
        return mon.consultarUsuario(nom);
    }
    @GET
    @Path("/usuari/{nombre}")
    @Produces(MediaType.APPLICATION_JSON) //NO RESPON, UNA FUNCIO SENZILLA SI QUE TIRA
    public Usuario enviarUsuarioJSON(@PathParam("nombre") String nombre) {
        Usuario temp = mon.consultarUsuario(nombre);
        return temp;
    }
    /*@GET
    @Path("/getEscenas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Escena> consultarEscenas() {
    }*/



}
