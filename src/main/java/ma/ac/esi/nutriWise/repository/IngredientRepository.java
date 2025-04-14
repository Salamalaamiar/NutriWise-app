package ma.ac.esi.nutriWise.repository;

import ma.ac.esi.nutriWise.model.Ingredient;
import ma.ac.esi.nutriWise.util.DBUtil;

import java.sql.*;

public class IngredientRepository {

    public boolean addIngredientToMeal(int mealId, Ingredient ingredient) {
        String query = "INSERT INTO ingredients (name, calories, meal_id) VALUES (?, ?, ?)";
        
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Paramétrage de la requête
            statement.setString(1, ingredient.getName());
            statement.setInt(2, ingredient.getCalories());
            statement.setInt(3, mealId);

            // Exécution de la requête
            int rowsAffected = statement.executeUpdate();

            // Retourne true si l'insertion a réussi (au moins une ligne insérée)
            return rowsAffected == 1;

        } catch (SQLException e) {
            // Affichage de l'erreur SQL pour faciliter le débogage
            e.printStackTrace();
            return false;
        }
    }

    public Ingredient getIngredientById(int ingredientId) {
        String query = "SELECT * FROM ingredients WHERE id = ?";
        
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, ingredientId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new Ingredient(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("calories")
                        
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateIngredient(Ingredient ingredient) {
        String query = "UPDATE ingredients SET name = ?, calories = ? WHERE id = ?";
        
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, ingredient.getName());
            statement.setInt(2, ingredient.getCalories());
            statement.setInt(3, ingredient.getId());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteIngredient(int ingredientId) {
        String query = "DELETE FROM ingredients WHERE id = ?";
        
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, ingredientId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
