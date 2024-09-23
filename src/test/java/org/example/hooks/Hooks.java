package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.config.ConfigReader;
import org.example.utility.GmailSendEmail;
import org.example.utility.TestSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentTest;


public class Hooks {

    private final ConfigReader prop =new ConfigReader();
    private static ExtentTest test;
    WebDriver  driver = TestSetup.getDriver();


    @AfterSuite
    public void tearDown() throws Exception {
      if(prop.sendEmailReport().equals("true")){
        GmailSendEmail.main();
        System.out.println("EMAIL SENT SUCCESSFULLY");
      }
    }

    private static int totalScenarios = 0;
    private static int passedScenarios = 0;
    private static int failedScenarios = 0;
    private static int skippedScenarios = 0;

    @Before
    public void beforeScenario(Scenario scenario) {
        totalScenarios++;
    }

    @After
    public void afterScenario(Scenario scenario) {
        switch (scenario.getStatus()) {
            case PASSED:
                passedScenarios++;
                break;
            case FAILED:
                failedScenarios++;
                break;
            case SKIPPED:
                skippedScenarios++;
                break;
        }

        // Print or log scenario counts
        System.out.println("Total Scenarios: " + totalScenarios);
        System.out.println("Passed Scenarios: " + passedScenarios);
        System.out.println("Failed Scenarios: " + failedScenarios);
        System.out.println("Skipped Scenarios: " + skippedScenarios);

        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Test");
        }
//        ExtentManager.getInstance().flush();
    }

}
