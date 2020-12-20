package ru.abtankshop.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import ru.abtankshop.DriverInitializer;

public class InitWebDriver {

    public static WebDriver webDriver;

    @Given("^I open web browser$")
    public void iOpenFirefoxBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}
