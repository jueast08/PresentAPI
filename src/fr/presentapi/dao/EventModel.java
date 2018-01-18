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

public class EventModel {

    private static final String TABLE = "Event";

    private final Connection _connexion;

    public EventModel() {
        _connexion = DbConnection.getConnection();
    }

    public boolean insertStatus(Event event) {
        boolean success = true;
        String query = "INSERT INTO " + TABLE;

        if (event.getEventId() != Status.DEFAULT_ID) {
            query += " VALUES(?, ?)";
        } else {
            query += "VALUES(?)";
        }

        try {
            PreparedStatement stmt = _connexion.prepareStatement(query);
            stmt.setString(2, String.valueOf(event.getNumEtu()));
            stmt.setString(3, event.getLabel());
            if (event.getEventId() != Event.DEFAULT_ID) {
                stmt.setString(1, String.valueOf(event.getEventId()));
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
}
