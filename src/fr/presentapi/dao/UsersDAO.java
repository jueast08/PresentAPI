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

public class UsersDAO {
    private static final String TABLE = "Users";
    
    private final Connection _connexion;
    
    public UsersDAO(){
		_connexion = DbConnection.getConnection();
    }
        
    public boolean insertUsers(Users users) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE;

        if (users.getStatusId() != Users.DEFAULT_ID) {
            query += " VALUES(?, ?, ?)";
        } else {
            query += "VALUES(?,?)";
        }

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setString(1, String.valueOf(users.getNumEtu()));
            stmt.setString(2, users.getSalt());
            if (users.getStatusId() != Users.DEFAULT_ID) {
                stmt.setString(3, String.valueOf(users.getStatusId()));
            }
            if (!stmt.execute()) {
                System.err.println("Error executing query: " + query);
                return false;
            }

            _connexion.commit();
        } catch (SQLException e) {
            System.err.println("SQLException");
            success = false;
        }

        return success;
    }
    
    boolean updateUsers(Users users) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE;

        if (users.getStatusId() != Users.DEFAULT_ID) {
            query += " VALUES(?, ?, ?)";
        } else {
            query += "VALUES(?,?)";
        }

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setString(1, String.valueOf(users.getNumEtu()));
            stmt.setString(2, users.getSalt());
            if (users.getStatusId() != Users.DEFAULT_ID) {
                stmt.setString(3, String.valueOf(users.getStatusId()));
            }
            if (!stmt.execute()) {
                System.err.println("Error executing query: " + query);
                return false;
            }

            _connexion.commit();
        } catch (SQLException e) {
            System.err.println("SQLException");
            success = false;
        }

        return success;
    }
    
    boolean deleteUsers(Users users) {
        return true;
    }
    
    
}
