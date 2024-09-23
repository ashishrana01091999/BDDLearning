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

public class PtoPage extends BasePage {

    private static final String username = TestSetup.generateRandomStringAndStore("pto");
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    public PtoPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    @FindBy(id = "pto")
    private WebElement ptoModule;

    @FindBy(id = "btn_design_list_openSiteAssessment")
    private WebElement addPtoBtn;

    @FindBy(id = "input_pto_form_name")
    private WebElement homeOwnerNameField;

    @FindBy(id = "input_address")
    private WebElement addressField;

    @FindBy(id = "input_email_pto")
    private WebElement emailField;

    @FindBy(id = "input_pto_form_utilityCompany")
    private WebElement utilyCompanyName;

    @FindBy(id = "input_pto_form_oneyearProduction")
    private WebElement oneYearProductionField;

    @FindBy(id = "input_pto_form_threeyearProduction")
    private WebElement threeYearProductionField;

    @FindBy(id = "input_pto_form_phone")
    private WebElement phoneNumberField;

    @FindBy(id = "systemdesignid")
    private WebElement systemDesignDocumentUploadArea;

    @FindBy(id = "utilitybillsid")
    private WebElement utilityBillUploadArea;

    @FindBy(id = "homeownerid")
    private WebElement homeOwnersContractUploadField;

    @FindBy(id = "textarea_assessmentform_prelim_comment")
    private WebElement commentsField;

    @FindBy(id = "pil_submit")
    private WebElement submitAndPayBtn;

    @FindBy(id = "_itConfirm")
    private WebElement confirmBtn;

    @FindBy(id = "_itCancel")
    private WebElement cancelBtn;

    @FindBy(id = "input_pto_listing_searchInputControl")
    private WebElement localSearchField;

    @FindBy(id = "list_pto_customerDetails")
    private List<WebElement> requestList;

    @FindBy(id = "btn_listing_pto_accept")
    private List<WebElement> acceptBtn;

    @FindBy(id = "btn_listing_pto_assign")
    private List<WebElement> assignBtn;

    @FindBy(id = "input_prelim_form_searchInputControl")
    private WebElement dialogSearchBoxField;

    @FindBy(id = "assignDesign")
    private WebElement dialogAssignDesignBtn;

    @FindBy(id = "btn_listing_ptoopertor_accept")
    private List<WebElement> acceptBtnOperator;

    @FindBy(id = "btn_listing_ptoopertor_inprogress")
    private List<WebElement> moveToInProgressBtn;

    @FindBy(id = "Interconnection Inprogress")
    private WebElement interconnectionInProgressSection;

    @FindBy(id = "Submission")
    private WebElement submissionTab;

    @FindBy(id = "detail_pto_casenumber")
    private WebElement caseNumberField;

    @FindBy(id = "detail_pto_interconnectionupdate")
    private WebElement formSubmitBtn;

    @FindBy(xpath = "//button/span[contains(text(),'In Review')]")
    private WebElement inReviewSection;

    @FindBy(id = "detail_pto_interconnectionComplete")
    private WebElement conditionalApprovalBtn;

    @FindBy(id = "btn_design_pto_assign")
    private List<WebElement> sendForPtoBtn;

    @FindBy(id = "Interconnection Completed")
    private WebElement interconnectionCompletedSection;

    @FindBy(xpath = "//button/span[contains(text(),'Submit')]")
    private WebElement popupSendForPtoSubmitBtn;

    @FindBy(id = "PTO Inprogress")
    private WebElement ptoInProgressSection;

    @FindBy(id = "pto_interconnectionid")
    private WebElement interconnectionFileUploadArea;

    @FindBy(id = "pto_ptofileid")
    private WebElement ptoFileUploadArea;

    @FindBy(id = "detail_pto_ptoComplete")
    private WebElement formCompleteBtn;

    @FindBy(id = "PTO Completed")
    private WebElement ptoCompletedSection;

    @FindBy(xpath = "//*[contains(@class,'colorStatus')]/p")
    private WebElement requestStatus;

    public void clickOnPtoModule() {
        js.executeScript("arguments[0].click();", ptoModule);
        waitForMillisecond(3000);
    }

    public void clickOnAddPtoForm() {
        clickElement(addPtoBtn);
    }

    public void fillFormDetails(String name, String address, String email, String utilityCompany, String estimatedProd1year, String estimatedProd3year, String phoneNum, String comments) throws InterruptedException {
        fillField(homeOwnerNameField, username);
        fillField(addressField, address);
        waitForMillisecond(2000);
        actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press Down Arrow
        actions.sendKeys(Keys.ENTER).perform(); // Select an option with Enter key
        fillField(emailField, email);
        clickElement(utilyCompanyName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//span[contains(text(),'AEP')]"))).click();
        fillField(oneYearProductionField, estimatedProd1year);
        fillField(threeYearProductionField, estimatedProd3year);
        fillField(phoneNumberField, phoneNum);
        uploadFile(systemDesignDocumentUploadArea,"src/main/resources/uploadData/systemDesign.pdf");
        uploadFile(utilityBillUploadArea,"src/main/resources/uploadData/utilityBill.pdf");
        uploadFile(homeOwnersContractUploadField,"src/main/resources/uploadData/img.png");
        fillField(commentsField, comments);
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

    private void uploadFile(WebElement element, String filePath) {
        element.sendKeys(Paths.get(filePath).toAbsolutePath().toString());
        waitForMillisecond(3000);
    }

    public void searchRequestInLocal() {
        fillField(localSearchField, username);
//        waitForMillisecond(3000);
        wait.until(ExpectedConditions.visibilityOfAllElements(requestList));
    }

    public void acceptAndAssignToOperator(String operatorUsername) {
        clickElement(acceptBtn.get(0));
        clickElement(assignBtn.get(0));
        fillField(dialogSearchBoxField, operatorUsername);
        clickElement(dialogAssignDesignBtn);
        waitForMillisecond(3000);
    }

    public void acceptAndComplete() {
        clickElement(acceptBtnOperator.get(0));
        waitForMillisecond(2500);
        clickElement(moveToInProgressBtn.get(0));
        waitForMillisecond(2500);
        clickElement(interconnectionInProgressSection);
        searchRequestInLocal();
        clickElement(requestList.get(0));
        clickElement(submissionTab);
        uploadFile(interconnectionFileUploadArea,"src/main/resources/uploadData/interconnectionfile.pdf");
        fillField(caseNumberField, "1234565");
        clickElement(formSubmitBtn);
    }

    public void interconnectionInReviewComplete() {
        clickElement(inReviewSection);
        clickElement(requestList.get(0));
        clickElement(conditionalApprovalBtn);
    }

    public void clientSendRequestForPto() {
        clickElement(interconnectionCompletedSection);
        searchRequestInLocal();
        clickElement(sendForPtoBtn.get(0));
        clickElement(popupSendForPtoSubmitBtn);
    }

    public void ptoComplete() {
        clickElement(ptoInProgressSection);
        searchRequestInLocal();
        clickElement(requestList.get(0));
        uploadFile(ptoFileUploadArea,"src/main/resources/uploadData/ptoFile.pdf");
        clickElement(formCompleteBtn);
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

    public String isRequestCompleted(){
        clickOnPtoModule();
        clickElement(ptoCompletedSection);
        searchRequestInLocal();
        clickElement(requestList.get(0));
        return requestStatus.getText();
    }

}
