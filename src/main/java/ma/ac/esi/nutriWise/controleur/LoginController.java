package ma.ac.esi.nutriWise.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.ac.esi.nutriWise.service.UserService;

import java.io.IOException;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private UserService userService;

    public LoginController() {
        super();
        this.userService = new UserService(); // Initialisation par défaut
    }

    // Méthode pour les tests (injection de mock)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // Pour les tests (injection de mock)
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login=request.getParameter("uname");
		String password =request.getParameter("psw");
		UserService userService= new UserService();
		if(userService.findUserByCredentials(login, password)) {
			HttpSession session = request.getSession(); 
	         session.setAttribute("login", login); 
	         response.sendRedirect("./MealController"); 
		}
		else {
			response.sendRedirect("error.html");
		}
		
		
	}

	
}
