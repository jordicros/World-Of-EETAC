package JOC.Celes;

public class Rio extends Celda{
    public String getSimbolo() {
        return "-";
    }

    @Override
    public int getPisablePersonaje() {
        return 0;
    }

    public int getInteractuable() {
        return 0;
    }

    @Override
    public int getPisableZombie() {
        return 0;
    }
}
