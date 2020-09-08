package com.renevangool.greet.test;

import com.renevangool.greet.greetingservice.service.UserClient;
import com.renevangool.greet.greetingservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserClient userClient;

    @InjectMocks
    UserService userService;


    @Test
    public void testGetUsernameReturnsStringWithCorrectValueFromClient() {
        //Arrange
        String mockResponse = "Erik van Bonen";
        Mockito.when(userClient.getUser()).thenReturn(mockResponse);
        //Act
        var result = userService.getUserName();

        //Assert
        Mockito.verify(userClient, times(1)).getUser();
        assertThat(result).isNotEmpty();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Erik van Bonen");
    }

    @Test
    public void testDefaultUsernameReturnsStringWithCorrectValue() {
        //Arrange
        //Not needed

        //Act
        var result = userService.defaultUserName();

        //Assert
        assertThat(result).isNotEmpty();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Giel Beelen");
    }

}
