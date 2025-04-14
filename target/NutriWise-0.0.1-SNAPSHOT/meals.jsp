<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="ma.ac.esi.nutriWise.model.Meal" %>
<%@ page import="ma.ac.esi.nutriWise.model.Ingredient" %>

<% List<Meal> meals = (List<Meal>) request.getAttribute("meals"); %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Meal Plan - Gain Weight</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
      margin: 0;
      padding: 0;
    }

    .sidebar {
      width: 80px;
      background-color: #2c2c54;
      color: white;
      padding-top: 20px;
      height: 100vh;
      position: fixed;
    }

    .sidebar a {
      color: white;
      display: block;
      padding: 15px;
      text-decoration: none;
      text-align: center;
    }

    .sidebar a:hover {
      background-color: #57577d;
    }

    .flex-grow-1 {
      margin-left: 80px; /* same width as sidebar */
    }

    .topbar {
      background-color: #ffffff;
      border-bottom: 1px solid #dee2e6;
      padding: 15px 30px;
      position: sticky;
      top: 0;
      z-index: 1050;
    }

    .kcal-box {
      background-color: #fff;
      padding: 20px;
      border-radius: 15px;
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    .meal-item {
      border-radius: 10px;
      background-color: #fff;
      padding: 10px;
      margin-bottom: 10px;
      text-align: center;
      box-shadow: 0 1px 3px rgba(0,0,0,0.1);
    }

    .meal-item img {
      width: 40px;
      height: 40px;
      display: block;
      margin: 0 auto 5px;
    }

    .user-info span {
      font-weight: 500;
      font-size: 14px;
    }
  </style>
</head>
<body>
  <div class="d-flex">
    
    <!-- Sidebar -->
    <div class="sidebar d-flex flex-column align-items-center">
      <a href="#"><i class="bi bi-grid"></i></a>
      <a href="#"><i class="bi bi-search"></i></a>
      <a href="#"><i class="bi bi-people"></i></a>
      <a href="#"><i class="bi bi-star"></i></a>
      <a href="#"><i class="bi bi-calendar"></i></a>
      <a href="#"><i class="bi bi-check-square"></i></a>
      <a href="#"><i class="bi bi-chat"></i></a>
      <a href="#"><i class="bi bi-envelope"></i></a>
    </div>

    <!-- Main Content Area -->
    <div class="flex-grow-1">
      
      <!-- Topbar -->
      <div class="topbar d-flex justify-content-between align-items-center">
        <h4 class="m-0"><strong>Meal plans</strong> / Gain weight</h4>

        <div class="d-flex align-items-center gap-3">
          <div class="user-info d-flex align-items-center">
            <i class="bi bi-person-circle me-1 fs-5"></i>
            <span><%= session.getAttribute("login") != null ? session.getAttribute("login") : "Utilisateur" %></span>
          </div>
          <form action="./LogoutController" method="post" class="m-0">
            <button type="submit" class="btn btn-outline-danger btn-sm">
              <i class="bi bi-box-arrow-right"></i> Déconnexion
            </button>
          </form>
        </div>
      </div>

      <!-- Main Content -->
      <div class="container mt-4">
        <div class="row mb-4">
          <div class="col-md-6">
            <img src="https://via.placeholder.com/600x300" class="img-fluid rounded" alt="Meal Image">
          </div>
          <div class="col-md-6 kcal-box">
            <h3><strong>823 kcal</strong></h3>
            <div class="progress my-3">
              <div class="progress-bar" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <p><i class="bi bi-fire"></i> 283 kcal burned</p>
          </div>
        </div>

        <!-- Add Ingredient Button -->
        <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addIngredientModal">
          <i class="bi bi-plus-lg"></i> Ajouter un ingrédient
        </button>

        <!-- Add Ingredient Modal -->
        <div class="modal fade" id="addIngredientModal" tabindex="-1" aria-labelledby="addIngredientModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5>Ajouter un nouvel ingrédient</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <form action="IngredientController" method="post">
                  <div class="mb-3">
                    <label class="form-label">Repas</label>
                    <select class="form-control" name="mealId" required>
                      <% for (Meal meal : meals) { %>
                        <option value="<%= meal.getMealId() %>"><%= meal.getName() %></option>
                      <% } %>
                    </select>
                  </div>
                  <div class="mb-3">
                    <label class="form-label">Nom de l'ingrédient</label>
                    <input type="text" class="form-control" name="name" required>
                  </div>
                  <div class="mb-3">
                    <label class="form-label">Calories</label>
                    <input type="number" class="form-control" name="calories" required>
                  </div>
                  <button type="submit" class="btn btn-success">Ajouter</button>
                </form>
              </div>
            </div>
          </div>
        </div>

        <!-- Meals Section -->
        <div class="row">
          <% if (meals != null) {
            for (Meal meal : meals) { %>
              <div class="col-md-3 mb-4">
                <h5><%= meal.getName() %></h5>
                <% for (Ingredient ing : meal.getIngredients()) { %>
                  <div class="meal-item d-flex justify-content-between align-items-center">
                    <div>
                      <img src="img/<%= ing.getName().replaceAll(" ", "") %>.jpg" alt="<%= ing.getName() %>">
                      <%= ing.getName() %><br>
                      <small><%= ing.getCalories() %> kcal</small>
                    </div>
                    <div>
                      <!-- Edit Button -->
                      <button class="btn btn-sm btn-warning" data-bs-toggle="modal" data-bs-target="#editIngredientModal<%= ing.getId() %>">
                        <i class="bi bi-pencil"></i>
                      </button>
                      <!-- Delete Button -->
                      <form action="DeleteIngredientController" method="post" class="d-inline">
                        <input type="hidden" name="ingredientId" value="<%= ing.getId() %>">
                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer cet ingrédient ?');">
                          <i class="bi bi-trash"></i>
                        </button>
                      </form>
                    </div>
                  </div>

                  <!-- Edit Modal -->
                  <div class="modal fade" id="editIngredientModal<%= ing.getId() %>" tabindex="-1" aria-labelledby="editIngredientLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title">Modifier l'ingrédient</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                          <form action="EditIngredientController" method="post">
                            <input type="hidden" name="ingredientId" value="<%= ing.getId() %>">
                            <div class="mb-3">
                              <label class="form-label">Nom</label>
                              <input type="text" class="form-control" name="name" value="<%= ing.getName() %>" required>
                            </div>
                            <div class="mb-3">
                              <label class="form-label">Calories</label>
                              <input type="number" class="form-control" name="calories" value="<%= ing.getCalories() %>" required>
                            </div>
                            <button type="submit" class="btn btn-success">Modifier</button>
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>

                <% } %>
              </div>
            <% }
          } %>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
