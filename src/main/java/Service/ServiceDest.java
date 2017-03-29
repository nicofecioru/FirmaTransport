package Service;

import Model.Destinatie;
import Repository.RepoDest;

import java.util.ArrayList;

/**
 * Created by NicoF on 3/22/2017.
 */
public class ServiceDest {

    RepoDest repo;

    public ServiceDest(RepoDest repo) {
        this.repo = repo;
    }

    public String getName(Integer id){
        return repo.get(id).getNume();

    }

    public Integer getId(String nume){
        for (Destinatie d :repo.getAll()){
            if(d.getNume().equals(nume))
                return d.getId();
        }
        return null;
    }

    public ArrayList<Destinatie> getList() {
        return repo.getAll();
    }
}
