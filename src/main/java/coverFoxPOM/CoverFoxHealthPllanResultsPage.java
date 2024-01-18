package coverFoxPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxHealthPllanResultsPage 
{
	@FindBy(xpath = "//div[contains(text(),'matching Health')]") private WebElement resultInString;
	   @FindBy(id = "plans-list") private List<WebElement> planList;
	   
	   public CoverFoxHealthPllanResultsPage(WebDriver driver)
	   {
		   PageFactory.initElements(driver, this);
	   }
	   
	   public int availablePlanNumberFromText()
	   {
		   String test = resultInString.getText();//49 matching health insurance plans
		   String ar[] = test.split(" ");
		   String numberOfResultsInString = ar[0];
		   
		   //convert string to integer
		   
		   int numberOfResultInInt = Integer.parseInt(numberOfResultsInString);	 
		   
		   return numberOfResultInInt;
	   }
		   
	   public int availablePlanNumberFromBanners()
	   {
		   int totalNumberOfPlan = planList.size();
		   return totalNumberOfPlan;
	   }
}
