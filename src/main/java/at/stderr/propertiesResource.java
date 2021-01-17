package at.stderr;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/properties")
public class propertiesResource {
	
	private final Properties properties = new Properties();
	
	@GET
	@Path("{property}")
	@Produces(MediaType.APPLICATION_JSON) 
	public HashMap<String, String> getProperties(@PathParam("property") String propertyName) {
		try {
			properties.load(propertiesResource.class.getResourceAsStream("/test.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		var propertyValue = properties.getProperty(propertyName, "NotFound");
		var property = new HashMap<String, String>();
		property.put(propertyName, propertyValue);
		
		return property;
	}
}
