package JOC.DAO;

public class Employee {
    private String nom;
    private String cognom;
    private int salari;

    public Employee(String nom,String cognom,int salari){
        this.nom=nom;
        this.cognom=cognom;
        this.salari=salari;
    }
    public Employee(){

    };
    public int getSalari() {
        return salari;
    }

    public String getCognom() {
        return cognom;
    }

    public String getNom() {
        return nom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSalari(int salari) {
        this.salari = salari;
    }
}

