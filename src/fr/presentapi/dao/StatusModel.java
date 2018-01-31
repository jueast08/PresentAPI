/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */
package fr.presentapi.dao;

import fr.presentapi.querybuilder.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusModel extends Model<Status> {

    private static final String TABLE = "Status";

    private final Connection _connexion;

    public StatusModel() {
        _connexion = DbConnection.getConnection();
    }
	
	@Override
	public QueryBuilder select(String[] attributes){
		return builderSelect(TABLE, attributes);
	}

    @Override
    public boolean insert(Status status) {
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
            if (stmt.executeUpdate() == 0) {
                System.err.println("StatusDAO.java(Error executing query): " + query);
                return false;
            }

            _connexion.commit();
        } catch (SQLException e) {
            System.err.println("StatusDAO.java(SQLException): " + e.getMessage());
            success = false;
        }

        return success;
    }

    @Override
    public boolean exists(Object pk) {
        return true;
    }
}
