package Service;

import Model.Cursa;
import Repository.RepoCursa;

import java.util.ArrayList;

/**
 * Created by NicoF on 3/22/2017.
 */
public class ServiceCursa {
    private RepoCursa rep;

    public ServiceCursa(RepoCursa rep) {
        this.rep = rep;
    }

    public ArrayList<Cursa> getList(){
        return rep.getAll();
    }

    public Integer searchId(Integer id, String date){
        for (Cursa c : getList()){
            if(c.getId_dest()==id && c.getDataPlecarii().equals(date)){
                return c.getId();
            }
        }
        return null;
    }

    public Cursa search(Integer id){
        for (Cursa c : getList()){
            if(c.getId()==id ){
                return c;
            }
        }
        return null;
    }

    public void update(Integer id){
        for (Cursa c : getList()){
            if(c.getId()==id ){
                rep.update(id, new Cursa(id, c.getDataPlecarii(), c.getNr_locuri()-1, c.getId_dest()));
            }
        }

    }
    }

