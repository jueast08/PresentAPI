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

public class CodeModel {
    
    private static final String TABLE = "Code";

    private final Connection _connexion;

    public CodeModel() {
        _connexion = DbConnection.getConnection();
    }

    public boolean insertCode(Code code) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE;

        if (code.getDuration() != Code.DEFAULT_DURATION) {
            query += "(code, creation, duration) VALUES(?, ?, ?)";
        } else {
            query += "(code, creation) VALUES(?, ?)";
        }

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setString(1, code.getCode());
            stmt.setString(2, code.getCreation());
            if (code.getDuration() != Code.DEFAULT_DURATION) {
                stmt.setString(3, String.valueOf(code.getDuration()));
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