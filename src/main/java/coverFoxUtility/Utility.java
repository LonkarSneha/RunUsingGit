package coverFoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.*;
import org.testng.Reporter;


public class Utility 
{
    public static String readDataFromExcel(int row, int cell) throws IOException
    {
    	Reporter.log("Reading data from excel sheet", true);
    	FileInputStream myFile = new FileInputStream("C:\\Users\\LENOVO\\eclipse-workspace\\firstMavenProject\\src\\test\\resources\\Test Data.xlsx");
    	Sheet mySheet = WorkbookFactory.create(myFile).getSheet("CoverFox");
    	String data = mySheet.getRow(row).getCell(cell).getStringCellValue();
    	return data;
    }
    
    public static void takeScreenShot(WebDriver driver, String TCID) throws IOException
    {
    	Reporter.log("Taking screenshot", true);
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	File dest = new File("E:\\Testing+TCID"+"_"+timeStamp+".png");
    	Reporter.log("Saved screenshot at "+dest, true);
    	FileHandler.copy(src, dest);
    }
}
