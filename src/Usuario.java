
public class Usuario {
    private String nickname;
    private String password;
    private int level;
    private int profession; // 1: Guerrero, 2: Arquero, 3: Mago
    private int attack;
    private int defense;
    private int magicAttack;
    private int magicDefense;
    private String[] listaAmigos;
    private String[] inventario;
    public String getNickname(){
        return this.nickname;
    }

    public String getPassword(){
        return this.password;
    }
    public void setLevel(){
        this.level++;
    }
    public int getLevel(){
        return this.level;
    }
    public int getProfession() {
        return profession;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttack() {
        return attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getDefense() {
        return defense;
    }
    public void setMagicAttack(int magicAttack) {
        this.magicAttack = magicAttack;
    }
    public int getMagicAttack() {
        return magicAttack;
    }
    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }
    public int getMagicDefense() {
        return magicDefense;
    }
    public void setInventario(String[] inventario) {
        this.inventario = inventario;
    }
    public String[] getInventario() {
        return inventario;
    }
    public void setListaAmigos(String[] listaAmigos) {
        this.listaAmigos = listaAmigos;
    }
    public String[] getListaAmigos() {
        return listaAmigos;
    }

    public Usuario()
    {
        this.listaAmigos = new String[50];
        this.inventario = new String[20]; //Potser es mes rentable un altre tipus d'estructura de dades.
    }
    public Usuario(String nick, String password, int profession){
        this.nickname = nick;
        this.password = password;
        this.profession = profession;
        this.listaAmigos = new String[50];
        this.inventario = new String[20]; //Potser es mes rentable un altre tipus d'estructura de dades.
        if(getProfession() == 1)
        {
            //Stats iniciales guerrero
        }
        else if(getProfession() ==2){
            //Stats iniciales arquero
        }
        else
        {
            //Stats iniciales mago
        }
    }
}
