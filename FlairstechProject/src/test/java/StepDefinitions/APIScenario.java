package StepDefinitions;

import org.json.simple.JSONObject;
import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import PageObjects.LoginPage;
import api.endpoints.Data;
import api.endpoints.UserEndPoints;
import io.restassured.response.Response;
import utils.BrowserFactory;
import utils.JsonFileManager;

public class APIScenario {

	@Test
	public void fullScenarioUsingAPIs() throws InterruptedException {

		WebDriver driver = BrowserFactory.getBrowserInstance();
		JSONObject testData = JsonFileManager.readJsonFile("src/test/resources/testData/data.json");

		new LoginPage(driver).navigateToLoginPage().login(JsonFileManager.getJsonValue(testData, "username"),
				JsonFileManager.getJsonValue(testData, "password"));

		Cookie seleniumCookies = BrowserFactory.getCookieValue(driver);

		Response createUserResponse = UserEndPoints.addUser(Data.getAddUserBody(), seleniumCookies);
		int userID = createUserResponse.body().jsonPath().getInt("data.id");
		
		Response deleteUserResponse = UserEndPoints.deleteUser(Data.getDeleteUserBody(userID));

		Assert.assertTrue(deleteUserResponse.statusCode()==200);
		
		BrowserFactory.closeBrowserInstance(driver);
	}

}
