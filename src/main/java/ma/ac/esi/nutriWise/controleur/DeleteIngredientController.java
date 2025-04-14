package ma.ac.esi.nutriWise.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ac.esi.nutriWise.service.IngredientService;

import java.io.IOException;

/**
 * Servlet implementation class DeleteIngredientController
 */
@WebServlet("/DeleteIngredientController")
public class DeleteIngredientController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));

        IngredientService ingredientService = new IngredientService();
        boolean success = ingredientService.deleteIngredient(ingredientId);

        if (success) {
            response.sendRedirect("MealController"); // Rafraîchir la page
        } else {
            response.sendRedirect("error.html");
        }
    }
}
