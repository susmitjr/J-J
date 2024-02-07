package StepDefinitions;

import utilities.ObjectManager;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.html.HTMLSelectElement;

import com.opencsv.exceptions.CsvValidationException;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.apache.commons.io.FileUtils;


public class Stepdefs {
	
	private WebDriver driver;
	private WebElement ele;
	private WebDriverWait w;
	private String file;
	private String tc;
	private List<Map<String, String>> records;
	private String uid;
	private String pwd;
	private String urls;
	private String aCName;
	private String lastName;
	private String mailCountry;
	private String phoneNo;
	private Object testCaseNo;
	
	private String first_Name;
	private String last_Name;
	private String emaiL;
	private String confirm_Email;
	private String passorD;
	private String confirm_Password;
	private String phonE;
	
    private ObjectManager objectManager;

    public Stepdefs() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\0033RC744\\Downloads\\edgedriver_win64_Latest\\msedgedriver.exe");
		driver = new EdgeDriver();
        objectManager = new ObjectManager(driver,"C:\\Users\\0033RC744\\Desktop\\test.csv");
    }

	
	@Given("Test data loaded from file {string} for the Case {string}")
	public void iextracttestData(String file , String tc) throws CsvValidationException {
		this.file = file;
		this.tc = tc;
		records = Reader.readData(file,tc);
		
		System.out.println(records);
		System.out.println(file);
		System.out.println(tc);
		// System.setProperty("webdriver.edge.driver", "C:\\Users\\0033RC744\\Downloads\\edgedriver_win64_Latest\\msedgedriver.exe");
 		
		// EdgeOptions eo = new EdgeOptions();
		// eo.addArguments("--disable-notifications");
	 	// driver = new EdgeDriver(eo);
	 	// w = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		
	}

	@Then("I refresh the page")
    public void iRefreshThePage() {
        driver.navigate().refresh();
    }
	@Then("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }
	@When("I navigate to URL")
    public void iopenLinkurl(){
		try {
			for (Map<String, String> record : records) {
				String url = record.get("URL");
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Then("I verify that {string} is displayed")
    public void iVerifyEleDisplayed(String elementName) {
		WebElement element = objectManager.getObject(elementName);
		System.out.println(element.getText());
		Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
}
	
	
	@SuppressWarnings("deprecation")
	@When("User navigates to Salesforce login page")
    public void ilogin() throws InterruptedException {
		
		driver.get("https://business-innovation-6073.my.salesforce.com/");
 		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("susmitjana34-k1sa@force.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Monenei7@");
		ele = driver.findElement(By.xpath("//*[contains(@type,'submit')]"));
		ele.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		Assert.assertTrue(driver.findElement(By.id("tabBar")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Home']")).isDisplayed());	
		
	}
	
	
	
	@When("User Sign up for the Janssen CarePath Provider portal")
	public void providerLogin() {
		
		driver.get("https://janssencarepath--uat.sandbox.my.site.com/JanssenProvider/register");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement prodName = driver.findElement(By.xpath("//span[contains(text(),\"STELARA® (ustekinumab)\")]"));
		w.until(ExpectedConditions.elementToBeClickable(prodName));
		prodName.click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		WebElement mySites = driver.findElement(By.xpath("//button[contains(text(),'Find My Sites')]"));
		w.until(ExpectedConditions.elementToBeClickable(mySites));
		mySites.click();
		
//		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//button[contains(text(),'Search')])"))));
		WebElement state = driver.findElement(By.xpath("(//label[contains(text(),'State')]//following::button)[1]"));
		state.click();
		
		WebElement selectState = driver.findElement(By.xpath("(//span[contains(text(),\"Alaska\")])[1]"));
		selectState.click();
		driver.findElement(By.xpath("//input[@id= 'input-32']")).sendKeys("Test");
		driver.findElement(By.xpath("(//button[contains(text(),'Search')])")).click();
		
		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(text(),'Site Name')]"))));
		driver.findElement(By.xpath("//button[contains(text(),'Select')][1]")).click();
		
		WebElement noMuchInfo = driver.findElement(By.xpath("//button[contains(text(),'No')]"));
		w.until(ExpectedConditions.elementToBeClickable(noMuchInfo));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", noMuchInfo);
		
		WebElement mysiteContinue = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		w.until(ExpectedConditions.elementToBeClickable(mysiteContinue));
		mysiteContinue.click();
		
		//last step - code for input details for login as a caregiver... to be continued 12122023
		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id = 'input-428']"))));
		
		try {
		for (Map<String, String> record : records) {
	 			
	 	 		testCaseNo = record.get("TC");
	 	 		first_Name = record.get("first_Name");
	 	 		emaiL = record.get("emaiL");
	        	confirm_Email = record.get("emaiL");
	        	passorD = record.get("passorD");
	        	confirm_Password = record.get("passorD");
	        	phonE = record.get("phonE");
	        	System.out.println(first_Name);
	       	
		WebElement fname = driver.findElement(By.xpath("//input[@id='input-428']"));
//		fname.click();
		fname.sendKeys(first_Name);
//		driver.findElement(By.xpath("//*[text()='Last Name']")).sendKeys(last_Name);
//		driver.findElement(By.xpath("//*[text()='Email']")).sendKeys(emaiL);
//		driver.findElement(By.xpath("//*[text()='Confirm Email']")).sendKeys(confirm_Email);
//		driver.findElement(By.xpath("//*[text()='Password']")).sendKeys(passorD);
//		driver.findElement(By.xpath("//*[text()='Confirm Password']")).sendKeys(confirm_Password);
//		
////		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//label[text()='Type']"))));
////		driver.findElement(By.xpath("//label[text()='Type'][1]")).click();
//		driver.findElement(By.xpath("//label[text()='Phone']")).sendKeys(phonE);
//		
//		Select select = new Select(driver.findElement(By.xpath("//label[text()='Role']")));
//		select.selectByVisibleText("Other");
		
		driver.findElement(By.xpath("//button[text()='Continue']")).click();	
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
		
	@SuppressWarnings("deprecation")
	@When("User navigate to Others page")
	public void others() throws InterruptedException {
	
//    	driver.findElement(By.partialLinkText("Contacts")).click();
        WebElement c = driver.findElement(By.xpath("//a[@title='Contacts']"));
        w.until(ExpectedConditions.elementToBeClickable(c));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(c).perform();
//        c.click();
        
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", c);
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//    	Assert.assertTrue(driver.findElement(By.name("new")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.xpath("//button[contains(text(),'New')]")).isDisplayed(),"Pass");
	}
	
	
	@SuppressWarnings("deprecation")
	@When("User navigates to Salesforce for contact creation")
    public void icreateContact() throws InterruptedException {
    	
 	
 		for (Map<String, String> record : records) {
 		try {	
 	 		testCaseNo = record.get("TC");
// 		    urls = record.get("urls");
// 		    uid = record.get("uid");
// 		    pwd = record.get("pwd");
 		    aCName = record.get("ACName");
        	lastName = record.get("LastName");
        	phoneNo = record.get("Phone");
        	mailCountry = record.get("MailCountry");
       	
 	
 		//driver.get("https://janssencarepath--sit.sandbox.my.salesforce.com/");
// 		driver.get(urls);
// 		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(uid);
//		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
// 		
//		
//		ele = driver.findElement(By.xpath("//*[contains(@type,'submit')]"));
//		ele.click();
//		
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        Assert.assertTrue(driver.findElement(By.id("tabBar")).isDisplayed());
       
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    	driver.findElement(By.partialLinkText("Contacts")).click();
//    	driver.findElement(By.name("new")).click();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	
        driver.findElement(By.xpath("//button[contains(text(),'New')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'New Contact')]")).isDisplayed(),"Pass");
	
        WebElement acNameInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Search Accounts...')]"));
        WebElement lastNm = driver.findElement(By.xpath("//input[contains(@name,'lastName')]"));
        WebElement ph = driver.findElement(By.xpath("//input[contains(@name,'Phone')]"));
        WebElement mailc = driver.findElement(By.xpath("//input[contains(@name,'country')]"));
        
        acNameInput.sendKeys(aCName);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'dropdown-element-610')]")));w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'dropdown-element-610')]")));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class,'slds-listbox__item')]")));        
        WebElement sugg = driver.findElement(By.xpath("//li[contains(@class,'slds-listbox__item')]"));
        sugg.click();
    
        lastNm.sendKeys(lastName); 
        ph.sendKeys(phoneNo);
        mailc.sendKeys(mailCountry);
        
        driver.findElement(By.xpath("//button[contains(@name,'SaveEdit')]")).click();
 		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 		
 		
 		try 
 		{
 			WebElement cc = driver.findElement(By.xpath("//span[contains(@class,'custom-truncate uiOutputText')]"));
 			cc.isDisplayed();
 			Assert.assertEquals(cc.getText().toString(), lastName );
 			icaptureScreenshot("ContactCreated_"+ testCaseNo);
 		}
 		catch(AssertionError ae) {
 			ae.printStackTrace();
 			icaptureScreenshot("Failure_" + testCaseNo);
 		}
 		}
 		catch(AssertionError ae) {
 			ae.printStackTrace();
 			icaptureScreenshot("Failure_" + testCaseNo);
 			driver.quit();
 		}
 		}
	}
   @After
   public void tearDown(Scenario scenario) {
	driver.quit();
	System.out.println("Test data used :");
	System.out.println(records);
	System.out.println(file);
	System.out.println(tc);

	   if (scenario.isFailed()) {
           icaptureScreenshot("Test step failed" + scenario.getName());
       }
       else{
		System.out.println("Test Step Passed " + scenario.getName());
	   }
   }

   @And("I capture Screenshot {string}")
    public void icaptureScreenshot(String screenshotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] sbytes = screenshot.getScreenshotAs(OutputType.BYTES);
            String path = "C:/Automation/com.carepath.jnj/screenshots/" + screenshotName + ".png";
            
            FileUtils.writeByteArrayToFile(new File (path), sbytes);
            System.out.println("Screenshot Saved at: " + path);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
