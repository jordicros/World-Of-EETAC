package JOC.Mon;

import JOC.Objectes.Objeto;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nickname;
    private int profession; // 1: Guerrero, 2: Arquero, 3: Mago
    private int attack;
    private int defense;
    private int magicAttack;
    private List<Objeto> inventario;
    public Jugador(String nick, int profession){
        this.nickname = nick;
        this.profession = profession;
        this.inventario = new ArrayList<Objeto>(); //Potser es mes rentable un altre tipus d'estructura de Dades.
        if(getProfession() == 1)
        {
            //Stats iniciales guerrero
        }
        else if(getProfession() ==2){
            //Stats iniciales arquero
        }
        else
        {
            //Stats iniciales mago
        }
    }
    public int getProfession() {
        return profession;
    }
}
