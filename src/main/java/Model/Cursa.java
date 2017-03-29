package Model;




import java.util.Stack;

/**
 * Created by NicoF on 3/20/2017.
 */
public class Cursa extends HasId{

    private String dataPlecarii;
    private int nr_locuri;
    private int id_dest;

    public Cursa(Integer id, String dataPlecarii, int nr_locuri, int id_dest) {
        super(id);
        this.dataPlecarii =  dataPlecarii;
        this.nr_locuri = nr_locuri;
        this.id_dest = id_dest;
    }

    public int getNr_locuri() {
        return nr_locuri;
    }

    public void setNr_locuri(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public String getDataPlecarii() {
        return dataPlecarii;
    }

    public void setDataPlecarii(String dataPlecarii) {
        this.dataPlecarii = dataPlecarii;
    }

    public int getId_dest() {
        return id_dest;
    }

    public void setId_dest(int id_dest) {
        this.id_dest = id_dest;
    }


}
