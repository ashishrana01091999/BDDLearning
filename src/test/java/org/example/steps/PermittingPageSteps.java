package org.example.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.config.ConfigReader;
import org.example.pages.PermittingPage;

import org.example.utility.TestSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class PermittingPageSteps {
    private final WebDriver driver;
    private final PermittingPage permittingPage;
    private final ConfigReader prop;

    public PermittingPageSteps() {
        prop = new ConfigReader();
        this.driver = TestSetup.getDriver();
        permittingPage = new PermittingPage(driver);
    }

    @Then("user should see the Permitting form")
    public void userShouldSeeThePermittingForm() {
        permittingPage.clickOnPermittingModule();
        permittingPage.clickOnAddPermittingForm();
    }

    @Then("user will fill the form and submit the form")
    public void userWillFillTheFormAndSubmitTheForm() {
        permittingPage.fillFormDetails();
        permittingPage.submitForm();
    }

    @Then("user clicks on permitting module")
    public void userClicksOnPermittingModule() {
        permittingPage.clickOnPermittingModule();
    }

    @Then("wattmonk admin will accept and assign request to operator in permitting new section")
    public void wattmonkAdminWillAcceptAndAssignRequestToOperatorInPermittingNewSection() {
        permittingPage.searchRequestInLocal();
        permittingPage.acceptAndAssignToOperator(prop.getOperatorUsername());
    }

    @Then("operator will complete the pending permitting process")
    public void operatorWillCompleteThePendingPermittingProcess() {
        permittingPage.searchRequestInLocal();
        permittingPage.operatorUploadFileAndSubmitForm();
        permittingPage.operatorWillReviewAndComplete();
    }

    @Then("operator will submit request for final inspection")
    public void operatorWillSubmitRequestForFinalInspection() {
        permittingPage.operatorSubmitForFinalInspection();
    }

    @And("operator will complete the final inspection for permitting")
    public void operatorWillCompleteTheFinalInspectionForPermitting() {
        permittingPage.operatorCompleteFinalInspectionProcess();
    }

    @Then("client will check permitting request is completed")
    public void clientWillCheckPermittingRequestIsCompleted() {
        Assert.assertEquals(permittingPage.isRequestCompleted(),"Completed");
    }

}


