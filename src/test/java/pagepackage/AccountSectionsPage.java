package pagepackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountSectionsPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='Reports']")
	WebElement reports;
	@FindBy(xpath="//select[@name='report[period]']")
	WebElement periodselect;
	@FindBy(xpath="//select[@name='report[statement_status]']")
	WebElement statusselect;
	@FindBy(xpath="//select[@name='report[currency]']")
	WebElement currencyselect;
	@FindBy(xpath="//*[@id=\"generate_report\"]/div/section[2]/div[1]/button")
	WebElement generateReportButton;
	@FindBy(xpath="//*[@id=\"generate_report\"]/div/section[2]/div[2]/div/a")
	WebElement downloadReportButton;
	
	@FindBy(xpath="//span[text()='Invoices']")
	WebElement invoices;
	@FindBy(xpath="//*[@id=\"wrapper\"]/main/header/div/div/a/span")
	WebElement addNewButton;
	@FindBy(xpath="//a[@class='i_new_invoice']")
	WebElement invoiceSelect;
	
	@FindBy(xpath="//*[@id=\"wrapper\"]/nav/div[1]/div/div/div")
	WebElement accountclick ;
	@FindBy(xpath="//*[@id=\"wrapper\"]/nav/div[1]/div/div/ul/li[6]/a")
	WebElement logout;
	
		public AccountSectionsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void invoicesSection() throws AWTException, InterruptedException
	{
		String parentwindow=driver.getWindowHandle(); //current window
		Actions act=new Actions(driver);
		act.contextClick(invoices).perform();
		
		Robot rob=new Robot();
		rob.keyPress(KeyEvent.VK_DOWN);/* pressing the "Down" arrow key to highlight the first option in the context menu.*/
		rob.keyRelease(KeyEvent.VK_DOWN);//releases the "Down" arrow key.
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		Set<String> allwindowHandles=driver.getWindowHandles();
		for(String handle:allwindowHandles)
		{
			if(!handle.equalsIgnoreCase(parentwindow))
			{
				driver.switchTo().window(handle);
			}
		}
		
		addNewButton.click();
		invoiceSelect.click();
	}

	public void pagescreenshot() throws IOException  
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("./screenshot/invoicesection.png"));
	}
	public void elementscreenshot() throws IOException
	{
		File src1=accountclick.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src1, new File("./screenshot/descriptnelement.png"));
	}
	
	public void reportsSectionAndVerification() throws AWTException
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(reports));
		reports.click();
		
		wait.until(ExpectedConditions.visibilityOf(periodselect));
		Select period=new Select(periodselect);
		period.selectByIndex(1);
		Select status=new Select(statusselect);
		status.selectByValue("unpaid");
		Select currency=new Select(currencyselect);
		System.out.println("Currency element is visible : "+currencyselect.isDisplayed());
		currency.selectByVisibleText("Indian Rupee - INR");
		    // Get the currently selected option
		WebElement selectedOptionCurrency=currency.getFirstSelectedOption();
		    // Check if the selected option's text is "Indian Rupee - INR"
		if(selectedOptionCurrency.getText().equals("Indian Rupee - INR"))
		{
			System.out.println("Indian Rupee - INR is selected.");
        } 
		else
		{
            System.out.println("Indian Rupee - INR is NOT selected.");
        }
		
		generateReportButton.click();
		downloadReportButton.isEnabled();
		System.out.println("Download Report Button is enabled : "+downloadReportButton.isEnabled());
			      
	ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles()); //  Get all window handles
		     // Switch to the second tab
	driver.switchTo().window(windowHandles.get(1)); // Assuming the second tab is at index 1
	driver.close();  //  Close the second tab
	driver.switchTo().window(windowHandles.get(0));  //  Switch back to the first tab
	accountclick.click();
	logout.click();
	}
}
	