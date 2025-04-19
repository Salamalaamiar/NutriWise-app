package ma.ac.esi.nutriWise.Testcontroller;

import ma.ac.esi.nutriWise.controleur.LoginController;
import static org.mockito.Mockito.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.ac.esi.nutriWise.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.IOException;

public class LoginControllerTest {

    private LoginController controller;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        controller = new LoginController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testLoginSuccess() throws ServletException, IOException {
        when(request.getParameter("uname")).thenReturn("salma");
        when(request.getParameter("psw")).thenReturn("1234");

        try (MockedConstruction<UserService> mocked = mockConstruction(UserService.class,
                (mock, context) -> when(mock.findUserByCredentials("salma", "1234")).thenReturn(true))) {

            controller.doPost(request, response);

            // 👇 Vérifie que le mock session a bien reçu le setAttribute
            verify(session).setAttribute(eq("login"), eq("salma"));
            verify(response).sendRedirect("./MealController");
        }
    }

    @Test
    public void testLoginFailure() throws ServletException, IOException {
        when(request.getParameter("uname")).thenReturn("salma");
        when(request.getParameter("psw")).thenReturn("wrongpass");

        try (MockedConstruction<UserService> mocked = mockConstruction(UserService.class,
                (mock, context) -> when(mock.findUserByCredentials("salma", "wrongpass")).thenReturn(false))) {

            controller.doPost(request, response);

            verify(response).sendRedirect("error.html");
        }
    }
}
