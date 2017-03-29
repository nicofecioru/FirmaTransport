package Repository;

import Model.Client;
import Model.Destinatie;
import sun.security.krb5.internal.crypto.Des;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by NicoF on 3/21/2017.
 */
public class RepoDest extends Repo<Destinatie> {
    public RepoDest(Properties props, String tableName) {
        super(props, tableName);
    }

    @Override
    public void add(Destinatie entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into destinatie values ($next_id,?)")) {
            //preStmt.setInt(1,entity.getId());
            preStmt.setString(2, entity.getNume());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }

    }


    @Override
    public void update(Integer id, Destinatie entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update destinatie set nume=? where id = ?")) {
            preStmt.setInt(2, id);
            preStmt.setString(1, entity.getNume());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public ArrayList<Destinatie> getAll() {
        Connection c = null;
        Statement stmt = null;
        ArrayList<Destinatie> all = new ArrayList<Destinatie>();
        try {

            c = conn.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM destinatie;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nume");
                all.add(new Destinatie(id, name));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return all;
    }


    public Destinatie get(Integer id){
        Connection con=conn.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from "+ tableName + " where id=?")) {
            preStmt.setInt(1, id);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    String data = result.getString("nume");

                    return new Destinatie(id, data);
                }

            }
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
            return null;
        }
    return new Destinatie(1, "jhbh");
    }
}
