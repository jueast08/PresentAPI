/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */
package fr.presentapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusDAO {
    private static final String TABLE = "Status";

    private final Connection _connexion;

    public StatusDAO() {
        _connexion = DbConnection.getConnection();
    }

    public boolean insertStatus(Status status) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE;

        if (status.getStatusId() != Status.DEFAULT_ID) {
            query += " VALUES(?, ?)";
        } else {
            query += "VALUES(?)";
        }

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setString(2, String.valueOf(status.getStatusId()));
            if (status.getStatusId() != Status.DEFAULT_ID) {
                stmt.setString(1, String.valueOf(status.getStatusId()));
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
}
