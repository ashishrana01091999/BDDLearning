package org.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.config.ConfigReader;
import org.example.pages.LoginPage;
import org.example.utility.TestSetup;
import org.openqa.selenium.WebDriver;

public class LoginPageSteps {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final ConfigReader prop;

    public LoginPageSteps() {
        prop = new ConfigReader();
        this.driver = TestSetup.getDriver();
        loginPage = new LoginPage(driver);
    }

    @When("user login with client credentials")
    public void userLoginWithClientCredentials() {
        loginPage.loginWithCredentials(prop.getClientUsername(), prop.getClientPassword());
    }

    @Then("user will logout from profile")
    public void userWillLogoutFromProfile() {
        loginPage.logout();
    }

    @Then("user login with interconnection operator credentials")
    public void userLoginWithInterconnectionOperatorCredentials() {
        loginPage.loginWithCredentials(prop.getOperatorUsername(), prop.getBasePasword());
    }

    @Then("user login with wattmonk admin credentials")
    public void userLoginWithWattmonkAdminCredentials() {
        loginPage.loginWithCredentials(prop.getAdminUsername(), prop.getAdminPassword());
    }
}
