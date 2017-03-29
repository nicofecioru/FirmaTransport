package Repository;

import Model.User;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by NicoF on 3/21/2017.
 */
public class RepoUser extends Repo<User> {
    public RepoUser(Properties props, String tableName) {
        super(props, tableName);
    }

    @Override
    public void add(User entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into User values ($next_id,?,?)")) {
            preStmt.setString(3,entity.getParola());
            preStmt.setString(2, entity.getNume());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }

    }

    @Override
    public void update(Integer id, User entity) {
        Connection con = conn.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update User set nume=?, parola=? where id = ?")) {
            preStmt.setInt(3, id);
            preStmt.setString(1, entity.getNume());
            preStmt.setString(2, entity.getParola());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public ArrayList<User> getAll() {
        Connection c = null;
        Statement stmt = null;
        ArrayList<User> all = new ArrayList<User>();
        try {

            c = conn.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nume");
                String parola = rs.getString("parola");
                all.add(new User(id, name,parola));
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

