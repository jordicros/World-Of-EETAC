package JOC.Mon;

import JOC.Celes.*;
import JOC.DAO.Factory;
import JOC.DAO.Session;
import JOC.Objectes.Objeto;
import JOC.mains_tests.Dades;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Mundo {
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Partida> partides = new ArrayList<Partida>();
    private List<Escena> escenas= new ArrayList<Escena>();
    private List<Objeto> objetos= new ArrayList<Objeto>();
    private List<Mapa> mapes = new ArrayList<Mapa>();
    private Session session;

    public Mundo()  throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        //S'hauria de carregar tota la base de dades amb usuaris, etc
        cargarObjetos();
        cargarEscenasJson("escenarisJ.txt");
        //cargarEscenasTxt("escenaris.txt"); //Provisional, mentres enfoquem a generar/llegir JSON
        cargarUsuarios();
        ferMapes(this.escenas);
    }
    public void writeJSON(int id, String nomJSON) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Escena obj = this.obtenerEscena(id);
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
            reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/escenas/escenasTxt/"+rutes[a]));
            escena.setNombre(rutes[a]);
            escena.setAncho(Integer.parseInt(reader.readLine()));
            escena.setAlto(Integer.parseInt(reader.readLine()));
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
                            Random rand = new Random();
                            List<Objeto> contenido=new LinkedList<Objeto>();
                            for (int k = 0; k <4 ; k++) {
                                int objectId = rand.nextInt(objetos.size());
                                contenido.add(objetos.get(objectId));
                            }
                            matriz[i][j]=new Cofre(contenido);

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
    public int cargarObjetos() throws IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        Factory factory= new Factory();
        session = factory.openSession();
        int res =session.initBD();
        if(res==0) {
            boolean fin = false;
            int i = 1;
            while (!fin) {
                Objeto objeto = (Objeto) session.get(i, Objeto.class);
                if (objeto == null) {
                    fin = true;
                } else {
                    objeto.setId(i - 1);
                    objetos.add(objeto);
                    i++;
                }
            }
        }
        return 0;
    }
    public int cargarUsuarios() throws IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
            boolean fin=false;
            int i=1;
            while(!fin) {
                Usuario usuario = (Usuario) session.get(i, Usuario.class);
                if(usuario==null){
                    fin=true;
                }
                else{
                    usuarios.add(usuario);
                    i++;
                }
            }
        return 0;
    }

    public boolean crearUsuario(Usuario u) throws IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        //Primer ens asegurem que el nickname sigui unic
        if (consultarUsuario(u.getNickname()) == null) {
            usuarios.add(u);
            //Peta pk falten els camps inventari, llista de amics
            session.save(u);
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
   /* public void añadirObjetoAUsuario(Usuario u, Objeto o) {
        int id = usuarios.indexOf(u);
        if(id==-1)
            return;
        Usuario user= usuarios.get(id);
        user.getInventario().add(o);
        
    }*/
    /*public List<Objeto> consultarObjetosDeUsuario(Usuario U) {
        return U.getInventario();
    }*/
    public Objeto consultarObjetoDeUsuario(Usuario u,String nombreObjeto) {
        boolean encontrado = false;
        int i = 0;
        Objeto obj=null;
       // List<Objeto> objetos =u.getInventario();
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
   /* public boolean eliminarObjetosDeUsuario(Usuario u, Objeto o){
        return u.getInventario().remove(o);
    }
    public void transferirObjetoEntreUsuarios(Usuario origen,Usuario destino,Objeto o) {
        origen.getInventario().remove(o);
        destino.getInventario().add(o);
    }*/
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
    public Escena obtenerEscena(int id){
        boolean encontrado = false;
        int i = 0;
        Escena pantalla = null;
        if(escenas.size()>id) {
            pantalla = escenas.get(id);
            return pantalla;
        }
        else
            return null;
    }
    public List<Escena> consultarEscenas() {
        return escenas;
    }
    public List<Usuario> consultarUsuarios() {
        return usuarios;
    }


    public List<Mapa> ferMapes(List<Escena> pantalles){
        List<Escena> escenes = new ArrayList<Escena>();
        List<Transicion> canvis = new ArrayList<Transicion>();
        List<Mapa> mapes = new ArrayList<Mapa>();
        //Fer mapes...(falta fer les escenes i saber com combinarles)
        Mapa map = new Mapa(escenes,canvis);
        this.mapes.add(map);//Fer aixo per cada mapa
        return this.mapes;
    }

    public Mapa chestGenerator(Mapa mapWithNoChest)
    {
        int alt,ample;
        int counter = 4;
        for(int j=0;j<mapWithNoChest.pantalles.size();j++) {

            List<Celda> pisables = new ArrayList<Celda>();
            for (int i = 0; i < mapWithNoChest.pantalles.get(j).getAncho();i++) {
                for(int x=0;i <mapWithNoChest.pantalles.get(j).getAlto();x++){
                    if(mapWithNoChest.pantalles.get(j).getDatos()[x][i].getPisable()==1)
                        pisables.add(mapWithNoChest.pantalles.get(j).getDatos()[x][i]);
                }
            }
            Random r = new Random();
            Celda cofre = pisables.get(r.nextInt(pisables.size()-0));
            cofre = new Cofre();
            ((Cofre) cofre).addObject(new Objeto());
                   //Afegir material al cofre
        }
        return mapWithNoChest;

    }

    //Es crea una partida i es guarda a la List, fins que s'acabi. Mentres va la partida, l'usuari anira informant al server per generar nous cofres i enemics.
    // Així successivament fins que es mori, que es guardarà la partida a la BBDD
    public Partida crearPartida(Partida partida)
    {
        //FALTA CARREGAR EL MAPA, AMB LES ESCENES  I TRANSICIONS, ELS COFRES NO.
        if(partida.mapSelection==0)
            this.mapes.get(0);
        else if(partida.mapSelection==1)
           this.mapes.get(1);
        this.partides.add(partida);
        partida = gestionarPartida(this.partides.get(this.partides.indexOf(partida)));
        return partida;
    }
    public Partida gestionarPartida(Partida partida)
    {
        //Buscar partida
        int i = this.partides.indexOf(partida);
        partida = this.partides.get(i);
        chestGenerator(partida.map);
        partida.ronda++;
        partida.enemics=partida.ronda*15 +20;

        return partida;
    }
    public Partida guardarPartida(Partida partida)
    {
        //Buscar partida
        int i = this.partides.indexOf(partida);
        partida = this.partides.get(i);
        //guardar db

        return partida;
    }
    }
