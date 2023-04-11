package org.example.ui.tests.registration;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/org/example/ui/feature/registration/LimitedVersion.feature",
        glue = "org\\example\\ui\\stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"}, monochrome = true)
public class LimitedVersionTests {
}
