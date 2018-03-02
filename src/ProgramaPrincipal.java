import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProgramaPrincipal {
    public static void main(String[] args) throws IOException{
        Mundo mundo= new Mundo();
        boolean menu=true;
        while(menu)
        {
            System.out.println("0* Salir\n"+
                    "1* Añadir Usuario\n"+
                    "2* Eliminar Usuario\n"+
                    "3* Consultar Usuario\n"+
                    "4* Añadir Objeto\n"+
                    "5* Consultar Objetos\n"+
                    "6* Consultar un Objeto específico\n"+
                    "7* Eliminar Objeto\n"+
                    "8* Transferir Objeto\n"+
                    "9* Cargar Escenas\n"+
                    "10* Mostrar Escenas\n"
            );
            boolean res;
            Scanner scanner= new Scanner(System.in);
            String linea = scanner.nextLine();
            String[] partes;
            Usuario user;
            String usuario;
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
                    System.out.println("Escriba el usuario, el identificador, el nombre, la descripcion y el tipo");
                    linea = scanner.nextLine();
                    partes = linea.split(" ");
                    mundo.añadirObjetoAUsuario(mundo.consultarUsuario(partes[0]), new ObjetoEquipable(Integer.parseInt(partes[1]),partes[2],partes[3],Integer.parseInt(partes[4])));
                    break;
                case 5:
                    System.out.println("Escriba el nombre del usuario");
                    linea = scanner.nextLine();
                    List<Objeto> lista= mundo.consultarObjetosDeUsuario(mundo.consultarUsuario(linea));
                    for(int i=0;i<lista.size();i++) {
                        Objeto obj = lista.get(i);
                        System.out.println("ID: "+obj.getID()+" Nombre: "+obj.getNombre()+" Descripción: "+obj.getDescripcion()+" Tipo: "+obj.getTipo());
                    }
                    break;
                case 6:
                    System.out.println("Escriba el nombre del usuario");
                    linea = scanner.nextLine();
                    usuario=linea;
                    System.out.println("Escriba el nombre del objeto");
                    linea = scanner.nextLine();
                    Objeto obj= mundo.consultarObjetoDeUsuario(mundo.consultarUsuario(usuario),linea);
                    System.out.println("ID: "+obj.getID()+" Nombre: "+obj.getNombre()+" Descripción: "+obj.getDescripcion()+" Tipo: "+obj.getTipo());
                    break;
                case 7:
                    System.out.println("Escriba el nombre del usuario");
                    linea = scanner.nextLine();
                    usuario=linea;
                    System.out.println("Escriba el nombre del objeto");
                    linea = scanner.nextLine();
                    user=mundo.consultarUsuario(usuario);
                    res= mundo.eliminarObjetosDeUsuario(user,mundo.consultarObjetoDeUsuario(user,linea));
                    if(res)
                        System.out.println("Eliminado correctamente");
                    else
                        System.out.println("No se ha podido eliminar");
                    break;
                case 8:
                    System.out.println("Escriba el nombre del usuario origen");
                    linea = scanner.nextLine();
                    Usuario user1=mundo.consultarUsuario(linea);
                    System.out.println("Escriba el nombre del usuario destino");
                    linea = scanner.nextLine();
                    Usuario user2=mundo.consultarUsuario(linea);
                    System.out.println("Escriba el nombre del objeto");
                    linea = scanner.nextLine();
                    mundo.transferirObjetoEntreUsuarios(user1,user2,mundo.consultarObjetoDeUsuario(user1,linea));
                    break;
                case 9:
                    System.out.println("Escriba el nombre del fitxero con los escenarios");
                    linea = scanner.nextLine();
                    mundo.cargarEscenas(linea);
                    break;
                case 10:
                    List<Escena> escenas= mundo.consultarEscenas();
                    for(int i=0;i<escenas.size();i++) {
                        Escena escena = escenas.get(i);
                        System.out.println(" Nombre: "+escena.getNombre()+" Alto: "+escena.getAlto()+" Ancho: "+escena.getAncho()+" Descripción: "+escena.getDescripcion());
                        System.out.println("");
                        escena.pintar();
                        System.out.println("");
                    }
                    break;
            }
        }

    }
}
