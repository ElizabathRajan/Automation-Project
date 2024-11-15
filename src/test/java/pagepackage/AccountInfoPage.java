package pagepackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountInfoPage {
	
	WebDriver driver;

	@FindBy(xpath="//*[@id=\"identifier\"]")
	WebElement vanityurl;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[4]/div/form/div[2]/button/div/p")
	WebElement startusinginvoicely;
	@FindBy(xpath = "//a[contains(@href, 'invoicely.com')]")
	WebElement selectbusiness;
	@FindBy(xpath="//*[@id=\"wrapper\"]/nav/div[1]/div/div/div/img")
	WebElement hamburgermenu;
	@FindBy(xpath="//*[@id=\"wrapper\"]/nav/div[1]/div/div/ul/li[3]/a")
	WebElement account;
	@FindBy(name="first_name")
	WebElement firstname;
	@FindBy(name="last_name")
	WebElement lastname;
	@FindBy(name="address")
	WebElement address;
	@FindBy(name="post_code")
	WebElement postalcode;
	@FindBy(name="state")
	WebElement state;
	@FindBy(name="city")
	WebElement city;
	@FindBy(xpath="//select[@name='country_code']")
	WebElement countrydropdown;
	@FindBy(xpath="//*[@id=\"wrapper\"]/main/header/div/div/button")
	WebElement saveChanges;
	
	public AccountInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void addbusiness()
	{    
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(selectbusiness));
	selectbusiness.click();
	
		//vanityurl.sendKeys("qstab");  ///
		//startusinginvoicely.click(); ///
	}
	public void editaccount() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(hamburgermenu));
	hamburgermenu.click();
	account.click();
		Thread.sleep(3000);
		firstname.clear();
		lastname.clear();
		address.clear();
		postalcode.clear();
		state.clear();
		city.clear();
		firstname.sendKeys("Olivia");
		lastname.sendKeys("Bell");
		address.sendKeys("123,XYZ Road, Kalamassery");
		postalcode.sendKeys("657613");
		state.sendKeys("Kerala");
		city.sendKeys("Kochi");
		Select country=new Select(countrydropdown);
		country.selectByValue("IN");
		saveChanges.click();
		Thread.sleep(2000);
			}
	}
