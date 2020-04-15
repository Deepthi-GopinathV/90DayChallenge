package test.exercise;

	import java.util.List;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class Myntra_WishList {
		
		public static void main(String[] args) throws InterruptedException
		{
			//1) Open https://www.myntra.com/
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeDriver driver = new ChromeDriver();
			driver.get("https://www.myntra.com");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//2) Mouse over on WOMEN (Actions -> moveToElement
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElementByXPath("(//a[text()='Women'])[1]")).build().perform();
			Thread.sleep(2000);
			
			//3) Click Jackets & Coats
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[text()='Jackets & Coats'])[1]")));
			driver.findElementByXPath("(//a[text()='Jackets & Coats'])[1]").click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//4) Find the total count of item (top) -> getText -> String
			String str = driver.findElementByClassName("title-count").getText();
			String text = str.replaceAll("\\D","");
			int intTotalCountOfItem =  Integer.parseInt(text);
			
			//5) Validate the sum of categories count matches
			WebElement category_Jacket = driver.findElementByXPath("(//ul[@class='categories-list']//span[@class='categories-num'])[1]");
			WebElement category_Coats = driver.findElementByXPath("(//ul[@class='categories-list']//span[@class='categories-num'])[2]");
			int intcategory_Jacket = Integer.parseInt(category_Jacket.getText().replaceAll("\\D", ""));
			int intcategory_Coats = Integer.parseInt(category_Coats.getText().replaceAll("\\D", ""));
			if((intcategory_Jacket+intcategory_Coats)==intTotalCountOfItem)
			{
				System.out.println("Count matches");
			}
			else
			{
				System.out.println("Count is not matched");
			}
			
			//6) Check coats:
			driver.findElementByXPath("//label[text()='Coats']//div").click();
			Thread.sleep(2000);
			
			//7) Click + More option under BRAND	Click more under brand
			driver.findElementByXPath("//div[@class='brand-more']").click();
			Thread.sleep(2000);
			
			//8) Type MANGO and click checkbox
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']")));
			driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("MANGO");
			
			driver.findElementByXPath("//span[@class='FilterDirectory-count']/following-sibling::div").click();
			
			//9) Close the pop-up x
			driver.findElementByXPath("//span[contains(@class,'myntraweb-sprite FilterDirectory-close')]").click();
			
			//10) Confirm all the Coats are of brand MANGO
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//ul[@class='results-base']//h3[@class='product-brand'])[1]")));
			List<WebElement> lst = driver.findElementsByXPath("//ul[@class='results-base']//h3[@class='product-brand']");
			boolean bool_IfAnyNotMango = false;
			for(WebElement ele:lst)
			{
				//wait.until(ExpectedConditions.visibilityOf(ele));
				if(!ele.getText().equalsIgnoreCase("MANGO"))
				{
					bool_IfAnyNotMango = true;
				}
				
			}
			if(bool_IfAnyNotMango==false)
			{
				System.out.println("All Mango brands are only displayed");
			}
			
			//11) Sort by Better Discount
			action.moveToElement(driver.findElementByXPath("//div[@class='sort-sortBy']")).build().perform();
			action.moveToElement(driver.findElementByXPath("//label[text()='Better Discount']")).click().perform();
			
			
			//12) Find the price of first displayed item
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//ul[@class='results-base']//span[@class='product-discountedPrice'])[1]")));
			String text_Price = driver.findElementByXPath("(//ul[@class='results-base']//span[@class='product-discountedPrice'])[1]").getText();
			String text_PriceAlone = text_Price.replaceAll("\\D","");
			int intPrice = Integer.parseInt(text_PriceAlone);
			System.out.println("Price of first displayed item - "+intPrice);
			
			//13) Mouse over on size of the first item
			action.moveToElement(driver.findElementByXPath("(//h4[@class='product-product'])[1]")).build().perform();
			
			//14) Click on WishList Now
			driver.findElementByXPath("(//ul[@class='results-base']//span[@class='product-actionsButton product-wishlist product-prelaunchBtn'])[1]").click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			// 15) Close Browser
			if(driver.getTitle().equalsIgnoreCase("Login"))
			{
				driver.close();
			}
			
			
		}

	
}
