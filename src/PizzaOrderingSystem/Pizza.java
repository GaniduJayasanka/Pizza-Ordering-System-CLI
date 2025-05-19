package PizzaOrderingSystem;

import java.util.ArrayList;
import java.util.List;

// The Pizza class represents a customizable pizza with attributes.
public class Pizza {
    private String name; // Name of the pizza
    private List<String> type; // Pizza types 
    private String size;// Size of the Pizza
    private String crust;// Type of crust
    private String sauce;// Type of sauce used on the pizza
    private List<String> toppings; // List of toppings added to the pizza
    private double basePrice; // Base price of the pizza

    // Private constructor to create a pizza object using the PizzaBuilder
    private Pizza(PizzaBuilder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.size = builder.size;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.basePrice = builder.basePrice;
    }

    // Getter for pizza type
    public List<String> getType() {
        return type;
    }

    // Getter for pizza size
    public String getSize() {
        return size;
    }

    // Getter for base price (optional, if needed elsewhere)
    public double getBasePrice() {
        return basePrice;
    }

    // Returns a string representation of the pizza with all its attributes
    @Override
    public String toString() {
        return "Pizza Name: " + name + ", Type: " + type + " [Size: " + size + ", Crust: " + crust + 
                ", Sauce: " + sauce + ", Toppings: " + toppings + "]";
    }

    //The PizzaBuilder class provides a flexible way to construct pizza objects
    public static class PizzaBuilder {
        private String name;
        private List<String> type = new ArrayList<>();
        private String size;
        private String crust;
        private String sauce;
        private List<String> toppings = new ArrayList<>();
        private double basePrice;

        // Setters
        
        public PizzaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PizzaBuilder addType(String type) {
            this.type.add(type);
            return this;
        }

        public PizzaBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        public PizzaBuilder setBasePrice(double basePrice) {
            this.basePrice = basePrice;
            return this;
        }
        
        // Builds and returns a pizza object using the specified attributes.
        public Pizza build() {
            return new Pizza(this);
        }
    }
}
