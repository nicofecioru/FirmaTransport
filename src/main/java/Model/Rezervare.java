package Model;

/**
 * Created by NicoF on 3/20/2017.
 */
public class Rezervare extends HasId {
    private Integer idClient;
    private Integer idCursa;
    private Integer loc;


    public Rezervare(Integer id,Integer idClient, Integer idCursa, Integer loc) {
        super(id);
        this.idClient = idClient;
        this.idCursa = idCursa;
        this.loc = loc;
    }

    public Integer getIdCursa() {
        return idCursa;
    }

    public void setIdCursa(Integer idCursa) {
        this.idCursa = idCursa;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getLoc() {
        return loc;
    }

    public void setLoc(Integer loc) {
        this.loc = loc;
    }
}
