package StepDefinitions;

import org.openqa.selenium.WebDriver;

import PageObjects.AdminPage;
import PageObjects.LoginPage;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import utils.BrowserFactory;

public class E2EScenario {
	
	private WebDriver driver;
	private int noOfRecords;
	private String username;
	
	@Before
	public void intializeDriver()
	{
		driver = BrowserFactory.getBrowserInstance();
	}
	
	@After
	public void tearDownDriver()
	{
		BrowserFactory.closeBrowserInstance(driver);
	}
	
	@Given("Login to Orange HRM Website")
	public void navigate_to_orange_hrm_website() throws InterruptedException 
	{
		new LoginPage(driver)
		.navigateToLoginPage()
		.login("Admin","admin123");
	}

	@When("Click on Admin tab on the left side menu")
	public void click_on_admin_tab_on_the_left_side_menu() throws InterruptedException {
	    new AdminPage(driver).
	       goToAdminPage();
	    
	}

	@When("Get the number of records found")
	public void get_the_number_of_records_found() {
	  
		noOfRecords=new AdminPage(driver).getNoOfRecords();
		System.out.println(noOfRecords);
	}

	@When("Click on add button")
	public void click_on_add_button() throws InterruptedException {
		new AdminPage(driver).clickOnAddButton();
	   
	}

	@When("Fill the required data")
	public void fill_the_required_data() throws InterruptedException {
		username=new AdminPage(driver).fillRequiredData();
	}

	@When("Click on save button")
	public void click_on_save_button() throws InterruptedException {
	   new AdminPage(driver).clickOnSaveButton();
	}

	@Then("Verify that the number of records increased by {int}")
	public void verify_that_the_number_of_records_increased_by(Integer int1) {
	  
		int actualNoOfRecords=new AdminPage(driver).getNoOfRecords();
		Assert.assertEquals(actualNoOfRecords, noOfRecords+1);
		noOfRecords=actualNoOfRecords;
	}


	@When("Delete the new user")
	public void delete_the_new_user() throws InterruptedException {
		
		new AdminPage(driver).deleteUser(username);
	}

	@Then("Verify that the number of records decreased by {int}")
	public void verify_that_the_number_of_records_decreased_by(Integer int1) 
	{
		int actualNoOfRecords=new AdminPage(driver).getNoOfRecords();
		Assert.assertEquals(actualNoOfRecords, noOfRecords-1);
	}

}
