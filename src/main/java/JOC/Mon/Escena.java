package JOC.Mon;

import JOC.Celes.Celda;

public class Escena {
    private String nombre;
    private int ancho;//Ancho
    private int alto;//Alto
    private String descripcion;
    private Celda[][] Datos;//Ancho x alto CELDAS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAncho(int x) {
        ancho= x;
    }

    public void setAlto(int y) {
        alto = y;
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

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
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
        this.setAncho(x);
        this.setAlto(y);
        this.setDescripcion(descripcion);
        this.setDatos(datos);
    }
}
