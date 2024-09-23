package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.Paths;

public class BasicFormPage extends BasePage {

    public BasicFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "homeOwnerName")
    private WebElement homeOwnerName;

    @FindBy(name = "homeOwnerAddress")
    private WebElement homeOwnerAddress;

    @FindBy(name = "homeOwnerEmail")
    private WebElement homeOwnerEmail;

    @FindBy(name = "phoneNumber")
    private WebElement phoneNumber;

    @FindBy(name = "utilityCompany")
    private WebElement utilityCompany;

    @FindBy(name = "yearlyProduction1")
    private WebElement yearlyProduction1;

    @FindBy(name = "comments")
    private WebElement comments;

    @FindBy(name = "systemDesignDocument")
    private WebElement systemDesignDocument;

    @FindBy(name = "utilityBills")
    private WebElement utilityBills;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='successModal']//p[contains(@class, 'success-message')]")
    private WebElement successText;

    public void fillForm(String name, String address, String email, String phone, String utility, String production, String comment) {
        fillField(homeOwnerName, name);
        fillField(homeOwnerAddress, address);
        fillField(homeOwnerEmail, email);
        fillField(phoneNumber, phone);
        fillField(utilityCompany, utility);
        fillField(yearlyProduction1, production);
        fillField(comments, comment);
    }

    public void uploadSystemDesignDocument(String filePath) {
        uploadFile(systemDesignDocument, filePath);
    }

    public void uploadUtilityBills(String filePath) {
        uploadFile(utilityBills, filePath);
    }

    public void submitForm() throws InterruptedException {
        submitButton.click();
        Thread.sleep(5000);
    }

    private void fillField(WebElement element, String value) {
        element.sendKeys(value);
        try {
            Thread.sleep(1000); // Adjust the speed by changing the sleep time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void uploadFile(WebElement element, String filePath) {
        element.sendKeys(Paths.get(filePath).toAbsolutePath().toString());
        try {
            Thread.sleep(1000); // Adjust the speed by changing the sleep time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getPopupText(){
        return successText.getText();
    }
}
