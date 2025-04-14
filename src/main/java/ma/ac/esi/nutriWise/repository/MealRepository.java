package ma.ac.esi.nutriWise.repository;

import java.sql.*;
import java.util.*;
import ma.ac.esi.nutriWise.model.Meal;
import ma.ac.esi.nutriWise.model.Ingredient;
import ma.ac.esi.nutriWise.util.DBUtil;
public class MealRepository { 
    public List<Meal> getAllMeals() { 
        List<Meal> meals = new ArrayList<>(); 
        String mealQuery = "SELECT * FROM meals"; 
        String ingredientQuery = "SELECT * FROM ingredients WHERE meal_id = ?"; 
 
        try (Connection connection= DBUtil.getConnection(); 
             PreparedStatement mealStmt = connection.prepareStatement(mealQuery); 
             ResultSet mealRs = mealStmt.executeQuery()) { 
 
            while (mealRs.next()) { 
                int mealId = mealRs.getInt("id"); 
                String mealName = mealRs.getString("name"); 
                List<Ingredient> ingredients = new ArrayList<>(); 
 
                try (PreparedStatement ingStmt = connection.prepareStatement(ingredientQuery)) 
{ 
                    ingStmt.setInt(1, mealId); 
                    ResultSet ingRs = ingStmt.executeQuery(); 
                    while (ingRs.next()) { 
                        ingredients.add(new Ingredient( 
                                ingRs.getInt("id"), 
                                ingRs.getString("name"), 
                                ingRs.getInt("calories") 
                        )); 
                    } 
                } 
                meals.add(new Meal(mealId, mealName, ingredients)); 
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        return meals; 
    } 
} 