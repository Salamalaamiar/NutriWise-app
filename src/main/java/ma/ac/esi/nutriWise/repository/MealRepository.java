package ma.ac.esi.nutriWise.repository;

import java.sql.*;
import java.util.*;
import ma.ac.esi.nutriWise.model.Meal;
import ma.ac.esi.nutriWise.model.Ingredient;
import ma.ac.esi.nutriWise.util.DBUtil;

public class MealRepository {

    // Méthode pour récupérer tous les repas de la base de données
    public List<Meal> getAllMeals() {
        // Liste pour stocker les repas récupérés
        List<Meal> meals = new ArrayList<>();
        
        // Requête SQL pour récupérer tous les repas
        String mealQuery = "SELECT * FROM meals";
        
        // Requête SQL pour récupérer les ingrédients d'un repas spécifique
        String ingredientQuery = "SELECT * FROM ingredients WHERE meal_id = ?";

        // Bloc try-with-resources pour garantir que la connexion à la base de données est fermée automatiquement
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement mealStmt = connection.prepareStatement(mealQuery);
             ResultSet mealRs = mealStmt.executeQuery()) {

            // Parcours du résultat de la requête des repas
            while (mealRs.next()) {
                // Récupérer l'id et le nom du repas
                int mealId = mealRs.getInt("id");
                String mealName = mealRs.getString("name");

                // Liste pour stocker les ingrédients du repas actuel
                List<Ingredient> ingredients = new ArrayList<>();

                // Récupération des ingrédients du repas en utilisant la requête ingredientQuery
                try (PreparedStatement ingStmt = connection.prepareStatement(ingredientQuery)) {
                    // On définit l'id du repas dans la requête pour récupérer les ingrédients
                    ingStmt.setInt(1, mealId);
                    ResultSet ingRs = ingStmt.executeQuery();

                    // Parcours du résultat des ingrédients
                    while (ingRs.next()) {
                        // On ajoute chaque ingrédient à la liste d'ingrédients
                        ingredients.add(new Ingredient(
                                ingRs.getInt("id"),
                                ingRs.getString("name"),
                                ingRs.getInt("calories")
                        ));
                    }
                }

                // Ajout du repas avec sa liste d'ingrédients à la liste des repas
                meals.add(new Meal(mealId, mealName, ingredients));
            }
        } catch (SQLException e) {
            // En cas d'exception, on affiche l'erreur
            e.printStackTrace();
        }

        // Retourner la liste de tous les repas récupérés
        return meals;
    }
}
