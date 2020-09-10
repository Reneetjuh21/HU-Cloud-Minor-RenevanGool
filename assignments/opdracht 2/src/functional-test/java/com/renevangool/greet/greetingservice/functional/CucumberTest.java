package com.renevangool.greet.greetingservice.functional;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/functional-test/resources")
public class CucumberTest extends SpringIntegrationTest {
}
