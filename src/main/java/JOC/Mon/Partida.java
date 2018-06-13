package JOC.Mon;

import java.util.List;

//Server s'encarrega de gestionar l'inici i el final de la partida.
public class Partida {
    Usuario jugador;
    String nom;
    List<Escena> mapaFull;
    int ronda=0;
    int enemics;
    long score=0;
    public Partida(Usuario jug, List<Escena> mapa){

        jugador=jug;
        mapaFull=mapa;
    }
}
