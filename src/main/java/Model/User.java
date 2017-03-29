package Model;

/**
 * Created by NicoF on 3/20/2017.
 */
public class User extends HasId {
    public User( Integer id,String nume, String parola) {
        super(id);
        this.nume = nume;
        this.parola = parola;
    }

    private  String nume;
    private  String parola;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
