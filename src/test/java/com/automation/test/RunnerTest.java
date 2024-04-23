package com.automation.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features = "src/test/resources",
        glue = {"com/automation/test"},
//        tags = {"@Smoke"},
        monochrome = true,
        dryRun = false,
        snippets = SnippetType.UNDERSCORE,
        plugin = {"html:target/cucumber-html-report","json:target/cucumber.json"}
        )

public class RunnerTest {

}
