package JOC.mains_tests;

import JOC.Mon.Escena;
import JOC.Mon.Mundo;
import JOC.Mon.Usuario;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @Path("/getEscenaris/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Escena enviarEscenarioJSON(@PathParam("id") int id){
        return mon.obtenerEscena(id);

    }

   /* @GET
    @Path("/getUser/{nombre}")
    @Produces(MediaType.TEXT_PLAIN) //NO RESPON, UNA FUNCIO SENZILLA SI QUE TIRA
    public int enviarUsuarioJSON(@PathParam("nombre") String nombre) {


    }
    @GET
    @Path("/getEscenas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Escena> consultarEscenas() {
    }*/



}
