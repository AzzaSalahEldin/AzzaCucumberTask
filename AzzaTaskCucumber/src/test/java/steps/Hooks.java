package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import base.Base;

public class Hooks extends Base {
    public static WebDriver driver;
    @Before
    public void setUp() {
    	initializeDriver();
    }

    @After
    public void tearDown() {
        closeDriver();
    }
}
