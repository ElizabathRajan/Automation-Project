package pagepackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
		
	//login
	@FindBy(xpath="/html/body/header/div[2]/div/ul/li[5]/a/span")
	WebElement login;
	@FindBy(id="email")
	WebElement lemailid;
	@FindBy(id="password")
	WebElement lpassword;
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbutton;
	@FindBy(id="identifier")
	WebElement vanityurl;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div/form/div[2]/button[2]/p")
	WebElement savebusiness;
	
	public LoginPage (WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void loginclick()
	{
		login.click();
	}
	public void loginpage(String username,String password) throws InterruptedException
	
	{
		lemailid.clear();
		lpassword.clear();
		lemailid.sendKeys(username);
		lpassword.sendKeys(password);
		Thread.sleep(3000);
		loginbutton.click();
			
	}
	

}
