package com.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/Features",
        tags = {" @sampletest"},
        plugin = {"pretty", "html:target/cucmber_reports"},
        glue = {"com"})
public class RunnerSuite {

}
