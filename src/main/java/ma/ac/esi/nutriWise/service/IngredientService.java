package ma.ac.esi.nutriWise.service;

import ma.ac.esi.nutriWise.model.Ingredient;
import ma.ac.esi.nutriWise.repository.IngredientRepository;

public class IngredientService {
    private final IngredientRepository ingredientRepo;

    public IngredientService() {
        this.ingredientRepo = new IngredientRepository();
    }

    public boolean addIngredientToMeal(int mealId, String name, int calories) {
        Ingredient ingredient = new Ingredient(name, calories);
        return ingredientRepo.addIngredientToMeal(mealId, ingredient); // Ajout d'un ingrédient au repas
    }
    
    public boolean updateIngredient(int ingredientId, String name, int calories) {
        Ingredient ingredient = ingredientRepo.getIngredientById(ingredientId);
        if (ingredient != null) {
            ingredient.setName(name);
            ingredient.setCalories(calories);
            return ingredientRepo.updateIngredient(ingredient);
        }
        return false; // Si l'ingrédient n'existe pas, on retourne false
    }
    
    public boolean deleteIngredient(int ingredientId) {
        return ingredientRepo.deleteIngredient(ingredientId); // Suppression de l'ingrédient
    }
}
