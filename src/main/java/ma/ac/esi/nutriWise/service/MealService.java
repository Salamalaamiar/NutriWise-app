package ma.ac.esi.nutriWise.service;


import ma.ac.esi.nutriWise.repository.MealRepository;
import ma.ac.esi.nutriWise.model.Meal;

import java.util.List;

public class MealService {
    private final MealRepository mealRepository;

    // Constructeur
    public MealService() {
        this.mealRepository = new MealRepository();
    }

   
    public List<Meal> getMeals() {
        return mealRepository.getAllMeals();
    }
}
