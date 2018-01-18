/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModel extends Model<User> {

    public static final String TABLE = "Users";

    private final Connection _connexion;

    public UserModel() {
        _connexion = DbConnection.getConnection();
    }

    @Override
    public boolean insert(User user) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE
                + "(numEtu, firstname, lastname, mail, salt, statusId) "
                + "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setLong(1, user.getNumEtu());
            stmt.setString(2, user.getFName());
            stmt.setString(3, user.getLName());
            stmt.setString(4, user.getMail());
            stmt.setString(5, user.getSalt());
            stmt.setLong(6, user.getStatusId());

            if (stmt.executeUpdate() == 0) {
                System.err.println("UserModel.java(Error executing query): " + query);
                return false;
            }

            _connexion.commit();
        } catch (SQLException e) {
            System.err.println("UserModel.java(SQLException): " + e.getMessage());
            success = false;
        }

        return success;
    }

    public boolean deleteUsers(long numetu) {
        boolean success = true;
        String query = "DELETE FROM " + TABLE + " WHERE numEtu = ?";

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setLong(1, numetu);
            if (stmt.executeUpdate() == 0) {
                System.err.println("Error executing query: " + query);
                return false;
            }

            _connexion.commit();
        } catch (SQLException e) {
            System.err.println("UserModel.java(SQLException): " + e.getMessage());
            success = false;
        }

        return success;
    }

    @Override
    public boolean exists(Object pk) {
        String query = "SELECT 1 FROM " + UserModel.TABLE + " WHERE numEtu = ?";
        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setLong(1, (Long) pk);
            if (!stmt.execute()) {
                System.err.println("UserModel.java(Error executing query: " + query);
                return false;
            }
            return stmt.getResultSet().next();
        } catch (SQLException e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			return false;
        }
    }
}
