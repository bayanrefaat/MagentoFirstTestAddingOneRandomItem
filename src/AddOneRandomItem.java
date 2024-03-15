import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {
	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();

	@BeforeTest

	public void MySetup() {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}

	@Test

	public void AddOneItem() throws InterruptedException {
		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> MyList = Container.findElements(By.tagName("li"));
		int RandomIndex = rand.nextInt(6);
		MyList.get(RandomIndex).click();
		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {
			WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
			AddToCartButton.click();
		} else {
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
	}

}
