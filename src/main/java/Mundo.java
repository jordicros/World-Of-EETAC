import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Mundo {
    private List<Usuario> usuarios;
    private List<Escena> escenas;
    private List<Objeto> objetos;

    public Mundo() throws IOException {
        usuarios = new LinkedList<Usuario>();
        escenas= new LinkedList<Escena>();
        cargarEscenas("Escenas/escenaris.txt");
        ObjectMapper mapper = new ObjectMapper();
        Escena obj = this.obtenerEscena("Escenas/escenari1.txt");
        String ruta_abs = new File("").getAbsolutePath();
        mapper.writeValue(new File(ruta_abs+"/src/main/java/recursos/file.json"), Escena.class);

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

    public boolean eliminarObjetosDeUsuario(Usuario u,Objeto o){
        return u.getInventario().remove(o);
    }

    public void transferirObjetoEntreUsuarios(Usuario origen,Usuario destino,Objeto o) {
        origen.getInventario().remove(o);
        destino.getInventario().add(o);
    }

    public int cargarEscenas(String nom) throws FileNotFoundException,IOException {
    String ruta_abs = new File("").getAbsolutePath();
    BufferedReader reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/java/recursos/"+nom));
    int num = Integer.parseInt(reader.readLine());
    String[] rutes= new String[num];
    for(int i=0;i<num;i++) {
        rutes[i]=reader.readLine();
    }
    reader.close();
    for(int a=0;a<num;a++) {
        Escena escena= new Escena();
        reader = new BufferedReader(new FileReader(ruta_abs+"/src/main/java/recursos/"+rutes[a]));
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
                    case("h"):
                        matriz[i][j]=new Hierba(datos[j]);
                        break;
                    case("r"):
                        matriz[i][j]=new Rio(datos[j]);
                        break;
                    case("p"):
                        matriz[i][j]=new Hierba(datos[j]);
                        break;
                }

            }
        }
        escena.setDatos(matriz);
        escenas.add(escena);
    }
    return 0;
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

    public List<Escena> consultarEscenas() {
        return escenas;
    }

    }
