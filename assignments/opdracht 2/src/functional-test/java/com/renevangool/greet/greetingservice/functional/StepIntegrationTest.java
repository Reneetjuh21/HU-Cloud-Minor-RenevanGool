package com.renevangool.greet.greetingservice.functional;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepIntegrationTest extends SpringIntegrationTest{
    @When("^the client calls /greet$")
    public void the_client_issues_GET_version() throws Throwable{
        executeGet("http://localhost:8080/greet");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : "+
                latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("the client receives greeting saying {string}")
    public void the_client_receives_server_version_body(String greeting) throws Throwable {
        assertThat(latestResponse.getBody(), is(greeting));
    }
}
