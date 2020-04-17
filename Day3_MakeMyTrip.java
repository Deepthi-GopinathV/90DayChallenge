package test.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day3_MakeMyTrip {

	public static void main(String[] args) throws InterruptedException 
	{
	//	1) Go to https://www.makemytrip.com/
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	ChromeDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	driver.get("https://www.makemytrip.com/");
	
	//	2) Click Hotels
	driver.findElementByXPath("//a[@class='makeFlex hrtlCenter column']").click();

	//	3) Enter city as Goa, and choose Goa, India
	driver.findElementByXPath("//input[@id='city']").click();
	Thread.sleep(2000);
	driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']").sendKeys("Goa");
	driver.findElementByXPath("//p[contains(text(),'Goa, India')]").click();

	//	4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
	driver.findElementById("checkin").click();
	driver.findElementByXPath("(//div[text()='15'])[2]").click();
	String checkin = driver.findElementByXPath("(//div[text()='15'])[2]").getText();
	System.out.println(checkin);
	int startDate = Integer.parseInt(checkin);
	WebElement currentMonth = driver.findElementByXPath("(//div[@class='DayPicker-Month'])[2]");
	currentMonth.findElement(By.xpath("(//div[text()='"+(startDate+5)+"'])[2]")).click();
	
	//	5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
	driver.findElementByXPath("//input[@id='guest']").click();
	driver.findElementByXPath("//ul[@data-cy='adultCount']//li[text()=2]").click();
	driver.findElementByXPath("//p[@data-cy='childrenRange']/following::ul/li[text()=1]").click();
	WebElement ele = driver.findElementByClassName("ageSelectBox");
	Select dd= new Select(ele);
	dd.selectByVisibleText("12");
	driver.findElementByXPath("//button[text()='APPLY']").click();
	
	//	6) Click Search button
	driver.findElementByXPath("//button[text()='Search']").click();
	
	//	7) Select locality as Baga
	if(driver.findElementByXPath("//div[contains(@class, 'mmBackdrop wholeBlack')]").isDisplayed())
	driver.findElementByXPath("//div[contains(@class, 'mmBackdrop wholeBlack')]").click();
	driver.findElementByXPath("//div[@class='locationFtrModal']//label[contains(text(),'Baga')]").click();
	
	
	//	8) Select 5 start in Star Category under Select Filters
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(	driver.findElementByXPath("//div[text()='Star Category']/following::ul//label[text()='5 Star']")));
	Thread.sleep(2000);
	driver.findElementByXPath("//div[text()='Star Category']/following::ul//label[text()='5 Star']").click();
	
	//	9) Click on the first resulting hotel and go to the new window
	driver.findElementByXPath("(//div[@class='makeFlex spaceBetween']/div)[1]").click();
	Set<String> resultSet = driver.getWindowHandles();
	List<String> lst = new ArrayList<String>(resultSet);
	driver.switchTo().window(lst.get(1));
	
	//	10) Print the Hotel Name 
	String hotelName = driver.findElementById("detpg_hotel_name").getText();
	System.out.println("Hotel name is "+ hotelName);
	
	//	11) Click MORE OPTIONS link and Select 3Months plan and close
	driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
	Thread.sleep(3000);
	driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
	driver.findElementByXPath("//span[@class='close']").click();
	
	//	12) Click on BOOK THIS NOW
	driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();
	System.out.println("Book now button clicked");

	//	13) Print the Total Payable amount
	String totalAmount = driver.findElementById("revpg_total_payable_amt").getText();
	System.out.println("Total Payable AMount: "+totalAmount);
	
	//	14) Close the browser
	driver.quit();

	
	}

}
