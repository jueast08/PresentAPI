/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DbConnection {

    private DbConnection() {
    }
    private static Connection instance = null;

    public static synchronized Connection getConnection() {
        if (instance == null) {
			try {
				Context ctxt = new InitialContext();
				DataSource ds = (DataSource)ctxt.lookup("java:comp/env/jdbc/present.db");
				instance = ds.getConnection();
				instance.setAutoCommit(false);
			} catch (SQLException | NamingException e){
				Logger.getLogger("DbConnection.java").log(Level.SEVERE, e.getMessage());
			}
        }
        return instance;
    }
}
