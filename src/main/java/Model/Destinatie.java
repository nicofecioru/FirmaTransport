package Model;

/**
 * Created by NicoF on 3/20/2017.
 */
public class Destinatie extends HasId {
    private String nume;

    public Destinatie(Integer id, String nume) {
        super(id);
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String toString(){
        return nume;
    }
}
