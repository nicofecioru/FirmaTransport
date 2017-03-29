package Model;

/**
 * Created by NicoF on 3/20/2017.
 */
public class Client extends HasId{
    @Override
    public String toString() {
        return nume;
    }

    public Client(int id, String nume) {
        super(id);
        this.nume = nume;
    }

    private String nume;


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
