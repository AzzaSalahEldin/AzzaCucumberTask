package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPage {
	WebDriver driver;
	WebDriverWait wait;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	@FindBy(xpath = "//div[@class='orangehrm-header-container']//button")
	WebElement addBtn;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	WebElement employeeName;

	@FindBy(xpath = "//label[text()='Username']/following::input[1]")
	WebElement userName;

	@FindBy(xpath = "//label[text()='Password']/following::input[1]")
	WebElement password;

	@FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
	WebElement confirmPassword;

	@FindBy(xpath = "//label[text()='User Role']/following::div[@class='oxd-select-text-input'][1]")
	WebElement userRoleMenu;

	@FindBy(xpath = "//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
	WebElement statusMenu;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveBtn;

	@FindBy(xpath = "//h6[text()='Admin']")
	WebElement adminLogo;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
	WebElement rowsCount;

	@FindBy(xpath = "//label[text()='Username']/ancestor::div[contains(@class, 'oxd-input-group')]/descendant::input")
	WebElement searchUserName;

	@FindBy(xpath = "//button[@type='submit' and text()=' Search ']")
	WebElement searchBtn;

	@FindBy(xpath = "//div[@class='oxd-table-card']//div[contains(@class,'oxd-table-cell')][2]")
	List<WebElement> tableUserName;

	@FindBy(xpath = "//div[@class='oxd-table-card']")
	List<WebElement> tableRows;

	@FindBy(xpath = "//button//i[@class='oxd-icon bi-trash']") // Delete icon/button
	WebElement deleteButton;

	@FindBy(xpath = "//button[text()=' Delete Selected ']") // Delete icon/button
	WebElement deleteSelected;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement confirmDeleteButton;

	public void clickAdd() {
		addBtn.click();
	}

	public void selectUserRole(String role) {
		userRoleMenu.sendKeys(role);
		By suggestionsList = By.xpath("//div[@role='listbox']//span");
		wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsList));
		List<WebElement> suggestions = driver.findElements(suggestionsList);
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equalsIgnoreCase(role)) {
				suggestion.click();
				return;
			}
		}
	}

	public void selectStatus(String status) {
		statusMenu.sendKeys(status);
		By suggestionsList = By.xpath("//div[@role='listbox']//span");
		wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsList));
		List<WebElement> suggestions = driver.findElements(suggestionsList);
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equalsIgnoreCase(status)) {
				suggestion.click();
				return;
			}
		}
		;
	}

	public boolean isAdminPageDisplayed() {
		return adminLogo.isDisplayed();
	}

	public void enterEmployeeName(String name) {
		employeeName.clear();
		employeeName.sendKeys(name);
		By suggestionsList = By.xpath("//div[@role='listbox']//span");
		wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsList));
		List<WebElement> suggestions = driver.findElements(suggestionsList);
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equalsIgnoreCase(name)) {
				suggestion.click();
				return;
			}
		}
	}

	public void enterUserName(String name) {
		userName.sendKeys(name);
	}

	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}

	public void enterConfirmPassword(String pass) {
		confirmPassword.sendKeys(pass);
	}

	public void clickSave() {
		saveBtn.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='orangehrm-container']")));
	}
	
	public int getRecordsCount() {
		String text = rowsCount.getText(); 
		Pattern pattern = Pattern.compile("\\((\\d+)\\)");
		Matcher matcher = pattern.matcher(text);

		int recordCount = 0;
		if (matcher.find()) {
		    recordCount = Integer.parseInt(matcher.group(1));
		}
		return recordCount;
	}

	public void searchByUserName(String name) {
		clearSearchByUserName();
		searchUserName.sendKeys(name);
	}

	public void clickSearch() {
		searchBtn.click();
	}

	public void clearSearchByUserName() {
		searchUserName.click();
		searchUserName.sendKeys(Keys.CONTROL + "a");
		searchUserName.sendKeys(Keys.BACK_SPACE);
	}

	public boolean isUsernamePresent(String username) {
		clickSearch();
		return tableRows.stream().anyMatch(row -> row.getText().contains(username));
	}

	public void deleteUser(String username) {
		searchByUserName(username);
		clickSearch();
		driver.findElement(By.xpath("//div[@role='columnheader']//div[@class='oxd-checkbox-wrapper']")).click();
		deleteSelected.click();
		confirmDeleteButton.click();
		clearSearchByUserName();
		clickSearch();
	}
}
