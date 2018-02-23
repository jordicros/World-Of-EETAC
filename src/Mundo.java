import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Mundo {
    private List<Usuario> usuarios;
    private List<Objeto> objetos;

    public Mundo() {
        usuarios = new LinkedList<Usuario>();
        objetos = new LinkedList<Objeto>();
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

    public boolean eliminarUsuario(String nombre)
    {
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

    public void añadirObjetoAUsuario(Usuario u, Objeto o)
    {
        
    }

    

}
