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

public class UserModel {
    public static final String TABLE = "Users";
    
    private final Connection _connexion;
    
    public UserModel(){
		_connexion = DbConnection.getConnection();
    }
        
    public boolean insertUsers(User users) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE +
			"(numEtu, firstname, lastname, mail, salt, statusId) " + 
			"VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setLong(1, users.getNumEtu());
			stmt.setString(2, users.getFName());
			stmt.setString(3, users.getLName());
            stmt.setString(4, users.getMail());
            stmt.setString(5, users.getSalt());
            stmt.setLong(6, users.getStatusId());

            if (stmt.executeUpdate() == 0) {
                System.err.println("UsersDAO.java(Error executing query): " + query);
                return false;
            }

            _connexion.commit();
        } catch (SQLException e) {
            System.err.println("UserDAO.java(SQLException): " + e.getMessage());
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
            System.err.println("UsersDAO.java(SQLException): " + e.getMessage());
            success = false;
        }

        return success;
    }
}