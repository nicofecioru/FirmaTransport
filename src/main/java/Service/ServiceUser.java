package Service;

import Model.User;
import Repository.RepoUser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NicoF on 3/22/2017.
 */
public class ServiceUser {
    private RepoUser rep;

    public ServiceUser(RepoUser rep) {
        this.rep = rep;
    }

    public User search(String nume, String parola){
        for (User u : rep.getAll()){
            if (u.getParola().equals(parola) && u.getNume().equals(nume)){
                return u;
            }
        }
        return null;

    }
}
