package test.java;

import static org.junit.Assert.*;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Test;

import fr.presentapi.rest.Example;

public class ExampleTest extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(Example.class);
    }

    @Test
    public void testExample() {
        final Response response = target("example").request().get();
        final JSONObject expectedResult = new JSONObject();
        
        expectedResult.put("Julian", "Baddass");
        expectedResult.put("Easterly", "is the best");
        
        assertEquals(response.getStatus(), 200);
        assertEquals(response.readEntity(String.class), expectedResult.toString());
    }
    
    @Test
    public void testExamplePathParam() {
    	final String name = "chloe";
        final Response response = target("example/"+name).request().get();
        final JSONObject expectedResult = new JSONObject();
        
        expectedResult.put(name, "Baddass");
        expectedResult.put("you", "are the best");
        
        assertEquals(response.getStatus(), 200);
        assertEquals(response.readEntity(String.class), expectedResult.toString());
    }

}

