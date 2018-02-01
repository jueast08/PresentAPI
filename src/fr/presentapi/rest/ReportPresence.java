/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 18/01/18
 */
package fr.presentapi.rest;

import fr.presentapi.dao.CodeModel;
import fr.presentapi.dao.UserModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/i-am-here")
public class ReportPresence {
    @Path("{code}")
    @GET
    @Produces("application/json")
    public Response examplePathParam(@PathParam("code") String code) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(code, "Code à entrer");

        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response answerCall(String reception) {
        JSONObject jsonReception = new JSONObject(reception);
        int userId = Integer.parseInt(jsonReception.getJSONObject("data").getString("id"));
        String codeSent = jsonReception.getJSONObject("data").getString("code");

        UserModel user = new UserModel();
        if (!user.exists(userId)) {
            return Response.status(400).entity(new JSONObject("{\"message\": \"No such user\"}").toString()).build();
        }

        CodeModel codeM = new CodeModel();
        if (!codeM.exists(codeSent)) {
            return Response.status(400).entity(new JSONObject("{\"message\": \"No such code\"}").toString()).build();
        }
		// TODO: add user to present table
		
		JSONObject response = new JSONObject().put("data", new JSONObject().put("message", "You're here!"));
        return Response.status(200).entity(response.toString()).build();
    }
}
