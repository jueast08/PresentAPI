/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DbConnection{

	private final static String DEFAULT_DRIVER = "mysql";

	private DbConnection(){
	}
	private static Connection instance = null;

	private static String _getDatabaseDriverName(){
		Properties prop = new Properties();
		File f = new File("config.properties");
	
		try{
			prop.load(new FileInputStream(f));
		} catch(FileNotFoundException e){
			prop.setProperty("DatabaseDriver", DEFAULT_DRIVER);
			try{
				prop.store(new FileOutputStream(f), "Database driver among: \n\t- psql (postgreSQL)\n\t- mysql (MySQL)\n\t- sqlite (SQLite)");
			} catch(IOException ex){
				System.out.println(ex.getMessage());
			}
		} catch(IOException e){
			System.err.println(e.getMessage());
		}
		return prop.getProperty("DatabaseDriver", DEFAULT_DRIVER);
	}

	public static synchronized Connection getConnection(){
		System.err.println("Getting connection");
		if(instance == null){
			String drivername = _getDatabaseDriverName();
			System.err.println(drivername);
			try{
				Context ctxt = new InitialContext();
				DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/database_" + drivername);
				if(ds == null){
					Logger.getLogger("DbConnection.java").log(Level.SEVERE, "Can't connect to database");
				}
				instance = ds.getConnection();
				instance.setAutoCommit(false);
			} catch(SQLException ex){
				System.err.println("Can't get database - Erreur: " + ex.getMessage());

			} catch(NamingException e){
				Logger.getLogger(DbConnection.class
					.getName()).log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return instance;
	}
}
