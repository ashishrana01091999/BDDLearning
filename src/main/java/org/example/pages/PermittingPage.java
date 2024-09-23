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

public class PermittingPage extends BasePage {
    private static final String username= TestSetup.generateRandomStringAndStore("permitting");
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    public PermittingPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    @FindBy(id = "permitting")
    private WebElement permittingModule;

    @FindBy(id = "btn_design_list_openSiteAssessment")
    private WebElement permittingAddForm;

    @FindBy(xpath = "//input[@placeholder='eg: John']")
    private WebElement homeOwnerName;

    @FindBy(id = "input_address")
    private WebElement homeOwnerAddress;

    @FindBy(id = "input_email_permitting")
    private WebElement emailField;

    @FindBy(id = "input_pto_form_ahj")
    private WebElement ahjCompanyDropdown ;

    @FindBy(xpath = "//input[@placeholder='eg. 001-222-2222']")
    private WebElement phoneNumber;

    @FindBy(id = "systemdesignid")
    private WebElement systemDesignDocumentUploadArea;

    @FindBy(id = "homeownerid")
    private WebElement homeOwnersContractUploadField;

    @FindBy(id = "textarea_assessmentform_permitting_comment")
    private WebElement commentsField;

    @FindBy(id = "btn_form_permitting_submit")
    private WebElement submitAndPayBtn;

    @FindBy(id = "_itConfirm")
    private WebElement confirmBtn;

    @FindBy(id = "_itCancel")
    private WebElement cancelBtn;

    @FindBy(xpath = "//input[@placeholder='Search..']")
    private WebElement localSearchField;

    @FindBy(id = "list_design_permitting_customerDetails")
    private List<WebElement> requestList;

    @FindBy(id = "btn_design_permitting_assign")
    private List<WebElement> acceptBtn;

    @FindBy(id = "btn_listing_permitting_assign")
    private List<WebElement> assignBtn;

    @FindBy(id = "input_prelim_form_searchInputControl")
    private WebElement dialogSearchBoxField;

    @FindBy(id = "assignDesign")
    private WebElement dialogAssignDesignBtn;

    @FindBy(id = "list_design_prelim_customerDetails")
    private List<WebElement> permittingRequestListOperator;

    @FindBy(id = "detail_pto_interconnectionupdate")
    private WebElement formSubmitBtn;

    @FindBy(xpath = "//button/span[contains(text(),'In Review')]")
    private WebElement inReviewSection;

    @FindBy(id = "detail_pto_ptoComplete")
    private WebElement formCompleteBtn;

    @FindBy(id = "Permitting Completed")
    private WebElement permittingCompletedSection;

    @FindBy(id = "Final Inspection Requested")
    private WebElement finalInspectionRequestSection;

    @FindBy(id = "Final Inspection Completed")
    private WebElement finalInspectionCompletedSection;

    @FindBy(xpath = "//*[contains(@class,'colorStatus')]/p")
    private WebElement requestStatus;

    @FindBy(id = "permitting_permittingfileid")
    private WebElement permittingFileUploadArea;

    public void clickOnPermittingModule() {
        js.executeScript("arguments[0].click();", permittingModule);
        waitForMillisecond(3500);
    }

    public void clickOnAddPermittingForm(){
        clickElement(permittingAddForm);
    }

    public void fillFormDetails() {
       fillField(homeOwnerName,username);
       fillField(homeOwnerAddress,"7th Ave, New York, NY, USA");
        waitForMillisecond(2000);
        // Navigate through the dropdown options using arrow keys
        actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press Down Arrow
        // Select an option with Enter key
        actions.sendKeys(Keys.ENTER).perform();
        fillField(emailField,"permit@yopmail.com");
        waitForMillisecond(2000);
        clickElement(ahjCompanyDropdown);
        WebElement optionToSelect =driver.findElement(By.xpath("//mat-option//span[contains(text(),'Amherst town')]"));
        clickElement(optionToSelect);
        fillField(phoneNumber,"9785455554");
        uploadFile(systemDesignDocumentUploadArea,"src/main/resources/uploadData/systemDesign.pdf");
        uploadFile(homeOwnersContractUploadField,"src/main/resources/uploadData/img.png");
        fillField(commentsField,"this is test permitting record.");
    }

    private void fillField(WebElement element, String value) {
        element.clear(); // Clear field before entering new value
        element.sendKeys(value);
        waitForMillisecond(1500);
    }

    public void submitForm() {
        clickElement(submitAndPayBtn);
        clickElement(confirmBtn);
        waitForMillisecond(7000);
    }

    public void acceptAndAssignToOperator(String operatorUsername) {
        clickElement(acceptBtn.get(0));
        clickElement(assignBtn.get(0));
        fillField(dialogSearchBoxField,operatorUsername);
        waitForMillisecond(2000);
        clickElement(dialogAssignDesignBtn);
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

    public void operatorUploadFileAndSubmitForm(){
       clickElement(permittingRequestListOperator.get(0));
        uploadFile(permittingFileUploadArea,"src/main/resources/uploadData/permittingFile.pdf");
        clickElement(formSubmitBtn);
        waitForMillisecond(5000);
    }

    public void operatorWillReviewAndComplete(){
        clickElement(inReviewSection);
        clickElement(permittingRequestListOperator.get(0));
        clickElement(formCompleteBtn);
    }

    public void operatorSubmitForFinalInspection(){
        clickElement(permittingCompletedSection);
        searchRequestInLocal();
        clickElement(permittingRequestListOperator.get(0));
        clickElement(formCompleteBtn);
    }

    public void operatorCompleteFinalInspectionProcess(){
        clickElement(finalInspectionRequestSection);
        searchRequestInLocal();
        clickElement(permittingRequestListOperator.get(0));
        clickElement(formCompleteBtn);
    }

    public String isRequestCompleted(){
        clickOnPermittingModule();
        clickElement(finalInspectionCompletedSection);
        searchRequestInLocal();
        clickElement(requestList.get(0));
        return requestStatus.getText();
    }
}
