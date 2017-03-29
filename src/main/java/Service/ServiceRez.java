package Service;

import Model.Rezervare;
import Repository.RepoDest;
import Repository.RepoRezervare;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NicoF on 3/22/2017.
 */
public class ServiceRez {

    RepoRezervare repo;

    public Integer getMax() {
        return max;
    }

    Integer max;

    public ServiceRez(RepoRezervare repo) {
        this.repo = repo;
    }

    public ArrayList<Rezervare> getAll(Integer id){
        ArrayList<Rezervare> list = new ArrayList<Rezervare>();
        max = -1;
        for (Rezervare rez : repo.getAll()) {
            if (rez.getIdCursa() == id) {
                if (rez.getLoc() > max) {
                    max = rez.getLoc();
                }
                list.add(rez);
            }
        }
        if (max == -1) {
            max = 0;
        }
        for (int i = max + 1;i <= 18;i++) {
            list.add(new Rezervare(1, -1, id, i));
        }

        return list;
    }

    public void adauga(int id_client, int loc, int id_cursa){
        repo.add(new Rezervare(1, id_client, id_cursa, loc));
    }


}
