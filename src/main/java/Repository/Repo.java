package Repository; /**
 * Created by NicoF on 3/8/2017.
 */

import Model.HasId;

import java.sql.*;
import java.util.*;
import java.io.*;



public class Repo<T extends HasId> {
    //Conectarea la o baza de date SQLite

    protected ConnectionFactory conn;
    protected String tableName;
    public Repo(Properties props, String tableName){
        conn= new ConnectionFactory(props);
        this.tableName = tableName;

    }

    public ArrayList<T> getAll() {
        return new ArrayList<T>();
    }

    public void add(T elem){

    }
    public void delete (Integer id){
        Connection con=conn.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from "+ tableName + " where id=?")){
            preStmt.setInt(1,id);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }

    }

    public void update(Integer id, T elem){

    }



}
