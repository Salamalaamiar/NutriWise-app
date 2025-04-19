package ma.ac.esi.nutriWise.Testrepository;


import ma.ac.esi.nutriWise.repository.MealRepository;

import ma.ac.esi.nutriWise.model.Meal;
import ma.ac.esi.nutriWise.model.Ingredient;
import ma.ac.esi.nutriWise.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestMealRepository {

    private Connection mockConnection;
    private PreparedStatement mockMealStmt;
    private PreparedStatement mockIngStmt;
    private ResultSet mockMealRs;
    private ResultSet mockIngRs;

    private MealRepository mealRepository;

    @BeforeEach
    public void setUp() throws Exception {
        // Mock JDBC
        mockConnection = mock(Connection.class);
        mockMealStmt = mock(PreparedStatement.class);
        mockIngStmt = mock(PreparedStatement.class);
        mockMealRs = mock(ResultSet.class);
        mockIngRs = mock(ResultSet.class);

        // Override static DBUtil.getConnection() using spy or mock
        mockStatic(DBUtil.class);
        when(DBUtil.getConnection()).thenReturn(mockConnection);

        // Setup queries
        when(mockConnection.prepareStatement("SELECT * FROM meals")).thenReturn(mockMealStmt);
        when(mockMealStmt.executeQuery()).thenReturn(mockMealRs);

        when(mockConnection.prepareStatement("SELECT * FROM ingredients WHERE meal_id = ?")).thenReturn(mockIngStmt);
        when(mockIngStmt.executeQuery()).thenReturn(mockIngRs);

        mealRepository = new MealRepository();
    }

    @Test
    public void testGetAllMeals_ReturnsMealsWithIngredients() throws Exception {
        // Simuler un repas
        when(mockMealRs.next()).thenReturn(true, false); // un seul repas
        when(mockMealRs.getInt("id")).thenReturn(1);
        when(mockMealRs.getString("name")).thenReturn("Salade");

        // Simuler les ingrédients
        when(mockIngRs.next()).thenReturn(true, false);
        when(mockIngRs.getInt("id")).thenReturn(101);
        when(mockIngRs.getString("name")).thenReturn("Tomate");
        when(mockIngRs.getInt("calories")).thenReturn(20);

        List<Meal> meals = mealRepository.getAllMeals();

        assertNotNull(meals);
        assertEquals(1, meals.size());

        Meal meal = meals.get(0);
        assertEquals("Salade", meal.getName());
        assertEquals(1, meal.getIngredients().size());

        Ingredient ing = meal.getIngredients().get(0);
        assertEquals("Tomate", ing.getName());
        assertEquals(20, ing.getCalories());
    }
}

