package PizzaOrderingSystem;

import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Abstract class representing the base handler in the order processing chain
public abstract class OrderHandler {
    protected OrderHandler nextHandler; // Next handler in the chain

    // Sets the next handler in the chain
    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    //Abstract method for processing the order, to be implemented by concrete handler
    public abstract void handleOrder(Pizza.PizzaBuilder builder);

    
 // Text Style Codes
    public static final String Bold_Weight = "\033[1m";
    public static final String Reset_Color = "\033[0m";  
    
    //Text Color Codes
    public static final String Bright_White   = "\033[97m";
    public static final String Bright_Cyan    = "\033[96m";
    public static final String Bright_Yellow  = "\033[93m";
    public static final String Bright_Red     = "\033[91m";
    public static final String Bright_Magenta = "\033[95m";
    public static final String Bright_Blue    = "\033[94m";
    public static final String Yellow_color   = "\033[33m";
    public static final String Green_color    = "\033[32m";
    
}

// Concrete handler that processes the pizza type and size selection
class TypeHandler extends OrderHandler {
    private static final Map<String, Integer> pizzaPriceMap = new HashMap<>();
    private int selectedPrice; // Store the selected price for pizza

    static {
        // Initializing the pizza price map for different types and sizes
        pizzaPriceMap.put("Deval Chiken Pizza_Large Pizza", 1450);
        pizzaPriceMap.put("Chicken Pizza_Large Pizza", 1350);
        pizzaPriceMap.put("Sosegus Pizza_Large Pizza", 1250);
        pizzaPriceMap.put("Deval Chiken Pizza_Pan Pizza", 1050);
        pizzaPriceMap.put("Chicken Pizza_Pan Pizza", 950);
        pizzaPriceMap.put("Sosegus Pizza_Pan Pizza", 850);
    }

    // Returns the selected price for the pizza
    public int getSelectedPrice() {
        return selectedPrice;
    }

    // Processes the order by allowing the user to select pizza type and size
    @Override
    public void handleOrder(Pizza.PizzaBuilder builder) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Allow user to choose pizza type
        String pizzaType = null;
        while (pizzaType == null) {
            System.out.println(Bold_Weight + Bright_White +"[Pizza Types -> Devil Chicken Pizza / Chili Chicken Pizza / Sausage Pizza ]");
            String[] pizzaTypes = {"Deval Chiken Pizza", "Chicken Pizza", "Sosegus Pizza"};
            for (int i = 0; i < pizzaTypes.length; i++) {
                System.out.println((i + 1) + ". " + pizzaTypes[i]);
                
            }
            System.out.println("");
            System.out.print(Bold_Weight + Green_color + "Choose Pizza Type: " + Bright_Yellow);
            String input = scanner.nextLine(); // Read input as string
            

            // Validate the pizza type selection
            if (isValidNumber(input)) {
                int pizzaChoice = Integer.parseInt(input);
                if (pizzaChoice >= 1 && pizzaChoice <= pizzaTypes.length) {
                    pizzaType = pizzaTypes[pizzaChoice - 1]; // Valid input
                } else {
                    System.out.println(Bright_Red + Bold_Weight +"Invalid choice! Please try again.");
                }
            } else {
                System.out.println(Bright_Red + Bold_Weight +"Invalid input! Please enter a number.");
            }
        }

        // Step 2: Allow user to choose pizza size
        String pizzaSize = null;
        while (pizzaSize == null) {
        	System.out.println("");
            System.out.println(Bold_Weight + Bright_White +"[Pizza Size -> Large / Pan]");
            String[] pizzaSizes = {"Large Pizza", "Pan Pizza"};
            for (int i = 0; i < pizzaSizes.length; i++) {
                System.out.println((i + 1) + ". " + pizzaSizes[i]);
                
            }
            System.out.println("");
            System.out.print(Bold_Weight + Green_color + "Choose Pizza Size: " + Bright_Yellow);
            String input = scanner.nextLine(); // Read input as string

            // Validate the pizza size selection
            if (isValidNumber(input)) {
                int sizeChoice = Integer.parseInt(input);
                if (sizeChoice >= 1 && sizeChoice <= pizzaSizes.length) {
                    pizzaSize = pizzaSizes[sizeChoice - 1]; // Valid input
                } else {
                    System.out.println(Bright_Red + Bold_Weight +"Invalid choice! Please try again.");
                }
            } else {
                System.out.println(Bright_Red + Bold_Weight +"Invalid input! Please enter a number.");
            }
        }

