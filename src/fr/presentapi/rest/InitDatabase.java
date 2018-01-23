/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 * 
 * @version 0.0.1 - Last modified: 09/11/17
 */
package fr.presentapi.rest;

import fr.presentapi.csv.StudentLoader;
import fr.presentapi.dao.DbConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/initdb")
public class InitDatabase{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response init() throws SQLException, IOException, NamingException{
		JSONObject json = new JSONObject();
		String executed = "";
		/* TMP */
		//Context ctxt = new InitialContext();
		//String filename = (String)ctxt.lookup("java:comp/env/init");
		//System.out.println(filename);
		/*
		String s;
		StringBuffer sb = new StringBuffer();
		Connection conn = DbConnection.getConnection();
		Statement stmt = conn.createStatement();
		try{
			//BufferedReader br = new BufferedReader(new InputStreamReader(c.getResourceAsStream("../database.sql"), "UTF-8"));
			BufferedReader br = new BufferedReader(new FileReader(new File("/home/waffles/Documents/ensicaen/present/database.sql")));
			while((s = br.readLine()) != null){
				sb.append(s);
			}
			br.close();
			
			String[] inst = sb.toString().split(";");
			Logger.getLogger("InitDatabase.java").log(Level.SEVERE, Arrays.toString(inst));
			System.out.println("bah\n\n\n");
			for(String tmp : inst){
				if(!tmp.trim().equals("")){
					stmt.executeUpdate(tmp);
					executed += tmp + "\n";
				}
			}
			conn.commit();
		} catch(FileNotFoundException e){
			Logger.getLogger(InitDatabase.class.getName()).log(Level.SEVERE, e.getMessage());
		}
		/**/
		StudentLoader loader = new StudentLoader("resources/students.csv");
		loader.load();
		
		json.put("message", "Database successfully initialized");
		return Response.status(200).entity(json.toString()).build();
	}
		
}
