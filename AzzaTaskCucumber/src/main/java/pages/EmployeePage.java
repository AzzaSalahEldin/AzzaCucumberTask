package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeePage {
	WebDriver driver;
	WebDriverWait wait;

	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	@FindBy(xpath = "//div[@class='orangehrm-header-container']//button")
	WebElement addBtn;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveBtn;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='Middle Name']")
	WebElement middleName;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement lastName;

	@FindBy(xpath = "//label[text()='Employee Id']/following::input[@class='oxd-input oxd-input--active']")
	WebElement employeeId;

	public void clickAdminTab() {
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
	}

	public void clickAdd() {
		addBtn.click();
	}
	public void enterEmployeeDetails(String first, String middle, String last, long Id) {
		firstName.sendKeys(first);
		middleName.sendKeys(middle);
		lastName.sendKeys(last);
		employeeId.sendKeys(String.valueOf(Id));
		
	}
	public void clickSave() {
		saveBtn.click();
	}

	public boolean verifyEmployeeAddedSuccessfully() {
		WebElement employeeNameHeader = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));
		return employeeNameHeader.isDisplayed();
	}
}
