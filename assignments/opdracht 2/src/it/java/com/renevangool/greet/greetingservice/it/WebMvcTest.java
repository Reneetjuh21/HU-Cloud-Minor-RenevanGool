package com.renevangool.greet.greetingservice.it;

import com.renevangool.greet.greetingservice.controller.GreetingController;
import com.renevangool.greet.greetingservice.service.IGreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest(GreetingController.class)
public class WebMvcTest {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private IGreetingService greetingService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(greetingService.getGreeting()).thenReturn("Hello, Giel Beelen");
        this.mockMvc.perform(get("/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Giel Beelen")));
    }
}
