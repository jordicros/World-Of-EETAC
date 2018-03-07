public class Rio {
    private String info; //Variable temporal perque funcioni imprimir mapes
    public int getPisable() {
        return 0;
    }
    public int getInteractuable() {
        return 0;
    }
    public void setInfo(String i){
        this.info = i;
    }
    public String getInfo(){return this.info;}
    public Hierba(String terra)
    {setInfo(terra);}
}
