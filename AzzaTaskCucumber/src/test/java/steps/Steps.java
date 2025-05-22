package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import base.Base;
import pages.*;

public class Steps extends Base {

    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static AdminPage adminPage;
    static int initialCount = 0;
    static long currentTime = System.currentTimeMillis();

	public Steps() {
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		adminPage = new AdminPage(driver);
	}

	@Given("User opens the website and navigates to the login page")
	public void user_opens_website() {
		launchApp();
	}

	@When("User enters valid username {string} and password {string}")
	public void enter_valid_credentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
	}

	@Then("User should be navigated to the home page")
	public void verify_home_page() {
		dashboardPage.verifyDashboardIsVisible();
	}

	@When("User clicks on the Admin tab in the left menu")
	public void user_clicks_on_the_admin_tab_in_the_left_menu() {
		dashboardPage.clickAdminTab();
	}
	
	@Then("User gets and stores the current number of user records")
	public void user_gets_and_stores_user_count() {
		System.out.println("Total Records is " + adminPage.getRecordsCount());
	}

	@When("User clicks on the Add button")
	public void click_add_button() {
		initialCount = adminPage.getRecordsCount();
		adminPage.clickAdd();
	}

	@And("User fills in the required data:")
	public void fill_required_data(DataTable dataTable) {
		adminPage.enterEmployeeName(dataTable.cell(1, 0));
		adminPage.enterUserName(dataTable.cell(1, 1)+ currentTime);
		adminPage.enterPassword(dataTable.cell(1, 2));
		adminPage.enterConfirmPassword(dataTable.cell(1, 3));
		adminPage.selectUserRole("Admin");
		adminPage.selectStatus("Enabled");
	}

	@And("User clicks the Save button")
	public void click_save_button() {
		adminPage.clickSave();
	}

	@Then("The number of user records should increase by 1")
	public void verify_user_added() {
		Assert.assertEquals(initialCount + 1, adminPage.getRecordsCount());
	}

	@When("User searches for the username {string}")
	public void search_username(String username) {
		adminPage.searchByUserName(username + currentTime);
		adminPage.clickSearch();
	}

	@Then("The user {string} should appear in the search results")
	public void verify_user_appears(String username) {
		Assert.assertTrue(adminPage.isUsernamePresent(username + currentTime));	
	}

	@When("User deletes the user {string}")
	public void delete_user(String username) {
		adminPage.clearSearchByUserName();
		adminPage.clickSearch();
		initialCount = adminPage.getRecordsCount();
		adminPage.deleteUser(username + currentTime);
	}

	@Then("The number of user records should decrease by 1")
	public void verify_user_deleted() {
		Assert.assertEquals(initialCount - 1, adminPage.getRecordsCount());
	}
}
