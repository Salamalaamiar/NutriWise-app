package ma.ac.esi.nutriWise.TestServices;

import ma.ac.esi.nutriWise.service.UserService;
import ma.ac.esi.nutriWise.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestUserService {

    private UserRepository mockRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockRepository = mock(UserRepository.class);
        userService = new UserService(mockRepository); // il faut adapter UserService
    }

    @Test
    void testFindUserByCredentialsReturnsTrueWhenUserExists() {
        // Arrange
        String login = "user1";
        String password = "pass1";
        when(mockRepository.userExists(login, password)).thenReturn(true);

        // Act
        boolean result = userService.findUserByCredentials(login, password);

        // Assert
        assertTrue(result);
    }

    @Test
    void testFindUserByCredentialsReturnsFalseWhenUserDoesNotExist() {
        // Arrange
        String login = "user2";
        String password = "wrongpass";
        when(mockRepository.userExists(login, password)).thenReturn(false);

        // Act
        boolean result = userService.findUserByCredentials(login, password);

        // Assert
        assertFalse(result);
    }
}

