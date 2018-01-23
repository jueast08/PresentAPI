/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 09/11/17
 */
package test.java.fr.presentapi.rest;

import fr.presentapi.rest.CodeGenerator;
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
		String jsondata = "{}";
		final Response response1 = target("generate-code")
			.request(MediaType.APPLICATION_JSON).post(Entity.json(jsondata));
		final Response response2 = target("generate-code").request().get();
		JSONObject code1 = new JSONObject(
			response1.readEntity(String.class));
		JSONObject code2 = new JSONObject(
			response2.readEntity(String.class));

		assertEquals(200, response1.getStatus());
		assertNotEquals(code1.get("code"), code2.get("code"));
	}
}
