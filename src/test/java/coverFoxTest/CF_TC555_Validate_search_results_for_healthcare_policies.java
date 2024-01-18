package coverFoxTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDeatilsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHealthPllanResultsPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxUtility.Utility;

public class CF_TC555_Validate_search_results_for_healthcare_policies extends Base
{
	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDeatilsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxHealthPllanResultsPage result;
	
	@BeforeClass
	public void launchBrowser() throws InterruptedException
	{
		launchCoverFox();
		home = new CoverFoxHomePage(driver);
		healthPlan = new CoverFoxHealthPlanPage(driver);
		addressDetails = new CoverFoxAddressDeatilsPage(driver);
		memberDetails = new CoverFoxMemberDetailsPage(driver);
		result = new CoverFoxHealthPllanResultsPage(driver);
	}
	
	@BeforeMethod
	public void enterMemberDetails() throws InterruptedException, IOException
	{
		Reporter.log("clicking on gender button", true);
		home.clickOnFemaleButton();
		Thread.sleep(1000);
		
		Reporter.log("clicking on next button", true);
		healthPlan.clickOnNextButton();
		Thread.sleep(1000);
		
		Reporter.log("Handeling age drop down", true);
		memberDetails.handleAgeDropDown(Utility.readDataFromExcel(0, 0));
		Reporter.log("clicking on next button", true);
		memberDetails.clickOnNextButton();
		Thread.sleep(1000);
		
		Reporter.log("Entering pin code", true);
		addressDetails.enterPinCode(Utility.readDataFromExcel(0, 1));
		Reporter.log("Entering mobile num", true);
		addressDetails.enterMobileNum(Utility.readDataFromExcel(0, 2));
		Reporter.log("clicking on continue button", true);
		addressDetails.ClickOnContinueButton();
		Thread.sleep(1000);
	}
	
	@Test
	public void validateTestPlansFromTextAndBanners() throws InterruptedException, IOException
	{
		Thread.sleep(5000);
		Reporter.log("Fetching number of results from text", true);
		int textResult = result.availablePlanNumberFromText();
		Thread.sleep(7000);
		Reporter.log("Fetching number of results from banners", true);
		int bannerResult = result.availablePlanNumberFromBanners();
		Thread.sleep(1000);
		Assert.assertEquals(textResult, bannerResult,"Text results are not matching with Banner results, TC is failed");
		Reporter.log("TC is passed", true);
		Utility.takeScreenShot(driver, "CF_TC555");
	}
	
	@AfterMethod
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(3000);
		//closeCoverFox();
	}
}