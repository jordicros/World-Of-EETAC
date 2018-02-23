public class Escena {
    private String nombre;
    private int X;//Ancho
    private int Y;//Alto
    private String descripcion;
    private String[][] Datos;//Ancho x alto

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

    public void setDatos(String[][] datos) {
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

    public String[][] getDatos() {
        return Datos;
    }
}
