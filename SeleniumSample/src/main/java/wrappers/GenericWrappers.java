package wrappers;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericWrappers implements Wrappers {

	RemoteWebDriver driver;
	int i=1;
	

	public void invokeApp(String browser, String url) throws Exception {
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		try {
			
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("The Browser "+ browser +" launched");
			
		}
		catch (WebDriverException e) {			
			System.err.println(browser +" Browser could not be Loaded");
		}
		finally{
			takeSnap();
			
		}

	}

	public void enterById(String idValue, String data) throws Exception {
		
				try {
				
				driver.findElementById(idValue).clear();
				driver.findElementById(idValue).sendKeys(data);
				
				System.out.println("The Text Field "+ idValue +" is entered with data "+ data );
				
			} catch (NoSuchElementException e) {
				
				System.err.println(idValue +" enterById "+ data + " Value does not exist");
				
				
			}catch (WebDriverException e){
				
				System.err.println(" Browser closed due to Unexpected behavior");
				
			}
				finally {
				
				takeSnap();
				
			}
						
	}

	public void enterByName(String nameValue, String data) throws Exception {
		try {
			driver.findElementByName(nameValue).clear();
			driver.findElementByName(nameValue).sendKeys(data);
			
			System.out.println("The Text Field "+ nameValue +" is entered with data "+ data);
			
		} catch (NoSuchElementException e) {
			
			System.err.println(nameValue +" enterByName "+ data + " Value does not exist");
			
			//e.printStackTrace();
		} catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally {
			
			takeSnap();
		}
		

	}

	public void enterByXpath(String xpathValue, String data) throws Exception {
		try {
			
			driver.findElementByXPath(xpathValue).clear();
			driver.findElementByXPath(xpathValue).sendKeys(data);
			
			System.out.println("The Text Field "+ xpathValue +" is entered with data "+ data);
			
		} catch (NoSuchElementException e) {
			
			System.err.println(xpathValue +" enterByXpath "+ data + " Value does not exist");
			
			//e.printStackTrace();
		} catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally {
			
			takeSnap();		}
		
	}

	public boolean verifyTitle(String title) {
		
		boolean breturn = false;		
		String txt = null;

		try {
			txt=driver.getTitle();
			if(txt.equals(title)){
				System.out.println("Title" +title+ "is verified successfully");
				breturn = true;
			} 
			else {
				System.out.println("Title : " +title+ "is not verified ");
				
			}
		
		}catch(NoSuchElementException e){
			System.out.println("Title Could not verified.");
		
		}catch (WebDriverException e) {
			System.err.println( "Browser unexpected error occuired");
		} 
		finally{
			takeSnap();
		}	
		return breturn;
	}

	public void verifyTextById(String id, String text) {
		
		try {
			String txt = driver.findElementById(id).getText();
			if (txt.equals(text)) {
				
				System.out.println( text +" TextById Value is verified");
			}
			else {
				System.out.println( id+ "is not verified ");
				
			}
		} catch (NoSuchElementException e) {
			
			System.err.println(id +" enterByXpath "+ text + " Value does not exist");
			
			//e.printStackTrace();
		}catch (WebDriverException e) {
			
			System.err.println("Browser closed unexpectedly ");
			
			//e.printStackTrace();
		}
			
	}
	

	public void verifyTextByXpath(String xpath, String text) {
		
		try {
			String vxpaeletxt = driver.findElementByXPath(xpath).getText();
			
			if(vxpaeletxt.equals(text))
			{
				System.out.println(text +" TextByXpath Value is verified");
			}else {
				System.out.println( xpath+ "is not verified ");
				
			}
		} catch (NoSuchElementException e) {
			
			System.err.println(text +"is Not Found");
			
			//e.printStackTrace();
		} catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally{
			
			takeSnap();
		}


	}

	public void verifyTextContainsByXpath(String xpath, String text) {
		
		try {
			String vxpacontxt = driver.findElementByXPath(xpath).getText();
			
			if(vxpacontxt.equals(text))
			{
				System.out.println(text +" TextContainsByXpath Value is verified");
			}else {
				System.out.println( xpath+ "TextContent is not verified ");
				
			}
		} catch (NoSuchElementException e) {
			
			System.err.println(text+ " is Not Found");
			
			//e.printStackTrace();
		} catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally{
			
			takeSnap();
		}
		

	}

	public void clickById(String id) throws Exception {
		
		try {
			driver.findElementById(id).click();
			
			System.out.println("The Clicked name :"+ id +"is Clicked" );
			
		} catch (NoSuchElementException e) {
			
			System.err.println(id+ " is Not Found");
			
			//e.printStackTrace();
		} catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally{
			
			takeSnap();
		}
		

	}

	public void clickByClassName(String classVal) throws Exception {
		
		try {
			driver.findElementByClassName(classVal).click();
			
			System.out.println("The button "+ classVal + "is clicked");
			
		} catch (NoSuchElementException e) {
			
			System.err.println(classVal+ " is Not Found");
			
			//e.printStackTrace();
		} 
		catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally {
		
			takeSnap();
		}
		
	}

	public void clickByName(String name) throws Exception {
		
		try {
			driver.findElementByName(name).click();
			
			System.out.println("The Clicked name :" + name + "is Clicked");
			
		} catch (NoSuchElementException e) {
			
			System.err.println(name+ " is Not Found");
			
			//e.printStackTrace();
		} 
		catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally {
			
			takeSnap();
		}
	

	}

	public void clickByLink(String name) throws Exception {
		
		try {
			driver.findElementByLinkText(name).click();
			
			System.out.println("The Link "+ name + "is clicked");
			
		} catch (NoSuchElementException e) {
			
			System.err.println(name+ " is Not Found");
			
			//e.printStackTrace();
		} 
		catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally {
			
			takeSnap();
		}
	

	}

	public void clickByLinkNoSnap(String name) throws Exception {
		
		try {
			
		
		driver.findElementByLinkText(name).click();
		
		System.out.println("The Link "+ name + "is clicked");

		} catch (NoSuchElementException e) {
			
			System.err.println(name+ " is Not Found");
			
			//e.printStackTrace();
		} catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		

	}

	public void clickByXpath(String xpathVal) throws Exception {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathVal)));
			
			driver.findElementByXPath(xpathVal).click();
			
			System.out.println("The Link "+ xpathVal + "is clicked");
			
		}  catch (NoSuchElementException e) {
			
			System.err.println(xpathVal+ " is Not Found");
			
			//e.printStackTrace();
		} catch (WebDriverException e){
			
			System.err.println(" Browser closed due to Unexpected behavior");
			
		}
		finally {
			
			takeSnap();
		}
	

	}

	public void clickByXpathNoSnap(String xpathVal) throws Exception {
		
		try {
			driver.findElementByXPath(xpathVal).click();
		    
			System.out.println("The Link "+ xpathVal + "is clicked");
			
	} catch (NoSuchElementException e) {
		
		System.err.println(xpathVal+ " is Not Found");
		
		//e.printStackTrace();
	}catch (WebDriverException e){
		
		System.err.println(" Browser closed due to Unexpected behavior");
		
	}
	

}

	public String getTextById(String idVal) {
		
		try {
			driver.findElementById(idVal).getText();
			
			System.out.println("The "+ idVal + " Text");
		 }
		catch (NoSuchElementException e) {
			
			System.err.println(idVal+ " is Not Found");
			
			//e.printStackTrace();
		}
		catch (WebDriverException e) {
			
			System.err.println(idVal+ "is Not Found");
			
			//e.printStackTrace();
			
		}
		finally{
			takeSnap();
		}
		return null;
	}

	public String getTextByXpath(String xpathVal) {
		
		try {
			driver.findElementByXPath(xpathVal).getText();
			
			System.out.println("The "+ xpathVal + " Text");
		 }
		catch (WebDriverException e) {
			
			System.err.println(xpathVal+ "is Not Found");
			
			//e.printStackTrace();
			
		}
		finally{
			takeSnap();
		}
		return null;
	}

	public void selectVisibileTextById(String id, String value)
			throws Exception {
		
		try {
			WebElement we = driver.findElementById(id);
			Select dropdown = new Select (we);
			dropdown.selectByVisibleText(value);
			
			System.out.println(dropdown);
			
		} catch (NoSuchElementException e) {
			System.err.println("The dropdown "+id+ " does not exist");
		}
		finally{
			
			takeSnap();
		}

	}

	public void selectIndexById(String id, int value) throws Exception {
		
		try {
			WebElement we = driver.findElementById(id);
			Select dropdown = new Select (we);
			dropdown.selectByIndex(value);
			
			System.out.println(dropdown);
			
		} catch (NoSuchElementException e) {
			System.err.println("The dropdown "+id+ " does not exist");
		}
		finally{
			takeSnap();
		}
	}

	public void switchToParentWindow() {
		
		try {
			//driver.switchTo().window(parentWindow);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt());
			Set<String> allWindows=driver.getWindowHandles();
			for(String eachWindow:allWindows){
				driver.switchTo().window(eachWindow);
				break;
			}
		} catch (WebDriverException e) {
			System.err.println("Window not found");
		
		
		}
		
	}

	public void switchToLastWindow() {
		
		try {
			//String parentWindow = driver.getWindowHandle();
			//driver.switchTo().window(parentWindow);
			Set<String> allWindows=driver.getWindowHandles();
			for(String eachWindow:allWindows){
				driver.switchTo().window(eachWindow);
			}
		} catch (WebDriverException e) {
			System.err.println("Window not found");
			
		}

	}


	public void acceptAlert() {
		
		try {
			Alert a = driver.switchTo().alert();
			a.accept();
			System.out.println("The Alert is Accepted "+a);
		} 
		catch (NoAlertPresentException e) {
			System.err.println("Alert is not present");
		}
		catch (UnhandledAlertException e) {
			System.err.println("Alert is not handled");
		}
		finally{
			takeSnap();
		}
	}
		

	public void dismissAlert() {
		
		try {
			Alert a = driver.switchTo().alert();
			a.dismiss();
			System.out.println("The Alert is Not Accepted "+a);
		} 
		catch (NoAlertPresentException e) {
			System.err.println("Alert is not present");
		}
		catch (UnhandledAlertException e) {
			System.err.println("Alert is not handled");
		}
		finally{
			takeSnap();
		}
	}

	public String getAlertText() {
		
		try {
			Alert a = driver.switchTo().alert();
			a.getText();
			System.out.println("The Alert text is : "+a);
		} 
		catch (NoAlertPresentException e) {
			System.err.println("Alert is not present");
		}
		catch (UnhandledAlertException e) {
			System.err.println("Alert is not handled");
		}
		finally{
			takeSnap();
		}
	
		return null;
	}

	public long takeSnap() {
		File src = driver.getScreenshotAs(OutputType.FILE);
		
		File dec = new File("./snaps/snap" +i+ ".jpg");
		try {
			FileUtils.copyFile(src, dec);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		i++;
		return 0;
	}

	public void closeBrowser() {
		
		try {
			driver.close();
			
			System.out.println("The Browser is Closed");
		} 
		catch (WebDriverException e) {
			
			System.err.println("Unable to close driver");
			// e.printStackTrace();
			
		}

	}

	public void closeAllBrowsers() {
		
		try {
			driver.quit();
			
			System.out.println("The All Browsers are closed");
			}
			catch (WebDriverException e) {
				System.err.println("unable to close All Browsers");
				}
		}

	}