        // Add the selected pizza type and size to the builder
        builder.addType(pizzaType);
        builder.setSize(pizzaSize);

        // Step 3: Calculate and display the price for the selected combination
        String key = pizzaType + "_" + pizzaSize; // Construct the key
        if (pizzaPriceMap.containsKey(key)) {
            selectedPrice = pizzaPriceMap.get(key);
            System.out.println(""+Reset_Color);
            System.out.println(Bold_Weight + Bright_White +"Selected Price for " + key + Bright_Cyan + ": Rs." + selectedPrice);
            System.out.println("");
        } else {
            System.out.println(Bright_Red + Bold_Weight +"Error: Price not found for the selected combination.");
            selectedPrice = 0; // Default to 0 if price not found
        }

        // Continue to the next handler in the chain
        if (nextHandler != null) {
            nextHandler.handleOrder(builder);
        }
    }

    
     // Helper method to validate if a string is a valid number.
     
    private boolean isValidNumber(String input) {
        try {
            Integer.parseInt(input); // Attempt to parse as integer
            return true;
        } catch (NumberFormatException e) {
            return false; // Input is not a valid number
        }
    }
}

// CrustHandler processes crust type selection for the pizza order
class CrustHandler extends OrderHandler {
    @Override
    public void handleOrder(Pizza.PizzaBuilder builder) {
        Scanner scanner = new Scanner(System.in);
        String crust = null;

        // Step: Allow user to select crust type
        while (crust == null) {
        	
            System.out.println(Bold_Weight + Bright_White + "[Crust Types -> Thin / Thick / Stuffed]");
            System.out.println("");
            System.out.print(Bold_Weight + Green_color +"Enter Crust Type: " + Bright_Yellow); // Input prompt on the same line
            String input = scanner.nextLine().trim(); // Read input and trim excess whitespace

            // Validate crust input
            if (isValidCrust(input)) {
                crust = input; // Valid crust choice
            } else {
                System.out.println(Bold_Weight + Bright_Red + "Invalid crust choice! Please choose a valid crust (Thin / Thick / Stuffed)." + Reset_Color);
            }
        }

        builder.setCrust(crust); // Set the valid crust
        if (nextHandler != null) nextHandler.handleOrder(builder);
    }

