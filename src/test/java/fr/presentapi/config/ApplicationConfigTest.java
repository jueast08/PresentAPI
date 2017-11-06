package test.java.fr.presentapi.config;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.presentapi.config.ApplicationConfig;

public class ApplicationConfigTest extends ApplicationConfig {

	@Test
	public void testGetProperties() {
		assertEquals("fr.presentapi.rest", this.getProperties().get("jersey.config.server.provider.packages"));
	}

}
