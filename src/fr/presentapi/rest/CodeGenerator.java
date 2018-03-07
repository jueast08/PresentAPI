/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 18/01/18
 */
package fr.presentapi.rest;

import fr.presentapi.dao.Code;
import fr.presentapi.dao.CodeModel;
import fr.presentapi.dao.Event;
import fr.presentapi.dao.EventModel;
import fr.presentapi.dao.UserModel;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/generate-code")
public class CodeGenerator {

    private final static int CODE_LENGTH = 10;

    /* TODO: to review */
    private String generateRandomCode() {
        Random r = new Random();
        String str = "";
        for (int i = 0; i < CODE_LENGTH; i++) {
            str += (char) ('a' + r.nextInt(26));
        }
        return str;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response launchCall(String reception) {
        JSONObject jsonReception = new JSONObject(reception);
        JSONArray groups = jsonReception.getJSONObject("data").getJSONArray("groups");
        /* To review */
        int userId = jsonReception.getJSONObject("data").getInt("id");
        long duration = jsonReception.getJSONObject("data").getLong("duration");
        String eventName = "appel";
        /* ? */
        String currentDate = "now";
        /* get from reception */

        if (duration < Code.MIN_DURATION || duration > Code.MAX_DURATION) {
            return Response.status(400)
                    .entity(new JSONObject("{\"message\":\"Wrong duration must be an integer between 60 and 604800(1week)\"}").toString())
                    .build();
        }
        UserModel user = new UserModel();
        if (!user.exists(userId)) {
            return Response.status(400).entity(new JSONObject("{\"message\": \"No such user\"}").toString()).build();
        }
        for (int i = 0; i < groups.length(); i++) {
            String g = (String) groups.get(i);
            // TODO: insert into table EventGroup
        }

        new EventModel().insert(new Event(userId, eventName));

        Code code = new Code(generateRandomCode(), currentDate);
        new CodeModel().insert(code);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", new JSONObject().put("code", code.getCode()));
        return Response.status(200).entity(jsonResponse.toString()).build();
    }
}
