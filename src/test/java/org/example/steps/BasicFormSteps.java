package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.example.pages.BasicFormPage;
import org.example.utility.GetSheetUtils;
import org.example.utility.TestSetup;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;


public class BasicFormSteps {

    private final WebDriver driver;
    private final BasicFormPage basicFormPage;

    public BasicFormSteps() {
        this.driver = TestSetup.getDriver();
        basicFormPage = new BasicFormPage(driver);
    }

    @Given("I open the PTO form")
    public void iOpenThePTOForm() {
        String localFilePath = Paths.get("src/main/resources/inputform.html").toUri().toString();
        driver.get(localFilePath);
        driver.manage().window().maximize();
    }

    @When("I fill the form with data from Google Sheets")
    public void iFillTheFormWithDataFromGoogleSheets() throws IOException, GeneralSecurityException {
        String range = "Sheet1!A5:G5"; // Specify the range of cells to fetch
        List<List<Object>> data = GetSheetUtils.getData(range);

        if (!data.isEmpty()) {
            List<Object> row = data.get(0);
            // Assuming the order of data is as follows:
            // Name, Address, Email, Phone, Utility Company, Yearly Production 1 Year, Comments
            basicFormPage.fillForm(
                    row.get(0).toString(),
                    row.get(1).toString(),
                    row.get(2).toString(),
                    row.get(3).toString(),
                    row.get(4).toString(),
                    row.get(5).toString(),
                    row.get(6).toString()
            );
        }
    }

    @When("I upload the required documents")
    public void iUploadTheRequiredDocuments() {
        basicFormPage.uploadSystemDesignDocument("src/main/resources/uploadData/systemDesign.pdf");
        basicFormPage.uploadUtilityBills("src/main/resources/uploadData/utilityBill.pdf");
    }

    @Then("I submit the form")
    public void iSubmitTheForm() throws InterruptedException {
        basicFormPage.submitForm();
    }

    @Then("I verify the form submission")
    public void iVerifyTheFormSubmission() {
        String text=basicFormPage.getPopupText();
        System.out.println(text);
    }

    @After
    public void tearDown() {
        TestSetup.quitDriver();
    }

    @Then("clear the google sheets in range {string}")
    public void clearTheGoogleSheetsInRange(String range) throws GeneralSecurityException, IOException {
        GetSheetUtils.clearGoogleSheet(range);
    }

    @Then("write some data into google sheets")
    public void writeSomeDataIntoGoogleSheets() throws Exception {
        String range = "Sheet1!A2";
//        List<List<Object>> values = Arrays.asList(
//                Arrays.asList("A", "B", "C", "D"),
//                Arrays.asList("E", "F", "G", "H")
//        );
//        GetSheetUtils.writeData(range, values);
        GetSheetUtils.writeData(range,TestSetup.getCurrentDateTime());
    }

}
