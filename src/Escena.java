public class Escena {
    private String nombre;
    private int X;//Ancho
    private int Y;//Alto
    private String descripcion;
    private Celda[][] Datos;//Ancho x alto CELDAS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDatos(Celda[][] datos) {
        Datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Celda[][] getDatos() {
        return Datos;
    }
    public void Escena(){

    }

    public void Escena(String nombre, int x,int y, String descripcion,Celda[][] datos){
        this.setNombre(nombre);
        this.setX(x);
        this.setY(y);
        this.setDescripcion(descripcion);
        this.setDatos(datos);
    }
}
