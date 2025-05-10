package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.openqa.selenium.Cookie;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.PropertiesReader;

public class UserEndPoints {
	
	 private static RequestSpecification requestSpec = new RequestSpecBuilder()
			 	.setBaseUri((String) PropertiesReader.getProperties().get("base_url_api")).build();
	
	public static Response addUser(Map requestBody,Cookie cookie)
	{
		
		setHeaders();
		setCookie(cookie);
		
		 Response response=given()
	        		.spec(requestSpec)
					.body(requestBody)
					.when()
					.post(Routes.fullURL);
		 return response;
				
				
	}
	
	public static Response deleteUser(Map requestBody)
	{
		
		Response response=given()
							.spec(requestSpec)
							.body(requestBody)
							.when()
							.delete(Routes.fullURL);
		
		return response;					
							
	}
	
	private static void setHeaders()
	{
		requestSpec
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.header("Connection","keep-alive");
	}
	
	public static void setCookie(Cookie cookie)
	{
		requestSpec.cookie(cookie.getName(),cookie.getValue());
	}
}
