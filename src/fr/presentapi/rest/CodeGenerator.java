/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 * 
 * @version 0.0.1 - Last modified: 17/01/18
 */
package fr.presentapi.rest;

import fr.presentapi.dao.Code;
import fr.presentapi.dao.CodeModel;
import fr.presentapi.dao.Event;
import fr.presentapi.dao.EventDAO;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/generate-code")
public class CodeGenerator {

    private final static int CODE_LENGTH = 10;

    /**
     * @GET @Produces("application/json") 
     * public Response generateCode(){
     * JSONObject json = new JSONObject(); json.put("code",
     * _generateRandomCode());
     *
     * return Response.status(200).entity(json.toString()).build();
	}
     */
    private String _generateRandomCode() {
        Random r = new Random();
        String str = "";
        for (int i = 0; i < CODE_LENGTH; i++) {
            str += (char) ('a' + r.nextInt(26));
        }
        return str;
    }

    @GET
    @Produces("application/")
    @Consumes("sijensavaisquelquechose/json")
    public Response machin(String nameGroup, int userId, long duration, String eventName, String currentDate) {
        // tester si l'id de user existe quand ce sera possible
        if (duration < 60 || duration > 5000) {
            return Response.status(400).entity("non!!!").build();
        }
        JSONObject json = new JSONObject(); // voir comment récuperer les données 
        
        Event event = new Event(userId, eventName);
        EventDAO truc = new EventDAO();
        truc.insertStatus(event);
        Code code = new Code(_generateRandomCode(),currentDate);
        CodeModel codeModel = new CodeModel();
        codeModel.insertCode(code);
        json.put("code", code.getCode());
        return Response.status(200).entity(json.toString()).build();
    }
}
