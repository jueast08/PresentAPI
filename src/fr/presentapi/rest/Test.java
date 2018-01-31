/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.presentapi.rest;

import fr.presentapi.dao.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Test{
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() throws SQLException{
		UserModel model = new UserModel();
		String[] att = {};
		ResultSet rs = model.select(att).where("firstname", "smith").request();
		if(rs == null){
			return "Error occured : null ResultSet";
		}
		if(!rs.next()){
			return "No result!";
		}
		return rs.getString("lastname");
	}
}
