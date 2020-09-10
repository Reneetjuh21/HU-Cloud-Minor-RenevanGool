package com.renevangool.greet.greetingservice.functional;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.Header;
import org.mockserver.verify.VerificationTimes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = {8090, 8099})
public class MockServerTest {
    private final ClientAndServer mockServer;

    public MockServerTest(ClientAndServer mockServer) {
        this.mockServer = mockServer;
    }

    @Test
    public void dummyShouldBeReturnedWhenUsingMockServer() {
        createExpectationForSuccessGetRequest();

        org.apache.http.HttpResponse response = hitTheServerWithGetRequest();

        assertEquals(200, response.getStatusLine().getStatusCode());

        verifyGetRequest();
    }

    private void createExpectationForSuccessGetRequest() {
        new MockServerClient("localhost", 8090)
                .when(
                    request()
                        .withMethod("GET")
                        .withPath("/dummy"))
                .respond(
                    response()
                        .withStatusCode(200)
                        .withHeaders(
                            new Header("Content-Type", "application/json; charset=utf-8"))
                        .withBody("{ status: 'success', " +
                            "data: { " +
                                "id: 4, " +
                                "employee_name: 'Cedric Kelly', " +
                                "employee_salary': 433060," +
                                "employee_age: 22, " +
                                "profile_image:'' }," +
                            "message: 'Successfully! Record has been fetched.' }")
                );
    }

    private org.apache.http.HttpResponse hitTheServerWithGetRequest() {
        String url = "http://127.0.0.1:8090/dummy";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        get.setHeader("Content-type", "application/json");
        org.apache.http.HttpResponse response = null;

        try {
            get.getRequestLine();
            response = client.execute(get);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    private void verifyGetRequest() {
        new MockServerClient("127.0.0.1", 8090).verify(
            request()
                .withMethod("GET")
                .withPath("/dummy"),
            VerificationTimes.exactly(1)
        );
    }
}
