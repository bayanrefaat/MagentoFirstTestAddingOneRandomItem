import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AddOneRandomItem 
{
	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();
	String Password = "BRAbra@123";


	@BeforeTest

	public void MySetup() 
	{
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}

	@Test(priority = 1)

	public void AddOneItem() throws InterruptedException 
	{
		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> MyList = Container.findElements(By.tagName("li"));
		int RandomIndex = rand.nextInt(6);
		MyList.get(RandomIndex).click();
		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push"))
		{
			WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
			AddToCartButton.click();
		} else 
		{
			WebElement SizeContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
			List<WebElement> SizeList = SizeContainer.findElements(By.tagName("div"));
			int RandomSize = rand.nextInt(SizeList.size());
			SizeList.get(RandomSize).click();
			WebElement ColorContainer = driver
					.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
			List<WebElement> ColorList = ColorContainer.findElements(By.tagName("div"));
			int RandomColor = rand.nextInt(ColorList.size());
			ColorList.get(RandomColor).click();
			WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
			AddToCartButton.click();
			Thread.sleep(5000);

		}
		WebElement Msg = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		boolean actual = Msg.getText().contains("You added");
		boolean expected = true ; 
		Assert.assertEquals(actual, expected);
	}
	
		
		@Test (priority = 2)
		
		public void CheckOut()
		{
			String CheckOutPage = "https://magento.softwaretestingboard.com/checkout/cart/";
			driver.get(CheckOutPage);
			WebElement ProceedToCheckOutButton = driver.findElement(By.cssSelector("button[data-role='proceed-to-checkout']"));
			ProceedToCheckOutButton.click();
			
		}
		
		
		@Test (priority = 3)
		
		public void SignUp() throws InterruptedException
		{
			String ExpectedMsg = "Thank you for registering with Main Website Store.";
			Thread.sleep(4000);
			WebElement Email = driver.findElement(By.id("customer-email"));
			WebElement FirstName = driver.findElement(By.name("firstname"));
			WebElement LastName = driver.findElement(By.name("lastname"));
			WebElement StreetAddress = driver.findElement(By.name("street[0]"));
			WebElement City  = driver.findElement(By.name("city"));
			WebElement State = driver.findElement(By.name("region_id"));
			WebElement PostalCode  = driver.findElement(By.name("postcode"));
			WebElement Country  = driver.findElement(By.name("country_id"));
			WebElement PhoneNumber = driver.findElement(By.name("telephone"));
			WebElement NextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
			Select select = new Select(Country);
			
			Email.sendKeys("engbayn@gmail.com");
			FirstName.sendKeys("Bayan");
			LastName.sendKeys("Asaad");
			StreetAddress.sendKeys("Street1");
			City.sendKeys("Amman");
			State.sendKeys("Jubaiha");
			PostalCode.sendKeys("125874");
			//Country.sendKeys("Jordan");
			//NextButton.click();
			select.selectByVisibleText("Jordan");
			//select.selectByValue("CN");
			//select.selectByIndex(1);
			PhoneNumber.sendKeys("0799474657");
			Thread.sleep(5000);
			//WebElement NextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
			NextButton.click();			
			WebElement PlaceOrder = driver.findElement(By.xpath("//button[@title='Place Order']"));
			Thread.sleep(2000);
			PlaceOrder.click();
			
			WebElement CreatAnAcount = driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/checkout/account/delegateCreate/']"));
			Thread.sleep(3000);
			CreatAnAcount.click();
			WebElement PasswordButton = driver.findElement(By.id("password"));

			WebElement ConfirmPassword = driver.findElement(By.id("password-confirmation"));

			PasswordButton.sendKeys(Password);
			ConfirmPassword.sendKeys(Password);

			WebElement CreatAnAccountFinal = driver.findElement(By.cssSelector(".action.submit.primary"));
			Thread.sleep(3000);
			CreatAnAccountFinal.click();
			Thread.sleep(3000);
			WebElement succesfullMsg = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
			
			String ActualMsg = succesfullMsg.getText(); 
			
			Assert.assertEquals(ActualMsg, ExpectedMsg); 
	
		}
		
	

}
