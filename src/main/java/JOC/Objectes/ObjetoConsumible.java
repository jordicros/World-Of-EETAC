package JOC.Objectes;

public class ObjetoConsumible extends Objeto {
    public ObjetoConsumible(int identificador,String nombre, String descripcion, int tipo, int stat)
    {
        super(identificador,nombre,descripcion,tipo,stat);
    }
    public ObjetoConsumible(){}

    @Override
    public int getTipo() {
        return 0;
    }
}
