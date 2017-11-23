/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public final class DbConnection {

    private Connection _conn;

    private DbConnection() {
        try {
            _conn = DriverManager.getConnection("jdbc:sqlite:present.db");
        } catch (SQLException ex) {
            System.err.println("Erreur: " + ex.getMessage());
            System.exit(1);
        }
    }
    private static DbConnection instance = null;

    public static synchronized DbConnection getConnection() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }
}
