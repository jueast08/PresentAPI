/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 09/11/17
 */
package test.java.fr.presentapi.rest;

import fr.presentapi.rest.CodeGenerator;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import static org.junit.Assert.*;
import org.junit.Test;

public class CodeGeneratorTest extends JerseyTest{

	@Override
	public Application configure(){
		return new ResourceConfig(CodeGenerator.class);
	}

	/**
	 * Asserts that two successive generated code are differents.
	 */
	@Test
	public void testCodeGenerator(){
		String payload = "{"
			+ "\"data\": {"
				+ "\"id\": 1,"
				+ "\"duration\": 120,"
				+ "\"groups\": \"info\""
			+ "}}";
		
		final Response response1 = target("generate-code")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.json(payload));
		final Response response2 = target("generate-code")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.json(payload));
		
		System.out.println(">>>>>\n\n\n");
		System.out.println("1." + response2);
		System.out.println("2." + response2.readEntity(String.class));
		//assertEquals(0, 1);
		//JSONObject code1 = new JSONObject(response1.readEntity(String.class));
		//JSONObject code2 = new JSONObject(response2.readEntity(String.class));

		//assertEquals(200, response1.getStatus());
		//assertNotEquals(code1.get("code"), code2.get("code"));
	}
}
