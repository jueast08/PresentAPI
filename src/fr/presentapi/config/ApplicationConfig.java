package fr.presentapi.config;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application {
	
	public static final String PROVIDER_PACKAGE_KEY = "jersey.config.server.provider.packages";
	public static final String SERVICE_LOCATION =  "fr.presentapi.rest";
	
	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(PROVIDER_PACKAGE_KEY, SERVICE_LOCATION);
		return properties;
	}
}
