import java.util.List;
import java.util.Scanner;

public class ProgramaPrincipal {
    public static void main(String[] args)
    {
        Mundo mundo= new Mundo();
        boolean menu=true;
        while(menu)
        {
            System.out.println("0* Salir\n"+
                    "1* Añadir Usuario\n"+
                    "2* Eliminar Usuario\n"+
                    "3* Consultar Usurio\n"+
                    "4* Añadir Objeto\n"+
                    "5* Consultar Objetos\n"
            );
            boolean res;
            Scanner scanner= new Scanner(System.in);
            String linea = scanner.nextLine();
            String[] partes;
            Usuario user;
            int op= Integer.parseInt(linea);
            switch (op) {
                case 0: menu=false;
                        break;
                case 1:
                    System.out.println("Escriba el nombre,password y profesion separados por espacios");
                    linea = scanner.nextLine();
                    partes = linea.split(" ");
                    res= mundo.crearUsuario(new Usuario(partes[0],partes[1],Integer.parseInt(partes[2])));
                    if(res)
                        System.out.println("Añadido correctamente");
                    else
                        System.out.println("No se ha podido añadir");
                    break;
                case 2:
                    System.out.println("Escriba el nombre del usuario a eliminar");
                    linea = scanner.nextLine();
                    res= mundo.eliminarUsuario(linea);
                    if(res)
                        System.out.println("Eliminado correctamente");
                    else
                        System.out.println("No se ha podido eliminar");
                    break;
                case 3:
                    System.out.println("Escriba el nombre del usuario a consultar");
                    linea = scanner.nextLine();
                    user= mundo.consultarUsuario(linea);
                    if(user==null)
                        System.out.println("No se ha encontrado el usuario");
                    else
                        System.out.println("Nombre: "+user.getNickname()+" Password: "+user.getPassword()+" Profesion: "+user.getProfession());
                    break;
                case 4:
                    System.out.println("Escriba el usuario, el identificador, descripcion y el tipo");
                    linea = scanner.nextLine();
                    partes = linea.split(" ");
                    mundo.añadirObjetoAUsuario(mundo.consultarUsuario(partes[0]), new ObjetoImpl(Integer.parseInt(partes[1]),partes[2],Integer.parseInt(partes[3])));
                    break;
                case 5:
                    System.out.println("Escriba el nombre del usuario");
                    linea = scanner.nextLine();
                    List<Objeto> lista= mundo.consultarObjetosDeUsuario(mundo.consultarUsuario(linea));
                    for(int i=0;i<lista.size();i++) {
                        Objeto obj = lista.get(i);
                        System.out.println("ID: "+obj.getID()+" Descripción: "+obj.getDescripcion()+" Tipo: "+obj.getTipo());
                    }
                    break;
            }
        }

    }
}
