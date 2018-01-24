/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 24/01/18
 */
package fr.presentapi.rest;

import fr.presentapi.dao.CodeModel;
import fr.presentapi.dao.UserModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;


@Path("/login")
public class Connect {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String reception) {
        JSONObject jsonReception = new JSONObject(reception);
        int userId = jsonReception.getJSONObject("data").getInt("id");
        String password = jsonReception.getJSONObject("data").getString("password");

        UserModel user = new UserModel();
        if (!user.exists(userId)) {
            return Response.status(400).entity(new JSONObject("{\"message\": \"No such user\"}").toString()).build();
        }

        return Response.status(200).entity(new JSONObject("{\"message\": \"You're a user!\"}").toString()).build();
    }
}