    // Helper method to validate if the crust is one of the valid options
    private boolean isValidCrust(String input) {
        String[] validCrusts = {"Thin", "Thick", "Stuffed"};
        for (String validCrust : validCrusts) {
            if (validCrust.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false; // Invalid crust input
    }
}

// Handles Sauce selection for the pizza
class SauceHandler extends OrderHandler {
    @Override
    public void handleOrder(Pizza.PizzaBuilder builder) {
        Scanner scanner = new Scanner(System.in);
        String sauce = null;
     
        // Continuously prompt for a valid saucce until one is chosen
        while (sauce == null) {
        	System.out.println("");
            System.out.println(Bold_Weight + Bright_White + "[Sauce Types -> Tomato / BBQ / White]");
            System.out.println("");
            System.out.print(Bold_Weight + Green_color +"Enter Sauce Type: " + Bright_Yellow); // Input prompt on the same line
            String input = scanner.nextLine().trim(); // Read input and trim excess whitespace

            // Validate if the selected sauce is one of the valid options
            if (isValidSauce(input)) {
                sauce = input; // Valid sauce choice
            } else {
                System.out.println(Bold_Weight + Bright_Red + "Invalid sauce choice! Please choose a valid sauce (Tomato / BBQ / White)." + Reset_Color);
            }
        }

        builder.setSauce(sauce); // Set the valid sauce
        if (nextHandler != null) nextHandler.handleOrder(builder);
    }

    // Helper method to validate if the sauce is one of the valid options
    private boolean isValidSauce(String input) {
        String[] validSauces = {"Tomato", "BBQ", "White"};
        for (String validSauce : validSauces) {
            if (validSauce.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false; // Invalid sauce input
    }
}

class ToppingHandler extends OrderHandler {
    private double totalToppingCost = 0; // To store the total cost of selected toppings

    @Override
    public void handleOrder(Pizza.PizzaBuilder builder) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Ask if the user wants to add toppings
        String addToppingsChoice = null;
        while (addToppingsChoice == null) {
        	System.out.println("");
            System.out.print(Bold_Weight + Bright_Blue + "Would you like to add toppings? (yes/no): " + Bright_Yellow); // Same-line input prompt
            String input = scanner.nextLine().trim(); // Read input and trim excess whitespace

            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no")) {
                addToppingsChoice = input.toLowerCase();
            } else {
                System.out.println(Bold_Weight + Bright_Red + "Invalid choice! Please enter 'yes' or 'no'." + Reset_Color);
            }
        }

        // If the user chooses "no", skip the topping selection
        if (addToppingsChoice.equals("no")) {
            System.out.println(Bold_Weight + Green_color + "No toppings added." + Reset_Color);
            if (nextHandler != null) nextHandler.handleOrder(builder);
            return; // Exit the method
        }

        // Step 2: Display available toppings
        System.out.println("");
        System.out.println(Bold_Weight + Bright_White + "Select Toppings (Enter numbers separated by commas, e.g., 1,3): ");
        String[] availableToppings = {"Extra Cheese", "Extra Mushrooms", "Extra Pepperoni", "Extra Chicken"};
        double[] toppingPrices = {50.0, 30.0, 70.0, 100.0}; // Prices corresponding to toppings

        for (int i = 0; i < availableToppings.length; i++) {
            System.out.println((i + 1) + ". " + availableToppings[i] + " (Rs." + toppingPrices[i] + ")");
        }

        // Step 3: Validate and process user input for topping selection
        boolean validInput = false;
        while (!validInput) {
            System.out.println("");
            System.out.print(Bold_Weight + Green_color + "Select Extra Topping Type: " + Bright_Yellow);
            String toppingChoices = scanner.nextLine();
            String[] toppingIndexes = toppingChoices.split(",");

            // Assume input is valid until proven otherwise
            validInput = true;

            for (String index : toppingIndexes) {
                try {
                    int toppingIndex = Integer.parseInt(index.trim()) - 1; // Convert to zero-based index
                    if (toppingIndex >= 0 && toppingIndex < availableToppings.length) {
                        builder.addTopping(availableToppings[toppingIndex]); // Add topping to builder
                        totalToppingCost += toppingPrices[toppingIndex]; // Add cost to total
                    } else {
                        System.out.println(Bold_Weight + Bright_Red + "Invalid topping choice: " + index + Reset_Color);
                        validInput = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Bold_Weight + Bright_Red + "Invalid input: " + index + ". Please enter numbers only." + Reset_Color);
                    validInput = false;
                }
            }

            if (!validInput) {
                System.out.println(Bold_Weight + Bright_Red + "Please enter valid topping numbers separated by commas (e.g., 1,3)." + Reset_Color);
            }
        }

        // Proceed to the next handler if available
        if (nextHandler != null) nextHandler.handleOrder(builder);
    }

    public double getTotalToppingCost() {
        return totalToppingCost;
    }
}






class ConfirmationHandler extends OrderHandler {
    private User activeUser; // Store the active user

    // Constructor to pass the active user
    public ConfirmationHandler(User activeUser) {
        this.activeUser = activeUser;
    }
    /**
     * Validates that the given name contains only alphabetical characters.
     * 
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+"); // Allows letters and spaces only
    }


    @Override
    public void handleOrder(Pizza.PizzaBuilder builder) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Ask if the user wants to name the pizza
        String nameChoice = null;
        while (nameChoice == null) {
        	System.out.println("");
            System.out.print(Bold_Weight + Bright_Blue + "Would you like to name your custom pizza? (yes/no): " + Bright_Yellow); // Same-line input prompt
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("no")) {
                nameChoice = input;
            } else {
                System.out.println(Bold_Weight + Bright_Red + "Invalid choice! Please enter 'yes' or 'no'." + Reset_Color);
            }
        }

     // Step 2: Name the pizza if the user chose "yes"
        if ("yes".equalsIgnoreCase(nameChoice)) {
            System.out.println("");
            String pizzaName = null;

            // Validation loop for pizza name
            while (pizzaName == null || !isValidName(pizzaName)) {
                System.out.print(Bold_Weight + Green_color + "Enter a name for your pizza: " + Bright_Yellow);
                pizzaName = scanner.nextLine().trim();

                if (!isValidName(pizzaName)) {
                    System.out.println(Bold_Weight + Bright_Red + "Invalid name! Please enter only alphabetical characters for the pizza name." + Reset_Color);
                }
        
            }

            builder.setName(pizzaName);
        } else {
            builder.setName("Custom Pizza");
        }

        

        Pizza pizza = builder.build();
        System.out.println("");
        System.out.println(Bold_Weight + Bright_White + "Pizza created: " + Bright_Cyan + pizza + Reset_Color);

        // Step 3: Ask if the user wants to save the pizza as a favorite
        String saveChoice = null;
        while (saveChoice == null) {
        	System.out.println("");
            System.out.print(Bold_Weight + Green_color + "Would you like to save this pizza as your favorite? (yes/no): " + Bright_Yellow);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("no")) {
                saveChoice = input;
            } else {
                System.out.println(Bold_Weight + Bright_Red + "Invalid choice! Please enter 'yes' or 'no'." + Reset_Color);
            }
        }

        // Step 4: Save the pizza as a favorite if the user chose "yes"
        if ("yes".equals(saveChoice)) {
            if (activeUser != null) {
                activeUser.addFavoritePizza(pizza); // Link to active user
                System.out.println("");
                System.out.println(Bold_Weight + Bright_White + "Your favorite pizza has been saved: " +Bright_Cyan + pizza + Reset_Color);
                System.out.println("");
            } else {
                System.out.println(Bold_Weight + Bright_Red + "No active user. Please log in or register first." + Reset_Color);
            }
        } else {
            System.out.println(Bold_Weight + Bright_Red + "Favorite pizza was not saved." + Reset_Color);
        }

        // Continue to the next handler if available
        if (nextHandler != null) {
            nextHandler.handleOrder(builder);
        }
    }

    // Method to view user favorites
    public void viewUserFavorites() {
        if (activeUser == null) {
            System.out.println(Bold_Weight + Bright_Red +"No active user. Please log in or register first.");
            return;
        }

        List<Pizza> favorites = activeUser.getFavoritePizzas();
        if (favorites.isEmpty()) {
            System.out.println(Bold_Weight + Bright_Red +"You have no favorite pizzas.");
        } else {
            System.out.println("Your favorite pizzas:");
            for (int i = 0; i < favorites.size(); i++) {
                System.out.println((i + 1) + ". " + favorites.get(i));
            }
        }
    }

    // Method to reorder a favorite pizza
    public void reorderFavorite(int index) {
        if (activeUser == null) {
            System.out.println(Bold_Weight + Bright_Red +"No active user. Please log in or register first.");
            return;
        }

        List<Pizza> favorites = activeUser.getFavoritePizzas();
        if (index >= 1 && index <= favorites.size()) {
            Pizza pizzaToReorder = favorites.get(index - 1);
            System.out.println("Reordering: " + pizzaToReorder);
        } else {
            System.out.println(Bold_Weight + Bright_Red +"Invalid selection. Please choose a valid favorite pizza number.");
        }
    }
}




