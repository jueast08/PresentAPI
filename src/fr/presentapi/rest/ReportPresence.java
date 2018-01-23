/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.presentapi.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author Coline
 */


@Path("/i-am-here")
public class ReportPresence {
	  @GET
	  @Produces("application/json")
	  public Response example() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Me", "I am here!!");
		return Response.status(200).entity(jsonObject.toString()).build();
	  }

	  @Path("{code}")
	  @GET
	  @Produces("application/json")
	  public Response examplePathParam(@PathParam("code") String code) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(code, "Code à entrer");

		return Response.status(200).entity(jsonObject.toString()).build();
	  }
}
