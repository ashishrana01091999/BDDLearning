package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/test/resources/features",
        tags = "@runModule",  // Run scenarios with the specified tag
        glue = {"org.example.steps", "org.example.hooks","org.example.listeners"} // Correct package names, without spaces
)

public class TestRunner extends AbstractTestNGCucumberTests {
//    @DataProvider
//    @Override
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

    @Test
    public void runCucumberTests() {
        // Your TestNG test method can be empty as Cucumber will handle the execution
    }
}
