/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/02/18
 */
package fr.presentapi.rest;

import fr.presentapi.dao.UserModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/subscribe")
public class Subscription {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subscribe(String reception) {
        JSONObject jsonReception = new JSONObject(reception);

        int userId = Integer.parseInt(jsonReception.getJSONObject("data").getString("id"));
        String firstname = jsonReception.getJSONObject("data").getString("firstname");
        String lastname = jsonReception.getJSONObject("data").getString("lasname");
        String mail = jsonReception.getJSONObject("data").getString("mail");
        String imei = jsonReception.getJSONObject("data").getString("imei");
        // user a ajouter dans la base ? 

        UserModel user = new UserModel();
        if (!user.exists(userId)) {
            return Response.status(400).entity(new JSONObject("{\"message\": \"No such user\"}").toString()).build();
        }
        // vérifier la correspondace de l'imei au moment de la conenction ? 
        JSONObject response = new JSONObject().put("data", new JSONObject().put("message", "Welcome"));
        return Response.status(200).entity(response.toString()).build();
        
        // récupérer imei du téléphone ( format de 15 chiffres)
    // android.telephony.TelephonyManager.getDeviceId().
    }
}
