
public class Celda {
    int tipo; //potser val mes la pena fer classes heredades
    String info;

    public Celda(String a){
        info=a;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }
}
