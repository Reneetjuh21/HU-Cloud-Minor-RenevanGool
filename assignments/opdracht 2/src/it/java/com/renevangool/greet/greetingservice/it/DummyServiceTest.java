package com.renevangool.greet.greetingservice.it;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DummyServiceTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void dummyShouldReturnDummyEmployee() throws Exception {
        var path = "http://localhost:" + port + "/dummy";
        var response = this.restTemplate.getForObject(path, String.class);
        JSONObject object = new JSONObject(response);
        assertThat(object.get("employee_name")).isInstanceOf(String.class);
        assertThat(object.getString("employee_name")).contains("Cedric Kelly");
        assertThat(object.get("employee_salary")).isInstanceOf(String.class);
        assertThat(object.getString("employee_salary")).contains("433060");
        assertThat(object.get("employee_age")).isInstanceOf(Integer.class);
        assertThat(object.getInt("employee_age")).isEqualTo(22);
        assertThat(object.get("id")).isInstanceOf(Integer.class);
        assertThat(object.getLong("id")).isEqualTo(4);
    }
}
