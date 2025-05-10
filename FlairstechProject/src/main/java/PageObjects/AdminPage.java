package PageObjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.ElementActions;
import utils.JsonFileManager;

public class AdminPage {

	private WebDriver driver;
	private static JSONObject testData;
	private By adminButton = By.xpath("//span[text()='Admin']/parent::a");
	private By noOfRecords=By.xpath("//span[contains(.,'Record')]");
	private By addButton=By.xpath("//button[contains(.,'Add')]");
	private By selectLocator = By.cssSelector(".oxd-select-text-input");
	private By employeeName=By.cssSelector("input[placeholder='Type for hints...']");
	private By userNameLocator=getInputLocator("Username");
	private By passwordLocator=getInputLocator("Password");
	private By confirmPasswordLocator=getInputLocator("Confirm Password");
	private By saveButton=By.xpath("//button[text()=' Save ']");
	private By confirmDeleteButton=By.xpath("//i[@class='oxd-icon bi-trash oxd-button-icon']/parent::button");
	

	public AdminPage(WebDriver driver) {
		this.driver = driver;
		testData =JsonFileManager.readJsonFile("src/main/resources/data.json");
		
	}
	
	public void goToAdminPage() throws InterruptedException
	{
		ElementActions.clickElement(driver, adminButton);
	}
	
	public int getNoOfRecords() 
	{
		String noOfRecordsString=ElementActions.getText(driver, noOfRecords);
		return Integer.parseInt(noOfRecordsString.replaceAll("[^0-9]", ""));
	}
	
	public void clickOnAddButton() throws InterruptedException 
	{
		ElementActions.clickElement(driver, addButton);
	}
	
	public String fillRequiredData() throws InterruptedException
	{
		String username=JsonFileManager.getJsonValue(testData,"userName")+System.currentTimeMillis();
		By autoSuggestiveSelectOption=By.xpath("//span[contains(text(),'"+JsonFileManager.getJsonValue(testData,"empName")+"')]");
		
		ElementActions.type(driver, userNameLocator, username);
		ElementActions.type(driver, passwordLocator, JsonFileManager.getJsonValue(testData,"password"));
		ElementActions.type(driver, confirmPasswordLocator, JsonFileManager.getJsonValue(testData,"confirmPassword"));
		selectElementFromDropDown(0, JsonFileManager.getJsonValue(testData,"userRole"));
		selectElementFromDropDown(1, JsonFileManager.getJsonValue(testData,"status"));
		ElementActions.type(driver, employeeName, JsonFileManager.getJsonValue(testData,"empName"));
		ElementActions.clickElement(driver, autoSuggestiveSelectOption);
		return username;
	}
	
	public void clickOnSaveButton() throws InterruptedException
	{
		ElementActions.clickElement(driver, saveButton);
	}
	
	public void deleteUser(String username) throws InterruptedException
	{
		By deleteButtonForUser = By.xpath("//div[text()='" + username + "']/parent::div/parent::div//i[@class='oxd-icon bi-trash']/parent::button");
		ElementActions.clickElement(driver, deleteButtonForUser);
		ElementActions.clickElement(driver, confirmDeleteButton);

	}
	private void selectElementFromDropDown(int indexOfSelect, String selectElementName) throws InterruptedException {
		driver.findElements(selectLocator).get(indexOfSelect).click();
		ElementActions.clickElement(driver, getSelectLocator(selectElementName));
	}
	
	private static By getSelectLocator(String selectOption) {
		return By.xpath("//span[text()='" + selectOption + "']/parent::div");
	}

	private static By getInputLocator(String inputFieldName)
	{
		return By.xpath("//label[text()='"+inputFieldName+"']/parent::div/parent::div //input");
	}
	
	
	

}
