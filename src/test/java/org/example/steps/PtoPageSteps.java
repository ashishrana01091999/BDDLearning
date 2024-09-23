package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.config.ConfigReader;
import org.example.pages.PtoPage;
import org.example.utility.GetSheetUtils;
import org.example.utility.TestSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class PtoPageSteps {
    private final WebDriver driver;
    private final PtoPage ptoPage;
    private final ConfigReader prop;

    public PtoPageSteps() {
        prop = new ConfigReader();
        this.driver = TestSetup.getDriver();
        ptoPage = new PtoPage(driver);
    }

    @Given("user is on speed platform")
    public void userIsOnSpeedPlatform() {
        driver.get(prop.getBaseUrl());
    }

    @Then("user should see the PTO form")
    public void userShouldSeeThePTOForm() {
        ptoPage.clickOnPtoModule();
        ptoPage.clickOnAddPtoForm();
    }

    @When("user fills out the PTO form")
    public void userFillsOutThePTOForm() throws GeneralSecurityException, IOException, InterruptedException {
        String range = "Sheet2!A5:H5"; // Specify the range of cells to fetch
        List<List<Object>> data = GetSheetUtils.getData(range);
        if (!data.isEmpty()) {
            List<Object> row = data.get(0);
            // Assuming the order of data is as follows:
            // Name, Address, Email, Utility Company, Yearly Production 1 Year,Yearly Production 3 Year, Phone,Comments
            ptoPage.fillFormDetails(
                    row.get(0).toString(),
                    row.get(1).toString(),
                    row.get(2).toString(),
                    row.get(3).toString(),
                    row.get(4).toString(),
                    row.get(5).toString(),
                    row.get(6).toString(),
                    row.get(7).toString()
            );
        }
    }

    @And("user submits the form")
    public void userSubmitsTheForm() {
        ptoPage.submitForm();
    }

    @And("operator will complete the process")
    public void operatorWillCompleteTheProcess() {
        ptoPage.searchRequestInLocal();
        ptoPage.acceptAndComplete();
        ptoPage.interconnectionInReviewComplete();
    }

    @Then("client will send the request for pto")
    public void clientWillSendTheRequestForPto()  {
        ptoPage.clickOnPtoModule();
        ptoPage.clientSendRequestForPto();
    }

    @Then("operator will complete the pto process")
    public void operatorWillCompleteThePtoProcess()  {
          ptoPage.ptoComplete();
    }

    @Then("client will check the request is completed")
    public void clientWillCheckTheRequestIsCompleted() {
        Assert.assertEquals(ptoPage.isRequestCompleted(),"Completed");
    }

    @Then("user clicks on pto module")
    public void userClicksOnPtoModule() {
        ptoPage.clickOnPtoModule();
    }

    @Then("wattmonk admin will accept and assign request to operator in pto new section")
    public void wattmonkAdminWillAcceptAndAssignRequestToOperatorInPtoNewSection() {
        ptoPage.searchRequestInLocal();
        ptoPage.acceptAndAssignToOperator(prop.getOperatorUsername());
    }
}
