package Repository;

import Model.Cursa;

import java.sql.*;
//import java.sql.Date;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.Date;

/**
 * Created by NicoF on 3/21/2017.
 */
public class RepoCursa extends Repo<Cursa> {
    public RepoCursa(Properties props, String tableName) {
        super(props, tableName);
    }

    @Override
    public void add(Cursa entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into Cursa values ($next_id,?,?,?)")) {
            preStmt.setInt(3,entity.getNr_locuri());
            preStmt.setString(2, entity.getDataPlecarii().toString());
            preStmt.setInt(4,entity.getId_dest());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }

    }

    @Override
    public void update(Integer id, Cursa entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update Cursa set data_plecarii=?, nr_locuri=?, id_dest=? where id = ?")) {
            preStmt.setInt(4,id);
            preStmt.setInt(2,entity.getNr_locuri());
            preStmt.setString(1, entity.getDataPlecarii().toString());
            preStmt.setInt(3,entity.getId_dest());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public ArrayList<Cursa> getAll() {
        Connection c = null;
        Statement stmt = null;
        ArrayList<Cursa> all = new ArrayList<Cursa>();
        try {

            c = conn.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cursa;");

            Date newDate = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String data = rs.getString("data_plecarii");

                int nr_locuri = rs.getInt("nr_locuri");
                int id_dest = rs.getInt("id_dest");
                all.add(new Cursa(id, data,nr_locuri,id_dest));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return all;
    }
}
