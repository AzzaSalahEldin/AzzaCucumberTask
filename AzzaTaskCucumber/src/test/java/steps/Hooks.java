package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import org.openqa.selenium.WebDriver;
import base.Base;

public class Hooks extends Base {
    public static WebDriver driver;
    @BeforeAll
    public static void setUp() {
        initializeDriver();
    }

    @AfterAll
    public static void tearDown() {
        closeDriver();
    }
}
