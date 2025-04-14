package ma.ac.esi.nutriWise.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ac.esi.nutriWise.service.IngredientService;

import java.io.IOException;

/**
 * Servlet implementation class IngredientController
 */
@WebServlet("/IngredientController")
public class IngredientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredientController() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    IngredientService ingredientService = new IngredientService();
	    int mealId = Integer.parseInt(request.getParameter("mealId"));
	    String ingredientName = request.getParameter("name");
	    int calories = Integer.parseInt(request.getParameter("calories"));
	    boolean success = ingredientService.addIngredientToMeal(mealId, ingredientName, calories);
	    
	    if (success) {
	        response.sendRedirect("MealController");
	    } else {
	        response.sendRedirect("error.html");
	    }
	}
}