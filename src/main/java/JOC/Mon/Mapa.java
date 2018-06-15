package JOC.Mon;

import java.util.List;

public class Mapa {
    List<Escena> pantalles;

    public Mapa(List<Escena> pantalles) {
        this.pantalles = pantalles;
    }

    public List<Escena> getPantalles() {
        return pantalles;
    }

    public void setPantalles(List<Escena> pantalles) {
        this.pantalles = pantalles;
    }

}
