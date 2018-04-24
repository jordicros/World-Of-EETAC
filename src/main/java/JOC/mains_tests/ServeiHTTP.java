package JOC.mains_tests;

import JOC.Mon.Escena;
import JOC.Mon.Mundo;
import JOC.Mon.Usuario;
import JOC.Objectes.Objeto;
import JOC.Objectes.ObjetoEquipable;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response enviarEscenario(@PathParam("id") int id){
        Escena escena=mon.obtenerEscena(id);
        if(escena!=null){
            return Response.ok(escena,MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(204).build();
        }
    }
    @POST
    @Path("/usuari/register/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response regUsuario(Usuario user){
        boolean res =mon.crearUsuario(user);
        if(res){
            return Response.ok(mon.consultarUsuario(user.getNickname()),MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(204).build();
        }
    }
    @GET
    @Path("/usuari/{nombre}")
    public Response enviarUsuarioJSON(@PathParam("nombre") String nombre) {
        Usuario temp = mon.consultarUsuario(nombre);
        if(temp!=null){
            return Response.ok(temp,MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(204).build();
        }
    }
    @POST
    @Path("/objecte/add/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObjeto(@PathParam("nombre") String nombre,Objeto objeto){
        Usuario user = mon.consultarUsuario(nombre);
        if(user!=null) {
            mon.añadirObjetoAUsuario(user, objeto);
            return Response.status(200).build();
        }
        else {return Response.status(204).build();}

    }



}