package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDeatilsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxUtility.Utility;

public class CF_002Validate_Pincode_Error_Msg extends Base
{
	public static Logger logger;
	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDeatilsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	
	@BeforeClass
	public void launchBrowser() throws InterruptedException 
	{
		logger = Logger.getLogger("CoverFoxInsurance");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Launching CoverFox");
		logger.error("Please select browser of your choice");
		launchCoverFox();
		home = new CoverFoxHomePage(driver);
		healthPlan = new CoverFoxHealthPlanPage(driver);
		addressDetails = new CoverFoxAddressDeatilsPage(driver);
		memberDetails = new CoverFoxMemberDetailsPage(driver);
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	@BeforeMethod
	public void enterUserDetails() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		logger.info("Clicking on Female Button");
		logger.error("Enter on male button");
		home.clickOnFemaleButton();
		Thread.sleep(1000);
		logger.info("Clicking on Next Button");
		healthPlan.clickOnNextButton();
		Thread.sleep(1000);
		logger.info("Handling age dropdown");
		memberDetails.handleAgeDropDown(Utility.readDataFromExcel(0, 0));
		logger.info("Clicking on Next Button");
		memberDetails.clickOnNextButton();
		Thread.sleep(1000);
		logger.info("Entering mobile number");
		addressDetails.enterMobileNum(Utility.readDataFromExcel(0, 1));
		logger.info("Clicking on Continue Button");
		addressDetails.ClickOnContinueButton();
		Thread.sleep(1000);
		
	}
	
  @Test
  public void validate_Pincode_ErrorMsg() 
  {
	 Reporter.log("Validating Pincode Error Msg", true);
	 boolean result = addressDetails.ValidatePinErrorMsg();
	 Assert.assertTrue(result,"Pincode Error Msg Is Not Displayed,So TC Is Failled");
	 Reporter.log("TC IS Passed", true);
	 logger.warn("Please enter valid information");
	  
  }
  
  @AfterMethod
  public void closeBrowser() throws InterruptedException 
  {
	  logger.info("Closing Browser");
	  Reporter.log("Closing Browser", true);
	  Thread.sleep(5000);
	//closeCoverFox();
	 
  }
}
