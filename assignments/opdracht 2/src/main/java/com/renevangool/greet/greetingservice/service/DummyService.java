package com.renevangool.greet.greetingservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renevangool.greet.greetingservice.model.DummyEmployee;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DummyService {
    private RestTemplate restTemplate;
    private String uri;

    public DummyService() {
        restTemplate = new RestTemplate();
        uri = "http://dummy.restapiexample.com/api/v1";
    }

    public DummyEmployee getEmployee() throws Exception {
        String str  = restTemplate.getForObject(uri + "/employee/4", String.class);
        JSONObject json = new JSONObject(str);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json.getJSONObject("data").toString(), DummyEmployee.class);
    }
}
