/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.fr.presentapi.dao;

import fr.presentapi.dao.User;
import static org.junit.Assert.*;

import fr.presentapi.dao.UserModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UserModelTest extends JerseyTest{
	private Connection _conn;
	private UserModel _model; 
	
	@Override
	public Application configure(){
		return new ResourceConfig(UserModelTest.class);
	}
	
	@Before
	public void setup() throws SQLException{
		_conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/presentdb", "testuser", "password");
		_model = new UserModel(_conn);
	}
	@After
	public void cleanup() throws SQLException{
		_conn.close();
	}
	
	@Test
	public void testExists(){
		assertTrue(_model.exists((Long)1L));
		assertFalse(_model.exists((Long)120L));
	}
	
	@Test
	public void testInsert(){
		User u = new User("bruce", "wayne", "bruce@batman.com", "1234567890", 1L);
		//_model.insert(u);
		// do delete
	}
}
