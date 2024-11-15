package testpackage;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.Test;
import basepackage.Baseproject;
import pagepackage.AccountInfoPage;
import pagepackage.AccountSectionsPage;
import pagepackage.LoginPage;
import pagepackage.pageValidation;
import utilitypackage.SignupUtility;

public class Testproject extends Baseproject{
	@Test(priority=1)   
	public void testverifytitle()
	{
		pageValidation ob=new pageValidation(driver);
		ob.verifyTitle();
		ob.verifyText();
		ob.noOfLinks();
	}
@Test(priority=2)
public void loginoption() throws InterruptedException 
{
       LoginPage lp = new LoginPage(driver);
       lp.loginclick();

       String filePath = "F:\\LUMINAR\\loginproject.xlsx";
       String sheetName = "loginproject";
       // Get row count to iterate over each row
       int rowCount = SignupUtility.getRowCount(filePath, sheetName);

       for (int i = 1; i <= rowCount; i++) {
       // Read username, password from Excel
       String username = SignupUtility.getCellValue(filePath, sheetName, i, 0);
       String  password = SignupUtility.getCellValue(filePath, sheetName, i, 1);
        // Log the data read to confirm
       System.out.println("Row " + i + ": emailid = " +username + ", Password = " + password );
       // Perform login with data
       lp.loginpage(username, password);
       }
   }
@Test(priority=3)
public void loginpagedetails() throws InterruptedException
{
	AccountInfoPage aip = new AccountInfoPage(driver);
	aip.addbusiness();
	aip.editaccount();
	Thread.sleep(3000);
}
@Test(priority=4)
  public void accountinfo() throws InterruptedException, AWTException, IOException
  {
	AccountSectionsPage asp=new AccountSectionsPage(driver);
	driver.manage().window().maximize();
	Thread.sleep(2000);
	asp.invoicesSection();
	asp.pagescreenshot();
	asp.elementscreenshot();
	asp.reportsSectionAndVerification();
	
 }
}


