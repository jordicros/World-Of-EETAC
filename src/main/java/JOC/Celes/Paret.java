package JOC.Celes;

public class Paret extends Celda {
    @Override
    public int getPisablePersonaje() {
        return 0;
    }

    @Override
    public int getInteractuable() {
        return 0;
    }

    @Override
    public int getPisableZombie() {
        return 0;
    }

    @Override
    public String getSimbolo() {
        return "P";
    }
}
