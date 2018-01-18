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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

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

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response answerCall(String reception) {
        JSONObject jsonReception = new JSONObject(reception);
        int userId = jsonReception.getJSONObject("data").getInt("id");
        String codeSent = jsonReception.getJSONObject("data").getString("code");
        
        UserModel user = new UserModel();
        if (!user.exists(userId)) {
            return Response.status(400).entity(new JSONObject("{}").toString()).build();
        }
        
        //Code code = new Code(codeSent, "now");
        CodeModel codeM = new CodeModel();
        if (!codeM.exists(codeSent)) {
            return Response.status(400).entity(new JSONObject("{}").toString()).build();
        }
        return Response.status(200).entity(new JSONObject("{}").toString()).build();
    }
}
