package utils;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {
	
private static WebDriverWait wait;
	
	
	public static void clickElement(WebDriver driver, By elementLocator) throws InterruptedException
	{
		
		try {
		commonWait(driver,elementLocator);
		wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
		driver.findElement(elementLocator).click();
		}catch(ElementClickInterceptedException e)
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(elementLocator));
		}
	}
	
	public static void type(WebDriver driver, By elementLocator, String text)
	{
		commonWait(driver, elementLocator);
		driver.findElement(elementLocator).sendKeys(text);	
	}
	
	public static String getText(WebDriver driver, By elementLocator)
	{
		commonWait(driver,elementLocator);
		return driver.findElement(elementLocator).getText();
	
	}
	
	public static boolean checkIfElementExistsInSpecialContext(WebElement context,By elementLocator)
	{
		
		return context.findElements(elementLocator).size()>0;
	}
	
	public static boolean checkIfElementExists(WebDriver driver,By elementLocator)
	{
		try{
			commonWait(driver, elementLocator);
		}catch(Exception e){
			return false;
		}
		return driver.findElements(elementLocator).size()>0;
	}
	
	public static void moveToElement(WebDriver driver, By elementLocator)
	{
		commonWait(driver, elementLocator);
		Actions a =new Actions(driver);
		a.moveToElement(driver.findElement(elementLocator)).perform();
	}
	private static void commonWait(WebDriver driver, By elementLocator)
	{
		wait =new  WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		
	}
	
	

	

}
