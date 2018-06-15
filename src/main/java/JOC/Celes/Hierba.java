package JOC.Celes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by jordi on 05/03/2018.
 */
public class Hierba extends Celda{

    public String getSimbolo() {
        return "0";
    }

    public int getPisablePersonaje() {
        return 1;
    }
    public int getPisableZombie() {
        return 1;
    }
    public int getInteractuable() {
        return 0;
    }

}
