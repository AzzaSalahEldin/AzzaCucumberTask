package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
	WebDriver driver;

	private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyDashboardIsVisible() {
		WebElement header = driver.findElement(dashboardHeader);
		if (!header.isDisplayed()) {
			throw new AssertionError("Dashboard is not visible - login might have failed.");
		}
	}

	public void clickPIMTab() {
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
	}
}
