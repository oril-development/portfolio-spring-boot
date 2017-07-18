package com.orilinc.portfolio.controller;

import com.orilinc.portfolio.model.User;
import com.orilinc.portfolio.service.UserService;
import com.orilinc.portfolio.test.runner.ApplicationControllerRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ApplicationControllerRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Before
    public void before() {
        beforeByResource(userController);
        Mockito.reset(userService);
    }

    @After
    public void after() {
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void addNewUser() throws Exception {
        // Arrange
        User user = mock(User.class);
        when(userService.addUser(user)).thenReturn(user);
        // Act+Assert
        mvc.perform(get("/user/add?name="+user.getName()+"&email="+user.getEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userService).addUser(userCaptor.capture());
    }

    @Test
    public void getAllUser() throws Exception {
        // Act+Assert
        mvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(userService).findAllUsers();
    }
}
