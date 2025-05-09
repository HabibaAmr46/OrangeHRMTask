package api.endpoints;

import java.util.Properties;

import utils.PropertiesReader;

public class Routes {
	public static String base_url=PropertiesReader.getProperties().getProperty("base_url_api");
	
	
	public static String fullURL=base_url+"admin/users";
	
}
