package fr.presentapi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/example")
public class Example {

	  @GET
	  @Produces("application/json")
	  public Response example() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Julian", "Baddass");
		jsonObject.put("Easterly", "is the best");
		return Response.status(200).entity(jsonObject.toString()).build();
	  }

	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response examplePathParam(@PathParam("f") float f) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius =  (f - 32)*5/9; 
		jsonObject.put("F Value", f); 
		jsonObject.put("C Value", celsius);

		return Response.status(200).entity(jsonObject.toString()).build();
	  }
}