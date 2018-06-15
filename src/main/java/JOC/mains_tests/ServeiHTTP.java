package JOC.mains_tests;

import JOC.DAO.Main;
import JOC.Mon.Escena;
import JOC.Mon.Mundo;
import JOC.Mon.Partida;
import JOC.Mon.Usuario;
import JOC.Objectes.Objeto;
import JOC.Objectes.ObjetoEquipable;
import org.apache.log4j.Logger;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jordi on 20/03/2018.
 */
@Path("service")
public class ServeiHTTP {

    private  Mundo mon;
    public ServeiHTTP() throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        mon = Dades.getInstance().getMundo();
    }

    @GET
    @Path("/proba")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario Proba(){
        Usuario u = new Usuario("Marc","Marcp",2);
        return u;
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
    public Response regUsuario(Usuario user)throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
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
    @Path("/usuari/login/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario user) {
        Usuario temp = mon.consultarUsuario(user.getNickname());
        if(temp.getPassword().equals(user.getPassword())){
            return Response.ok(mon.consultarUsuario(user.getNickname()),MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(204).build();
        }
    }
    /*@POST
    @Path("/objecte/add/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObjeto(@PathParam("nombre") String nombre,Objeto objeto){
        Usuario user = mon.consultarUsuario(nombre);
        if(user!=null) {
            mon.añadirObjetoAUsuario(user, objeto);
            return Response.status(200).build();
        }
        else {return Response.status(204).build();}

    }*/

    @GET
    @Path("/usuaris")
    public Response enviarUsuaris(){
        List<Usuario> usuaris = mon.consultarUsuarios();
        if(usuaris.size()>0){
            return Response.ok(usuaris,MediaType.APPLICATION_JSON).build();
        }
        else {
            return Response.status(204).build();
        }
    }
    @POST
    @Path("/partida/start/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startGame(Partida partida)throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        //partida =mon.crearPartida(partida); Quan estiguin els mapes, discommenttt
        return Response.ok(partida,MediaType.APPLICATION_JSON).build();
    }
    @POST
    @Path("/partida/nextRound/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nextRoundGame(Partida partida)throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        partida =mon.gestionarPartida(partida);
        return Response.ok(partida,MediaType.APPLICATION_JSON).build();
    }
    @POST
    @Path("/partida/finish/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response partidaEnded(Partida partida)throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        mon.guardarPartida(partida);
        return Response.ok(200).build();
    }
}
