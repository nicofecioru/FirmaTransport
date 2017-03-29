package Model;

/**
 * Created by NicoF on 3/20/2017.
 */
public class HasId {
    @Override
    public String toString() {
        return
                "id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HasId(Integer id) {
        this.id = id;
    }

    protected Integer id;
}
