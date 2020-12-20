package ru.abtankshop.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginAndCreateBrandSteps {

    private WebDriver webDriver = InitWebDriver.webDriver;

    @When("^I click on brands link$")
    public void iClickOnBrandsLink() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("brands_link"));
        Thread.sleep(2000);
        webElement.click();
    }

    @Then("^table brands should be \"([^\"]*)\"$")
    public void tableBrandsShouldBe(String table_name) throws InterruptedException {
        Thread.sleep(2000);
        WebElement webElement = webDriver.findElement(By.id("table_header"));
        assertThat(webElement.getText()).isEqualTo(table_name);
    }

    @When("^I click on create brand button$")
    public void iClickOnCreateBrandButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement webElement = webDriver.findElement(By.id("btn_create_brand"));
        webElement.click();
    }

    @Then("^label brand should be \"([^\"]*)\"$")
    public void formBrandShouldBe(String label_name) throws InterruptedException {
        Thread.sleep(2000);
        WebElement webElement = webDriver.findElement(By.id("brand_name"));
        assertThat(webElement.getText()).isEqualTo(label_name);
    }

    @And("^I provide brand name as \"([^\"]*)\"$")
    public void iProvideBrandNameAs(String brand) throws InterruptedException {
        Thread.sleep(2000);
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(brand);
    }

    @And("^I click on submit button$")
    public void iClickOnSubmitButton() {
        WebElement webElement = webDriver.findElement(By.id("btn_form_brand_submit"));
        webElement.click();
    }

}
