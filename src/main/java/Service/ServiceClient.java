package Service;

import Model.Client;
import Repository.RepoClient;
import Repository.RepoDest;

import java.util.ArrayList;

/**
 * Created by NicoF on 3/22/2017.
 */
public class ServiceClient {
    RepoClient repo;

    public ServiceClient(RepoClient repo) {
        this.repo = repo;
    }

    public String getName(Integer id) {
        if (repo.get(id) != null)
            return repo.get(id).getNume();
        return "-";
    }

    public ArrayList<Client> getList() {
        return repo.getAll();
    }
}
