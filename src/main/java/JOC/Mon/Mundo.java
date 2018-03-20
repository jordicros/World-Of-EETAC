package JOC.Mon;

import JOC.Celes.*;
import JOC.Objectes.Objeto;
import JOC.mains_tests.Dades;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("mundo")
public class Mundo {
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Escena> escenas= new ArrayList<Escena>();
    private List<Objeto> objetos= new ArrayList<Objeto>();

    public Mundo()  throws IOException{
        cargarEscenasJson("escenarisJ.txt");
        Dades.getInstance(escenas,usuarios);
        Dades singleton = Dades.getInstance(escenas,usuarios);
        escenas= singleton.getEscenas();
        usuarios=singleton.getUsuarios();
        //cargarEscenasTxt("escenaris.txt"); //Provisional, mentres enfoquem a generar/llegir JSON


    }
    public void writeJSON(String nomEscenari, String nomJSON) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Escena obj = this.obtenerEscena(nomEscenari);
        String ruta_abs = new File("").getAbsolutePath();
        mapper.writeValue(new File(ruta_abs+"/src/main/resources/escenas/escenasJson/"+nomJSON+".json"), obj);
    }
    public int cargarEscenasTxt(String nom) throws FileNotFoundException,IOException {
        String ruta_abs = new File("").getAbsolutePath();
        BufferedReader reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/escenas/escenasTxt/"+nom));
        int num = Integer.parseInt(reader.readLine());
        String[] rutes= new String[num];
        for(int i=0;i<num;i++) {
            rutes[i]=reader.readLine();
        }
        reader.close();
        for(int a=0;a<num;a++) {
            Escena escena= new Escena();
            int numCofres,numPuertas;
            reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/escenas/escenasTxt/"+rutes[a]));
            escena.setNombre(rutes[a]);
            escena.setAncho(Integer.parseInt(reader.readLine()));
            escena.setAlto(Integer.parseInt(reader.readLine()));
            numCofres=Integer.parseInt(reader.readLine());
            numPuertas=Integer.parseInt(reader.readLine());
            escena.setDescripcion(reader.readLine());
            Celda[][] matriz= new Celda[escena.getAlto()][escena.getAncho()];
            for(int i=0;i<escena.getAlto();i++) {
                String[] datos= new String[escena.getAncho()];
                datos=reader.readLine().split(" ");
                for (int j = 0; j < escena.getAncho();j++) {
                    switch (datos[j]){
                        case "0": //JOC.Celes.Hierba
                            matriz[i][j]=new Hierba();
                            break;
                        case "X": //JOC.Celes.Cofre
                            matriz[i][j]=new Cofre();
                            break;
                        case "G": //Porta
                            matriz[i][j]=new Puerta();
                            break;
                        case "-": //Riu
                            matriz[i][j]=new Rio();
                            break;
                        default:
                            matriz[i][j]=new Hierba();
                    }
                }
            }
            escena.setDatos(matriz);
            escenas.add(escena);
        }
        return 0;
    }
    public int cargarEscenasJson(String nom) throws FileNotFoundException,IOException {
        String ruta_abs = new File("").getAbsolutePath();
        BufferedReader reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/escenas/escenasJson/"+nom));
        int num = Integer.parseInt(reader.readLine());
        String[] rutes= new String[num];
        for(int i=0;i<num;i++) {
            rutes[i]=reader.readLine();
        }
        reader.close();
        for(int a=0;a<num;a++) {
            ObjectMapper mapper = new ObjectMapper();
            Escena obj = mapper.readValue( new File(ruta_abs+"/src/main/resources/escenas/escenasJson/"+rutes[a]),Escena.class);
            escenas.add(obj);
        }
        return 0;
    }

    @GET
    @Path("/getEscenaris/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Escena enviarEscenarioJSON(@PathParam("id") int id){
       // return Response.status(200).entity(escenas.get(0)).build();
        if(escenas.get(id)!=null)
        return escenas.get(id);
        else
            return null;
    }

    @GET
    @Path("/getUser/{nombre}")
    @Produces(MediaType.TEXT_PLAIN) //NO RESPON, UNA FUNCIO SENZILLA SI QUE TIRA
    public int enviarUsuarioJSON(@PathParam("nombre") String nombre) {
        boolean encontrado = false;
        int i = 0;
        Usuario user = null;
        while (i < usuarios.size() && !encontrado) {
            user = usuarios.get(i);
            if (user.getNickname().equals(nombre)) {
                encontrado = true;
            } else
                i++;
        }
        if (encontrado)
            return 1;
            //return Response.ok(user).build();
        else
            return -1;
            //return Response.status(201).build();

    }

    public boolean crearUsuario(Usuario u) {
        //Primer ens asegurem que el nickname sigui unic
        if (consultarUsuario(u.getNickname()) == null) {
            usuarios.add(u);
            return true;
        }
        else
            return false;
    }
    public boolean eliminarUsuario(String nombre) {
        Usuario user = consultarUsuario(nombre);
        if(user!=null) {
            usuarios.remove(user);
            return true;
        }
        else
            return false;
    }
    public Usuario consultarUsuario(String nombre) {
        boolean encontrado = false;
        int i = 0;
        Usuario user = null;
        while (i < usuarios.size() && !encontrado) {
            user = usuarios.get(i);
            if (user.getNickname().equals(nombre)) {
                encontrado = true;
            } else
                i++;
        }
        if (encontrado)
            return user;
        else
            return null;
    }
    public void añadirObjetoAUsuario(Usuario u, Objeto o) {
        int id = usuarios.indexOf(u);
        if(id==-1)
            return;
        Usuario user= usuarios.get(id);
        user.getInventario().add(o);
        
    }
    public List<Objeto> consultarObjetosDeUsuario(Usuario U) {
        return U.getInventario();
    }
    public Objeto consultarObjetoDeUsuario(Usuario u,String nombreObjeto) {
        boolean encontrado = false;
        int i = 0;
        Objeto obj=null;
        List<Objeto> objetos =u.getInventario();
        while (i < objetos.size() && !encontrado) {
            obj = objetos.get(i);
            if (obj.getNombre().equals(nombreObjeto)) {
                encontrado = true;
            } else
                i++;
        }
        if (encontrado)
            return obj;
        else
            return null;
    }
    public boolean eliminarObjetosDeUsuario(Usuario u, Objeto o){
        return u.getInventario().remove(o);
    }
    public void transferirObjetoEntreUsuarios(Usuario origen,Usuario destino,Objeto o) {
        origen.getInventario().remove(o);
        destino.getInventario().add(o);
    }
    public void guardarEscenasJSON() throws java.io.IOException {
        for(int i=0;i<escenas.size();i++)
        {
            ObjectMapper mapper = new ObjectMapper();
            String ruta_abs = new File("").getAbsolutePath();
            String nom = escenas.get(i).getNombre();
            nom =nom.substring(0,nom.lastIndexOf("."));
            mapper.writeValue(new File(ruta_abs+"/src/main/resources/escenas/escenasJson/"+nom+".json"),escenas.get(i));
        }
    }
    public Escena obtenerEscena(String nombre){
        boolean encontrado = false;
        int i = 0;
        Escena pantalla = null;
        while (i < escenas.size() && !encontrado) {
            pantalla = escenas.get(i);
            if (pantalla.getNombre().equals(nombre)) {
                encontrado = true;
            } else
                i++;
        }
        if (encontrado)
            return pantalla;
        else
            return null;
    }

    @GET
    @Path("/getEscenas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Escena> consultarEscenas() {
        return escenas;
    }

    }
