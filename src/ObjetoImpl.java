public class ObjetoImpl implements Objeto {
    private int id;
    private String descripcion;
    private int tipo;

        public int getID(){
            return this.id;
        }
        public String getDescripcion(){
            return this.descripcion;
        }
        public int getTipo(){
            return this.tipo;
        } //Arma, pocion, etc

    public ObjetoImpl(int identificador, String descripcion, int tipo)
        {
            this.id=identificador;
            this.descripcion=descripcion;
            this.tipo=tipo;
        }

    }
