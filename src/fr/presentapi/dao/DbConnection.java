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

    private DbConnection() {
    }
    private static Connection instance = null;

    public static synchronized Connection getConnection() {
        if (instance == null) {
			try {
				instance = DriverManager.getConnection("jdbc:sqlite:present.db");
				instance.setAutoCommit(false);
			} catch (SQLException ex) {
				System.err.println("Erreur: " + ex.getMessage());
				System.exit(1);
			}
        }
        return instance;
    }
}
