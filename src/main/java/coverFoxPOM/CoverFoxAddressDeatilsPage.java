package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxAddressDeatilsPage 
{
	   @FindBy(xpath = "(//input[@type='number'])[1]") private WebElement pinCode;
	   @FindBy(xpath = "(//input[@type='number'])[2]") private WebElement mobNum;
	   @FindBy(xpath = "//div[text()='Continue']") private WebElement continueButton;
	   @FindBy(className = "error-ui") private WebElement errorMsg;

	   public CoverFoxAddressDeatilsPage(WebDriver driver)
	   {
		  PageFactory.initElements(driver, this);
	   }

	   public void enterPinCode(String pincode)
	   {
		  pinCode.sendKeys(pincode);
	   }

	   public void enterMobileNum(String mobnum)
	   {
		  mobNum.sendKeys(mobnum);
	   }

	   public void ClickOnContinueButton()
	   {
		  continueButton.click();
	   }
	   
	   public boolean ValidatePinErrorMsg()
	   {
		   boolean result = errorMsg.isDisplayed();
		   return result;
	   }
}

