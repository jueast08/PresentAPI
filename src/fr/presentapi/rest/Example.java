package fr.presentapi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/example")
public class Example {

	  @GET
	  @Produces("application/json")
	  public Response example() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Julian", "Baddass");
		jsonObject.put("Easterly", "is the best");
		return Response.status(200).entity(jsonObject.toString()).build();
	  }

	  @Path("{your_name}")
	  @GET
	  @Produces("application/json")
	  public Response examplePathParam(@PathParam("your_name") String name) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(name, "Baddass");
		jsonObject.put("you", "are the best");

		return Response.status(200).entity(jsonObject.toString()).build();
	  }
}