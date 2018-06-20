package JOC.Mon;

import JOC.Celes.*;
import JOC.DAO.Factory;
import JOC.DAO.Main;
import JOC.DAO.Session;
import JOC.Objectes.Objeto;
import JOC.Objectes.ObjetoConsumible;
import JOC.Objectes.ObjetoEquipable;
import JOC.mains_tests.Dades;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Mundo {
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Partida> partides = new ArrayList<Partida>();
    private List<Escena> escenas= new ArrayList<Escena>();
    private List<Objeto> objetos= new ArrayList<Objeto>();
    private List<Mapa> mapes = new ArrayList<Mapa>();
    private Session session;
    final static Logger log = Logger.getLogger(Main.class);

    public Mundo()  throws IOException,IllegalAccessException,InvocationTargetException,SQLException,InstantiationException,NoSuchMethodException{
        //S'hauria de carregar tota la base de dades amb usuaris, etc
        cargarObjetos();
        objetos = new ArrayList<Objeto>();
        cargarEscenasJson("escenarisJ.txt");
        cargarObjetosJson("objetosJ.txt");
        cargarEscenasTxt("escenaris.txt"); //Per editar mapes
       //cargarObjetosTxt("objetos.txt");
        //guardarObjetosJSON();
        cargarUsuarios();
        ferMapes(this.escenas);
    }
    public int cargarObjetosJson(String nom) throws FileNotFoundException,IOException {
        String ruta_abs = new File("").getAbsolutePath();
        BufferedReader reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/objetos/objetosConsumiblesJson/"+nom));
        int num = Integer.parseInt(reader.readLine());
        String[] rutes= new String[num];
        for(int i=0;i<num;i++) {
            rutes[i]=reader.readLine();
        }
        reader.close();
        for(int a=0;a<num;a++) {
            ObjectMapper mapper = new ObjectMapper();
            ObjetoConsumible obj = mapper.readValue( new File(ruta_abs+"/src/main/resources/objetos/objetosConsumiblesJson/"+rutes[a]),ObjetoConsumible.class);
            objetos.add(obj);
        }
        reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/objetos/objetosEquipablesJson/"+nom));
        num = Integer.parseInt(reader.readLine());
        rutes= new String[num];
        for(int i=0;i<num;i++) {
            rutes[i]=reader.readLine();
        }
        reader.close();
        for(int a=0;a<num;a++) {
            ObjectMapper mapper = new ObjectMapper();
            ObjetoEquipable obj = mapper.readValue( new File(ruta_abs+"/src/main/resources/objetos/objetosEquipablesJson/"+rutes[a]),ObjetoEquipable.class);
            objetos.add(obj);
        }

        return 0;
    }
    public int cargarObjetosTxt(String path) throws IOException
    {
        String ruta_abs = new File("").getAbsolutePath();
        BufferedReader reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/objetos/objetosTxt/"+path));
        int num = Integer.parseInt(reader.readLine());
        String[] rutes= new String[num];
        for(int i=0;i<num;i++) {
            rutes[i]=reader.readLine();
        }
        reader.close();
        for(int a=0;a<num;a++) {
            Objeto obj= new Objeto();
            reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/objetos/objetosTxt/"+rutes[a]));
            obj.setId(a);
            obj.setNombre(reader.readLine());
            obj.setDescripcion(reader.readLine());
            obj.setTipo(Integer.parseInt(reader.readLine()));
            obj.setStatUpgrader(Integer.parseInt(reader.readLine()));
            if(obj.getTipo()==0)
            {
                obj = new ObjetoConsumible(obj.getID(),obj.getNombre(),obj.getDescripcion(),obj.getTipo(),obj.getStatUpgrader());
            }
            else {
                obj = new ObjetoEquipable(obj.getID(), obj.getNombre(), obj.getDescripcion(), obj.getTipo(),obj.getStatUpgrader());
                if(obj.getID()==1) //CAP
                    ((ObjetoEquipable) obj).parteCuerpo = 1;
                else if(obj.getID()==2) //COS
                    ((ObjetoEquipable) obj).parteCuerpo = 2;
                else if(obj.getID()==3) //MALLA
                    ((ObjetoEquipable) obj).parteCuerpo = 3;
                else if(obj.getID()==4) //BOTAS
                    ((ObjetoEquipable) obj).parteCuerpo = 4;
                else if(obj.getID()==5||obj.getID()==6||obj.getID()==7) //ARMA
                    ((ObjetoEquipable) obj).parteCuerpo = 5;
            }
            objetos.add(obj);
        }
        return 0;
    }
    public void guardarObjetosJSON() throws java.io.IOException {
        for(int i=0;i<objetos.size();i++)
        {
            if(objetos.get(i).getTipo()==0) {
                ObjectMapper mapper = new ObjectMapper();
                String ruta_abs = new File("").getAbsolutePath();
                String nom = objetos.get(i).getNombre();
                mapper.writeValue(new File(ruta_abs + "/src/main/resources/objetos/objetosConsumiblesJson/" + objetos.get(i).getNombre() + ".json"), objetos.get(i));
            }
            else//Tota la resta son equipables
            {
                ObjectMapper mapper = new ObjectMapper();
                String ruta_abs = new File("").getAbsolutePath();
                String nom = objetos.get(i).getNombre();
                mapper.writeValue(new File(ruta_abs + "/src/main/resources/objetos/objetosEquipablesJson/" + objetos.get(i).getNombre() + ".json"), objetos.get(i));

            }
        }
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
                        case "D": //Porta
                            matriz[i][j]=new Puerta();
                            break;
                        case "P": //Paret
                            matriz[i][j]=new Paret();
                            break;
                        case "W": //Finestra
                            matriz[i][j]=new Ventana();
                            break;
                        case "T": //Trampilla
                            matriz[i][j]=new Trampilla();
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


    public List<Mapa> ferMapes(List<Escena> pantalles) throws IOException {
        List<Escena> escenes = pantalles;
        List<Transicion> canvis = new ArrayList<Transicion>();
        List<Mapa> mapes = new ArrayList<Mapa>();

        //Mapeig de les transicions (per poderles editar els mapes 100% fora del codi)
        String ruta_abs = new File("").getAbsolutePath();
        BufferedReader reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/resources/transicions/transicions.txt"));
        int num = Integer.parseInt(reader.readLine());
        String[] rutes= new String[num];
        for(int i=0;i<num;i++) {
            rutes[i]=reader.readLine();
        }

        for(int a=0;a<num;a++) {
            TransicionMapper transicion = new TransicionMapper();
            reader = new BufferedReader(new FileReader(ruta_abs + "/src/main/resources/transicions/" + rutes[a]));
            transicion.escenario1 = Integer.parseInt(reader.readLine());
            transicion.escenario2 = Integer.parseInt(reader.readLine());
            transicion.X1Puerta = Integer.parseInt(reader.readLine());
            transicion.Y1Puerta = Integer.parseInt(reader.readLine());
            transicion.X2Puerta = Integer.parseInt(reader.readLine());
            transicion.Y2Puerta = Integer.parseInt(reader.readLine());
            transicion.X1Respawn = Integer.parseInt(reader.readLine());
            transicion.Y1Respawn = Integer.parseInt(reader.readLine());
            transicion.X2Respawn = Integer.parseInt(reader.readLine());
            transicion.Y2Respawn = Integer.parseInt(reader.readLine());
            Transicion trans1 = new Transicion(transicion.escenario2, transicion.X2Respawn, transicion.Y2Respawn);
            Puerta porta1 = (Puerta) this.escenas.get(transicion.escenario1).getDatos()[transicion.Y1Puerta][transicion.X1Puerta];
            porta1.setTeleport(trans1);
            Transicion trans2 = new Transicion(transicion.escenario1, transicion.X1Respawn, transicion.Y1Respawn);
            Puerta porta2 = (Puerta) this.escenas.get(transicion.escenario2).getDatos()[transicion.Y2Puerta][transicion.X2Puerta];
            porta2.setTeleport(trans2);
        }
        //Fer mapa 1
        List<Escena> mapa1 = new ArrayList<Escena>();
        mapa1.add(pantalles.get(0));
        mapa1.add(pantalles.get(1));
        mapa1.add(pantalles.get(2));
        mapa1.add(pantalles.get(3));
        Mapa map = new Mapa(escenes);
        this.mapes.add(map);//Fer aixo per cada mapa
        return this.mapes;
    }
    public Mapa referMapes(Mapa mapaAmbCofresVells)
    {
        for(int j=0;j<mapaAmbCofresVells.pantalles.size();j++) {


            for (int i = 0; i < mapaAmbCofresVells.pantalles.get(j).getAncho();i++) {

                for(int x=0;x <mapaAmbCofresVells.pantalles.get(j).getAlto();x++){
                    if(mapaAmbCofresVells.pantalles.get(j).getDatos()[x][i].getSimbolo().equals("X")) {
                        mapaAmbCofresVells.pantalles.get(j).getDatos()[x][i] = new Hierba();
                    }
                }
            }

        }
        return mapaAmbCofresVells;
    }
    public Mapa chestGenerator(Mapa mapWithNoChest)
    {
        int alt,ample;
        int counter = 4;
        mapWithNoChest = referMapes(mapWithNoChest);
        List<Integer> pisablesX = new ArrayList<Integer>();
        List<Integer> pisablesY = new ArrayList<Integer>();

        for(int j=0;j<mapWithNoChest.pantalles.size();j++) {


            for (int i = 0; i < mapWithNoChest.pantalles.get(j).getAncho();i++) {

                for(int x=0;x <mapWithNoChest.pantalles.get(j).getAlto();x++){
                    if(mapWithNoChest.pantalles.get(j).getDatos()[x][i].getPisablePersonaje()==1) {
                        pisablesX.add(x);
                        pisablesY.add(i);
                    }
                }
            }
            Random r = new Random();

            int rand = r.nextInt(pisablesX.size());
            List<Objeto> cofreContent = new ArrayList<Objeto>();
            cofreContent.add(objetos.get(r.nextInt(objetos.size())));
            mapWithNoChest.pantalles.get(j).getDatos()[pisablesX.get(rand)][pisablesY.get(rand)] =(Cofre) new Cofre(cofreContent);

        }
        return mapWithNoChest;

    }

    //Es crea una partida i es guarda a la List, fins que s'acabi. Mentres va la partida, l'usuari anira informant al server per generar nous cofres i enemics.
    // Així successivament fins que es mori, que es guardarà la partida a la BBDD
    public Partida crearPartida(Partida partida)
    {
        //log.info("Detalls de la partida: "+partida.jugador + "     " + Integer.toString(partida.mapSelection));
        if(partida.mapSelection==0)
             partida.map = this.mapes.get(0);
        else if(partida.mapSelection==1)
           partida.map = this.mapes.get(1);
        partida.player = new Jugador(partida.jugador,partida.proffSelection);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        partida.nom = partida.jugador + timeStamp;
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
        this.partides.remove(i);
        //guardar db

        return partida;
    }
    }
