/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 * 
 * @version 0.0.1 - Last modified: 09/11/17
 */
package fr.presentapi.rest;

import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;



@Path("/generate-code")
public class CodeGenerator{
	private final static int CODE_LENGTH = 10;
	
	@GET
	@Produces("application/json")
	public Response generateCode(){
		JSONObject json = new JSONObject();
		json.put("code", _generateRandomCode());

		return Response.status(200).entity(json.toString()).build();
	}

	private String _generateRandomCode(){
		Random r = new Random();
		String str = "";
		for(int i = 0; i < CODE_LENGTH; i++){
			str += (char)('a' + r.nextInt(26));
		}
		return str;
	}
}
