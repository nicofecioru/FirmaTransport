package Repository;

import Model.Rezervare;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by NicoF on 3/22/2017.
 */
public class RepoRezervare extends Repo<Rezervare> {
    public RepoRezervare(Properties props, String tableName) {
        super(props, tableName);
    }

    @Override
    public void add(Rezervare entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into Rezervare values ($next_id,?,?,?)")) {
            preStmt.setInt(2,entity.getIdClient());
            preStmt.setInt(3, entity.getLoc());
            preStmt.setInt(4,entity.getIdCursa());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }

    }

    @Override
    public void update(Integer id, Rezervare entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update Rezervare set id_client=?, loc=?, id_cursa=? where id = ?")) {
            preStmt.setInt(4,id);
            preStmt.setInt(1,entity.getIdClient());
            preStmt.setInt(2, entity.getLoc());
            preStmt.setInt(3,entity.getIdCursa());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public ArrayList<Rezervare> getAll() {
        Connection c = null;
        Statement stmt = null;
        ArrayList<Rezervare> all = new ArrayList<Rezervare>();
        try {

            c = conn.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Rezervare;");
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_client = rs.getInt("id_client");
                int loc = rs.getInt("loc");
                int id_cursa = rs.getInt("id_cursa");
                all.add(new Rezervare(id, id_client,id_cursa,loc));
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

