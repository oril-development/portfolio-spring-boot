package com.orilinc.portfolio.service;

import com.orilinc.portfolio.model.User;
import com.orilinc.portfolio.repositories.UserRepository;
import com.orilinc.portfolio.test.runner.ApplicationServiceRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest extends ApplicationServiceRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void before () {
        Mockito.reset(userRepository);
    }

    @After
    public void after() {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void findAllUsers() {
        // Arrange
        Iterable<User> userMockList = mock(Iterable.class);
        when(userRepository.findAll()).thenReturn(userMockList);
        // Act
        Iterable<User> userUnderTest = userService.findAllUsers();
        // Assert
        verify(userRepository).findAll();
    }

    @Test
    public void addUserTest() {
        // Arrange
        User user = mock(User.class);
        when(userRepository.save(user)).thenReturn(user);
        // Act
        User userUnderTest = userService.addUser(user);
        // Assert
        assertThat(userUnderTest, equalTo(user));
        verify(userRepository).save(user);
    }
}
