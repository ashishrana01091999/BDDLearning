package org.example.steps;


import io.cucumber.java.en.Then;
import org.example.config.ConfigReader;
import org.example.pages.SurveyPage;
import org.example.utility.TestSetup;
import org.openqa.selenium.WebDriver;


public class SurveyPageSteps {
    private final WebDriver driver;
    private final SurveyPage surveyPage;
    private final ConfigReader prop;

    public SurveyPageSteps() {
        prop = new ConfigReader();
        this.driver = TestSetup.getDriver();
        surveyPage = new SurveyPage(driver);
    }

    @Then("user should see the survey add form")
    public void userShouldSeeTheSurveyAddForm() {
        surveyPage.clickOnSurveyModule();
        surveyPage.clickOnAddSurveyForm();
    }

    @Then("user fill out the survey request form and submit")
    public void userFillOutTheSurveyRequestFormAndSubmit() {
        surveyPage.fillFormDetails();
        surveyPage.submitForm();
    }
}


