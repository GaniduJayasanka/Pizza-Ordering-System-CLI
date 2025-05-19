package PizzaOrderingSystem;


import java.util.ArrayList;
import java.util.List;


public class User {
    private String username;
    private String password; // Stores the user's password
    private List<Pizza> favoritePizzas = new ArrayList<>();
    private int loyaltyPoints;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loyaltyPoints = 0;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Pizza> getFavoritePizzas() {
        return favoritePizzas;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    // Add favorite pizza
    public void addFavoritePizza(Pizza pizza) {
        favoritePizzas.add(pizza);
    }
    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    @Override
    public String toString() {
        return "User: " + username + "\nFavorites: " + favoritePizzas + "\nLoyalty Points: " + loyaltyPoints;
    }
}
