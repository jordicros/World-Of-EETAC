package JOC.Mon;

import java.util.List;

//Server s'encarrega de gestionar l'inici i el final de la partida.
public class Partida {
    Mapa map;
    int mapSelection;
    Usuario jugador;
    String nom;
    int ronda=0;
    int enemics;
    long score=0;
    public Partida(Usuario jug, int a){

        jugador=jug;
        this.mapSelection=a;
    }
}
