package ma.ac.esi.nutriWise.model;

import java.util.List;

public class Meal {
	private int mealId;
	private String name; 
	private List<Ingredient> ingredients;
	public String getName() {
		return name;
	}
	public int getMealId() {
        return mealId;
    }
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Meal(int mealId, String name, List<Ingredient> ingredients) {
		super();
		this.mealId = mealId;
		this.name = name;
		this.ingredients = ingredients;
	}
	
	

}
