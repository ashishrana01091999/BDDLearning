package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(id = "input_email")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "btn_signin_form_submit")
    private WebElement signInBtn;

    @FindBy(id = "profile")
    private WebElement profileIcon;

    @FindBy(id = "btn_profile_onSignout")
    private WebElement signOutBtn;

    @FindBy(id = "close")
    private List<WebElement> closeBtn;

    private WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Page-specific method to log in
    public void loginWithCredentials(String username, String password) {
        fillField(usernameField,username);
        fillField(passwordField,password);
        clickElement(signInBtn);

        // Wait for potential pop-up or additional elements to be ready
        if (closeBtn.size() == 1) {
           clickElement(closeBtn.get(0));
        }
    }

    public void logout() {
        clickElement(profileIcon);
        clickElement(signOutBtn);
        waitForMillisecond(2000);
    }

    public void waitForMillisecond(Integer duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillField(WebElement element, String value) {
        element.clear(); // Clear field before entering new value
        element.sendKeys(value);
        waitForMillisecond(1000);
    }

    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();//
        waitForMillisecond(1500);
    }
}
