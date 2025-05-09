package PageObjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementActions;
import utils.PropertiesReader;

public class LoginPage {

	private WebDriver driver;
	
	private By username= By.name("username");
	private By password= By.name("password");
	private By loginButton=By.tagName("button");
	Properties prop;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		prop=PropertiesReader.getProperties();
	}
	
	public LoginPage navigateToLoginPage()
	{
		driver.get(prop.getProperty("url"));
		return this;
	}
	public void login(String email, String password) throws InterruptedException
	{
		ElementActions.type(driver, this.username, email);
		ElementActions.type(driver, this.password, password);
		ElementActions.clickElement(driver, loginButton);
	}
	
	
	
	
}
