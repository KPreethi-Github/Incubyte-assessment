package stepdefinitions;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class ComposeMail_steps {
	WebDriver driver = null;

	@Given("^I launch the Chrome browser$")
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Launched Chrome");
	}

	@Given("^I navigate to Gmail login screen$")
	public void gotoGmail() {
		driver.navigate().to("https://gmail.google.com/inbox/");
		driver.findElement(By.xpath("//a[contains(text(),'Sign in to Inbox')]")).click();
		System.out.println("Navigated to Gmail signin-screen");
	}

	@When("^I login to my Gmail with username as \"([^\"]*)\"$")
	public void enterUname(String arg) {
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(arg);
		System.out.println("Entered Username");
	}

	@When("^I login to my Gmail with password as \"([^\"]*)\"$")
	public void enterPwd(String arg) throws InterruptedException {
		/*
		 * if(
		 * driver.findElement(By.xpath("//span[text()='Try again']")).isDisplayed()==
		 * true) driver.findElement(By.xpath("//span[text()='Try again']")).click();
		 * Thread.sleep(4000);
		 */
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(arg);
		System.out.println("Entered Password");
	}

	@When("^I click the \"([^\"]*)\" button$")
	public void clickButton(String arg) throws InterruptedException {
		if (arg.equals("Next")) {
			driver.findElement(By.xpath("//span[text()='Next']")).click();
		} else if (arg.equals("Compose")) {
			driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
		} else if (arg.equals("Send")) {
			driver.findElement(By.xpath("//*[text()='Send']")).click();
		} else {
			System.out.println("Button name not matched");
		}
		System.out.println("Clicked Button - " + arg);
		Thread.sleep(4000);
	}

	@When("^I compose the mail with all necessary details$")
	public void composeMail(DataTable table) throws InterruptedException {
		List<List<String>> data = table.cells();

		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'Am Al editable')]")));

		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(data.get(1).get(1));
		driver.findElement(By.xpath("//*[@name='subjectbox']")).sendKeys(data.get(2).get(1));
		driver.findElement(By.xpath("//div[text()='Recipients']")).click();
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(data.get(3).get(1));

		System.out.println("Composed the mail");
	}

	@Then("^I validate the success message$")
	public void validateMsg() {
		List<WebElement> temp = driver.findElements(By.xpath("// span[text()='Message sent']"));
		if (temp.size() == 1) {
			System.out.println("Validated the success message - 'Message Sent'");
		} else {
			System.out.println("Success message Not displayed");
		}
	}

	@When("^I close the browser$")
	public void closeBrowser() {
		driver.quit();
		System.out.println("Closed the browser");

	}
}
