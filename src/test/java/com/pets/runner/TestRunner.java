package com.pets.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber/cucumber-report.html",
                "json:target/cucumber/cucumber.json"},
        features = "src/test/resources/features",
        glue = "com.pets.stepdefinitions",
        stepNotifications = true,
        tags = "@test"
)
public class TestRunner {
}