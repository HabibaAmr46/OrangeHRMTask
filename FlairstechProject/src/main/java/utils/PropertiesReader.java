package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public static Properties getProperties()
	{
		Properties prop=new Properties();
		String path=System.getProperty("user.dir");
		InputStream input;
		try {
			input = new FileInputStream(path+"\\src\\main\\resources\\config.properties");
			prop.load(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
	}


}
