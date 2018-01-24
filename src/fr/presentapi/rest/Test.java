/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.presentapi.rest;

import fr.presentapi.dao.UserModel;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Test{
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		UserModel model = new UserModel();
		boolean res = model.exists((Long)7L);
		return res ? "exists" : "doesn't exist";
	}
}
