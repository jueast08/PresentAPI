/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 07/12/17
 */
package fr.presentapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BelongModel extends Model<Belong> {

    public final static String TABLE = "Belongs";
    public final static String[] FIELDS = {"uid", "groupId"};

    private final Connection _conn;

    public BelongModel() {
        _conn = DbConnection.getConnection();
    }

    @Override
    public boolean insert(Belong belong) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE + "(uid, groupId) VALUES(?, ?)";

        try {
            PreparedStatement stmt = _conn.prepareStatement(query);
            stmt.setLong(1, belong.getUid());
            stmt.setLong(2, belong.getGroupId());
            if (!stmt.execute()) {
                System.err.println("BelongModel.java(Error executing query): " + query);
                success = false;
            }
            _conn.commit();
        } catch (SQLException e) {
            System.err.println("BelongModel.java(SQLException): " + e);
            success = false;
        }

        return success;
    }

    public List<Group> findGroupForUser(long uid) {
        ArrayList<Group> groups = new ArrayList<>();
        ResultSet rs;
        String query = "SELECT * FROM " + TABLE + " g "
                + "LEFT JOIN " + UserModel.TABLE + " u ON g.uid = u.uid "
                + "WHERE uid = ?";

        try {
            PreparedStatement stmt = _conn.prepareStatement(query);
            stmt.setLong(1, uid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                groups.add(new Group(
                        rs.getLong("groupId"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
        }

        return groups;
    }

    public List<User> findUserInGroup(long gid) {
        ArrayList<User> users = new ArrayList<>();
        ResultSet rs;
        String query = "SELECT * FROM " + TABLE
                + "WHERE groupId = ?";

        try {
            PreparedStatement stmt = _conn.prepareStatement(query);
            stmt.setLong(1, gid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getLong("uid"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getString("mail"),
                        rs.getString("salt"),
                        rs.getLong("statusId")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
        }

        return users;
    }

    @Override
    public boolean exists(Object pk) {
        return true;
    }
}