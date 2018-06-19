package JOC.Objectes;

public class ObjetoEquipable extends Objeto {
    public int parteCuerpo;
    public ObjetoEquipable(int identificador,String nombre, String descripcion, int tipo)
    {
        super(identificador,nombre,descripcion,tipo);
    }
    public ObjetoEquipable(){}
    @Override
    public int getTipo() {
        return 1;
    }

}