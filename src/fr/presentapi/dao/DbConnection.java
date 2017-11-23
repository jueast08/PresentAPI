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
