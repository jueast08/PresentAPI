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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/generate-code")
public class CodeGenerator {

    private final static int CODE_LENGTH = 10;

    private String generateRandomCode() {
        Random r = new Random();
        String str = "";
        for (int i = 0; i < CODE_LENGTH; i++) {
            str += (char) ('a' + r.nextInt(26));
        }
        return str;
    }

    @GET
    @Produces("application/json")
    //@Consumes("application/json")
    public Response launchCall(/*String reception*/) {
       /* JSONObject jsonReception= new JSONObject(reception);
        String nameGroup = jsonReception.getJSONObject("data").getString("groups");
        int userId = jsonReception.getJSONObject("data").getInt("id");
        long duration = jsonReception.getJSONObject("data").getLong("duration");
        String eventName = "appel";*/
        String currentDate = "now";
        
        /*
        UserModel user = new UserModel();
        if (!user.exists(userId)) {
            return Response.status(400).entity("User doesn't exist").build();
        }
        
        if (duration < 60 || duration > 5000) {
            return Response.status(400).entity("Impossible value for duration").build();
        }
        
        Event event = new Event(userId, eventName);
        EventModel eventModel = new EventModel();
        eventModel.insert(event);
        */
        // Code code = new Code(generateRandomCode(), currentDate);
        //CodeModel codeModel = new CodeModel();
        //codeModel.insertCode(code);
        
        JSONObject jsonReponse = new JSONObject();
        jsonReponse.put("code", generateRandomCode());
        
        return Response.status(200).entity(jsonReponse.toString()).build();
    }
}
