/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.presentapi.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Coline
 */
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
