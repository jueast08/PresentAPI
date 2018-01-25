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

public class EventModel extends Model<Event> {

    private static final String TABLE = "Event";

    private final Connection _connexion;

    public EventModel() {
        _connexion = DbConnection.getConnection();
    }
	
	
	@Override
	public QueryBuilder select(String[] attributes){
		return builderSelect(TABLE, attributes);
	}

    @Override
    public boolean insert(Event event) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE;

        if (event.getEventId() != Event.DEFAULT_ID) {
            query += " VALUES(?, ?)";
        } else {
            query += "(numEtu, label) VALUES(?, ?)";
        }

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setString(2, String.valueOf(event.getUid()));
            stmt.setString(3, event.getLabel());
            if (event.getEventId() != Event.DEFAULT_ID) {
                stmt.setString(3, String.valueOf(event.getEventId()));
            }
            if (stmt.executeUpdate() == 0) {
                System.err.println("EventDAO.java(Error executing query): " + query);
                return false;
            }

            _connexion.commit();
        } catch (SQLException e) {
            System.err.println("EventDAO.java(SQLException): " + e.getMessage());
            success = false;
        }

        return success;
    }

    @Override
    public boolean exists(Object pk) {
        return true;
	}
}
