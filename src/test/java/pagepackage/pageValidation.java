package pagepackage;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class pageValidation {
	
	WebDriver driver;
	
	@FindBy(tagName="a") //@FindBy locates elements on the page, making them easy to reuse.
	List<WebElement> li;
	
	public pageValidation(WebDriver driver)  // Constructor that accepts WebDriver instance
	{
		this.driver=driver; // Initialize the driver instance
		PageFactory.initElements(driver, this);
		
	}
	public void verifyTitle()
	{
		String actTitle=driver.getTitle();
		String expTitle="Free Online Invoicing for Small Businesses - invoicely";
		Assert.assertEquals(expTitle, actTitle, "Title is incorrect");
		System.out.println("Title verification passed");
		
	}
	public void verifyText()
	{
		String actualsource=driver.getPageSource();
		String expword="Powerful Invoicing Platform";
		Assert.assertTrue(actualsource.contains(expword),"Text is incorrect");
		System.out.println("Text verification passed");
		}
	public void noOfLinks()
	{
       
		System.out.println("number of links : " +li.size());
		for(WebElement lin:li)
		{                   // trim=Remove leading/trailing whitespace
			String linkedtext=lin.getText().trim();// accurately checks if lin.getText() has no visible characters.
			String linkhref=lin.getAttribute("href");

			if(linkhref !=null)  // Null check
			{
			System.out.println((linkedtext.isEmpty() ? "No Link Text" : linkedtext)+"..."+linkhref);
			/* Prints "No Link Text" only if linkedtext truly has no visible characters
               linkedtext.isEmpty() checks if the linkedtext string has a length of zero, 
                ie it contains no characters. If linkedtext is truly empty, then isEmpty() will return true.*/
		    verifyLink(linkhref);
			}
			else
			{
				System.out.println((linkedtext.isEmpty() ? "No Link Text" : linkedtext)+"..."+"linkhref is null");
			}
		
		}
	}
	private void verifyLink(String linkhref) {
		try
		{
			URI obj=new URI(linkhref);
			HttpURLConnection c=(HttpURLConnection)obj.toURL().openConnection();
			if(c.getResponseCode()==200)
			{
				System.out.println("SUCCESS,Response code is 200");
			}
			else if(c.getResponseCode()==404)
			{
				System.out.println("FAILURE,Response code is 404");
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
