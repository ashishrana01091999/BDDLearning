package org.example.pages;

import org.example.utility.TestSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class SurveyPage extends BasePage {
    private static final String username= TestSetup.generateRandomStringAndStore("survey");
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    public SurveyPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    @FindBy(id = "Survey")
    private WebElement surveyModule;

    @FindBy(id = "survey_add_form")
    private WebElement surveyAddForm;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "input_email_survey")
    private WebElement emailField;

    @FindBy(id = "survey_phone")
    private WebElement phoneNumber;

    @FindBy(id = "input_address")
    private WebElement addressField;

    @FindBy(id = "drpdown_addsurvey_form_installtype")
    private WebElement installTypeDropdown;

    @FindBy(id = "datepicker_calendar")
    private WebElement calendarBtn;

    @FindBy(xpath = "//button[contains(@class,'mat-calendar-body-active')]")
    private WebElement currentDate;

    @FindBy(id = "survey_comments")
    private WebElement commentsField;

    @FindBy(id = "slots")
    private List<WebElement> timeSlots;

    @FindBy(id = "undefined")
    private WebElement finalDesignProposalFileUploadArea;

    @FindBy(id = "schedule_survey")
    private WebElement scheduleSurveyBtn;

    @FindBy(id = "survey_payandproceed")
    private WebElement payAndProceedBtn;

    @FindBy(id = "_itConfirm")
    private WebElement confirmBtn;

    @FindBy(id = "_itCancel")
    private WebElement cancelBtn;

    @FindBy(xpath = "//input[@placeholder='Search..']")
    private WebElement localSearchField;

    @FindBy(id = "list_design_permitting_customerDetails")
    private List<WebElement> requestList;



    public void clickOnSurveyModule() {
        js.executeScript("arguments[0].click();", surveyModule);
        waitForMillisecond(3500);
    }

    public void clickOnAddSurveyForm(){
        clickElement(surveyAddForm);
    }

    public void fillFormDetails() {
       fillField(nameField,username);
        fillField(emailField,"testsurvey@yopmail.com");
        waitForMillisecond(2000);
        fillField(phoneNumber,"4047241937");
        fillField(addressField,"7900 Fannin St, Houston, TX 77054, USA");
        waitForMillisecond(2000);
        // Navigate through the dropdown options using arrow keys
        actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press Down Arrow
        // Select an option with Enter key
        actions.sendKeys(Keys.ENTER).perform();
        clickElement(installTypeDropdown);
        WebElement optionToSelect =driver.findElement(By.xpath("//mat-option//span[contains(text(),'House Roof')]"));
        clickElement(optionToSelect);
        clickElement(calendarBtn);
        clickElement(currentDate);
        clickElement(timeSlots.get(0));
        uploadFile(finalDesignProposalFileUploadArea,"src/main/resources/uploadData/finalDesignProposalFile.pdf");
        fillField(commentsField,"this is test survey record");
    }

    private void fillField(WebElement element, String value) {
        element.clear(); // Clear field before entering new value
        element.sendKeys(value);
        waitForMillisecond(1500);
    }

    public void submitForm() {
        clickElement(scheduleSurveyBtn);
        clickElement(payAndProceedBtn);
        clickElement(confirmBtn);
        waitForMillisecond(7000);
    }


    public void searchRequestInLocal() {
        fillField(localSearchField, username);
        waitForMillisecond(3000);
  }

    private void uploadFile(WebElement element, String filePath) {
        element.sendKeys(Paths.get(filePath).toAbsolutePath().toString());
        waitForMillisecond(3000);
    }

    public void waitForMillisecond(Integer duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();//
        waitForMillisecond(2500);
    }

}
